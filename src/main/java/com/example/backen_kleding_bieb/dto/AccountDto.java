package com.example.backen_kleding_bieb.dto;

import KledingBib.demo.models.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class AccountDto {


    @Id
    private Long id;

    private String userInfo;

    private String subscriptionInfo;


    @JsonIncludeProperties({"url", "fileName", "textType"})
    private Upload upload;


    @JsonIncludeProperties({"date", "type"})
    private Subscription subscription;

    @JsonIncludeProperties({"username", "password", "apikey", "email"})
    private User user;

    public AccountDto(String userInfo, String subscriptionInfo) {
    }


    //  private Object upload;
    //   private Object subscription;


    //VERGELIJKING
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto accountDto = (AccountDto) o;

        return Objects.equals(id, accountDto.id) && Objects.equals(userInfo, accountDto.userInfo) &&
                Objects.equals(subscriptionInfo, accountDto.subscriptionInfo) && Objects.equals(subscription, accountDto.subscription) &&
                Objects.equals(upload, accountDto.upload) && Objects.equals(user, accountDto.user);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userInfo, subscriptionInfo, subscription, upload, user);
    }


}
    // public Iterable<? extends User> getUsers() {
    //     return null;
    //  }
