package com.example.backen_kleding_bieb.service;


import com.example.backen_kleding_bieb.dto.UserDto;
import com.example.backen_kleding_bieb.models.Authority;
import com.example.backen_kleding_bieb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*annotatie !!!   DONE*/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /*inject userservice !!! DONE */


    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    public CustomUserDetailsService(UserService userService,  UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

//    @Autowired
//    private AuthorityService authorityService; */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getUser(username);



        String password = userDto.getPassword();

        Set<Authority> authorities = userDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);

    }}