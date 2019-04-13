package com.prateek.reap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.prateek.reap.util.Constants.*;

@Service
@EnableAsync
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmailToSingleRecipient(String recipient, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(HTML_TEMPLATE_BEFORE_BODY+body+HTML_TEMPLATE_AFTER_BODY,true);
        javaMailSender.send(mimeMessage);

    }

    @Override
    @Async
    public void sendEmailStarRecognition(String recipient, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(HTML_RECOGINITION_TEMPLATE_BEFORE_BODY+body+HTML_RECOGNITION_TEMPLATE_AFTER_BODY,true);
        javaMailSender.send(mimeMessage);

    }


    @Override
    @Async
    public void sendEmailStarRevocation(String recipient, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(HTML_REVOCATION_TEMPLATE_BEFORE_BODY +body+HTML_REVOCATION_TEMPLATE_AFTER_BODY,true);
        javaMailSender.send(mimeMessage);

    }

}
