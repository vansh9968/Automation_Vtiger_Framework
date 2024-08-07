package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.CreateAccountPageOR;

public class CreateAccountPage extends CreateAccountPageOR{
	private WebUtil util;
	private Map<String, String> dataMap;

	

	public CreateAccountPage(WebUtil util, Map<String, String> dataMap) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		this.dataMap = dataMap;

	}

	public void fillUpAccountInfo() {

		util.SendKeys(getAccountName(), dataMap.get("AccountName"));
		util.SendKeys(getWebsite(), dataMap.get("WebSite"));
		util.SendKeys(getPhoneNo(), dataMap.get("Phone"));
		util.SendKeys(getEmail(), dataMap.get("Email"));
	

		
				
		util.Click(getAddMember());
		String mainWindowHandleValue=util.windowHandle("Popup&popuptype");
		util.validateElementByGetText(getOpenAccountWindow(), "Accounts");

		util.SelectByVisibletext(getSelectBillingCity(), dataMap.get("FieldName"));

		util.SendKeys(getSearchTextbox(), dataMap.get("BillingCity"));
		util.Click(getSearchButton());

		String accountLinkValue=dataMap.get("AccountLink");
		WebElement accountLink=util.getDriver().findElement(By.xpath("//td[@class='lvtCol']/parent::tr/following-sibling::tr/td/a[text()='"+accountLinkValue+"']"));
		util.Click(accountLink);
		util.alertHandle("ok");
		util.sleep(2000);
		util.switchToWindow(mainWindowHandleValue);
		
		
		String assignTovalue=dataMap.get("AssignTo");
		if(assignTovalue.equalsIgnoreCase("group")) {
			util.Click(getGroupBT());
			util.SelectByVisibletext(getGroupValue(), dataMap.get("AssignToValue"));
		}else {
			util.Click(getUserBT());

		}

		
	}

	public void clickSaveButton(){
		util.Click(getSaveButton());
		
	}

	public void clickCancelButton() {
		util.Click(getCancelButton());
	}

}
