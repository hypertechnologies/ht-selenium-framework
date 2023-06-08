package stepDefinitions;

import java.io.*;
import java.util.Properties;

public class Base {
    protected static int explicitWaitTimeout = Integer.parseInt(getProperties("src/test/resources/selenium.properties").getProperty("explicitWaitTimeout"));
    protected static int implicitWaitTimeout = Integer.parseInt(getProperties("src/test/resources/selenium.properties").getProperty("implicitWaitTimeout"));

    protected static Properties getProperties(String propertiesFilePath) {
        // Set path of the properties file
        File file = new File(propertiesFilePath);

        // Create input stream of the properties file
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Get all properties and save in an object
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return properties object
        return prop;
    }
}
