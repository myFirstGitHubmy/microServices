package com.api;

import com.shared.utils.EmailHandler;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SenderEmail {
    private final String username;
    private final String password;
    private final Properties props;

    public SenderEmail(String username, String password){
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
//        props.put("mail.smtp.socketFactory.port", "993");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
    }

    public String send(String toEmail, String textMessage, String subject) throws Exception {
        EmailHandler emailHandler = new EmailHandler();
        toEmail = emailHandler.getEmail(toEmail);
        Session session = Session.getInstance(props,
                                              new Authenticator() {
                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                    return new PasswordAuthentication(username, password);
                                                }
                                            });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(textMessage);
            Transport.send(message);
            return "Отправлено";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean check(String toEmail) throws Exception {
        EmailHandler emailHandler = new EmailHandler();
        toEmail = emailHandler.getEmail(toEmail);
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("check");
            message.setText("1");
            Transport.send(message);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
