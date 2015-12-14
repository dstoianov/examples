package com.tipsandtricks.gmail;

/**
 * Created by Funker on 28.10.2015.
 */

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class MailReader {

    public static void main(String args[]) {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "cccc@gmail.com", "cccccc");
            System.out.println(store);

//            Folder inbox = store.getFolder("Inbox");
            Folder inbox = store.getFolder("booking");
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.getMessages();

            for (Message message : messages) {

                System.out.println("---------------------------------");
                System.out.println("Content-Type: " + message.getContentType());
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());

//                System.out.println(message);

            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

