package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.orderEntry.customer.domain.Customer;
import com.mrporter.pomangam.orderEntry.customer.repository.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Customer customer = customerJpaRepository.findById(id);
        if(customer == null) {
            throw new UsernameNotFoundException(id);
        }

        return new User(customer.getId(), customer.getPw(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public Customer save(Customer customer) {
        customer.setPw(passwordEncoder.encode(customer.getPw()));
        return customerJpaRepository.save(customer);
    }

    public void delete(String id) {
        customerJpaRepository.deleteById(id);
    }
}
