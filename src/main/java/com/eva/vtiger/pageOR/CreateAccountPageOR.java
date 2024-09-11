package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class CreateAccountPageOR {

	@FindBy(xpath = "//input[@name='accountname']")
    private WebElement accountName;

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

	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupBT;

	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement groupValue;

	@FindBy(xpath = "//select[@name='assigned_user_id']")
	private WebElement userBT;

	@FindBy(xpath = "//input[@value='U']")
	private WebElement userValue;
	
	@FindBy(xpath = "//td[text()='Phone ']//following-sibling::td/input[@id='phone']")
	private WebElement phoneNo;
	
	@FindBy(xpath = "//td[text()='Website 			']//following-sibling::td/input[@name='website']")
	private WebElement website;
	
	@FindBy(xpath = "//td[text()='Email ']//following-sibling::td/input[@id='']")
	private WebElement email;

	@FindBy(xpath = "(//input[@value='  Save  '])[1]")
	private WebElement saveButton;

	@FindBy(xpath = "(//input[@value='  Cancel  '])[1]")
	private WebElement cancelButton;
	
}
