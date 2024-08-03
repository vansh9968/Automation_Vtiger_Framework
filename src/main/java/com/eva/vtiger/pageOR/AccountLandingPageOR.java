package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class AccountLandingPageOR {
	
	@FindBy(xpath = "//b[text()='In']//parent::td//following-sibling::td//div//select[@id='bas_searchfield']")
	private WebElement selectInBT;

	@FindBy(xpath = "//b[text()='Search for']//parent::td//following-sibling::td//input[@name='search_text']")
	private WebElement searchTextBox;

	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBT;

	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBT;

	
	@FindBy(xpath = "//img[@alt='Create Account...']")
	private WebElement createAccountBT;


}
