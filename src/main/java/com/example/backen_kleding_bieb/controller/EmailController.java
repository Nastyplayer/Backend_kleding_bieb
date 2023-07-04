package com.example.backen_kleding_bieb.controller;



import com.example.backen_kleding_bieb.models.Email;
import com.example.backen_kleding_bieb.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

@Autowired
    private EmailRepository emailRepository;


    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Email email) {
        String status = emailRepository.sendMail(email);
        return status;
    }
    @PostMapping("/sendWithAttachment")
    public String sendWithAttachment(@RequestBody Email email) {
        String status = emailRepository.sendWithAttachment(email);
        return status;

}}
