package com.mrporter.pomangam.orderEntry.cart.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.orderEntry.cart.domain.*;
import com.mrporter.pomangam.orderEntry.cart.repository.CartJpaRepository;
import com.mrporter.pomangam.orderEntry.cart.repository.CartRepositoryImpl;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemDto;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemInputDto;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemViewDto;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.service.OrderTimeServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.repository.ProductRepositoryImpl;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    CartJpaRepository cartJpaRepository;
    CartRepositoryImpl cartRepository;
    CartItemJpaRepository cartItemJpaRepository;
    OrderRepositoryImpl orderRepository;
    OrderTimeServiceImpl orderTimeService;
    StoreJpaRepository storeJpaRepository;
    ProductRepositoryImpl productRepository;

    @Override
    public Cart update(Integer cart_idx, CartDto dto) {
        final Cart fetched = cartJpaRepository.getOne(cart_idx);
        if (fetched == null) {
            return null;
        }

        fetched.setCustomerIdx(dto.getCustomerIdx());
        fetched.setGuestIdx(dto.getGuestIdx());
        fetched.setDetailSiteIdx(dto.getDetailSiteIdx());
        fetched.setArrivalDate(Timestamp.valueOf(dto.getArrivalDate()));

        return cartJpaRepository.save(fetched);
    }

    @Override
    public Cart patch(Integer cart_idx, CartDto dto) {
        final Cart fetched = cartJpaRepository.getOne(cart_idx);
        if (fetched == null) {
            return null;
        }

        if (dto.getCustomerIdx() != null) {
            fetched.setCustomerIdx(dto.getCustomerIdx());
        }
        if (dto.getGuestIdx() != null) {
            fetched.setGuestIdx(dto.getGuestIdx());
        }
        if (dto.getDetailSiteIdx() != null) {
            fetched.setDetailSiteIdx(dto.getDetailSiteIdx());
        }
        if (dto.getArrivalDate() != null) {
            fetched.setArrivalDate(Timestamp.valueOf(dto.getArrivalDate()));
        }

        return cartJpaRepository.save(fetched);
    }

    @Override
    public Boolean delete(Integer id) {
        final Cart fetched = cartJpaRepository.getOne(id);
        if (fetched == null) {
            return false;
        } else {
            cartJpaRepository.delete(fetched);
            return true;
        }
    }

    private List<CartItemViewDto> recursiveSort(List<CartItemViewDto> items, Integer parentIdx) {
        List<CartItemViewDto> result = new ArrayList<>();
        if(items == null) {
            return null;
        }
        for(CartItemViewDto item : items) {
            if(item.getParentItemIdx() == parentIdx) {
                List<CartItemViewDto> subItems = recursiveSort(items, item.getIdx());
                item.setSubItems(subItems);
                result.add(item);
            }
        }
        return result;
    }

    private List<CartItemViewDto> iterativeSort(List<CartItemViewDto> items) {
        List<CartItemViewDto> cartItemDtoList = new ArrayList<>();
        if(items == null) {
            return null;
        }
        for(CartItemViewDto mainItem : items) {
            Integer parentIdx = mainItem.getParentItemIdx();
            if(parentIdx == null) {
                List<CartItemViewDto> subDtoList = new ArrayList<>();
                for(CartItemViewDto subItem : items) {
                    Integer subItemParentIdx = subItem.getParentItemIdx();
                    if(subItemParentIdx != null && subItemParentIdx.intValue() == mainItem.getIdx()) {
                        subDtoList.add(subItem);
                    }
                }
                mainItem.setSubItems(subDtoList);
                cartItemDtoList.add(mainItem);
            }
        }
        return cartItemDtoList;
    }

    @Override
    public CartViewDto getCartDtoByCustomerIdx(Integer customer_idx) {
        CartDto cart = cartRepository.getByCustomerIdx(customer_idx);
        return getCartDto(cart);
    }

    @Override
    public CartViewDto getCartDtoByGuestIdx(Integer guestIdx) {
        CartDto cart = cartRepository.getByGuestIdx(guestIdx);
        return getCartDto(cart);
    }

    @Override
    public int getTotalAmount(Integer cartIdx) {
        int totalAmount = 0;
        List<CartItem> cartItems = cartItemJpaRepository.findByCartIdx(cartIdx);
        for(CartItem it : cartItems) {
            ProductWithCostDto product = productRepository.findByProductIdx(it.getProductIdx());
            if(product != null) {
                int amount = product.getFinal_cost().intValue();
                int quantity = it.getQuantity().intValue();
                totalAmount += amount * quantity;
            }
        }
        return totalAmount;
    }

    private CartViewDto getCartDto(CartDto cart) {
        CartViewDto dto = new CartViewDto();
        if(cart != null) {
            List<CartItem> cartItems = cartItemJpaRepository.findByCartIdx(cart.getIdx());
            List<CartItemViewDto> cartItemViewDtoList = new ArrayList<>();
            for(CartItem it : cartItems) {
                CartItemViewDto cvDto = new CartItemViewDto(it);
                cvDto.setProduct(productRepository.findByProductIdx(it.getProductIdx()));
                cartItemViewDtoList.add(cvDto);
            }
            dto.setCartItems(iterativeSort(cartItemViewDtoList));

            List<CartTimeMapDto> cartTimeMapDtoList = getCartWithArrivalTimeByCartIdx(cart.getIdx());
            dto.setCartTimeMapDtoList(cartTimeMapDtoList);

            LocalDateTime min = getMaximumTime(cartTimeMapDtoList);
            if(min != null && min.isAfter(cart.getArrivalDate())) {
                cart.setArrivalDate(min);
            }

            if(CustomTime.isPast(cart.getArrivalDate())) {
                cart.setArrivalDate(LocalDateTime.now());
            }
            dto.setCart(cart);
        }
        return dto;
    }

    private LocalDateTime getMaximumTime(List<CartTimeMapDto> timeMapList) {
        if(timeMapList == null || timeMapList.isEmpty()) {
            return null;
        }
        LocalDateTime max = timeMapList.get(0).getArrivalTime();
        for(CartTimeMapDto dto : timeMapList) {
            LocalDateTime ldt = dto.getArrivalTime();
            if(ldt != null && ldt.isAfter(max)) {
                max = ldt;
            }
        }
        return max;
    }


    @Override
    public int countCartByCustomerIdx(Integer customerIdx) {
        return cartRepository.countCartByCustomerIdx(customerIdx);
    }

    @Override
    public List<CartTimeMapDto> getCartWithArrivalTimeByCustomerIdx(Integer customer_idx) {
        CartDto dto = cartRepository.getByCustomerIdx(customer_idx);
        if(dto == null) {
            return null;
        } else {
            return getCartWithArrivalTime(dto.getIdx(), ZoneId.of("Asia/Seoul"));
        }
    }

    @Override
    public List<CartTimeMapDto> getCartWithArrivalTimeByCartIdx(Integer cart_Idx) {
        return getCartWithArrivalTime(cart_Idx, ZoneId.of("Asia/Seoul"));
    }

    @Override
    public List<CartTimeMapDto> getCartWithArrivalTime(Integer cart_Idx, ZoneId zoneId) {
        List<CartTimeMapDto> result = new ArrayList<>();
        //Map<Integer, LocalDateTime> map = new HashMap<>();

        CartDto cart = cartRepository.getByIdx(cart_Idx);
        List<CartInStoreQuantityDto> dtoList = cartRepository.findCartInStoreQuantityByIdx(cart_Idx);

        if(!dtoList.isEmpty()) {
            for(CartInStoreQuantityDto dto : dtoList) {
                // TZ 설정
                LocalDateTime ldt = cart.getArrivalDate();
                ZonedDateTime arrTimeWithZone = ZonedDateTime.of(
                        ldt,
                        zoneId);
                if(CustomTime.isPast(arrTimeWithZone)) {
                    ldt = LocalDateTime.now();
                    arrTimeWithZone = ZonedDateTime.now(zoneId);
                }

                Integer store_idx = dto.getStore_idx();
                int quantity = dto.getQuantity(); // quantity 는 product.type - Main 메뉴에 해당하는 물품 개수만 측정.

                List<OrderTimeSalesVolumeDto> svList = orderRepository.getSalesVolumeByArrivalDateAndStoreIdx(
                        ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        store_idx);
                Store storeInfo = storeJpaRepository.getOne(store_idx);
                int rc; // remaining capacity 현재까지 남은 주문 가능 수량
                int pp = storeInfo.getParallel_production();  // 병렬 생산량
                int mt = storeInfo.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
                int mp = storeInfo.getMaximum_production();   // 최대 가능 생산량

                int roopCnt = 0;
                boolean isTomorrow = true;
                do {
                    if(++roopCnt > 20) {
                        // 무한 루프 방지
                        CartTimeMapDto cartTimeMapDto = new CartTimeMapDto();
                        cartTimeMapDto.setStore_idx(store_idx);
                        cartTimeMapDto.setArrivalTime(null);
                        cartTimeMapDto.setRemaining_capacity(0);
                        result.add(cartTimeMapDto);
                        break;
                    }

                    if(svList == null || svList.isEmpty()) {
                        break;
                    }
                    for(OrderTimeSalesVolumeDto svDto : svList) {

                        LocalDateTime arrival_time = LocalDateTime.of(ldt.toLocalDate(), svDto.getArrival_time().toLocalTime());

                        int sv = svDto.getSv()==null?0:svDto.getSv().intValue();
                        if(sv > mp) {
                            // 판매량이 최대 생산량을 초과한 경우
                            continue;
                        }

                        if(CustomTime.isToday(arrTimeWithZone)){
                            if (svDto.getState_pause().intValue()== 1) {
                                // (해당 시간대) 주문 정지
                                continue;
                            }
                        }

                        // (해당 시간대) 주문 마감 시간
                        ZonedDateTime endTimeWithZone = ZonedDateTime.of(
                                LocalDateTime.of(ldt.toLocalDate(), svDto.getOrder_deadline().toLocalTime()),
                                zoneId);

                        // 남은 시간 계산
                        long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

                        // 주문 가능 수량 계산
                        long temp = td*pp/mt;
                        rc = temp > mp ? mp : (int)temp;
                        rc -= sv;

                        if(rc < quantity) {
                            // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                            if(rc > 0) {
                                // quantity에 문제가 생긴 경우
                                isTomorrow = false;

                                CartTimeMapDto cartTimeMapDto = new CartTimeMapDto();
                                cartTimeMapDto.setStore_idx(store_idx);
                                cartTimeMapDto.setArrivalTime(arrival_time);
                                cartTimeMapDto.setRemaining_capacity(rc-quantity);
                                result.add(cartTimeMapDto);
                                break;
                            }
                        } else {
                            isTomorrow = false; // 금일 주문이 가능한 경우 이므로 다음날로 넘어가진 않음 (false)

                            CartTimeMapDto cartTimeMapDto = new CartTimeMapDto();
                            cartTimeMapDto.setStore_idx(store_idx);
                            cartTimeMapDto.setArrivalTime(arrival_time);
                            cartTimeMapDto.setRemaining_capacity(rc-quantity);
                            result.add(cartTimeMapDto);

                            //map.put(store_idx, arrival_time);
                            break;
                        }
                    }
                    if(isTomorrow) {
                        ldt = ldt.plus(1, ChronoUnit.DAYS);
                    }
                } while(isTomorrow);
            }
        }

        return result;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartJpaRepository.save(cart);
    }

    @Override
    public Cart saveCartItemInput(CartItemInputDto cartItems) {

        Integer customerIdx = cartItems.getCustomerIdx();
        Integer guestIdx = cartItems.getGuestIdx();

        CartDto dto;

        if(customerIdx == null) {
            dto = cartRepository.getByGuestIdx(guestIdx);
            if(dto == null) {
                dto = new CartDto();
            }
            dto.setGuestIdx(guestIdx);
        } else {
            dto = cartRepository.getByCustomerIdx(customerIdx);
            if(dto == null) {
                dto = new CartDto();
            }
            dto.setCustomerIdx(customerIdx);
        }
        LocalDateTime arrivalTime = cartItems.getArrivalDate();
        if(arrivalTime == null) {
            // 제휴음식점 부분에서 장바구니 추가한 경우
            arrivalTime = orderTimeService.getMinimumArrivalTime( // 지금 시간에서 가장 가까운 주문시간대 반환
                    cartItems.getMainItem().getStoreIdx(),
                    cartItems.getMainItem().getQuantity());
        }

        if(arrivalTime.isAfter(dto.getArrivalDate())) {
            dto.setArrivalDate(arrivalTime);
        }
        dto.setDetailSiteIdx(cartItems.getDetailForDeliverySiteIdx());
        Cart cart = cartJpaRepository.save(dto.toEntity());

        CartItemDto mainItem = cartItems.getMainItem();
        mainItem.setCartIdx(cart.getIdx());
        Integer mainIdx = cartItemJpaRepository.save(mainItem.toEntity()).getIdx();

        List<CartItemDto> subItems = mainItem.getSubItems();
        if(subItems != null) {
            for(CartItemDto c : subItems) {
                c.setCartIdx(cart.getIdx());
                c.setParentItemIdx(mainIdx);
                cartItemJpaRepository.save(c.toEntity());
            }
        }
        return cart;
    }

    @Override
    public int countCartByGuestIdx(Integer guestIdx) {
        return cartRepository.countCartByGuestIdx(guestIdx);
    }

    @Override
    public Boolean deleteByCustomerIdx(Integer customerIdx) {
        final Cart fetched = cartJpaRepository.getByCustomerIdx(customerIdx);
        if (fetched == null) {
            return false;
        } else {
            cartJpaRepository.delete(fetched);
            return true;
        }
    }

    @Override
    public Boolean deleteByGuestIdx(Integer guestIdx) {
        final Cart fetched = cartJpaRepository.getByGuestIdx(guestIdx);
        if (fetched == null) {
            return false;
        } else {
            cartJpaRepository.delete(fetched);
            return true;
        }
    }
}
