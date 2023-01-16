import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {

    private final String PATH_PROJECT = System.getProperty("user.dir");
    private String userName;
    private String userPassword;
    private Properties props = new Properties();

    /**
     * sends an email according to the specified parameters
     */
    public SendEmail(String mailRecipient, String mailSubject, String mailText) throws IOException {
        loadUserDataFromConfig();
        configureProps();
        sendEmail(mailRecipient, mailSubject, mailText);
    }

    /**/

    /**
     * to be able to send emails from the application, you need
     * to generate a special password
     *
     * https://support.google.com/accounts/answer/185833?visit_id=638093045649618309-3914306815&p=InvalidSecondFactor&rd=1
     */
    private void loadUserDataFromConfig() throws IOException {
        String pathDir = "resources";
        String filename = "config.proporties";
        String fullPathAndFile = PATH_PROJECT + File.separator + pathDir + File.separator + filename;

        FileInputStream fileInputStream = new FileInputStream(fullPathAndFile);
        Properties properties = new Properties();
        properties.load(fileInputStream);

        userName = properties.getProperty("mail.user");
        userPassword = properties.getProperty("mail.password");
    }

    /**
     * we prescribe settings for our props
     */
    private void configureProps() {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
    }

    /**
     * we create a session where we enter our settings from props and
     * data for user authorization
     */
    private Session getSession() {
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        };

        return Session.getInstance(
            props,
            auth
        );
    }

    /**
     * we take the data from our session and send an email according
     * to the receiving parameters of the method
     */
    private void sendEmail(String mailRecipient, String mailSubject, String mailText) {
        Session session = getSession();

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(mailRecipient)
            );
            message.setSubject(mailSubject);
            message.setText(mailText);

            Transport.send(message);
            System.out.println("SendEmail - Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
