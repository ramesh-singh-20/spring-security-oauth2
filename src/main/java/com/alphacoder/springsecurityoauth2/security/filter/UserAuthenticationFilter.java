package com.alphacoder.springsecurityoauth2.security.filter;

import com.alphacoder.springsecurityoauth2.security.authentication.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager manager;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String queryString= req.getQueryString();
        String[] queryParams= queryString.split("&");
        String[] usernameParam= queryParams[1].split("=");
        String username= usernameParam[1];
        String[] passwordParam= queryParams[2].split("=");
        String password= passwordParam[1];

        Authentication authentication= new UserAuthentication(username, password);
        authentication= this.manager.authenticate(authentication);

        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(req, res);
        }else{
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("token");
    }
}
