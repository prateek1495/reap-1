package com.prateek.reap.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailService {

    public void sendEmailToSingleRecipient(String recipient, String subject, String body) throws MessagingException;

    @Async
    void sendEmailStarRecognition(String recipient, String subject, String body) throws MessagingException;

    @Async
    void sendEmailStarRevocation(String recipient, String subject, String body) throws MessagingException;
}
