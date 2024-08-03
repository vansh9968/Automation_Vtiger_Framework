package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class AccountDetailPageOR {

	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBT;
	
	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateBT;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBT;
	
	@FindBy(xpath = "//td[text()='Account Name']//following-sibling::td[@id='mouseArea_Account Name']/span")
	private WebElement accName;
	
	@FindBy(xpath = "//td[text()='Website']//following-sibling::td[@id='mouseArea_Website']/span")
	private WebElement website;
	
	@FindBy(xpath = "//td[text()='Email']//following-sibling::td[@id='mouseArea_Email']/span")
	private WebElement email;
	
	@FindBy(xpath = "//td[text()='Phone']//following-sibling::td[@id='mouseArea_Phone']/span")
	private WebElement phoneNo;
	
	@FindBy(xpath = "//td[text()='Billing City']//following-sibling::td[@id='mouseArea_Billing City']/span")
	private WebElement billingCity;
	
	@FindBy(xpath = "//td[text()='Assigned To']//following-sibling::td[@id='mouseArea_Assigned To']/span/a")
	private WebElement assignedToValue;
	
}
