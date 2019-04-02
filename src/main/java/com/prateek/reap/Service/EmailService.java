package com.prateek.reap.Service;


import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendEmailToSingleRecipient(String recipient, String Body);

    public void sendEmailToSingleRecipient(String recipient, String subject, String body);
}
