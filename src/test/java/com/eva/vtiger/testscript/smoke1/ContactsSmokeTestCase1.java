package com.eva.vtiger.testscript.smoke1;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.eva.vtiger.pages.HomePage;
import com.eva.vtiger.pages.LeadDetailsPage;
import com.eva.vtiger.pages.LeadsLandingPage;
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.ContactsDetailsPage;
import com.eva.vtiger.pages.ContactsLandingPage;
import com.eva.vtiger.pages.CreateContactsPage;
import com.eva.vtiger.pages.CreateLeadsPage;

public class ContactsSmokeTestCase1 extends BaseTest{
	
//	@DataProvider
//	public Object[][] provideAccountData(){
//		Map<String, String> listDataMap=util.getTestData("Contacts","ContactsD_002");
//		int dataMapCount=listDataMap.size();
//		Object[][] allData=new Object[dataMapCount][1];
//
//		for (int i = 0; i < dataMapCount-1; i++) {
//			allData[i][0]=listDataMap.get(i);
//		}
//		return allData;
//
//	}
	
	
	
//	@Test(priority = 0)
	public  void verifyCreateContacts() {
		Map<String, String> contactsDataMap=util.getTestData("Contacts", "ContactsD_001");
		HomePage homePage=new HomePage(util, contactsDataMap);
		ContactsLandingPage contactsLanding=homePage.gotoContactsPage();
	    CreateContactsPage	createContactsPage=contactsLanding.clickCreateContactsBT();
	    createContactsPage.fillupContactsInfo();
	    createContactsPage.clickSaveButton();
		
	}

	@Test(priority = 1)
    public  void verifySearchContacts() {
		
		Map<String, String> contactsData=util.getTestData("Contacts", "ContactsD_001");
		HomePage homePage=new HomePage(util,contactsData);
		ContactsLandingPage contactsLanding=homePage.gotoContactsPage();
		contactsLanding.verifySearchContacts();
		
	}
	
	
//	@Test(priority = 2)
    public  void verifyDetailContacts() {
		
		   Map<String, String> contactsData=util.getTestData("Contacts", "ContactsD_001");
			HomePage homePage=new HomePage(util, contactsData);
			ContactsLandingPage contactsLanding=homePage.gotoContactsPage();
			ContactsDetailsPage contactsDetail=contactsLanding.verifySearchContacts();
			contactsDetail.verifyLeadDetails("tom", "petter", "ExpertView", "1234967891", "tom_petter@rock.com","Support Group");
			
		
	}

	
    
//	@Test(priority = 3)
    public  void verifyDeleteContacts() {
		
		Map<String, String> contactsData=util.getTestData("Contacts", "ContactsD_001");
		HomePage homePage=new HomePage(util, contactsData);
		ContactsLandingPage contactsLanding=homePage.gotoContactsPage();
		contactsLanding.verifySearchContacts();
		contactsLanding.verifyDeleteContacts("ok");
		
	}
    
	

	
}
