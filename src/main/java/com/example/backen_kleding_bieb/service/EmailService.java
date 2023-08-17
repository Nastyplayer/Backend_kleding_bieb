package com.example.backen_kleding_bieb.service;


import com.example.backen_kleding_bieb.models.Email;
import com.example.backen_kleding_bieb.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;


@Service

public class EmailService implements EmailRepository {
    private JavaMailSender javaMailSender;
    private String sender;


    public String sendMail(Email email) {
        try {
            MailMessage mailMessage = new MailMessage() {
                @Override
                public void setFrom(String from) throws MailParseException {

                }

                @Override
                public void setReplyTo(String replyTo) throws MailParseException {

                }

                @Override
                public void setTo(String to) throws MailParseException {

                }

                @Override
                public void setTo(String... to) throws MailParseException {

                }

                @Override
                public void setCc(String cc) throws MailParseException {

                }

                @Override
                public void setCc(String... cc) throws MailParseException {

                }

                @Override
                public void setBcc(String bcc) throws MailParseException {

                }

                @Override
                public void setBcc(String... bcc) throws MailParseException {

                }

                @Override
                public void setSentDate(Date sentDate) throws MailParseException {

                }

                @Override
                public void setSubject(String subject) throws MailParseException {

                }

                @Override
                public void setText(String text) throws MailParseException {

                }
            };

            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getReceiver());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send((SimpleMailMessage) mailMessage);
            return "Mail successfully sended...";
        }

        catch (Exception e) {
            return "Sending Mail in Error";
        }
    }

    public String
    sendWithAttachment(Email email) {
        MimeMessage myMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper myMessageHelper;

        try {
            myMessageHelper = new MimeMessageHelper(myMessage, true);
            myMessageHelper.setFrom(sender);
            myMessageHelper.setTo(email.getReceiver());
            myMessageHelper.setText(email.getMessage());
            myMessageHelper.setSubject(email.getSubject());

            FileSystemResource file = new FileSystemResource(new File(email.getAttachment()));
            myMessageHelper.addAttachment(file.getFilename(), file);


            javaMailSender.send(myMessage);
            return "Mail successfully sended";
        } catch (MessagingException e) {
            return "Sending Mail in Error!";
        }
    }
}

