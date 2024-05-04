package com.api.university.service;

import com.api.university.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("{spring.mail.username}")
    private String fromMailID;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


    public void sendHtmlEmail(String toMailId, String link) throws MessagingException {
        try {
            log.info("sendHtmlEmail:toMailId={}",toMailId);
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress(fromMailID));
            message.setRecipients(MimeMessage.RecipientType.TO, toMailId);
            message.setSubject(Constants.MAIL_SUBJECT_PASSWORD_RESET);
            String htmlContent = "\t<div>\n" +
                    "<p>Trouble signing in?<br>\n" +
                    "Resetting your password is easy.<br>\n" +
                    "Just press the button below and follow the instructions. We’ll have you up and running in no time.<br><br>\n" +
                    "<a href='"+link+"'/>Reset Password</a> <br><br>\n" +
                    "If you did not make this request then please ignore this email.<p>\n" +
                    "\t</div>";
            message.setContent(htmlContent, "text/html; charset=utf-8");
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
