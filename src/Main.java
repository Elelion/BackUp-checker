import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String mailRecipient = "alexander.gints@icloud.com";
        String mailSubject = "BackUp Checker - report";
        String mailTextSuccess = "The archive has been checked" + "\n\n" + "Everything is in order";
        String mailTextError = "ERROR archive checked" + "\n\n" + "Necessary check the archive !!!";

        FileCheck fileCheck = new FileCheck();
        fileCheck.setFullPathAndFile("arch.zip");

        if (fileCheck.getFileStatus()) {
            new SendEmail(mailRecipient, mailSubject, mailTextSuccess);
        } else {
            new SendEmail(mailRecipient, mailSubject, mailTextError);
        }
    }

}
