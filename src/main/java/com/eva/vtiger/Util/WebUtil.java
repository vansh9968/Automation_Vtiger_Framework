package com.eva.vtiger.Util;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Property;
//import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import lombok.Getter;
import lombok.Setter;
 


@Getter
@Setter

  public class WebUtil {

private WebDriver driver;
private  Properties propObj;
private ExtentTest extTest;
private WebDriverWait wait;

private ExtentReports extendReport;
//private Logger log;

public WebUtil() {
//	log=Logger.getLogger(WebUtil.class);
}


public Properties loadProperty(String fileName) {
	
	try {
		InputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName+".properties"));
	     propObj=new Properties();
			propObj.load(fis);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return propObj;
}

	
/*************************** Reporting **********************************************/

	public String createExtendReports(String name) {
		extendReport =new ExtentReports();
		ExtentSparkReporter extSpark=new ExtentSparkReporter("Reports\\"+ name+".html");
		extendReport.attachReporter(extSpark);
		String report=extendReport.toString();
		return report;
	}
	
	public void createTest(String testcase) {
		this.extTest=extendReport.createTest(testcase);
	}
	
	public void flush() {
		extendReport.flush();
		extTest.log(Status.INFO,"flush all the report");
	}
	
	public void attachScreenshot(String path) {
		extTest.addScreenCaptureFromPath(path);
	}
	
	
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
	
	
	/*************************** Browser Related Method Exception *************************************************/
	
	/*
	 * this method will launch the browser ,it require browser name arguments -
	 * String browser name (launch for browser) String getURL (hit for url) It has
	 * return type webdriver
	 */

	public WebDriver launchBrowser(String browserName) {
		
			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				
			}else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				
			}else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
				
			}else if (browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
			}
			driver.manage().window().maximize();
			ImpliciteWait(60);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		
		return driver;
	}
	
	
	public void findOutBrokenLink(String url) {
		
		try {
			URL ur=new URL(url);
			HttpsURLConnection httpConnection=(HttpsURLConnection)ur.openConnection();
			httpConnection.setConnectTimeout(40000);
			httpConnection.connect();
			int getRspCode=httpConnection.getResponseCode();
			if(getRspCode!=200) {
				System.out.println("Broken link :- "+url);
			}else {
				System.out.println("Given link is not broken link :- "+url);
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
				
	}
	
	
	
/*************************** Data read in excel **********************************************/
    /* this method will read the data in excel 
	 * It take two argument   SheetName  -- String
	 *                        dataID    -- String  
	 */
	
	public Map<String, String> getTestData(String sheetName, String dataID) {
		Map<String, String> dataMap = new HashMap<String, String>();
		InputStream ip;
		int dataRownumber = 0;
		try {
			ip = new FileInputStream("src\\test\\resources\\data\\Vtiger Data.xlsx");
			Workbook wBook = new XSSFWorkbook(ip);
			Sheet sheetobj = wBook.getSheet(sheetName);
			int rowCount = sheetobj.getLastRowNum();
			for (int i = 0; i <= rowCount; i++) {
				Row row = sheetobj.getRow(i);
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				String dataIDValue = cell.getStringCellValue();
				if (dataIDValue.equalsIgnoreCase(dataID)) {
					dataRownumber = i;
				}
			}
			Row firstRow = sheetobj.getRow(0);
			Row rowobj = sheetobj.getRow(dataRownumber);
			for (int j = 1; j <= firstRow.getLastCellNum() - 1; j++) {
				Cell cellKey = firstRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				Cell cellValue = rowobj.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				String columnKey = cellKey.getStringCellValue();
				String columnValue = cellValue.getStringCellValue();
				
				dataMap.put(columnKey, columnValue);
//				System.out.println(columnKey + " -> " + columnValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataMap;
	}
		
//	public List<Map<String , String>> getTestData(String sheetName) {
//		
//		List<Map<String, String>> listMap=new ArrayList<Map<String, String>>();
//
//		InputStream ip;
//		int dataRownumber = 0;
//		try {
//			ip = new FileInputStream("src\\test\\resources\\data\\Vtiger Data.xlsx");
//			Workbook wBook = new XSSFWorkbook(ip);
//			Sheet sheetobj = wBook.getSheet(sheetName);
//			int rowCount = sheetobj.getLastRowNum();
//		
//			
//			
//		for (int j = 0; j < rowCount; j++) {
//			Map<String, String> dataMap = new HashMap<String, String>();
//			Row dataRow=sheetobj.getRow(j);
//			Row firstRow=sheetobj.getRow(0);
//			for (int i = 1; i <= firstRow.getLastCellNum() - 1; i++) {
//				Cell cellKey = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//				Cell cellValue = dataRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//				String columnKey = cellKey.getStringCellValue();
//				String columnValue = cellValue.getStringCellValue();
//				
//				dataMap.put(columnKey, columnValue);
////				System.out.println(columnKey + " -> " + columnValue);
//			}
//			listMap.add(dataMap);
//		}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return listMap;
//	}

	/************************************** Screen Shot Related Method **********************************************/

	/*
	 * this method will click takeScreenShotAtEndOfTest this method is use in every
	 * generic methods for generate report It has no return type
	 */
	
	public  String takeScreenShot(String name) {
		File toFile ;
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty(name);
			 toFile= new File(currentDir + "/screenshorts/" + System.currentTimeMillis() + ".png");
			 try{
			FileUtils.copyFile(scrFile,toFile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return toFile.getAbsolutePath();
	}
	
	

	/************************* WebDriver Current URL , Title and findElement Related Method **************************/

	public boolean isDisplay(WebElement display) {
		boolean result = false;
		try {
			 result=display.isDisplayed();
			 extTest.log(Status.INFO, result+" element is display");
		}catch(Exception e) {
			 extTest.log(Status.INFO, result+" element is not display");
		}
		return result;
	}
	
	
	/*
	 * this method will hit the URL in browser , it require URL It has not return
	 * type
	 */
	public void hitURL(String URL) {
	driver.get(URL);
//	extTest.log(Status.PASS, "hit url successfully");
	
	}

	/*
	 * this method will close current browser
	 */
	public void Close() {
		try {
		driver.close();
		extTest.log(Status.PASS, "close successfully");
		}catch(Exception e){
			extTest.log(Status.FAIL, "close not successfully");
		}
	}

	/*
	 * this method will terminate intance of browser
	 */
	public void Quit() {
		try {
		driver.quit();
		extTest.log(Status.PASS, "quit successfully");
		}catch(Exception e){
			extTest.log(Status.FAIL, "quit not successfully");
		}
	}

	/*
	 * this method will fetch the current URL on the page It has return type of
	 * String
	 */

	public String GetCurrentURL() {
		String currentURL = driver.getCurrentUrl();
		extTest.log(Status.INFO, currentURL+" It is a current url");
		return currentURL;
	}

	/*
	 * this method will fetch the title on the page It has return type of String
	 */

	public String GetTitle() {
		String title = driver.getTitle();
		extTest.log(Status.INFO, title+" It is a title of page");
		return title;
	}

	/*
	 * this method will clear in textbox any value in there , it require locator It
	 * has no return type
	 */

	public void ClearInTextbox(WebElement locator) {
		try {
      locator.clear();
		extTest.log(Status.PASS," clear data in textbox");
		}catch (Exception e) {
			extTest.log(Status.FAIL," clear data is not in textbox");

		}
	}

	/*
	 * this method will find elements on page it use in every generic method
	 * argument - WebElement locator It has return - webelement
	 */
//	WebElement find;
//
//	public WebElement FindElement(WebElement locator) {
//		find = null;
//		try {
//			find = driver.findElement(By.xpath(locator));
//		} catch (InvalidSelectorException e) {
//			System.err.println("locator syntax is incorrect fail");
//			takeScreenShotAtEndOfTest();
//			throw e;
//		} catch (NoSuchElementException e) {
//			System.out.println("no such element on page fail ");
//			throw e;
//		} catch (Exception e) {
//			System.out.println("Exception occured which is not handle fail");
//			takeScreenShotAtEndOfTest();
//			throw e;
//		}
//		return find;
//	}

//	public List<WebElement> FindElements(WebElement locator) {
//		List<WebElement> Listfind = null;
//		try {
//			Listfind = driver.findElements(By.xpath(locator));
//		} catch (InvalidSelectorException e) {
//			System.err.println("locator syntax is incorrect fail");
//			takeScreenShotAtEndOfTest();
//			throw e;
//		} catch (NoSuchElementException e) {
//			System.out.println("no such element on page fail ");
//			throw e;
//		} catch (Exception e) {
//			System.out.println("Exception occured which is not handle fail");
//			takeScreenShotAtEndOfTest();
//			throw e;
//		}
//		return Listfind;
//	}

	/*************************** WebElement Related Methods Exception ************************/

	/*
	 * this method will click on the element in page arguments - WebElement locator
	 * String element name It has no return type
	 */

	public void Click(WebElement locator){
		try {
			locator.click();
			extTest.log(Status.PASS," clicked on the button");
		} catch (StaleElementReferenceException e) {
			locator.click();
			extTest.log(Status.WARNING," clicked on the button again");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jase = (JavascriptExecutor) driver;
			jase.executeScript("arguments[0].click()", locator);
			extTest.log(Status.WARNING,"element is hidden, trying javascript");
		} catch (Exception e) {
			extTest.log(Status.FAIL,"exception occured which is not handled");
			throw e;

		}
	}

	/*
	 * this method will put value on the textbox in page arguemnts - WebElement locator
	 * String value String element name It has no return type
	 */

	public void SendKeys(WebElement accountName, String value ) {

		try {
			ClearInTextbox(accountName);
			accountName.sendKeys(value);
			extTest.log(Status.PASS," filled in the textbox");
		} catch (ElementNotInteractableException e) {
			JavaScriptSendKeys(accountName, value);
			extTest.log(Status.WARNING," filled in the textbox trying javascript");
			} catch (Exception e) {
				extTest.log(Status.WARNING,"exception occured which is not handled");
		}
	}

	/*
	 * this method will fetch the value of ribute arguments - WebElement locator
	 * String ribute  It has return type which is String
	 * ributeValue
	 */

	public String getAttributeValue(WebElement locator, String ribute ) {
		String ributeValue = locator.getAttribute(ribute);
		extTest.log(Status.INFO, ributeValue+" get ribute value of the element");
		return ributeValue;

	}

	public boolean ElementIsSelected(WebElement locator) {
		boolean selected = locator.isSelected();
		if(selected==true) {
			extTest.log(Status.INFO, selected+" It is selected in radio button");
		}else {
			extTest.log(Status.INFO, selected+" It is selected in radio button");
		}	
		return selected;
	}

	/***************************** Iframe Related Methods Exception ********************************************************/

	/*
	 * this methos will for iframe arguments - WebElement locator ,It has no return type
	 */

	public void SwitchToFrameByWebElement(WebElement locator) throws Exception {
		try {
			driver.switchTo().frame(locator);	
			extTest.log(Status.PASS, " It is switch in another frame");
		} catch (Exception e) {
			extTest.log(Status.FAIL, " It is not frame");
			throw e;
		}

	}

	/*
	 * this methos will for iframe arguments - int frameNumber ,It has no return
	 * type Note - Indexing starts from 0
	 */

	public void SwitchToFrameByIndex(int frameNumber) throws Exception {

		try {
			driver.switchTo().frame(frameNumber);
			extTest.log(Status.PASS, " It is switch in another frame with the help of index");
		} catch (NoSuchFrameException e) {
			extTest.log(Status.FAIL, " No such frame in page");
		} catch (Exception e) {
			extTest.log(Status.FAIL, " No such frame in page");
			throw e;
		}
	}

	/*
	 * this methos will focused parent frame and no arguments , It has no return
	 * type
	 */

	public void ParentFrame() {

		driver.switchTo().parentFrame();
		extTest.log(Status.PASS, "Focused on parent frame ");

	}

	/*
	 * this methos will focused default content frame and no arguments , It has no
	 * return type
	 */

	public void DefaultContent() {

		driver.switchTo().defaultContent();
		extTest.log(Status.PASS, "Focused on default content ");

	}

	/************************** Select class Related Methods ***************************/

	/*
	 * this method will select by visible text in the dropdown in the page argument
	 * - WebElement locator String text  It has no return type
	 */

	public void SelectByVisibletext(WebElement locator, String text ) {
		try {
			Select dropdown = new Select(locator);
			dropdown.selectByVisibleText(text);
			sleep(2000);
			extTest.log(Status.INFO,text + " is select from Dropdown  ");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL,text + " Exception occured which is not handle  dropdown  ");
			throw e;

		}

	}

	/*
	 * this method will select by value in the dropdown in the page argument -
	 * WebElement locator String text  It has no return type
	 */

	public void SelectByvalue(WebElement locator, String value ) {
		Select dropdown = new Select(locator);
		try {
			dropdown.selectByValue(value);
			extTest.log(Status.INFO,dropdown + " is selected from dropdown ");

		} catch (Exception e) {
			extTest.log(Status.INFO,dropdown + " is not selected from dropdown ");

			e.printStackTrace();

		}
	}

	/*
	 * this method will select by index in the dropdown in the page argument -
	 * WebElement locator int index  It has no return type
	 */

	public void SelectByIndex(WebElement locator, int index ) {
		Select dropdown = new Select(locator);
		try {
			dropdown.selectByIndex(index);
			extTest.log(Status.INFO,dropdown + " is selected from dropdown ");

		} catch (Exception e) {
			extTest.log(Status.FAIL,dropdown + " is not selected from dropdown ");

			e.printStackTrace();

		}
	}
	
	/*
	 * this method will fetch first selected option which on page arguments - String
	 * locator  (for using reporting) It has return type is
	 * webelement
	 */

	public String GetFirstSelectedOption(WebElement locator ) {
		String firstselectedOption = null;
		Select dropdown = new Select(locator);
		try {
			WebElement getFirstselected = dropdown.getFirstSelectedOption();
			firstselectedOption = getFirstselected.getText();
			extTest.log(Status.INFO,firstselectedOption + " is selected from dropdown  ");

		} catch (Exception e) {
			
			extTest.log(Status.FAIL, firstselectedOption + " is not selected from dropdown  ");
			e.printStackTrace();
		}
		return firstselectedOption;
	}

	/*
	 * this method will fetch the alltext from dropdown ,it require locator It has
	 * return type is list<String>
	 * 
	 */

	public List<String> AllOptionInDropDown(WebElement locator) throws Exception {
		Select select = new Select(locator);
		List<String> listOptionText;
		try {
			listOptionText = new ArrayList<String>();
			List<WebElement> list = select.getOptions();
			for (WebElement listWebElement : list) {
				String optionText = listWebElement.getText();
				listOptionText.add(optionText);
			}
			
		} catch (Exception e) {

			throw e;
		}
		return listOptionText;
	}

	/******************************* Actions class Related Methods ****************************************/

	/*
	 * this method will click on the element in page arguemnts - WebElement locator ,
	 *  String value String element name It has no return type
	 */

	public void ActionsClick(WebElement locator ) throws Exception {
		Actions act = new Actions(driver);
		try {
			act.click(locator).build().perform();
			extTest.log(Status.PASS," cliced on the button by action class");
		} catch (StaleElementReferenceException e) {
			locator.click();
			extTest.log(Status.FAIL," cliced on the button by action class");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL," Exception occured which is not handle");
			throw e;
		}

	}

	/*
	 * this method will put value on the textbox in page arguemnts - WebElement locator
	 * String value String element name It has no return type
	 */

	public void ActionsSendKeys(WebElement locator, String value ) throws Exception {

		Actions act = new Actions(driver);
		try {
			act.sendKeys(locator, value).build().perform();
			extTest.log(Status.PASS,"filled in the textbox");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "'", locator);
			extTest.log(Status.WARNING," Element is hidden by trying javaScript");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL," Exception occured which is not handle ");
			throw e;
		}

	}

	/*
	 * this method will mouse over on element , button , link , radio button etc
	 * arguments - WebElement locator It has no return type
	 */

	public void ActionsMouseOver(WebElement locator) {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(locator).build().perform();
			extTest.log(Status.PASS," Mouse over is at the target locator ");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL," Mouse over isn't at the target locator ");

			throw e;
		}
	}

	/*
	 * this method will Right click on like button ,element ,link ,etc arguments -
	 * WebElement locator It has no return type
	 */

	public void ActionsContextClick(WebElement locator) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.contextClick(locator).build().perform();
			extTest.log(Status.PASS," Right click perform at the target locator ");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL," Right click not perform at the target locator ");
			throw e;
		}
	}

	/*
	 * this method will double click on like button ,element ,link ,etc arguments -
	 * WebElement locator It has no return type
	 */

	public void ActionsDoubleClick(WebElement locator) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.doubleClick(locator).build().perform();
			extTest.log(Status.PASS," Double click perform at the target locator ");
		} catch (Exception e) {
			
			extTest.log(Status.FAIL," Double click not perform at the target locator ");
			throw e;
		}
	}

	/*
	 * this method will use drag and drop of the element on page arguments - String
	 * locatorfrom ( this argument drag the element from as your location) String
	 * locatorTo (this argument drop the element as your location) It has no return
	 * type
	 */

	public void ActionsDragAndDrop(WebElement locatorfrom, WebElement locatorTo) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.dragAndDrop(locatorfrom, locatorTo).build().perform();
			extTest.log(Status.PASS,"Drag and drop perform at the target locator pass");
			} catch (Exception e) {
			
				extTest.log(Status.FAIL,"Drag and drop not perform at the target locator Fail");
			throw e;
		}
	}
	

	/*
	 * this method will scroll up and down of the page arguments - WebElement locator
	 * int startScroll ( where will start from scroll) int endScroll ( where will
	 * end from scroll) It has no return type
	 */

	public void ActionsScrollUpAndDown(int startScroll, int endScroll) throws Exception {
		try {
			Actions act = new Actions(driver);
			// WebElement find=FindElement(driver, location);
			act.scrollByAmount(startScroll, endScroll).build().perform();
			System.out.println("Scroll up & down of the age pass");
		} catch (Exception e) {
			System.err.println("No Scroll up & down of the age fail");
//			takeScreenShotAtEndOfTest();
			throw e;
		}
	}

	/******************************* Switch Window Related Methods ***********************************************/

	/*
	 * this method will switch require window arguments - String windowURL It has no
	 * return type
	 */

	public String windowHandle(String windowURL_title) {
		String parentWindow = driver.getWindowHandle();
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			for (String data : handlevalue) {
				driver.switchTo().window(data);
				String title = GetTitle();
				String currentURL = GetCurrentURL();

				if (currentURL.contains(windowURL_title) || title.contains(windowURL_title)) {
					extTest.log(Status.PASS,"Reached require window");
					break;
				}
			}
		} catch (NoSuchWindowException e) {
			
			extTest.log(Status.FAIL,"No reached require window fail");
			e.printStackTrace();
		}
		return parentWindow;
	}
	
	
	public void switchToWindow(String windowURL_Title) {
		try {
			driver.switchTo().window(windowURL_Title);
		}catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	/*********************************** Alert Related Methods *************************************/

	/*
	 * this method will handle the alert on the page ,it require String decision
	 * which you want It has no return type
	 */

	public void alertHandle(String decision) {

		try {
			Alert alert = driver.switchTo().alert();
			if (decision.equalsIgnoreCase("ok")) {
				alert.accept();
				extTest.log(Status.PASS,"Handle alert");
			}
			else if (decision.equalsIgnoreCase("cancel")) {
				alert.dismiss();
				extTest.log(Status.PASS,"Handle alert");
			}

		} catch (Exception e) {
			extTest.log(Status.FAIL,"No handle alert");
			throw e;
		}
	}

/********************************* Location , size and gettext Related Method ******************************/
	/*
	 * this method will fetch location of element in page arguments - WebElement locator
	 *  , it has return type for Y point
	 */

	public int GetElementlocationForPointX(WebElement locator ) {
		Point point = locator.getLocation();

		int pointX = point.getX();
		return pointX;
	}

	/*
	 * this method will fetch location of element in page arguments - WebElement locator
	 *  , it has return type for X point
	 */

	public int GetElementlocationForPointY(WebElement locator ) {
		Point point = locator.getLocation();

		int pointY = point.getY();
		return pointY;
	}

	/*
	 * this method will fetch size of element in the page arguments - WebElement locator
	 *  , It has return type for height
	 */

	public int GetElementSizeForHeight(WebElement locator ) {
		Dimension dimension = locator.getSize();

		int height = dimension.getHeight();
		return height;

	}

	/*
	 * this method will fetch size of element in the page arguments - WebElement locator
	 *  , It has return type for width
	 */

	public int GetElementSizeForWidth(WebElement locator ) {
		Dimension dimension = locator.getSize();

		int width = dimension.getWidth();
		return width;

	}

	/*
	 * this method will fetch the text on page arguments - WebElement locator String
	 * element It has no return type
	 */

	public String getText(WebElement locator) {
		String gettext = "";
			gettext = locator.getText();
		
		return gettext;
	}

	/***************************** Wait Related method Exception *********************************************************/

	
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
			extTest.log(Status.INFO,millis+" minute wait for element");
		} catch (InterruptedException e) {
			extTest.log(Status.FAIL,millis+" minute wait for element but it is not present in page");
			e.printStackTrace();
		}
	}
	
	
	
	public void ImpliciteWait(int durationTime) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(durationTime));
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * this method will wait for elemenet is visible arguments - WebElement locator int
	 * seconds It has no return type
	 */

	public boolean WaitForElementVisiblity(String locator, int seconds) throws Exception {
		boolean findStatus = true;
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			
			findStatus = false;
			throw e;
		}
		return findStatus;
	}

	/*
	 * this method will wait for elemenet is invisible arguments - WebElement locator
	 * int seconds It has return type is boolean
	 */

	public boolean WaitForElementInVisiblity(String locator, int seconds) throws Exception {
		boolean findStatus = true;
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))));
		} catch (Exception e) {
			
			findStatus = false;
			throw e;
		}
		return findStatus;
	}

	/*
	 * this method will wait for elemenet is enabled arguments - WebElement locator int
	 * seconds It has no return type
	 */

	public void WaitForElementEnabled(String locator, int seconds) throws Exception {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		} catch (Exception e) {
			
			throw e;
		}
	}

	/*
	 * this method will wait for elemenet is disable, arguments - WebElement locator int
	 * seconds It has no return type
	 */

	public void WaitForElementDisabled(String locator, int seconds) throws Exception {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath(locator))));
		} catch (Exception e) {
			
			throw e;
		}
	}

	/*
	 * this method will wait for elemenet is enabled arguments - WebElement locator ,
	 * int seconds String textToWait it has no return type
	 */

	public void WaitForTextToBePresentInElementLocated(String locator, int seconds, String textToWait) throws Exception {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(locator), textToWait));
		} catch (Exception e) {
			
			throw e;
		}
	}

	/*
	 * this method will wait for elemenet is presence in html dom , arguments -
	 * WebElement locator , int seconds it has no return type
	 */

	public void WaitFoElementPresenceInHtmlDOM(String locator, int seconds) throws Exception {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {

			
			throw e;
		}
	}

/****************************** JavaScript Related Methods ******************************************************/

	/*
	 * this method will click on element in page by javaScript , it require will
	 * locator on which to do it has no return type
	 */
	public void JavaScriptClick(WebElement locator) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", locator);
			extTest.log(Status.PASS, "click on button from javascript");
		} catch (Exception e) {
			extTest.log(Status.FAIL,"No click By javaScript");
		}
	}

	/*
	 * this method will sendkeys on textbox in page by javaScript , it require will
	 * locator and value on which to send it has no return type
	 */

	public void JavaScriptSendKeys(WebElement locator, String value) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "'", locator);
			extTest.log(Status.PASS, "fill in text box from javascript");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "No sendkeys By javaScript");
		}
	}

	/*
	 * this method will scroll in page by javaScript arguments - WebElement locator int
	 * horizontalAmount ( how many to do horizontalAmount ) int VerticalAmount ( how
	 * many to do VerticalAmount ) it has no return type
	 */

	public void JavaScriptByScroll(WebElement locator, int horizontalAmount, int VerticalAmount) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + horizontalAmount + "," + "" + VerticalAmount + ")");

	}

	/*
	 * this method will scroll in page by javaScript arguments - WebElement locator int
	 * horizontalAmount ( how many to do horizontalAmount ) it has no return type
	 */

	public void JavaScriptByFullScroll(WebElement locator, int horizontalAmount) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(" + horizontalAmount + "," + "document.body.scrollHeight)");

	}

	/************************************ validate Related Methods Exception *****************************************/

	/*
	 * Note - this method will applicable for Account creating time arguments -
	 * WebElement locatorAccountName String expectedValueOfAccountName String
	 * locatorAssignedTo String expectedValueOfAssignedTo
	 */

//	public void validateCreateAccount(WebElement locatorAccountName, String expectedValueOfAccountName,
//			WebElement locatorAssignedTo, String expectedValueOfAssignedTo) throws Exception {
//		String actualTextOfAccountName = getText(locatorAccountName, expectedValueOfAccountName);
//		String actualTextAssignedTo = getText(locatorAssignedTo, expectedValueOfAssignedTo);
//		if (actualTextOfAccountName.equalsIgnoreCase(expectedValueOfAccountName)
//				&& actualTextAssignedTo.equalsIgnoreCase(expectedValueOfAssignedTo)) {
//			System.out.println(" validation create account Passed. Where information  Account Name - "
//					+ actualTextOfAccountName + " , Assigned To - " + actualTextAssignedTo);
//		} else {
//			System.out.println(" validation not create account Failed. Where information  Account Name - "
//					+ actualTextOfAccountName + " , Assigned To - " + actualTextAssignedTo);
//		}
//
//	}

	/*
	 * Note - this method will applicable for leads creating time arguments - String
	 * locatorLeadsLastName String expectedValueOfLeadsLastName String
	 * locatorCompany String expectedValueOfCompany
	 */

//	public void validateCreateLeads(WebElement locatorLeadsLastName, String expectedValueOfLeadsLastName,
//			WebElement locatorCompany, String expectedValueOfCompany) throws Exception {
//		String actualTextOfLeadsLastName = getText(locatorLeadsLastName, expectedValueOfLeadsLastName);
//		String actualTextOfCompany = getText(locatorCompany, expectedValueOfCompany);
//		if (actualTextOfLeadsLastName.equalsIgnoreCase(expectedValueOfLeadsLastName)
//				&& actualTextOfCompany.equalsIgnoreCase(expectedValueOfCompany)) {
//			System.out.println(" validation create leads Passed. Where information  Leads lastName - "
//					+ actualTextOfLeadsLastName + " , Company - " + actualTextOfCompany);
//		} else {
//			System.out.println(" validation not create leads Failed. Where information  Leads lastName- "
//					+ actualTextOfLeadsLastName + " , Company - " + actualTextOfCompany);
//		}
//	}

	public void validateElementByGetText(WebElement locator, String expectedValue ) {
		String actualValue = getText(locator);
		if (actualValue.equalsIgnoreCase(expectedValue)) {
			System.out.println(" validation Passed. Where Actual-" + actualValue + ", Expected-" + expectedValue);
		} else {
			System.out.println(" validation Failed. Where Actual-" + actualValue + ", Expected-" + expectedValue);
		}
	}

	public void validateEnabled(WebElement locator, boolean expectedValue) {
		boolean actualvalue = locator.isEnabled();  
		if (actualvalue == expectedValue) {
			System.out.println(" validation It is enable . Where Actual-" + actualvalue + ", Expected-" + expectedValue);
		} else {
			System.out.println(" validation It is displayed . Where Actual-" + actualvalue + ", Expected-" + expectedValue);
		}
	}
	
	public void validateDisabled(WebElement locator, boolean expectedValue) {
		boolean actualvalue = locator.isDisplayed();
		if (actualvalue == expectedValue) {
			System.out.println(" validation It is enable . Where Actual-" + actualvalue + ", Expected-" + expectedValue);
		} else {
			System.out.println(" validation It is displayed . Where Actual-" + actualvalue + ", Expected-" + expectedValue);
		}
	}
	

	public void validateCheckboxIschecked(WebElement locator, boolean expectedCheckbox) {
		boolean actualCheckbox = locator.isSelected();
		if (actualCheckbox == expectedCheckbox) {
			System.out.println(" validation checkbox is checked Passed. Where Actual-" + actualCheckbox + ", Expected-"
					+ expectedCheckbox);
		} else {
			System.out.println(" validation checkbox is not checked Failed. Where Actual-" + actualCheckbox
					+ ", Expected-" + expectedCheckbox);
		}
	}

	public void validatePageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle)) {
			System.out.println(" validation Passed. Where Actual-" + actualTitle + ", Expected-" + expectedTitle);
		} else {
			System.out.println(" validation Failed. Where Actual-" + actualTitle + ", Expected-" + expectedTitle);
		}

	}

	public void validatePageURL(String expectedURL) {
		String actualTitle = driver.getCurrentUrl();
		if (actualTitle.equals(expectedURL)) {
			System.out.println(" validation Passed. Where Actual-" + actualTitle + ", Expected-" + expectedURL);
		} else {
			System.out.println(" validation Failed. Where Actual-" + actualTitle + ", Expected-" + expectedURL);
		}

	}

	public void validateSelectedTextFromDropdown() {

	}

	public void validateributeValue() {

	}

	public void ValidateAlert(String expectedAlertText) {
		Alert al = driver.switchTo().alert();
		String actualAlertText = al.getText();
		if (actualAlertText.equals(expectedAlertText)) {
			System.out.println("Expected alert text is corrct Pass ");
		} else {
			System.out.println("Expected alert text is incorrct Fail ");
		}
	}

	public void validateGetFirstSelectedOption(WebElement locator, String expectedFirstSelectValue ) {
		Select dropdown = new Select(locator);
		String actualFirstselected = dropdown.getFirstSelectedOption().getText();
		if (actualFirstselected.equals(expectedFirstSelectValue)) {
			System.out.println(actualFirstselected + " is first selected from dropdown pass " );
		} else {
			System.out.println(actualFirstselected + " is not first selected from dropdown fail ");
		}

	}

	public void validateGetributeValue(WebElement locator, String ributeName, String expectedributeValue
			) {
		String actualributeValue = getAttributeValue(locator, ributeName);

		if (actualributeValue.equals(expectedributeValue)) {
			System.out.println(actualributeValue + " actual ribute value in " + " Pass");
		} else {
			System.out.println(actualributeValue + " actual ribute value in " +  " Failed");
		}

	}





	

}
