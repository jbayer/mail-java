package org.cloudfoundry.samples;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class MailUtility {

    private String smtpHost = "smtp.sendgrid.net";
    private int smtpPort = 465;
    private String defaultUser = "username";
    private String defaultPassword  = "password";

    public MailUtility()
    {
    	if( System.getenv("SMTP_HOST") != null )
    		smtpHost = System.getenv("SMTP_HOST");    		
    	if( System.getenv("SMTP_PORT") != null )
    		smtpPort = Integer.valueOf( System.getenv("SMTP_PORT") );
    	if( System.getenv("SMTP_AUTH_USER") != null )
    		defaultUser = System.getenv("SMTP_AUTH_USER");
    	if( System.getenv("SMTP_AUTH_PWD") != null )
    		defaultPassword = System.getenv("SMTP_AUTH_PWD");
    }
    
    public static void main(String[] args) throws Exception{
       new MailUtility().sendMailWithEnvValues();
    }
    
    public void sendMail(String smtpHost, int smtpPort, String smtpAuthUser, String smtpAuthPwd, String to, String from, String body, String subject) throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        
    	if( smtpPort == 465 )
    	{
    		props.put("mail.smtp.socketFactory.port", smtpPort);
    		props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory");    		
    	}
        else props.put("mail.smtp.port", smtpPort);

    	
        Authenticator auth = new SMTPAuthenticator( smtpAuthUser, smtpAuthPwd );
        Session mailSession = Session.getInstance(props, auth);
        
        // uncomment for debugging infos to stdout
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);

        message.setText(body);
        message.setFrom(new InternetAddress(from));
        message.setSubject(subject);
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(to));

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    	
    }

    public void sendMailWithEnvValues() throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", smtpHost);
        
        if(smtpPort == 465)
        {
    		props.put("mail.smtp.socketFactory.port", smtpPort);
    		props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory");                	
        }
        else props.put("mail.smtp.port", smtpPort);
        
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator( defaultUser, defaultPassword );
        Session mailSession = Session.getInstance(props, auth);
        // uncomment for debugging infos to stdout
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);

        message.setText("body test");
        message.setFrom(new InternetAddress("sender@someemail.com"));
        message.setSubject("subject test");
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress("recipient@someemail.com"));

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
    	
    	private String username;
    	private String password;
    	
    	public SMTPAuthenticator(String username, String password)
    	{
    		this.username = username;
    		this.password = password;
    	}
    	
        public PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(username, password);
        }
    }
}