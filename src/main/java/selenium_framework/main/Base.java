package selenium_framework.main;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.Properties;

public class Base {
    protected static JSONArray getSuite() {
        Properties prop = getProperties();
        String tsConfigFilePath = prop.getProperty("tsConfigFilePath").trim();
        JSONParser parser = new JSONParser();
        JSONArray suite = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader(tsConfigFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            suite = (JSONArray) jsonObject.get("Suite");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return suite;
    }

    protected static Properties getProperties() {
        // Set path of the properties file
        File file = new File("src/main/java/selenium_framework/configs/configs.properties");

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

    protected static void saveResultFile(XSSFWorkbook workbook, String testCaseFilePath) {
        File file = new File(testCaseFilePath);
        FileOutputStream fout= null;
        try {
            fout = new FileOutputStream(file);
            workbook.write(fout);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
