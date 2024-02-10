package com.example.backen_kleding_bieb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor

@Getter
@Setter


@Entity
@Table(name = "accounts")
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userInfo;

    private String subscriptionInfo;

    private String email;
    private String comment;




    public Account(Long id, String userInfo, String subscriptionInfo, String email, String comment, Subscription subscription, Upload upload) {
        this.id = id;
        this.userInfo = userInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.email = email;
        this.comment = comment;
        this.subscription = subscription;
//        this.upload = upload;
    }
    @OneToOne  ( mappedBy = "account")
    private Subscription subscription;


//    @OneToOne
//    @JsonIgnore
//    private Upload upload;

//    uploads

    @OneToOne( mappedBy = "account")
    @JsonIgnore
    private User user;

}


