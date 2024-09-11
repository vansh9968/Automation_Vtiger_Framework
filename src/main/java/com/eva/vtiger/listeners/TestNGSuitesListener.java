package com.eva.vtiger.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.*;
import org.testng.annotations.Parameters;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.LoginPage;




public class TestNGSuitesListener implements ISuiteListener{
	
//private WebUtil util;
//	public TestNGSuitesListener(WebUtil util) {
//this.util=util;
//		
//	}


	public void onStart() {

	}
	

	public void onFinish(ISuite suite) {
		System.out.println("#######################################################################");
		String to="vanshkumar.tester@gmail.com";
		String from="vanshrathore9968@gmail.com";
		final String password="cvze ayyt sfna fzey";
		String host="smtp.gmail.com";
		Properties properties= System.getProperties();
		properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465"); // Gmail SMTP port
        properties.setProperty("mail.smtp.auth", "true"); // Enable authentication
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
           
           MimeMessage message = new MimeMessage(session);

            
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Test execution report");

            // Now set the actual message
            Multipart multipart = new MimeMultipart();
            
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is the actual message");
           // message.setText("hi mam ,how are you");

            multipart.addBodyPart(messageBodyPart);
            
            messageBodyPart = new MimeBodyPart();
            WebUtil util=new WebUtil();
     
            String filename = "C:\\Users\\Hacker\\eclipse-QAtester workspace\\Automation_Vtiger_Framework\\Reports\\LeadsSmokeTestCase1.html"; // Replace with the path to the file you want to attach
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(new File(filename).getName());
            
            multipart.addBodyPart(messageBodyPart);
            
            message.setContent(multipart);

            System.out.println("Sending message...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (AuthenticationFailedException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            e.printStackTrace();
        } catch (MessagingException mex) {
            System.out.println("Messaging exception: " + mex.getMessage());
            mex.printStackTrace();
        }

	}

}