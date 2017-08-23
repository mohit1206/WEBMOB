package com.ctli.it.lib;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public WebDriver driver;
	public ExtentTest testReport;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
	}
	
	public  BaseClass(WebDriver driver,ExtentTest testReport)
	{
		this.driver=driver;
		this.testReport=testReport;
	}

	static final long TIME_OUT = 60;

	public void mouseMoveClick(WebElement ele) {
		try {
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			ele = dWait.until(ExpectedConditions.visibilityOf(ele));
			Actions action = new Actions(driver);
			action.moveToElement(ele).click().build().perform();
		} catch (Exception e) {
			fail("Failed mouseMoveClick() with Exception: " + e.getMessage());
		}
	}

	public void mouseHover(WebElement ele) {
		try {
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			ele = dWait.until(ExpectedConditions.visibilityOf(ele));
			Actions action = new Actions(driver);
			action.moveToElement(ele).perform();
			// action.moveToElement(ele).build().perform();
		} catch (Exception e) {
			fail("Failed mouseHover() with Exception: " + e.getMessage());
		}
	}

	public void mouseSelect(WebElement hoverElm, WebElement clickElm) {
		try {
			mouseHover(hoverElm);
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			clickElm = dWait.until(ExpectedConditions.visibilityOf(clickElm));
			Actions action = new Actions(driver);
			action.moveToElement(clickElm).click(clickElm).build().perform();
		} catch (Exception e) {
			fail("Failed mouseSelect() with Exception: " + e.getMessage());
		}
	}

	public boolean isMenuOptionAvailable(WebElement hoverElm, WebElement clickElm) {
		try {
			mouseHover(hoverElm);
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			clickElm = dWait.until(ExpectedConditions.visibilityOf(clickElm));
			Thread.sleep(3000);
			return !(clickElm.getAttribute("class").contains("disabledLink"));
		} catch (Exception e) {
			// fail("Failed mouseSelect() with Exception: " + e.getMessage());
			return false;
		}
	}

	public void mouseSelectWithRetry(WebElement hoverElm, WebElement clickElm) {
		int attempts = 0;
		boolean success = false;
		while (attempts < 3) {
			try {
				// System.out.print("attempts = " + attempts + "\n");
				mouseHover(hoverElm);
				WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
				Actions action = new Actions(driver);
				clickElm = dWait.until(ExpectedConditions.visibilityOf(clickElm));
				action.moveToElement(clickElm).click(clickElm).perform();
				success = true;
			} catch (StaleElementReferenceException sere) {
				System.out.print("StaleElementReferenceException\n");
			} catch (NoSuchElementException nsee) {
				System.out.print("NoSuchElementException\n");
			} catch (Exception e) {
				fail("Failed mouseSelectWithRetry() with Exception: " + e.getMessage());
			}
			if (success)
				break;
			attempts++;
		}
	}

	public void mouseSelectWithRetry(WebElement hoverElm1, WebElement hoverElm2, String clickElmName) {
		int attempts = 0;
		boolean success = false;
		while (attempts < 3) {
			try {
				// System.out.print("attempts = " + attempts + "\n");
				mouseHover(hoverElm1);
				WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
				Actions action = new Actions(driver);
				hoverElm2 = dWait.until(ExpectedConditions.visibilityOf(hoverElm2));
				action.moveToElement(hoverElm2);
				mouseHover(hoverElm2);
				dWait = new WebDriverWait(driver, TIME_OUT);
				WebElement clickElm = dWait
						.until(ExpectedConditions.visibilityOfElementLocated(ByName.name(clickElmName)));
				action.moveToElement(clickElm).click(clickElm).perform();
				success = true;
			} catch (StaleElementReferenceException sere) {
				System.out.print("StaleElementReferenceException\n");
			} catch (NoSuchElementException nsee) {
				System.out.print("NoSuchElementException\n");
			} catch (Exception e) {
				fail("Failed mouseSelectWithRetry() with Exception: " + e.getMessage());
			}
			if (success)
				break;
			attempts++;
		}
	}

	public void click(WebElement elm) {

		try {
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			elm = dWait.until(ExpectedConditions.elementToBeClickable(elm));
			elm.click();
			System.out.println("clicked Done");
			testReport.log(LogStatus.INFO,"successfully clicked on element");
		} catch (Exception e) {

			fail("Failed click " + elm.getTagName() + " with Exception: " + e.getMessage());

		}

	}

	public boolean isVisible(WebElement element) {
		try {
			// waitForPageToLoad();
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			dWait.until(ExpectedConditions.visibilityOf(element));
			//testReport.log(LogStatus.INFO,"element is visible");
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	public boolean isSelected(WebElement element) {
		try {
			// waitForPageToLoad();
			WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
			element = dWait.until(ExpectedConditions.visibilityOf(element));
			
			return element.isSelected();
			
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSelected(String locator) {
		// waitForPageToLoad();
		WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
		return dWait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator)));
	}

	/**
	 * Type something into an input field. WebDriver doesn't normally clear these *
	 * before typing, so this method does that first.
	 */

	public void type(WebElement element, String text) {
		// waitForPageToLoad();
		WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
		element = dWait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	public void setDropdown(String locator, String value) {
		try {
			// waitForPageToLoad();
			WebElement element = driver.findElement(By.xpath(locator));
			Select select = new Select(element);
			select.selectByVisibleText(value);
		} catch (Exception e) {
			fail("Failed setDropdown " + locator + " with Exception: " + e.getMessage());
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

	public boolean verifyElementInDropdown(WebElement element, String value) {
		try {
			// waitForPageToLoad();
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			for (int i = 0; i < options.size(); i++) {
				String txt = options.get(i).getText();
				if (txt.contains(value) || txt.equalsIgnoreCase(value))
					return true;

			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public Boolean isEnabled(WebElement element) {
		// waitForPageToLoad();
		WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
		element = dWait.until(ExpectedConditions.visibilityOf(element));
		return element.isEnabled();
	}

	public Boolean isDisplayed(WebElement element) {
		WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
		element = dWait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public void mouseHoverWithJs(WebElement hoverElm, WebElement clickElm) {
		try {
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, hoverElm);
			click(clickElm);

		} catch (StaleElementReferenceException sere) {
			System.out.print("StaleElementReferenceException\n");
		} catch (NoSuchElementException nsee) {
			System.out.print("NoSuchElementException\n");
		} catch (Exception e) {
			fail("Failed java script executor with Exception: " + e.getMessage());
		}
	}

	// AB67816
	public void mouseSelectWithRetry(WebElement hoverElm1, WebElement hoverElm2, WebElement hoverElm3,
			String clickElmName) {
		int attempts = 0;
		boolean success = false;
		while (attempts < 3) {
			try {
				// System.out.print("attempts = " + attempts + "\n");
				mouseHover(hoverElm1);
				WebDriverWait dWait = new WebDriverWait(driver, TIME_OUT);
				Actions action = new Actions(driver);
				hoverElm2 = dWait.until(ExpectedConditions.visibilityOf(hoverElm2));
				action.moveToElement(hoverElm2);

				mouseHover(hoverElm2);
				dWait = new WebDriverWait(driver, TIME_OUT);
				hoverElm3 = dWait.until(ExpectedConditions.visibilityOf(hoverElm3));
				action.moveToElement(hoverElm3);

				mouseHover(hoverElm3);
				dWait = new WebDriverWait(driver, TIME_OUT);
				WebElement clickElm = dWait.until(ExpectedConditions
						.visibilityOfElementLocated(ByXPath.xpath("(//a[contains(@name,'" + clickElmName + "')])[1]")));
				action.moveToElement(clickElm).click(clickElm).perform();
				success = true;
			} catch (StaleElementReferenceException sere) {
				System.out.print("StaleElementReferenceException\n");
			} catch (NoSuchElementException nsee) {
				System.out.print("NoSuchElementException\n");
			} catch (Exception e) {
				fail("Failed mouseSelectWithRetry() with Exception: " + e.getMessage());
			}
			if (success)
				break;
			attempts++;
		}
	}

	public void jsClick(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to click by java script");

		}

	}
	
	public  void highlightElement(WebElement element)
	{
		String presentColor=element.getCssValue("backgroundColor");
		String newCoclor="rgb(255,255,0)";
		
		for(int i=1;i<=3;i++)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].style.backgroundColor='"+newCoclor+"'",element);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.backgroundColor='"+presentColor+"'",element);
			
		}
	}
	
	public void webScroll()
	{
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("window.scrollBy(0,250)", "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail("unable to scroll the element");
		}
	}
	
	
	
	public void jsType(WebElement element, String xp)
	{
		String xp1="\"arguments[0].value='"+xp+"'\"";
//		((JavascriptExecutor)driver).executeAsyncScript("arguments[0].value='admin'",element);
		((JavascriptExecutor)driver).executeAsyncScript(xp1,element);
	}
	
//..............................colour verification.......................
	public  void verifyElementColor(WebElement element,String eHexColor)
	{
		
		String strRGB=element.getCssValue("color");
		System.out.println(strRGB);
		//testReport.log(LogStatus.INFO,"RGB is:"+strRGB);
		String hex=convertRGBtoHex(strRGB);	
		
		String msg1="<span style='color:"+eHexColor+";'>Expected color</span>";
		//testReport.log(LogStatus.INFO,"HTML",msg1);
		
		String msg2="<span style='color:"+hex+";'>Actual color</span>";
		//testReport.log(LogStatus.INFO,"HTML",msg2);
		System.out.println(hex);
		
		if(hex.equals(eHexColor))
		{
			System.out.println("successfully verified");
			//testReport.log(LogStatus.PASS,"Element color is matching");
		}
		else
		{
			System.out.println("not verified");
			//testReport.log(LogStatus.FAIL,"Element color is not matching");
		}
	}
	
	
	public  String convertRGBtoHex(String strRGB)
	{
		String hex="";
		List<Integer> rgb=new ArrayList<Integer>();
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(strRGB);
		 while(m.find())
		 {
			 int v=Integer.parseInt(m.group());
			 rgb.add(v);
		 }
		 		
		 int red=rgb.get(0);
		 int green=rgb.get(1);
		 int blue=rgb.get(2);
		 hex = String.format("#%02x%02x%02x",red, green,blue);
		 return hex; 
	}
	
	
	public  String getFormatedDateTime()
	{
		SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sd.format(new Date());
	}
	
	
	public void getPageScreenShot()
	{
		String imagePath="./ScreenShots"+"/"+getFormatedDateTime()+".png";
		EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
		try{
			FileUtils.copyFile(edriver.getScreenshotAs(OutputType.FILE),new File(imagePath));
			}
		catch(Exception e)
		{
			System.out.println("scrennshot couldn't capture");
		}
		
	}
	
	/*public void sentAnEmail()
	{
		 String fromMail =ReadPropertyFile.getPropertyValue("FromEmail");
		String tomail=ReadPropertyFile.getPropertyValue("TOEMAIL");
		String pwd=ReadPropertyFile.getPropertyValue("PASSWORD");
		String emailText=ReadPropertyFile.getPropertyValue("EMAILTEXT");
		String cc=ReadPropertyFile.getPropertyValue("CCEMAIL");
		String subject=ReadPropertyFile.getPropertyValue("SUBJECT");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "mailgate.uswc.uswest.com");
		props.put("mail.smtp.socketFactory.port", "25");
		//props.put("mail.smtp.socketFactory.class",
		//"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(fromMail,tomail);
		}
		});
		try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromMail));
		
		String[] recipientList = tomail.split(",");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		int counter = 0;
		for (String recipient : recipientList) {
		    recipientAddress[counter] = new InternetAddress(recipient.trim());
		    counter++;
		}
		message.setRecipients(Message.RecipientType.TO, recipientAddress);

		message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
		 
		 
		
		
		
		message.setSubject(subject);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(emailText);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		messageBodyPart = new MimeBodyPart();


	    String file= System.getProperty("user.dir")+"\\src\\test\\resources\\FeaturDataResults.xlsx";
	    String fileName = "OutputData.xlsx";
		DataSource source = new FileDataSource(file);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		} catch (MessagingException ex) {
		throw new RuntimeException(ex);
		}
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
