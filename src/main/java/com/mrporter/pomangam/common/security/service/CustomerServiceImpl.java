package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.common.security.domain.Customer;
import com.mrporter.pomangam.common.security.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Boolean isCustomerExist(Customer customer) {
        if(customer.getId() != null) {
            final Customer existingCustomer = customerRepository.findById(customer.getId());
            return existingCustomer == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
        final Customer fetchedCustomer = customerRepository.findById(customer.getId());
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
        fetchedCustomer.setStatus(customer.getStatus());
        fetchedCustomer.setRegister_date(customer.getRegister_date());
        fetchedCustomer.setModify_date(customer.getModify_date());
        fetchedCustomer.setPoint(customer.getPoint());
        customerRepository.save(fetchedCustomer);
        return fetchedCustomer;
    }

    @Override
    public Customer patchCustomer(String id, Customer customer) {
        final Customer fetchedCustomer = customerRepository.findById(customer.getId());
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
        if (customer.getStatus() != null) {
            fetchedCustomer.setStatus(customer.getStatus());
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
        customerRepository.save(fetchedCustomer);
        return fetchedCustomer;
    }

    @Override
    public Boolean deleteCustomer(String id) {
        final Customer fetchedCustomer = customerRepository.findById(id);
        if (fetchedCustomer == null) {
            return false;
        } else {
            customerRepository.delete(fetchedCustomer);
            return true;
        }
    }
}
