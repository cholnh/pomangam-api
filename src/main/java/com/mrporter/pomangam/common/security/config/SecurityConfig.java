package com.mrporter.pomangam.common.security.config;

import com.mrporter.pomangam.common.security.repository.CustomerRepository;
import com.mrporter.pomangam.common.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
/* 1 */      .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
	            .antMatchers("/**") //.hasRole("USER")
                .permitAll();
//            .and()
//
///* 2 */	    .formLogin()
//                .loginPage("/login")
//                .usernameParameter("id")
//                .passwordParameter("pw")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//            .and()
//
///* 3 */	    .logout()
//                .logoutUrl("/customLogout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .permitAll()
//            .and()
//
///* 4 */	    .csrf()
//                .disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}
