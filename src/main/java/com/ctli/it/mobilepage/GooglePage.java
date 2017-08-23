package com.ctli.it.mobilepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.BaseClass;
import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GooglePage  extends MobileBaseClass{
	public GooglePage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath="//input[@class=\"lst lst-tbb gsfi\"]")
	public WebElement tbx_google;
	
	
	
	public void enter_data()
	{
		type(tbx_google, "facebook");
	}

	

}
