package com.mrporter.pomangam.test.dataMake;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrporter.pomangam.common.util.queryRunner.QueryRunnerImpl;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.productEntry.cost.domain.Cost;
import com.mrporter.pomangam.productEntry.cost.repository.CostJpaRepository;
import com.mrporter.pomangam.productEntry.imageForProduct.domain.ImageForProduct;
import com.mrporter.pomangam.productEntry.imageForProduct.repository.ImageForProductJpaRepository;
import com.mrporter.pomangam.productEntry.product.domain.Product;
import com.mrporter.pomangam.productEntry.product.repository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/tests/data")
@AllArgsConstructor
public class DataMakeViewController {

    QueryRunnerImpl queryRunner;
    ProductJpaRepository productJpaRepository;
    CostJpaRepository costJpaRepository;
    ImageForProductJpaRepository imageForProductJpaRepository;

    @GetMapping("/login")
    public String login() {
        return "/dataMake/login";
    }

    @GetMapping("/token/{token}")
    public RedirectView saveToken(@PathVariable("token") String token,
                            HttpSession session) {
        session.setAttribute("token", token);
        return new RedirectView("../");
    }

    @GetMapping
    public String home(HttpSession session,
                       Model model) {

        String path = "c:/assets/9.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<FromProduct> jsons = gson.fromJson(bufferedReader, new TypeToken<List<FromProduct>>(){}.getType());
        //FromProduct json = jsons.get(0);

        for(FromProduct json : jsons) {
            Timestamp ts = CustomTime.curTimestampSql();
            Product prod = Product.builder()
                    .storeIdx(9)
                    .name(json.getName())
                    .description(json.getDescription())
                    .sub_description(json.getSub_description())
                    .category_name(json.getCategory())
                    .state_active(Byte.valueOf("1"))
                    .type(json.getType().intValue() == 1 ? Byte.valueOf("0") : Byte.valueOf("1"))
                    .cnt_like(0)
                    .register_date(ts)
                    .sequence(json.getSequence())
                    .build();
            Product mk = productJpaRepository.save(prod);
            Cost cost = Cost.builder()
                    .product_idx(mk.getIdx())
                    .unit_cost(json.getPrice()-json.getC_commission_prc())
                    .s_commission_pct(Short.parseShort(0+""))
                    .s_commission_prc(json.getS_commission_prc())
                    .c_commission_pct(Short.parseShort(0+""))
                    .c_commission_prc(json.getC_commission_prc())
                    .build();
            String imgpath = json.getImgpath();
            imgpath = "/assets/image/product/"+imgpath.split("/")[3];
            ImageForProduct image = ImageForProduct.builder()
                    .product_idx(mk.getIdx())
                    .imgpath(imgpath)
                    .type(Byte.valueOf("0"))
                    .build();
            costJpaRepository.save(cost);
            imageForProductJpaRepository.save(image);
        }

        return "/dataMake/home";
    }

    @Data
    class FromProduct {
        Integer idx;
        Integer idx_restaurant;
        String category;
        String name;
        Integer price;
        String imgpath;
        String description;
        Integer unit_amount;
        Integer unit_time;
        Integer cnt_sell;
        Integer isActive;
        String additional;
        Integer type;
        Integer sequence;
        String sub_description;
        Integer c_commission_prc;
        Integer s_commission_prc;
        Integer discount_prc;
    }

    @GetMapping("/store")
    public String viewMakeStore() {
        return "/dataMake/store";
    }

    @GetMapping("/orderTime")
    public String viewMakeOrderTime() {
        return "/dataMake/orderTime";
    }

    @GetMapping("/scheduleForStore")
    public String viewMakeScheduleForStore() {
        return "/dataMake/scheduleForStore";
    }

    @GetMapping("/imageForStore")
    public String viewMakeImageForStore() {
        return "/dataMake/imageForStore";
    }

}
