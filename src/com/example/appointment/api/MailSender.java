/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appointment.api;

/**
 *
 * @author Lou
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private String from;
    private String to;
    private String subject;
    private String text;
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;

    public MailSender(String from, String to, String subject, String text, String smtpHost, int smtpPort, String username, String password) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
    }

    public void send() throws MessagingException {
        // Create a Properties object to hold the SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session object with the SMTP server properties and the username and password for authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        // Create a new MimeMessage object with the session
        MimeMessage message = new MimeMessage(session);

        // Set the From, To, Subject, and Text fields of the message
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);

        // Send the message using the Transport object from the session
        Transport.send(message);
    }
}

