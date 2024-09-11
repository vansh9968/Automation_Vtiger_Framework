package com.eva.vtiger.testscript.smoke1;

import java.net.SocketException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.xdgf.util.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.HomePage;
import com.eva.vtiger.pages.LeadDetailsPage;
import com.eva.vtiger.pages.LeadsLandingPage;
import com.eva.vtiger.pages.LoginPage;
import com.eva.vtiger.pages.CreateLeadsPage;


public class LeadsSmokeTestCase1 extends BaseTest{

//@Test(priority = 0)
	public  void verifyCreateLeads() {
		
		
		Map<String, String> leadsData=util.getTestData("Leads", "LeadsD_001");
		HomePage homePage=new HomePage(util, leadsData);
		LeadsLandingPage leadsLanding=homePage.gotoLeadsPage();
	    CreateLeadsPage	createLeadsPage=leadsLanding.clickCreateLeadsBT();
		createLeadsPage.fillUpLeadsInfo();
		createLeadsPage.clickSaveButton();
	
		System.out.println("leads create ");

	}
	
    @Test(priority = 1)
	public  void verifySearchLeads()  {
    
	Map<String, String> leadsDataMap=util.getTestData("Leads", "LeadsD_001");
	HomePage homePage=new HomePage(util,leadsDataMap);
	LeadsLandingPage leadsLanding=homePage.gotoLeadsPage();
	leadsLanding.verifySearchLeads();
	
		
	}
    
    
//@Test(priority = 2)
	
	public  void verifyLeadsDetail() {
		
	    Map<String, String> leadsData=util.getTestData("Leads", "LeadsD_001");
		HomePage homePage=new HomePage(util, leadsData);
		LeadsLandingPage leadsLanding=homePage.gotoLeadsPage();
		LeadDetailsPage leadsDetail=leadsLanding.verifySearchLeads();
		leadsDetail.verifyLeadDetails("jockey", "koli", "ExpertView", "1234567891", "jockey@rock.com", "www.jockey.com", "Support Group");
		
	}


	
//	@Test(priority = 3)
	public  void verifyDeleteLeads() {
		
		Map<String, String> leadsData=util.getTestData("Leads", "LeadsD_001");
		HomePage homePage=new HomePage(util, leadsData);
		LeadsLandingPage leadsLanding=homePage.gotoLeadsPage();
		leadsLanding.verifySearchLeads();
		leadsLanding.verifyDeleteLeads("ok");
	
	}
	
	
	
}











