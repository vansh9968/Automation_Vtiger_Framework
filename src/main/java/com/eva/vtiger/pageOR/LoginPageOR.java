package com.eva.vtiger.pageOR;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class LoginPageOR {

	@FindBy(xpath = "//td[text()='User Name']//following-sibling::td//input[@name='user_name']")
	private WebElement UserName;

	@FindBy(xpath = "//td[text()='Password']//following-sibling::td//input[@name='user_password']")
	private WebElement Password;

	@FindBy(xpath = "//input[@value='  Login  ']")
	private WebElement LoginButton;

	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement logOut;
	
}
