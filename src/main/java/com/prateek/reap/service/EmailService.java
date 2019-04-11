package com.prateek.reap.service;


import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendEmailToSingleRecipient(String recipient, String Body);

    public void sendEmailToSingleRecipient(String recipient, String subject, String body);
}
