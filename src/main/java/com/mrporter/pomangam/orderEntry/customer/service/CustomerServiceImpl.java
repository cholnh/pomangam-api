package com.mrporter.pomangam.orderEntry.customer.service;

import com.mrporter.pomangam.orderEntry.customer.domain.Customer;
import com.mrporter.pomangam.orderEntry.customer.repository.CustomerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer findById(String id) {
        return customerJpaRepository.findById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerJpaRepository.findAll();
    }

    @Override
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerJpaRepository.findAll(pageable);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public Boolean isCustomerExist(Customer customer) {
        if(customer.getId() != null) {
            final Customer existingCustomer = customerJpaRepository.findById(customer.getId());
            return existingCustomer == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
        final Customer fetchedCustomer = customerJpaRepository.findById(customer.getId());
        if (fetchedCustomer == null) {
            return null;
        }
        fetchedCustomer.setDelivery_site_idx(customer.getDelivery_site_idx());
        fetchedCustomer.setId(customer.getId());
        fetchedCustomer.setPw(customer.getPw());
        fetchedCustomer.setName(customer.getName());
        fetchedCustomer.setNickname(customer.getNickname());
        fetchedCustomer.setGender(customer.getGender());
        fetchedCustomer.setYear_of_birth(customer.getYear_of_birth());
        fetchedCustomer.setMonth_of_birth(customer.getMonth_of_birth());
        fetchedCustomer.setDays_of_birth(customer.getDays_of_birth());
        fetchedCustomer.setPhone_number(customer.getPhone_number());
        fetchedCustomer.setState_active(customer.getState_active());
        fetchedCustomer.setRegister_date(customer.getRegister_date());
        fetchedCustomer.setModify_date(customer.getModify_date());
        fetchedCustomer.setPoint(customer.getPoint());
        customerJpaRepository.save(fetchedCustomer);
        return fetchedCustomer;
    }

    @Override
    public Customer patchCustomer(String id, Customer customer) {
        final Customer fetchedCustomer = customerJpaRepository.findById(customer.getId());
        if (fetchedCustomer == null) {
            return null;
        }

        if (customer.getDelivery_site_idx() != null) {
            fetchedCustomer.setDelivery_site_idx(customer.getDelivery_site_idx());
        }
        if (customer.getId() != null) {
            fetchedCustomer.setId(customer.getId());
        }
        if (customer.getPw() != null) {
            fetchedCustomer.setPw(customer.getPw());
        }
        if (customer.getName() != null) {
            fetchedCustomer.setName(customer.getName());
        }
        if (customer.getNickname() != null) {
            fetchedCustomer.setNickname(customer.getNickname());
        }
        if (customer.getGender() != null) {
            fetchedCustomer.setGender(customer.getGender());
        }
        if (customer.getYear_of_birth() != null) {
            fetchedCustomer.setYear_of_birth(customer.getYear_of_birth());
        }
        if (customer.getMonth_of_birth() != null) {
            fetchedCustomer.setMonth_of_birth(customer.getMonth_of_birth());
        }
        if (customer.getDays_of_birth() != null) {
            fetchedCustomer.setDays_of_birth(customer.getDays_of_birth());
        }
        if (customer.getPhone_number() != null) {
            fetchedCustomer.setPhone_number(customer.getPhone_number());
        }
        if (customer.getState_active() != null) {
            fetchedCustomer.setState_active(customer.getState_active());
        }
        if (customer.getRegister_date() != null) {
            fetchedCustomer.setRegister_date(customer.getRegister_date());
        }
        if (customer.getModify_date() != null) {
            fetchedCustomer.setModify_date(customer.getModify_date());
        }
        if (customer.getPoint() != null) {
            fetchedCustomer.setPoint(customer.getPoint());
        }
        customerJpaRepository.save(fetchedCustomer);
        return fetchedCustomer;
    }

    @Override
    public Boolean deleteCustomer(String id) {
        final Customer fetchedCustomer = customerJpaRepository.findById(id);
        if (fetchedCustomer == null) {
            return false;
        } else {
            customerJpaRepository.delete(fetchedCustomer);
            return true;
        }
    }
}
