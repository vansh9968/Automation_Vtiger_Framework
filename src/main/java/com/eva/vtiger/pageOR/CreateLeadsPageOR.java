package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class CreateLeadsPageOR {

	@FindBy(xpath = "(//input[@value='  Save  '])[1]")
	private WebElement saveButton;

	@FindBy(xpath = "(//input[@value='  Cancel  '])[1]")
	private WebElement cancelButton;

	@FindBy(xpath = "//td[contains(text(),'First Name')]//following-sibling::td/input[@name='firstname']")
	private WebElement firstnameField;

	@FindBy(xpath = "//td[text()='Last Name						']//following-sibling::td/input[@name='lastname']")
	private WebElement lastnameField;

	@FindBy(xpath = "//td[text()='Company 			']//following-sibling::td/input[@name='company']")
	private WebElement companyNameField;

	@FindBy(xpath = "//td[text()='Phone ']//following-sibling::td/input[@id='phone']")
	private WebElement phoneNumberField;

	@FindBy(xpath = "//td[text()='Email ']//following-sibling::td/input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupBT;

	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement groupValue;

	@FindBy(xpath = "//select[@name='assigned_user_id']")
	private WebElement userBT;

	@FindBy(xpath = "//input[@value='U']")
	private WebElement userValue;

	
}
