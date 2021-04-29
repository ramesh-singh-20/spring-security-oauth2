package com.alphacoder.springsecurityoauth2.config;

import com.alphacoder.springsecurityoauth2.repository.UserRepository;
import com.alphacoder.springsecurityoauth2.security.filter.UserAuthenticationFilter;
import com.alphacoder.springsecurityoauth2.security.provider.UserAuthenticationProvider;
import com.alphacoder.springsecurityoauth2.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserManagementConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService(this.repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserAuthenticationFilter userAuthenticationFilter() throws Exception {
        return new UserAuthenticationFilter(this.authenticationManager());
    }

    @Bean
    public UserAuthenticationProvider userAuthenticationProvider(){
        return new UserAuthenticationProvider(userDetailsService(), this.passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(userAuthenticationFilter(), BasicAuthenticationFilter.class);
    }
}
