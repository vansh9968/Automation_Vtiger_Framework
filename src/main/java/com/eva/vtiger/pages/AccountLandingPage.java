package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.AccountLandingPageOR;
import com.eva.vtiger.pages.AccountDetailsPage;
import com.eva.vtiger.pages.CreateAccountPage;

public class AccountLandingPage extends AccountLandingPageOR{

	
	private static CreateAccountPage createAccount;
	private static AccountDetailsPage accountDetail;
	private static WebUtil util;
	private static Map<String, String> dataMap;

	public AccountLandingPage(WebUtil util, Map<String, String> dataMap) {
		this.util=util;
		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);

	}

	public CreateAccountPage clickCreateAccountBT() {
		util.Click(getCreateAccountBT());
		createAccount=new CreateAccountPage(util, dataMap);
		return createAccount;
	}
	
	public AccountDetailsPage verifySearchAccount() {

		util.SelectByVisibletext(getSelectInBT(),dataMap.get("dataType"));
		String accountname=dataMap.get("AccountName");
		util.SendKeys(getSearchTextBox(), accountname);
		util.Click(getSearchBT());
		boolean account=false;
		WebElement acc=null;
		try {
		 acc=util.getDriver().findElement(By.xpath("//a[text()='Account Name']//parent::td//parent::tr//following-sibling::tr//td//a[text()='"+accountname+"']"));
			account= util.isDisplay(acc);
		}catch(Exception e) {
			System.out.println("No found Account !");
		}
		if(account==true) {
			util.Click(acc);
		}
		accountDetail=new AccountDetailsPage(util, dataMap);
		return accountDetail;		
	}

	public void verifyDeleateAccounts(String decision) {
		util.Click(getDeleteBT());
		util.alertHandle(decision);
	}


}
