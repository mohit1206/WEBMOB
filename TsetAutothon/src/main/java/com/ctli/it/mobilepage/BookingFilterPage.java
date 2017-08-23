package com.ctli.it.mobilepage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BookingFilterPage extends MobileBaseClass {

	public BookingFilterPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(name="no_rooms")
	public WebElement ddl_selectRoom;
	
	@FindBy(xpath="(//button[@data-tab-target=\"sr_sortfilters\"])[1]")
	public WebElement btn_Filter;
	@FindBy(xpath="(//a[@data-filter-name='WiFi'])[1]")
	public WebElement btn_Wifi;
//	@FindBy(name="no_rooms")
//	public WebElement ddl_selectRoom;
//	@FindBy(name="no_rooms")
//	public WebElement ddl_selectRoom;
	
	
	public void clickFilter()
	{
		
		//setDropdown(ddl_selectRoom, "2 rooms");
click(btn_Filter);
click(btn_Wifi);
System.out.println("wi fi click done");

	}
	ArrayList list=new ArrayList();
	public void selectBudget() throws InterruptedException
	{
		
		System.out.println("Ankit select budget");
		int count=0;
		while (count < 4) {
			   try {
			    //If exception generated that means It Is not able to find element then catch block will handle It.
			    WebElement staledElement = androiddriver.findElement(By.xpath("//button[@data-tab-target=\"sr_sortfilters\"]"));
			    //If exception not generated that means element found and element text get cleared.
			    staledElement.click();    
			   } catch (StaleElementReferenceException e) {
			    e.toString();
			    System.out.println("Trying to recover from a stale element :" + e.getMessage());
			    count = count + 1;
			   }
			   count = count + 4;
		
		}
		
		Thread.sleep(5000);
//		click(androiddriver.findElement(By.xpath("//button[@data-tab-target=\"sr_sortfilters\"]")));
//		click(btn_Filter);
		System.out.println("web scroll");
		webScroll();
		//System.out.println("web scroll after js");
		
		List<WebElement> ele = androiddriver.findElements(By.xpath("//div[contains(text(),'Set your budget per night')]/..//span"));
		for(int i=0;i<ele.size();i++)
		{
			System.out.println(ele.get(i).getText());
			list.add(ele.get(i).getText());
			
			
		}
		
		
		Collections.sort(list);
		String first=list.get(list.size()-1).toString();
		String second=list.get(list.size()-2).toString();
		//span[text()='652']
		String xp="//span[text()='"+first+"']";
		String xp1="//span[text()='"+second+"']";
		System.out.println(xp+"xp");
		System.out.println(xp1+"xp1");
		click(androiddriver.findElement(By.xpath(xp)));
		//jsClick(androiddriver.findElement(By.xpath("(//button[@data-tab-target=\"sr_sortfilters\"])[1]")));
		//click(androiddriver.findElement(By.xpath("(//button[@data-tab-target=\"sr_sortfilters\"])[1]")));
		
		
		
		Thread.sleep(6000);
		int count1=0;
		while (count1 < 4) {
			   try {
			    //If exception generated that means It Is not able to find element then catch block will handle It.
			    WebElement staledElement = androiddriver.findElement(By.xpath("//button[@data-tab-target=\"sr_sortfilters\"]"));
			    //If exception not generated that means element found and element text get cleared.
			    staledElement.click(); 
			    System.out.println("webelement clicked");
			   } catch (StaleElementReferenceException e) {
			    e.toString();
			    System.out.println("Trying to recover from a stale element :" + e.getMessage());
			    count1 = count1 + 1;
			   }
			   count1 = count1 + 4;
		
		}
		
		
		
		click(androiddriver.findElement(By.xpath(xp1)));
		
		
		
		
	}
	
	
}
