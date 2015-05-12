package com.tipsandtricks.gmail;

/**
 * Created by Denis Stoianov on 09/09/2014, 5:02 PM
 * E-mail: denis@revimedia.com
 */

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class SendMailTLS {

    public static void main(String[] args) {

        final String username = "*****@gmail.com";
        final String password = "*****";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("****@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("*****@gmail.com"));
            message.setSubject("Octopus!!!");
            message.setText("Dear Alexandra"
                    + "\n\n octopus is here!"
                    + "\n and time is " + time);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
