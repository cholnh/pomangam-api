package com.mrporter.pomangam.orderEntry.customer.service;

import com.mrporter.pomangam.orderEntry.customer.domain.Customer;
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
    Customer findById(String id);

    /**
     * @return the list of all customers
     */
    List<Customer> findAllCustomers();

    /**
     * @return the list of all customers by pageable
     */
    Page<Customer> findAllCustomers(Pageable pageable);

    /**
     * @param customer Customer entity to be saved
     */
    Customer saveCustomer(Customer customer);

    /**
     * @param customer Customer entity to check existence
     * @return true if exist; otherwise, return false
     */
    Boolean isCustomerExist(Customer customer);

    /**
     * @param customer updated customer entity
     * @return updated customer entity
     */
    Customer updateCustomer(String id, Customer customer);

    /**
     * @param customer updated customer entity
     * @return patched customer entity
     */
    Customer patchCustomer(String id, Customer customer);

    /**
     * @param id customer ID to be deleted
     * @return true, if deleted; otherwise, return false
     */
    Boolean deleteCustomer(String id);
}
