package com.example.backen_kleding_bieb.dto;

import com.example.backen_kleding_bieb.models.Account;
import com.example.backen_kleding_bieb.models.Authority;
import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.models.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {

    public String username;
    public String password;

    public String email;


//    public UserDto(String username, String password, String email) {
//
//        this.username = username;
//        this.password = password;
//
//        this.email = email;
//
//    }

    @JsonSerialize
    public Set<Authority> authorities;

    @JsonIgnore
    public
    List<Item> item;

    @JsonIgnore
    public List<Order> order;


    @JsonIgnore
    Account account;
}


