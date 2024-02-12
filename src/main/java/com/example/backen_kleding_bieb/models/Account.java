package com.example.backen_kleding_bieb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
//@AllArgsConstructor

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


//    public Subscription getSubscription() {
//        return subscription;
//    }
//
//    public void setSubscription(Subscription subscription) {
//        this.subscription = subscription;
//    }
//
//    public Upload getUpload() {
//        return uploads;
//    }
//
//
//    public void setUpload(Upload upload) {
//        this.uploads = uploads;
//    }
//



    @OneToOne  ( mappedBy = "account")
    private Subscription subscription;


//    @OneToOne
//    @JsonIgnore
//    private Upload uploads;

    @OneToOne( mappedBy = "account")
    @JsonIgnore
    private User user;

}


