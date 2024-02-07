package com.example.backen_kleding_bieb.dto;


import com.example.backen_kleding_bieb.models.Subscription;
import com.example.backen_kleding_bieb.models.Upload;
import com.example.backen_kleding_bieb.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class AccountDto {

    @Id
    private Long id;

    private String userInfo;


    private String subscriptionInfo;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    private String comment;


//    @JsonIncludeProperties({"url", "fileName", "textType"})
//    private Upload upload;


    @JsonIncludeProperties({"date", "type"})
    private Subscription subscription;

    @JsonIncludeProperties({"username", "password",  "email"})
    private User user;

}




