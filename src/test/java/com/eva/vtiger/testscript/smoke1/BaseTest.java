package com.eva.vtiger.testscript.smoke1;

import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.LoginPage;

public class BaseTest {
	
	protected WebUtil util;
	private ExtentReports ext;
	private ExtentReports extReport;
	private LoginPage login;
	private Properties propObj;
	public static String suiteName;
	
	@BeforeSuite
	public void beforeSuite() {
	
		

		
	}
	
	@BeforeTest
	public void connectToDB(ITestContext context) {
		System.out.println("connect to database");
		util = new WebUtil();
		propObj=util.loadProperty("config");
		util.createExtendReports(context.getCurrentXmlTest().getName());
	}
	
	
	@BeforeClass
	public void beforeClass() {	 

		
	}
	
	
//	@Parameters("browser")
	@BeforeMethod
	public void hitURLAndLogin(Method mt) { //,String browser) {
		util.launchBrowser("chrome");

		String testCaseName=mt.getName();
		util.createTest(testCaseName);
		util.hitURL("http://localhost:8888");
//		Map<String, String> dataMap = util.getTestData("Login", "LD_001");

		 login = new LoginPage(util);
		login.validLogin(propObj.getProperty("username"), propObj.getProperty("password"));
		
	}
	
	@AfterMethod
	public void afterTestCase(ITestResult itr, Method mt) {
		if(itr.getStatus()==ITestResult.FAILURE) {
			String path=util.takeScreenShot(mt.getName());
			util.attachScreenshot(path);
			util.getExtTest().log(Status.FAIL, "snapshot taken for failure");
		}
		util.sleep(2000);
		login.logout();
	
	}
	

	
	@AfterClass
	public void afterClass() {
		util.Quit();
	}
	
	@AfterTest
	public void disconnectFromDB() {
		//System.out.println("disconnect to database");
	util.flush();
	}
	
	@AfterSuite
	public void afterSuite() {
		util.flush();
		
	}
	



	
	
}
