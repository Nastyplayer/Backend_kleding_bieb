package com.example.backen_kleding_bieb.dto;

import com.example.backen_kleding_bieb.models.Account;
import com.example.backen_kleding_bieb.models.Subscription;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class SubscriptionDto {

    @Id
    private Long id;

    private String typeSubscription;

    private LocalDate expirationDate;

    List<Subscription.SubscriptionStatus> subscriptionStatus;


    @JsonIncludeProperties({"id", "userInfo", "subscriptionInfo"})
    private Account account;


}


