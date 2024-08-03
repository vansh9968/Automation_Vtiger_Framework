package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class CreateContactsPageOR {

	@FindBy(xpath = "//td[contains(text(),'First Name')]/following-sibling::td/input[@name='firstname']")
	private WebElement firstnameField;
	
	@FindBy(xpath = "//td[text()='Last Name						']/following-sibling::td/input[@name='lastname']")
	private WebElement lastnameField;
	
	@FindBy(xpath = "//td[text()='Email ']//following-sibling::td/input[@id='email']")
	private WebElement email;
	
	@FindBy(xpath = "//td[text()='Office Phone ']//following-sibling::td/input[@id='phone']")
	private WebElement officePhone;
	
	@FindBy(xpath = "//td[text()='Title ']//following-sibling::td/input[@id='title']")
	private WebElement title;
	
	@FindBy(xpath = "//td[@class='dvtCellLabel']//following-sibling::td//img[@title='Select']")
	private WebElement addMember;
	
	@FindBy(xpath = "//td[@class='moduleName']")
	private WebElement openAccountWindow;

	@FindBy(xpath = "//select[@name='search_field']")
	private WebElement selectBillingCity;
	
	@FindBy(xpath = "//input[@id='search_txt']")
	private WebElement searchTextbox;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//td[text()='Assigned To 			']/following-sibling::td/input[@value='T']")
	private WebElement groupBT;

	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement groupValue;

	@FindBy(xpath = "//td[text()='Assigned To 			']/following-sibling::td/input[@value='U']")
	private WebElement userBT;

	@FindBy(xpath = "//select[@name='assigned_user_id']")
	private WebElement userValue;
	
	@FindBy(xpath = "(//input[@value='  Save  '])[1]")
	private WebElement saveButton;

	@FindBy(xpath = "(//input[@value='  Cancel  '])[1]")
	private WebElement cancelButton;
	
}
