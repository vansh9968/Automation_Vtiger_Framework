package com.eva.vtiger.pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import com.eva.vtiger.Util.WebUtil;
import com.eva.vtiger.pageOR.LeadsLandingPageOR;
import com.eva.vtiger.pages.LeadDetailsPage;

public class LeadsLandingPage extends LeadsLandingPageOR{
	
	
	private CreateLeadsPage createLeads;
	private LeadDetailsPage leadsDetail;
	private WebUtil util;
	private Map<String, String> dataMap;
	
	public LeadsLandingPage(WebUtil util, Map<String, String> dataMap) {
		this.util=util;
		this.dataMap=dataMap;
		PageFactory.initElements(util.getDriver(), this);
		
	}
	
	public CreateLeadsPage clickCreateLeadsBT() {
		util.Click(getCreateLeadsBT());
			createLeads=new CreateLeadsPage(util, dataMap);
			return createLeads;
	}
	

	public LeadDetailsPage verifySearchLeads() {
		
		util.SelectByVisibletext(getSelectInBT(), dataMap.get("dataType"));
		String lastName=dataMap.get("LastName");
		util.SendKeys(getSearchTextField(), lastName);
		util.Click(getSearchBT());
		boolean leads=false;
		WebElement le=null;
		try {
		 le=util.getDriver().findElement(By.xpath("//a[text()='Last Name']//parent::td//parent::tr//following-sibling::tr//td//a[text()='"+lastName+"']"));
	     leads= util.isDisplay(le);
		}catch(Exception e) {
			System.out.println("No found Account !");
		}
		if(leads==true) {
			util.Click(le);
		}
		leadsDetail=new LeadDetailsPage(util, dataMap);
		return leadsDetail;
	}
	
    public void verifyDeleteLeads(String decision) {
		util.Click(getDeleteBT());
		util.alertHandle(decision);
		
	}
        	
}
