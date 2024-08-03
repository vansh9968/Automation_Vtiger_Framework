package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.CreateLeadsPageOR;

public class CreateLeadsPage extends CreateLeadsPageOR{


	private WebUtil util;
	private Map<String, String> dataMap;
	public CreateLeadsPage(WebUtil util, Map<String, String> dataMap) {
		this.util=util;
		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);

	}


	public void fillUpLeadsInfo() {
		util.SendKeys(getFirstnameField(), dataMap.get("FirstName"));
		util.SendKeys(getLastnameField(),dataMap.get("LastName"));
		util.SendKeys(getCompanyNameField(), dataMap.get("CompanyName"));
		util.SendKeys(getPhoneNumberField(), dataMap.get("Phone"));
		util.SendKeys(getEmailField(), dataMap.get("Email"));
		
		String assignTovalue=dataMap.get("AssignTo");
		if(assignTovalue.equalsIgnoreCase("group")) {
			util.Click(getGroupBT());
			util.SelectByVisibletext(getGroupValue(), dataMap.get("AssignToValue"));
		}else {
			util.Click(getUserBT());
		}

	
	}
	public void clickSaveButton() {
		util.Click(getSaveButton());

	}

	public void clickCancelButton() {
		util.Click(getCancelButton());

	}





}
