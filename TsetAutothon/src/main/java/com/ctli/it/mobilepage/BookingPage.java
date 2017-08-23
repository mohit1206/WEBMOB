package com.ctli.it.mobilepage;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BookingPage extends MobileBaseClass {

	public BookingPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id="input_destination")
	public WebElement tbx_search;
	@FindBy(xpath="//label[contains(text(),'Check-in date')]")
	public WebElement btn_CheckInDate;
	
	@FindBy(xpath="//label[contains(text(),'Check-out date')]")
	public WebElement btn_CheckOutDate;
	@FindBy(xpath="//button[text()=' > ']")
	public WebElement btn_nextMonth;
//	@FindBy(xpath="//label[contains(text(),'Check-in date')]")
//	public WebElement btn_CheckInDate;
	@FindBy(xpath="//button[text()='22']")
	public WebElement btn_selctcheckinDate;
	@FindBy(xpath="(//button[text()='5'])[2]")
	public WebElement btn_selctcheckoutDate;
	@FindBy(name="group_adults")
	public WebElement ddl_noOfAdults;
	
	@FindBy(xpath="//span[contains(text(),'Yes')]")
	public WebElement rb_areYouTraveling;
	
	@FindBy(xpath="//button[contains(text(),'Search')]")
	public WebElement btn_search;
	
	@FindBy(xpath="(//button[text()=' > '])[2]")
	public WebElement btn_nextMonthcheckOut;
	
	@FindBy(xpath="//b[text()='New Delhi']")
	public WebElement select_newDelhi;;
	
	
	//b[text()='New Delhi']
	
	
	
	
	//button[contains(text(),'Search')]
	
	//span[contains(text(),'Yes')]
	
	public void selctplace() throws Exception
	{
		/*String contextName = androiddriver.getContext();
		System.out.println(contextName);
		androiddriver.context("NATIVE_APP");
		//androiddriver.getScreenshotAs(arg0)
		File screenShot = androiddriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File("C:\\LastHope\\TestAutothon\\Resources\\data.png"));
		androiddriver.context(contextName );*/
		
		tbx_search.sendKeys("new delhi");
		click(select_newDelhi);
		System.out.println("Ankit");
	}
	
	public void DateSelectCheckIn() throws InterruptedException
	{
		//click(btn_CheckInDate);
		for(int i=0;i<4;i++)
		{
			//Thread.sleep(6000);
			btn_nextMonth.click();
			//click(btn_nextMonth);
		}
		
		
		click(btn_selctcheckinDate);
	}
	
	
	public void DateSelectCheckOut()
	{
		click(btn_CheckOutDate);
		for(int i=0;i<1;i++)
		{
			click(btn_nextMonthcheckOut);
		}
		
		
		click(btn_selctcheckoutDate);
	}
	
	public void selctAdults()
	{
		setDropdown(ddl_noOfAdults, "4");
		click(rb_areYouTraveling);
		click(btn_search);
		//jsClick(btn_search);
	}

}
