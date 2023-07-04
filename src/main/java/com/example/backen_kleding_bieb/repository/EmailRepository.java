package com.example.backen_kleding_bieb.repository;


import com.example.backen_kleding_bieb.models.Email;

public interface EmailRepository {
    String sendMail(Email email);

    String sendWithAttachment(Email email);
}