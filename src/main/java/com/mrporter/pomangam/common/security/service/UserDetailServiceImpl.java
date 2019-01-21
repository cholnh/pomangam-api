package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.common.security.repository.CustomerRepository;
import com.mrporter.pomangam.common.security.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Customer customer = customerRepository.findById(id);
        if(customer == null) {
            throw new UsernameNotFoundException(id);
        }

        return new User(customer.getId(), customer.getPw(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
