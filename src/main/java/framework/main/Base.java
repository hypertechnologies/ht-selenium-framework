package framework.main;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Base {
    protected static JSONObject getSuiteConfigs() {
        Properties prop = getProperties();
        String tsConfigFilePath = prop.getProperty("testSuiteConfigFilePath").trim();
        JSONParser parser = new JSONParser();
        JSONObject configs = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(tsConfigFilePath));
            configs = (JSONObject) obj;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return configs;
    }

    protected static JSONArray getBrowsers() {
        JSONObject jsonObject = getSuiteConfigs();
        return (JSONArray) jsonObject.get("browsers");
    }

    protected static JSONArray getSuite() {
        JSONObject jsonObject = getSuiteConfigs();
        return (JSONArray) jsonObject.get("Suite");
    }

    protected static Properties getProperties() {
        // Set path of the properties file
        File file = new File("src/main/java/framework/configs/configs.properties");

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

    protected static void saveResultFile(XSSFWorkbook workbook, String testCaseFilePath, String sessionId) {
        String testResultFileName = new File(testCaseFilePath).getName();
        File testResultDir = new File((testCaseFilePath.split(testResultFileName)[0]) + "../../reports"+ "/" + sessionId);
        if (!testResultDir.exists()){
            testResultDir.mkdirs();
        }
        String testResultPath = testResultDir +"/"+ "Report_" + testResultFileName;

        FileOutputStream fout= null;
        try {
            fout = new FileOutputStream(testResultPath);
            workbook.write(fout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void closeWorkBook(XSSFWorkbook workbook) {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static List<File> getAllTestCaseFilePaths(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
            } else if (file.isDirectory()) {
                resultList.addAll(getAllTestCaseFilePaths(file.getAbsolutePath()));
            }
        }
        return resultList;
    }

    protected static String getCurrentDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm:ss:a_MM-dd-yyyy");
        return myDateObj.format(myFormatObj);
    }
}
