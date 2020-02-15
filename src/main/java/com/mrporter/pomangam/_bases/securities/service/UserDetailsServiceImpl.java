package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import com.mrporter.pomangam.client.repositories.employee.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserServiceImpl userService
    ) {
        this.userService = userService;
    }

    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userService.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UsernameNotFoundException(phoneNumber);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
