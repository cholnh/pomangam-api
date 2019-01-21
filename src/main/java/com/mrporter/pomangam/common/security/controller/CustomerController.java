package com.mrporter.pomangam.common.security.controller;

import com.mrporter.pomangam.common.security.domain.CustomerTbl;
import com.mrporter.pomangam.common.security.domain.CustomerTblDto;
import com.mrporter.pomangam.common.security.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    CustomerServiceImpl customerService;

    @PostAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable) {

        //localhost:8080/customer?page=0&size=10&sort=idx,desc
        Page<CustomerTbl> pages =  customerService.findAllCustomers(pageable);
        return new ResponseEntity<Page<CustomerTbl>>(pages, HttpStatus.OK);
    }

    @PostAuthorize("isAuthenticated() and (( returnObject.id == principal.username ) or hasRole('ROLE_ADMIN'))")
    @GetMapping("/{id}")
    public CustomerTbl get(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PreAuthorize("isAnonymous() or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody CustomerTblDto dto) {
        return new ResponseEntity<CustomerTbl>(
                customerService.saveCustomer(dto.toEntity()),
                HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (( id == principal.username ) or hasRole('ROLE_ADMIN'))")
    @DeleteMapping
    public ResponseEntity delete(String id) {
        return new ResponseEntity<Boolean>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (( id == principal.username ) or hasRole('ROLE_ADMIN'))")
    @PutMapping("/{id}")
    public ResponseEntity put(
            @PathVariable String id,
            @RequestBody CustomerTblDto dto) {
        return new ResponseEntity<CustomerTbl>(
                customerService.updateCustomer(id, dto.toEntity()),
                HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (( id == principal.username ) or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/{id}")
    public ResponseEntity patch(
            @PathVariable String id,
            @RequestBody CustomerTblDto dto) {
        return new ResponseEntity<CustomerTbl>(
                customerService.patchCustomer(id, dto.toEntity()),
                HttpStatus.OK);
    }
}
