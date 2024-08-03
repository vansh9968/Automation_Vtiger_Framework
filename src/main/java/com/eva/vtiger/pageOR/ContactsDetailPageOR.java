package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class ContactsDetailPageOR {

	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBT;
	
	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateBT;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBT;
	
	@FindBy(xpath = "//td[text()='First Name']//following-sibling::td[@id='mouseArea_First Name']/span")
	private WebElement firstName;
	
	@FindBy(xpath = "//td[text()='Title']//following-sibling::td[@id='mouseArea_Title']/span")
	private WebElement title;
	
	@FindBy(xpath = "//td[text()='Last Name']//following-sibling::td[@id='mouseArea_Last Name']/span")
	private WebElement lastName;
	
	@FindBy(xpath = "//td[text()='Email']//following-sibling::td[@id='mouseArea_Email']/span")
	private WebElement email;
	
	@FindBy(xpath = "//td[text()='Office Phone']//following-sibling::td[@id='mouseArea_Office Phone']/span")
	private WebElement phoneNo;
	
	@FindBy(xpath = "//td[text()='Assigned To']//following-sibling::td[@id='mouseArea_Assigned To']/span/a")
	private WebElement assignToValue;
	
}
