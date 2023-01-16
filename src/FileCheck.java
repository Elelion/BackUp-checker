import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class FileCheck {
    private final String PATH_PROJECT = System.getProperty("user.dir");
    private File FILE;
    private final String ERR_FILE_SIZE = "ERR_FILE_SIZE";
    private final String ERR_FILE_EXIST = "ERR_FILE_EXIST";

    /**/

    /**
     * setting the path and file name
     */
    public void setFullPathAndFile(String filename) {
        String pathDir = "resources";
        String fullPathAndFile = PATH_PROJECT + File.separator + pathDir + File.separator + filename;

        FILE = new File(fullPathAndFile);
    }

    /**
     * checks whether there is a file or not, and whether it satisfies
     * our conditions or not
     */
    public boolean getFileStatus() {
        if (getFileSize() >= 999) {
            errorCodePrint(ERR_FILE_SIZE);
            return false;
        }

        if (isFileExists(getFullPathAndFile())) {
            System.out.println("-> getFileExtension: " + getFileExtension());
            System.out.println("-> getFileSize: " + getFileSize());
            return true;
        } else {
            errorCodePrint(ERR_FILE_EXIST);
            return false;
        }
    }

    /**
     * condition for checking our file
     */
    private boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory() && file.isFile() && !file.isHidden();
    }

    /**/

    /**
     * gets the full path to the specified file, including the file itself
     */
    private File getFullPathAndFile() {
        System.out.println("-> getFullPathAndFile: " + FILE);

        return FILE;
    }

    /**
     * gets the extension of the specified file
     */
    private String getFileExtension() {
        String fileName = FILE.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return  "";
        }
    }

    /**
     * calculates the file size in GB, dividing the size by a dot
     */
    private float getFileSize() {
        NumberFormat f = NumberFormat.getInstance(Locale.GERMANY);
        float fileSize = FILE.length() / 1_000_000;

        return Float.parseFloat(f.format(fileSize));
    }

    /**
     * outputs errors according to the transmitted code
     */
    private void errorCodePrint(String errCode) {
        switch (errCode) {
            case ERR_FILE_SIZE -> System.out.println("The file size is too large. Limit: 999 GB");
            case ERR_FILE_EXIST -> System.out.println("The file doesn't exist");
        }
    }
}
