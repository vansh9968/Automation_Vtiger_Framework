package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.ContactsLandingPageOR;

public class ContactsLandingPage extends ContactsLandingPageOR{
	
	
	
	private WebUtil util;
	private Map<String, String> dataMap;
	private CreateContactsPage createContacts;
	private ContactsDetailsPage contactsDetail;
	public ContactsLandingPage(WebUtil util, Map<String,String> dataMap) {
		this.util=util;
		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);
	}
	
	
	public CreateContactsPage clickCreateContactsBT() {
		util.Click(getCreateContactsBT());
		createContacts=new CreateContactsPage(util, dataMap);
		return createContacts;
	}

    public ContactsDetailsPage verifySearchContacts() {
		
    	util.SelectByVisibletext(getSelectInBT(), dataMap.get("dataType"));
		String lastName=dataMap.get("LastName");
		util.SendKeys(getSearchForBT(), lastName);
		util.Click(getSearchBT());
		WebElement le=util.getDriver().findElement(By.xpath("//a[text()='Last Name']//parent::td//parent::tr//following-sibling::tr//td//a[text()='"+lastName+"']"));
	    boolean leads= util.isDisplay(le);
		if(leads==true) {
			util.Click(le);
		}else {
			System.out.println("No found Account !");
		}
		contactsDetail=new ContactsDetailsPage(util, dataMap);
		return contactsDetail;
	}
    
    public void verifyDeleteContacts(String decision) {
    	
    	util.Click(getDeleteBT());
		util.alertHandle(decision);
		
   	}
    
    
}
