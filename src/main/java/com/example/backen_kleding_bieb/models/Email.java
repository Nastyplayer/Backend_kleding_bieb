package com.example.backen_kleding_bieb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String receiver;
    private String message;
    private String subject;
    private String attachment;

    public Email(String receiver, String message, String subject) {
        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
    }

}