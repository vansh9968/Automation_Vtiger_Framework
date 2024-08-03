package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.HomePageOR;
import com.eva.vtiger.pages.LeadsLandingPage;

public class HomePage extends HomePageOR{


	private WebUtil util;
	private Map<String, String> dataMap;
	private AccountLandingPage accountLanding;
	private LeadsLandingPage leadsLanding;
	private ContactsLandingPage contactsLanding;
	
	public HomePage(WebUtil util,Map<String, String> dataMap) {
		this.util=util;
		PageFactory.initElements(util.getDriver(), this);
		this.dataMap=dataMap;
	}




	public AccountLandingPage gotoAccountPage() {

		util.ActionsMouseOver(getMoveToMarketing());
		util.Click(getClickAccount());
		accountLanding=new AccountLandingPage(util, dataMap);
		return accountLanding;		
	}

	public LeadsLandingPage gotoLeadsPage() {

		util.ActionsMouseOver(getMoveToMarketing());
		util.Click(getClickLeads());
		leadsLanding=new LeadsLandingPage(util, dataMap);
		return leadsLanding;
	}

	public ContactsLandingPage gotoContactsPage() {

		util.ActionsMouseOver(getMoveToMarketing());
		util.Click(getClickContacts());
		contactsLanding=new ContactsLandingPage(util, dataMap);
		return contactsLanding;		
	}


}
