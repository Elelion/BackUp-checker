import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String mailSender = "elelion@yandex.ru";
        String mailSubject = "BackUp Checker - report";
        String mailTextSuccess = "The archive has been checked" + "\n\n" + "Everything is in order";
        String mailTextError = "ERROR archive checked" + "\n\n" + "Necessary check the archive !!!";

        FileCheck fileCheck = new FileCheck();
        fileCheck.setFullPathAndFile("arch.zip");

        if (fileCheck.getFileStatus()) {
            new SendEmail(mailSender, mailSubject, mailTextSuccess);
        } else {
            System.out.println("ttt");
            new SendEmail(mailSender, mailSubject, mailTextError);
        }
    }

}
