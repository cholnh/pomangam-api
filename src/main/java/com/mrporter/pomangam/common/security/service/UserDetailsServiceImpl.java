package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.humanResource.employee.repository.EmployeeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserServiceImpl userService;
    EmployeeJpaRepository employeeJpaRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException(id);
        }
        String[] authorities = user.getAuthorities() != null
                ? user.getAuthorities().split(",")
                : null;
        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(authorities));
    }
}
