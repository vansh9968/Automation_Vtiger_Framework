package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.CreateContactsPageOR;

public class CreateContactsPage extends CreateContactsPageOR{
	
	private WebUtil util;
	private Map<String, String> dataMap;
	
		
	public CreateContactsPage(WebUtil util, Map<String, String> dataMap) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		this.dataMap = dataMap;
	}
	
	public void fillupContactsInfo() {

		util.SendKeys(getFirstnameField(), dataMap.get("FirstName"));
		util.SendKeys(getLastnameField(), dataMap.get("LastName"));
		util.SendKeys(getEmail(), dataMap.get("Email"));
		util.SendKeys(getOfficePhone(), dataMap.get("Office Phone"));
		util.SendKeys(getTitle(), dataMap.get("Title"));
						
		util.Click(getAddMember());
		String mainWindowHandleValue=util.windowHandle("Popup&popuptype");
		util.validateElementByGetText(getOpenAccountWindow(), "Accounts");

		util.SelectByVisibletext(getSelectBillingCity(), dataMap.get("FieldName"));

		util.SendKeys(getSearchTextbox(), dataMap.get("BillingCity"));
		util.Click(getSearchButton());

		String accountLinkValue=dataMap.get("AccountLink");
		WebElement accountLink=util.getDriver().findElement(By.xpath("//td[@class='lvtCol']/parent::tr/following-sibling::tr/td/a[text()='"+accountLinkValue+"']"));
		util.Click(accountLink);
		util.switchToWindow(mainWindowHandleValue);

		String assignTo=dataMap.get("AssignTo");
		if(assignTo.equalsIgnoreCase("group")) {
			util.Click(getGroupBT());
			util.SelectByVisibletext(getGroupValue(), dataMap.get("AssignToValue"));
		}else {
			util.Click(getUserBT());

		}
		
	}

	public void clickSaveButton(){
		util.Click(getSaveButton());
//		util.alertHandle("ok");
	}

	public void clickCancelButton() {
		util.Click(getCancelButton());
		util.alertHandle("cancel");
	}
	
}
