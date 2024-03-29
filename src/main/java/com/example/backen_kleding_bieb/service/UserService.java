package com.example.backen_kleding_bieb.service;

import com.example.backen_kleding_bieb.dto.UserDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Authority;
import com.example.backen_kleding_bieb.models.User;
import com.example.backen_kleding_bieb.repository.OrderRepository;
import com.example.backen_kleding_bieb.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository OrderRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        OrderRepository = orderRepository;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }
    public UserDto getUser(String username) {
        return userRepository.findById(username)
                .map(this::fromUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


    public String createUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = userRepository.save(toUser(userDto));
        newUser = userRepository.save(newUser);
        return newUser.getUsername();


    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();

        user.setPassword(newUser.getPassword());

        userRepository.save(user);
    }

    public void patchUser(String username, UserDto changeUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        if (changeUser.getPassword() != "") {
            user.setPassword(passwordEncoder.encode(changeUser.getPassword()));
        }
        if (changeUser.getEmail() != "") {
            user.setEmail(changeUser.getEmail());
        }
        userRepository.save(user);
    }



    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
    /////////////
    public UserDto fromUser(User user){

        var userDto = new UserDto();
        userDto.order = user.getOrders();
        userDto.item = user.getItems();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.authorities = user.getAuthorities();
        if(userDto.getAccount()== null) {
            userDto.setAccount(user.getAccount());
        }

        return userDto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();
        user.setOrders(userDto.getOrder());
        user.setItems(userDto.getItem());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        if(userDto.getAccount()!=null) {
            user.setAccount(userDto.getAccount());
        }

        user.setEmail(userDto.getEmail());
        return user;
    }

}