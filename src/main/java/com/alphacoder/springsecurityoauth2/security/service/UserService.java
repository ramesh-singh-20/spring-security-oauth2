package com.alphacoder.springsecurityoauth2.security.service;

import com.alphacoder.springsecurityoauth2.repository.UserRepository;
import com.alphacoder.springsecurityoauth2.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional= this.userRepository.findById(username);
        var userEntity= userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found in the database."));
        var user= new User(userEntity);
        return user;
    }
}
