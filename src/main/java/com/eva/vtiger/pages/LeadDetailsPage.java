package com.eva.vtiger.pages;

import java.util.Map;

import org.apache.logging.log4j.util.PropertySource.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.LeadsDetailPageOR;


public class LeadDetailsPage extends LeadsDetailPageOR{

	
	public  WebUtil util;
	public  Map<String, String> dataMap;
	public LeadDetailsPage(WebUtil util, Map<String, String> dataMap){
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
	
	public void acceptAlert(String decision) {
		util.alertHandle(decision);
	}
	
	
	public void VerifyFirstName(String exFirstname) {
		String acFirstName = util.getText(getFirstName());
		if (exFirstname.equals(acFirstName)) {
			System.out.println(exFirstname+" expected First name is equal to actual First name "+acFirstName);
		}else {
			System.out.println(exFirstname+" expected First name is not equal to actual First name "+acFirstName);
		}
		
	}
	
	public void VerifyLastName(String exLastname) {
		String acLastName = util.getText(getLastName());
		if (exLastname.equals(acLastName)) {
			System.out.println(exLastname+" expected last name is equal to actual last name "+acLastName);
		}else {
			System.out.println(exLastname+" expected last name is not equal to actual last name "+acLastName);
		}
		
	}
	
	public void VerifyCompany(String exCompany) {
		String acCompany = util.getText(getCompany());
		if (exCompany.equals(acCompany)) {
			System.out.println(exCompany+" expected Company is equal to actual Company "+acCompany);
		}else {
			System.out.println(exCompany+" expected Company is not equal to actual Company "+acCompany);
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
	
	public void VerifyEmail(String exEmail) {
		String acEmail = util.getText(getEmail());
		if (exEmail.equals(acEmail)) {
			System.out.println(exEmail+" expected Phone number is equal to actual Phone number "+acEmail);
		}else {
			System.out.println(exEmail+" expected Phone number is not equal to actual Phone number "+acEmail);
		}
		
	}
	
	public void verifyWebSite(String exWebSite) {
		String acWebSite=util.getText(getWebSite());
		if(exWebSite.equals(acWebSite)) {
			System.out.println(exWebSite+" expected WebSite is equal to actual WebSite "+acWebSite);
		}else {
			System.out.println(exWebSite+" expected WebSite is not equal to actual WebSite "+acWebSite);
		}
	}
	
	public void verifyAssignToValue(String exAssignToValue) {
		String acAssignToValue=util.getText(getAssignToValue());
		if(exAssignToValue.equals(acAssignToValue)) {
			System.out.println(exAssignToValue+" expected AssignToValue is equal to actual AssignToValue "+acAssignToValue);
		}else {
			System.out.println(exAssignToValue+" expected AssignToValue is not equal to actual AssignToValue "+acAssignToValue);
		}
	}
	
	public void verifyLeadDetails(String exFirstName, String exLastName, String exCompany, String exPhoneNo, String exEmail, String exWebSite, String exAssignToValue) {
		VerifyFirstName(exFirstName);
		VerifyLastName(exLastName);
		VerifyCompany(exCompany);
		VerifyPhoneNo(exPhoneNo);
		VerifyEmail(exEmail);
		verifyWebSite(exWebSite);
		verifyAssignToValue(exAssignToValue);
	}
}
