package otpjsp;



import java.util.Properties;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class otpSendMail  {
	
    String emailid="";
    public int otp=0;
	Properties emailProperties;

	Session mailSession;
	MimeMessage emailMessage;
	public otpSendMail() {
		
	}
	
    public otpSendMail(String s) {
    	
    	emailid=s;
    }
	public static void main(String args[]) throws AddressException, MessagingException {

		//otpSendMail javaEmail = new otpSendMail();
		//ReceiveSendEmail rse=new ReceiveSendEmail();

		/*javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage();
		javaEmail.sendEmail();*/
	}

	public void setMailServerProperties() {

		String emailPort = "587";// gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.ssl.trust", "*");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		try {
		Random rd=new Random();
		otp=rd.nextInt(9999);
		createEmailMessage(otp);
		sendEmail();
		}
		catch(Exception e) {
			
		}
		

	}

	public void createEmailMessage(int otp) throws AddressException,
			MessagingException {
		String s="";
		s+=otp;
		String[] toEmails = { emailid };
		String emailSubject = "Java Email";
		String emailBody = s;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");
		//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "adinaik98@gmail.com";//just the id alone without @gmail.com
		String fromUserEmailPassword = "adityanaik";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
}
}