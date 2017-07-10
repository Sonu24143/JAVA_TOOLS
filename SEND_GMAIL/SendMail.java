import javax.mail.*;
import javax.mail.internet.*;
import java.util.*; 

public class SendMail
{
public static void main(String [] args) throws MessagingException 
{
    final String username = "sonu24143parekaden@gmail.com";
    final String password = "XXXXXXXX";
    String [] recipients = new String[1];
    recipients[0] = "sonu.j@appnomic.com";
    String from = "sonu@gmail.com";
    String subject = "Automated mail";
    String message = "This is a test mail from java";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
//    Session session = Session.getDefaultInstance(props, null);
    Session session = Session.getInstance( props , new javax.mail.Authenticator() { protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(username , password); } } ); 
    session.setDebug(true);

    Message msg = new MimeMessage(session);

    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
    for (int i = 0; i < recipients.length; i++) 
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);

    msg.addHeader("MyHeaderName", "Header");
    msg.setSubject(subject);
    msg.setContent(message, "text/plain");
    Transport.send(msg);
}
}
