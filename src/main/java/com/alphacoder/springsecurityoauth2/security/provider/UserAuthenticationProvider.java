package com.alphacoder.springsecurityoauth2.security.provider;

import com.alphacoder.springsecurityoauth2.security.authentication.UserAuthentication;
import com.alphacoder.springsecurityoauth2.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username= authentication.getName();
        var password= String.valueOf(authentication.getCredentials());

        var user= this.userService.loadUserByUsername(username);
        if(this.passwordEncoder.matches(password, user.getPassword())){
            return new UserAuthentication(username, password, user.getAuthorities());
        }else{
            throw new BadCredentialsException("Invalid credentials provided.");
        }
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UserAuthentication.class.equals(authType);
    }
}
