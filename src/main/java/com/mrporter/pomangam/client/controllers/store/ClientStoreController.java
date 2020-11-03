package com.mrporter.pomangam.client.controllers.store;

import com.mrporter.pomangam.client.domains.store.SortType;
import com.mrporter.pomangam.client.services.store.ClientStoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dsites/{dIdx}/stores")
@AllArgsConstructor
public class ClientStoreController {

    ClientStoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @RequestParam(value = "oIdx", required = false) Long oIdx,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "oDate", required = false) LocalDate oDate,
            @RequestParam(value = "sIdxes", required = false) List<Long> sIdxes,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            @RequestParam(value = "sortType", required = false, defaultValue = "SORT_BY_RECOMMEND_DESC") SortType sortType
    ) {
        if(sIdxes != null) {
            return new ResponseEntity(storeService.findQuantityOrderableByIdxes(dIdx, oIdx, oDate, sIdxes), HttpStatus.OK);
        } else if(oIdx != null && oDate != null) {
            return new ResponseEntity(storeService.findOpeningStores(dIdx, oIdx, oDate, pageable, sortType), HttpStatus.OK);
        } else {
            return new ResponseEntity(storeService.findByIdxDeliverySite(dIdx, pageable), HttpStatus.OK);
        }
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "idx", required = true) Long idx,
            Authentication auth
    ) {
        String phoneNumber = null;
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            phoneNumber = auth.getName();
        }
        return new ResponseEntity(storeService.findByIdx(idx, phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @RequestParam(value = "oIdx", required = false) Long oIdx,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "oDate", required = false) LocalDate oDate
    ) {
        if(oIdx != null && oDate != null) {
            return new ResponseEntity(storeService.countOpeningStores(dIdx, oIdx, oDate), HttpStatus.OK);
        } else {
            return new ResponseEntity(storeService.count(), HttpStatus.OK);
        }
    }
}
