package com.eva.vtiger.testscript.smoke1;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.AccountDetailsPage;
import com.eva.vtiger.pages.CreateAccountPage;
import com.eva.vtiger.pages.HomePage;
import com.eva.vtiger.pages.LoginPage;
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pages.AccountLandingPage;

//@Listeners(com.eva.vtiger.listeners.TestNGSuitesListener.class)
public class AccountsSmokeTestCase1 extends BaseTest{

//	@DataProvider
//	public Object[][] provideAccountData(){
//		List<Map<String, String>> listDataMap=util.getTestData("Account");
//		int dataMapCount=listDataMap.size();
//		Object[][] allData=new Object[dataMapCount][1];
//
//		for (int i = 0; i < dataMapCount-1; i++) {
//			allData[i][0]=listDataMap.get(i);
//		}
//		return allData;
//
//	}


	private  AccountLandingPage accountLanding;

	
	@Test(description = "" , priority = 0)
	
	public  void verifyCreateAccounts() {

		Map<String, String> accountData=util.getTestData("Account", "AD_004");
		HomePage homePage = new HomePage(util, accountData);
		accountLanding=homePage.gotoAccountPage();
		CreateAccountPage createAccount=accountLanding.clickCreateAccountBT();
		createAccount.fillUpAccountInfo();
		createAccount.clickSaveButton();
		System.out.println("accounts create ");

	}

//	@Test(priority = 1)
	public  void verifySearchAccounts() {

		Map<String, String> accountData=util.getTestData("Account", "AD_003");
		HomePage homePage = new HomePage(util, accountData);
		accountLanding=homePage.gotoAccountPage();
		accountLanding.verifySearchAccount();

	}

//	@Test(priority = 2)
	public  void verifyAccountDetails() {

		Map<String, String> accountData=util.getTestData("Account", "AD_001");
		HomePage homePage = new HomePage(util,accountData);
		accountLanding=homePage.gotoAccountPage();
		AccountDetailsPage accountDetail=accountLanding.verifySearchAccount();
		accountDetail.verifyAccountDetail("hadric", "www.hadric.com", "5673456789", "hadric@eva.com", "San Mateo", "Team Selling");

	}


//	@Test(priority = 3)
	public void verifyAccountIsDelete() {

		Map<String, String> accountData=util.getTestData("Account", "AD_001");
		HomePage homePage = new HomePage(util,accountData);
		accountLanding=homePage.gotoAccountPage();
		accountLanding.verifySearchAccount();
		accountLanding.verifyDeleateAccounts("ok");

	}

}
