package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.LoginPageOR;

import lombok.Getter;


public class LoginPage extends LoginPageOR{
	private WebUtil util;
//	private Map<String, String> dataMap;

	
	public LoginPage(WebUtil util) {
		this.util=util;
//		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);
	

	}

	public void validLogin(String userName , String password) {
	
		util.SendKeys(getUserName(),userName );
		
		util.SendKeys(getPassword(), password);
		
		util.Click(getLoginButton());

	}

	public void logout() {
		util.Click(getLogOut());
	}
	

}
