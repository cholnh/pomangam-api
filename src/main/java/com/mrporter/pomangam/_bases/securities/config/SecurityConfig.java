package com.mrporter.pomangam._bases.securities.config;

import com.mrporter.pomangam._bases.securities.handler.CustomLoginFailureHandler;
import com.mrporter.pomangam._bases.securities.handler.CustomLoginSuccessHandler;
import com.mrporter.pomangam._bases.securities.service.PostUserDetailsChecker;
import com.mrporter.pomangam._bases.securities.service.PreUserDetailsChecker;
import com.mrporter.pomangam._bases.securities.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private PostUserDetailsChecker postUserDetailsChecker;
    private PreUserDetailsChecker preUserDetailsChecker;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
            .anonymous()
                .disable()

            .authorizeRequests()
                .antMatchers("/staff").hasRole("ADMIN") // Not Working
                .antMatchers("/staff/**").hasRole("ADMIN") // Not Working
                .antMatchers("/jpa").denyAll()
                .antMatchers("/jpa/**").denyAll()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and().userDetailsService(userDetailsService)

            .cors()
                .and()

            .csrf()
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring().antMatchers("/assets/**")
            .and().ignoring().antMatchers("/js/**")
            .and().ignoring().antMatchers("/health")
            .and().ignoring().antMatchers("/versions/**")
            .and().ignoring().antMatchers("/actuator/**")
            .and().ignoring().antMatchers("/tests/**")
            .and().ignoring().antMatchers("/payments/**")
            .and().ignoring().antMatchers("/policies/**")
            .and().ignoring().antMatchers("/orders/callback")
            .and().ignoring().antMatchers("/emails/**")
            .and().ignoring().antMatchers("/kakaos/callback")
            .and().ignoring().antMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPostAuthenticationChecks(postUserDetailsChecker);
        authenticationProvider.setPreAuthenticationChecks(preUserDetailsChecker);
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {};
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore(DataSource dataSource) {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public TokenStore JdbcTokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/"); //default로 이동할 url
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomLoginFailureHandler();
    }

    @Bean
    @Primary
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }
}
