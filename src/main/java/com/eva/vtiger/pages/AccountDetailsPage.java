package com.eva.vtiger.pages;

import java.util.Map;

import org.apache.logging.log4j.util.PropertySource.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.AccountDetailPageOR;


public class AccountDetailsPage extends AccountDetailPageOR{
	
	
	public static WebUtil util;
	public static Map<String, String> dataMap;
	public AccountDetailsPage(WebUtil util, Map<String, String> dataMap){
		this.util=util;
		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);
	}
	
	public void clickDeleteButton() {
		util.Click(getDeleteBT());
		
	}

	public void clickDuplicateButton() {
		util.Click(getDuplicateBT());
	}
	
	public void clickEditButton() {
		util.Click(getEditBT());
	}
	
	public void VerifyAccountName(String exAccountname) {
		String acAccountName = util.getText(getAccName());
		if (exAccountname.equals(acAccountName)) {
			System.out.println(exAccountname+" expected Account name is equal to actual Account name "+acAccountName);
		}else {
			System.out.println(exAccountname+" expected Account name is not equal to actual Account name "+acAccountName);
		}
		
	}
	
	public void VerifyWebSite(String exWebSite) {
		String acWebSite = util.getText(getWebsite());
		if (exWebSite.equals(acWebSite)) {
			System.out.println(exWebSite+" expected WebSite is equal to actual WebSite "+acWebSite);
		}else {
			System.out.println(exWebSite+" expected WebSite is not equal to actual WebSite "+acWebSite);
		}
		
	}
	
	public void VerifyEmail(String exEmail) {
		String acEmail = util.getText(getEmail());
		if (exEmail.equals(acEmail)) {
			System.out.println(exEmail+" expected Email is equal to actual Email "+acEmail);
		}else {
			System.out.println(exEmail+" expected Email is not equal to actual Email "+acEmail);
		}
		
	}
	
	public void VerifyPhoneNo(String exPhoneNo) {
		String acPhoneNo = util.getText(getPhoneNo());
		if (exPhoneNo.equals(acPhoneNo)) {
			System.out.println(exPhoneNo+" expected Phone number is equal to actual Phone number "+acPhoneNo);
		}else {
			System.out.println(exPhoneNo+" expected Phone number is not equal to actual Phone number "+acPhoneNo);
		}
		
	}
	
	public void VerifyBillingCity(String exBillingCity) {
		String acBillingCity = util.getText(getBillingCity());
		if (exBillingCity.equals(acBillingCity)) {
			System.out.println(exBillingCity+" expected Billing City is equal to actual Billing City "+acBillingCity);
		}else {
			System.out.println(exBillingCity+" expected Billing City is not equal to actual Billing City "+acBillingCity);
		}
		
	}
	
	public void VerifyAssignedToValue(String exAssignedToValue) {
		String acAssignedToValue = util.getText(getAssignedToValue());
		if (exAssignedToValue.equals(acAssignedToValue)) {
			System.out.println(exAssignedToValue+" expected AssignedToValue is equal to actual AssignedToValue "+acAssignedToValue);
		}else {
			System.out.println(exAssignedToValue+" expected AssignedToValue is not equal to actual AssignedToValue "+acAssignedToValue);
		}
		
	}
	
	public void verifyAccountDetail(String exAccountName, String exWebSite, String exPhoneNo, String exEmail, String exBillingCity, String exAssignToValue) {
		VerifyAccountName(exAccountName);
		VerifyWebSite(exWebSite);
		VerifyPhoneNo(exPhoneNo);
		VerifyEmail(exEmail);
		VerifyBillingCity(exBillingCity);
		VerifyAssignedToValue(exAssignToValue);
	}
	
	public void acceptAlert(String decision) {
		util.alertHandle(decision);
	}
}
