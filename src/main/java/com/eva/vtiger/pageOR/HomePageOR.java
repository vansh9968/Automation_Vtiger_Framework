package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class HomePageOR {

	@FindBy(xpath = "//a[text()='Marketing']")
	private WebElement moveToMarketing;

	@FindBy(xpath = "//div[@id='Marketing_sub']//a[text()='Accounts']")
	private WebElement clickAccount;

	@FindBy(xpath = "//div[@id='Marketing_sub']//a[text()='Leads']")
	private WebElement clickLeads;
	
	@FindBy(xpath = "//div[@id='Marketing_sub']//a[text()='Contacts']")
	private WebElement clickContacts;
	
	
	
}
