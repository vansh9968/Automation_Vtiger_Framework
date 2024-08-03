package com.eva.vtiger.testscript.smoke1;


	

	import java.util.Properties;

	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

	public class SendMail {
		
		public static void main(String[] args) {
			//String to="priyagupta542004@gmail.com";
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
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject("Testing Mail");

	            // Now set the actual message
	            message.setText("hi sir ,how are you");

	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
		}
	}



