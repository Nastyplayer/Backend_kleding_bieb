package com.example.backen_kleding_bieb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column
    private Long id;
    private String typeSubscription;

    private LocalDate expirationDate;



    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSubscriptionStatus(List<SubscriptionStatus> subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public List<SubscriptionStatus> getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public enum SubscriptionStatus {
        ACTIVE,
        EXPIRE,
        CANCELED
    }



    @OneToOne (targetEntity = Account.class,  cascade = {CascadeType.ALL})

    @JsonIgnore
    private Account account;


    @ElementCollection(targetClass = SubscriptionStatus.class)

    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN)
    List<SubscriptionStatus> subscriptionStatus;


}

