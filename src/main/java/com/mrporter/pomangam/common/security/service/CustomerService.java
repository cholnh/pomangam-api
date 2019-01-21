package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.common.security.domain.CustomerTbl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    /**
     * To return a customer object fetched by ID
     *
     * @param id customer ID
     * @return Customer object
     */
    CustomerTbl findById(String id);

    /**
     * @return the list of all customers
     */
    List<CustomerTbl> findAllCustomers();

    /**
     * @return the list of all customers by pageable
     */
    Page<CustomerTbl> findAllCustomers(Pageable pageable);

    /**
     * @param customer Customer entity to be saved
     */
    CustomerTbl saveCustomer(CustomerTbl customer);

    /**
     * @param customer Customer entity to check existence
     * @return true if exist; otherwise, return false
     */
    Boolean isCustomerExist(CustomerTbl customer);

    /**
     * @param customer updated customer entity
     * @return updated customer entity
     */
    CustomerTbl updateCustomer(String id, CustomerTbl customer);

    /**
     * @param customer updated customer entity
     * @return patched customer entity
     */
    CustomerTbl patchCustomer(String id, CustomerTbl customer);

    /**
     * @param id customer ID to be deleted
     * @return true, if deleted; otherwise, return false
     */
    Boolean deleteCustomer(String id);
}
