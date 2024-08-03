//package com.eva.vtiger.Utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.google.common.io.Files;
//
//public class pract {
//	private static  ExtentReports ext;
//	private static  ExtentTest logger;
//
//public static void main(String[] args) throws IOException {
//	testData("Login", "LD_002");
	
	
//	WebDriver driver=new ChromeDriver();
//testData("Login", "LD_001");
//initHtmlReport();	
//logger.log(Status.INFO, "user is able to pass username(xyz)");
//logger.log(Status.INFO, "user is able to pass password(expertview)");
//logger.log(Status.INFO, "click on login button");
//logger.log(Status.PASS, "user lands on home page");
//flushReport();
//
//TakesScreenshot snap=  (TakesScreenshot) driver;
//File fileFrom=snap.getScreenshotAs(OutputType.FILE);
//File fileTo=new File("report\\ss.png");
//Files.copy(fileFrom, fileTo);
//}

//public static void setExtentLogger(String testCaseName) {
//	logger=ext.createTest(testCaseName);
//	
//}
//
//public static ExtentTest getExtentLogger() {
//	return logger;
//}
//
//public static void flushReport() {
//	ext.flush();
//}
//
//public static void initHtmlReport() {
//	 ExtentHtmlReporter report=new ExtentHtmlReporter("report\\testreport.html");
//	 ExtentReports ext = new ExtentReports();
//	ext.attachReporter(report);
//	report.config().setDocumentTitle("wisa");
//	report.config().setReportName("xyz");
//	
//	ext.setSystemInfo("Machine", System.getProperty("testpc1"));
//	ext.setSystemInfo("OS", System.getProperty("os.name"));
//	ext.setSystemInfo("browser", System.getProperty("browser.name"));
//	ext.setSystemInfo("user name", System.getProperty("user.name"));
//	ext.setSystemInfo("processer name",System.getProperty( "os.arch"));
//	
//	
//}
//
//
//
//
//public static Map<String, String> testData(String sheetName, String dataID) {
//	Map<String, String> dataMap = new HashMap<String, String>();
//	InputStream ip;
//	int dataRownumber = 0;
//	try {
//		ip = new FileInputStream("data\\Vtiger Data.xlsx");
//		Workbook wBook = new XSSFWorkbook(ip);
//		Sheet sheetobj = wBook.getSheet(sheetName);
//		int rowCount = sheetobj.getLastRowNum();
//		for (int i = 0; i <= rowCount; i++) {
//			Row row = sheetobj.getRow(i);
//			Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			String dataIDValue = cell.getStringCellValue();
//			if (dataIDValue.equalsIgnoreCase(dataID)) {
//				dataRownumber = i;
//			}
//		}
//		Row firstRow = sheetobj.getRow(0);
//		Row rowobj = sheetobj.getRow(dataRownumber);
//		for (int j = 1; j <= firstRow.getLastCellNum() - 1; j++) {
//			Cell cellKey = firstRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			Cell cellValue = rowobj.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			String columnKey = cellKey.getStringCellValue();
//			String columnValue = cellValue.getStringCellValue();
//			
//			dataMap.put(columnKey, columnValue);
//			System.out.println(columnKey + " -> " + columnValue);
//		}
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//
//	return dataMap;
//}
//
//}
//


