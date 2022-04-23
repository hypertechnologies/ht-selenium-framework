//package framework;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.json.simple.*;
//import org.json.simple.parser.*;
//import java.io.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//
//public class Base {
//    protected static JSONObject suiteConfigs;
//    protected static int implicitWaitTimeout;
//    protected static int explicitWaitTimeout;
//    protected static String testCaseFilePath;
//    protected static String sessionId;
//    protected static boolean tc_failed = false;
//
//    protected static void setUp() {
//        suiteConfigs = getSuiteConfigs();
//        sessionId = getCurrentDateTime();
//        implicitWaitTimeout = (int) (long) suiteConfigs.get("implicitWaitTimeout");
//        explicitWaitTimeout = (int) (long) suiteConfigs.get("explicitWaitTimeout");
//    }
//
//    protected static JSONObject getSuiteConfigs() {
//        String tsConfigFilePath = "src/main/java/configs/testSuiteConfigs.json";
//        JSONParser parser = new JSONParser();
//        JSONObject configs = new JSONObject();
//        try {
//            Object obj = parser.parse(new FileReader(tsConfigFilePath));
//            configs = (JSONObject) obj;
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//        }
//        return configs;
//    }
//
//    protected static JSONArray getBrowsers() {
//        return (JSONArray) suiteConfigs.get("browsers");
//    }
//
//    protected static JSONArray getSuite() {
//        return (JSONArray) suiteConfigs.get("suites");
//    }
//
//    protected static JSONObject getExcelIndexes() {
//        return (JSONObject) suiteConfigs.get("excelIndexes");
//    }
//
//    // Not in use
//    protected static Properties getProperties(String propertiesFilePath) {
//        // Set path of the properties file
//        File file = new File(propertiesFilePath);
//
//        // Create input stream of the properties file
//        FileInputStream fileInput = null;
//        try {
//            fileInput = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Get all properties and save in an object
//        Properties prop = new Properties();
//        try {
//            prop.load(fileInput);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // return properties object
//        return prop;
//    }
//
//    protected static XSSFWorkbook getWorkbook(String testCaseFilePath) {
//        // Read Excel file
//        FileInputStream excelFileStream = null;
//        try {
//            excelFileStream = new FileInputStream(testCaseFilePath);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Reading Workbook
//        XSSFWorkbook workbook = null;
//        try {
//            assert excelFileStream != null;
//            workbook = new XSSFWorkbook(excelFileStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }
//
//    protected static void saveAndCloseResultFile(XSSFWorkbook workbook, String testCaseFilePath, String sessionId, String browser) {
//        String testResultFileName = new File(testCaseFilePath).getName();
//        File dirFromSuite = new File((testCaseFilePath.split(testResultFileName)[0]).split(suiteConfigs.get("suitesDir").toString())[1]);
//        File testResultDir = new File((testCaseFilePath.split(testResultFileName)[0]) + "../../reports"+ "/" + sessionId + "/" + browser + "/" + dirFromSuite);
//        if (!testResultDir.exists()){
//            testResultDir.mkdirs();
//        }
//        String testResultPath = testResultDir +"/"+ "Report_" + testResultFileName;
//
//        FileOutputStream fout= null;
//        try {
//            fout = new FileOutputStream(testResultPath);
//            workbook.write(fout);
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected static List<File> getAllTestCaseFilePaths(String directoryName) {
//        File directory = new File(directoryName);
//
//        List<File> resultList = new ArrayList<File>();
//
//        // get all the files from a directory
//        File[] fList = directory.listFiles();
//        resultList.addAll(Arrays.asList(fList));
//        for (File file : fList) {
//            if (file.isFile()) {
//            } else if (file.isDirectory()) {
//                resultList.addAll(getAllTestCaseFilePaths(file.getAbsolutePath()));
//            }
//        }
//        return resultList;
//    }
//
//    protected static String getCurrentDateTime() {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh.mm.ss.a_MM-dd-yyyy");
//        return myDateObj.format(myFormatObj);
//    }
//}
