package com.example.backen_kleding_bieb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userInfo;

    private String subscriptionInfo;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Subscription subscription;

    @OneToOne
    Upload upload;

    @OneToOne
    User user;


    //maybe ?


    //VERGELIJKING
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userInfo, account.userInfo) &&
                Objects.equals(subscriptionInfo, account.subscriptionInfo ) && Objects.equals(subscription, account.subscription)
                && Objects.equals(upload, account.upload ) && Objects.equals(user, account.user) ;

    }
    @Override
    public int hashCode() {
        return Objects.hash(id, userInfo, subscriptionInfo,  subscription, upload, user);
    }





}