package com.eva.vtiger.pageOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class LeadsLandingPageOR {

	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement createLeadsBT;
	
	@FindBy(xpath = "//b[text()='In']/parent::td//following-sibling::td/div/select[@id='bas_searchfield']")
	private WebElement selectInBT;
	
	@FindBy(xpath = "//b[text()='Search for']/parent::td//following-sibling::td/input[@name='search_text']")
	private WebElement searchTextField;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBT;
		
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteBT;
	
}
