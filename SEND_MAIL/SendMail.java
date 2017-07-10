import javax.mail.*;
import javax.mail.internet.*;
import java.util.*; 

public class SendMail
{
public static void main(String [] args) throws MessagingException 
{
    String [] recipients = new String[1];
    recipients[0] = "sonu.j@appnomoic.com";
//["sonu.j@appnomoic.com"];
    String from = "test@appnomic.com";
    String subject = "Automated mail";
    String message = "This is a test mail from java";
    Properties props = new Properties();
    props.put("mail.smtp.host", "10.1.1.42");

    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(false);

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
