package com.example.backen_kleding_bieb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


@Entity
@Table(name = "users")
public class User {


    @Id

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column
    private String email;
    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;

    }


    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Item> items;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
    @JsonIgnore
    List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Account account;


    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)


    private Set<Authority> authorities = new HashSet<>();


    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public void addAuthority(Object username, String role_user) {
    }

    public void setUser(User user) {
    }
}