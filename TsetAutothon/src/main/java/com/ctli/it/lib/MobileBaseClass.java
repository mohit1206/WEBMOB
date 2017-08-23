package com.ctli.it.lib;

import static org.testng.Assert.fail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MobileBaseClass {
	public AndroidDriver androiddriver;
	static final long TIME_OUT = 60;

	public MobileBaseClass(AndroidDriver driver) {
		this.androiddriver = driver;
	}

	public void tap(WebElement element) {
		try {
			shouldBeVisible(element);
			TouchAction t = new TouchAction(androiddriver);
			t.tap(element).perform();
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to tap on mobile element");
		}
	}

	public void shouldBeVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(androiddriver, TIME_OUT);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			fail("element is not visible");
		}
	}

	public void verticalScroll(String text ,WebElement element) {

		// AndroidElement list = (AndroidElement)
		// androiddriver.findElement(By.name("Coming Soon"));
		// MobileElement listGroup = list.findElement(MobileBy.AndroidUIAutomator(
		// "new UiScrollable(new UiSelector()).scrollIntoView(" + "new
		// UiSelector().text(\"Mom\"));"));
       for(int i=0;i<10;i++)
       {
    	   try
    	   {
    		   androiddriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	   if(element.isDisplayed())
    	   {
    		   break;
    	   }}
    	   catch(Exception e)
    	   {
    		   Dimension size = androiddriver.manage().window().getSize();
   			int y_start = (int) (size.height * 0.60);
   			int y_end = (int) (size.height * 0.20);
   			int x = size.width / 2;
   			androiddriver.swipe(x, y_start, x, y_end, 500);
    		   e.printStackTrace();
    	   }
    	 }
    	 
       
		

	}

	public void horizontalScroll() {
		try {
			Dimension size = androiddriver.manage().window().getSize();
			int x_start = (int) (size.width * 0.60);
			int x_end = (int) (size.width * 0.30);
			int y = 130;
			androiddriver.swipe(x_start, y, x_end, y, 4000);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}

	}

	public void seekBar(WebElement element) {
		try {
			// element should be the locator of seekbar
			shouldBeVisible(element);
			// get start co-ordinate of seekbar
			int start = element.getLocation().getX();
			// Get width of seekbar
			int end = element.getSize().getWidth();
			// get location of seekbar vertically
			int y = element.getLocation().getY();

			// Select till which position you want to move the seekbar
			TouchAction action = new TouchAction(androiddriver);

			// Move it will the end
			action.press(start, y).moveTo(end, y).release().perform();

			// Move it 40%
			int moveTo = (int) (end * 0.4);
			action.press(start, y).moveTo(moveTo, y).release().perform();

		} catch (Exception e) {
			e.printStackTrace();
			fail("");

		}
	}

	// to handle app permission (allow this app to access ur conatct)
	//
	public void allowAppPermission(String xpath) {
		while (androiddriver.findElements(MobileBy.xpath(xpath)).size() > 0)

		{
			androiddriver.findElement(MobileBy.xpath(xpath)).click();
		}

	}

	public void type(WebElement element, String text) {
		try {
			shouldBeVisible(element);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to enter text");
		}
		// androiddriver.

	}

	public Boolean isEnabled(WebElement element) {
		shouldBeVisible(element);
		return element.isEnabled();
	}

	public Boolean isDisplayed(WebElement element) {
		shouldBeVisible(element);
		return element.isDisplayed();
	}

	public void webScroll() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) androiddriver;
			jse.executeScript("window.scrollBy(0,250)", "");
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to scroll the element");
		}
	}
	
	
	public void setDropdown(WebElement element, String value) {
		try {
			// waitForPageToLoad();
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			for (int i = 0; i < options.size(); i++) {
				String txt = options.get(i).getText();
				if (txt.contains(value) || txt.equalsIgnoreCase(value)) {
					select.selectByVisibleText(txt);
					return;
				}
			}
		} catch (Exception e) {
			fail("Failed setDropdown " + element.getTagName() + " with Exception: " + e.getMessage());
		}
		fail("Failed setDropdown " + element.getTagName() + " Option not found");
	}

	
	public void click(WebElement elm) {

		try {
			WebDriverWait dWait = new WebDriverWait(androiddriver, TIME_OUT);
			elm = dWait.until(ExpectedConditions.elementToBeClickable(elm));
			elm.click();
			System.out.println("clicked Done");
			//testReport.log(LogStatus.INFO,"successfully clicked on element");
		} catch (Exception e) {

			fail("Failed click " + elm.getTagName() + " with Exception: " + e.getMessage());

		}

	}

	public void jsClick(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) androiddriver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to click by java script");

		}

	}
	
	
	public void getPageScreenShot()
	{
		String contextName = androiddriver.getContext();
		androiddriver.context("NATIVE_APP");
		String imagePath="./ScreenShots"+"/"+getFormatedDateTime()+".png";
		EventFiringWebDriver edriver=new EventFiringWebDriver(androiddriver);
		try{
			File screenShot = androiddriver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File(imagePath));
			}
		catch(Exception e)
		{
			System.out.println("scrennshot couldn't capture");
		}
		
	}
	
	
	

	//System.out.println(contextName);
	//androiddriver.context("NATIVE_APP");
	//androiddriver.getScreenshotAs(arg0)
//	File screenShot = androiddriver.getScreenshotAs(OutputType.FILE);
//	FileUtils.copyFile(screenShot, new File("C:\\LastHope\\TestAutothon\\Resources\\data.png"));
//	androiddriver.context(contextName );
	
	public  String getFormatedDateTime()
	{
		SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sd.format(new Date());
	}
	
	
}
