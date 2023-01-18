import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    /**
     * load the configuration file to the Properties
     * check the email for validity through regular expressions
     */
    public static void main(String[] args) throws IOException {
        LoadConfig loadConfig = new LoadConfig();
        String mailRecipient = loadConfig.takeProperties("mail.recipient");

        if (checkMailStatus(mailRecipient)) {
            sendEmail(mailRecipient);
        } else {
            System.out.println("Invalid email has been entered");
        }
    }

    /**
     * check the email for validity through regular expressions
     */
    private static boolean checkMailStatus(String email) {
        String regexMailCheck = "[A-Za-z0-9+]+(@{1})+([A-Za-z0-9+])+(\\.{1})+([A-Za-z]{2,})";

        Pattern pattern = Pattern.compile(regexMailCheck);
        Matcher matcher = pattern.matcher(email);

        if (matcher.find()) {
            return true;
        }

        return false;
    }

    /**
     * we send an email, if the conditions for checking the file are true,
     * then we send an email that everything is OK, if not, then we
     * send an error message
     */
    private static void sendEmail(String mailRecipient) throws IOException {
        String mailSubject = "BackUp Checker - report";
        String mailTextSuccess = "The archive has been checked" + "\n\n" + "Everything is in order";
        String mailTextError = "ERROR archive checked" + "\n\n" + "Necessary check the archive !!!";

        FileCheck fileCheck = new FileCheck();
        fileCheck.setFullPathAndFile("arch.zip");

        new SendEmail(mailRecipient, mailSubject,
            fileCheck.getCheckFileStatus() ? mailTextSuccess : mailTextError);
    }

}
