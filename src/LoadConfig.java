import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadConfig {
    protected Properties properties = new Properties();

    /**
     * Load the configuration file to the Properties
     */
    public LoadConfig() throws IOException  {
        String fullPathAndFile = getPath();
        FileInputStream fileInputStream = new FileInputStream(fullPathAndFile);

        properties.load(fileInputStream);
    }

    /**/

    /**
     * returns the path to the configuration file
     */
    private String getPath() {
        String PATH_PROJECT = System.getProperty("user.dir");
        String pathDir = "resources";
        String filename = "config.proporties";

        return PATH_PROJECT + File.separator + pathDir + File.separator + filename;
    }

    /**
     * returns properties for the specified key
     */
    public String takeProperties(String key) {
        return properties.getProperty(key);
    }
}
