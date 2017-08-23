package com.ctli.it.mobilepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ApiDemoPage extends MobileBaseClass {

	public ApiDemoPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(name="Views")
	public WebElement btn_Views;
	
	@FindBy(name="Date Widgets")
	public WebElement date_widgets;
	
	
	@FindBy(name="1. Dialog")
	public WebElement dialogue;
	
	@FindBy(name="change the date")
	public WebElement btn_changeTheDate;
	
	@FindBy(id="android:id/next")
	public WebElement btn_Next;
	
	@FindBy(xpath="//android.view.View[@content-desc='20 September 2017']")
	public WebElement btn_selectDate;
	
	@FindBy(name="Auto Complete")
	public WebElement btn_AutoComplete;
	
	@FindBy(name="1. Screen Top")
	public WebElement btn_ScreenTop;
	@FindBy(id="demo.fun.com.apis:id/edit")
	public WebElement tbx_country;
	
	
	
	
	
	public void click_views()
	{
		verticalScroll("", btn_Views);
		tap(btn_Views);
		tap(date_widgets);
		tap(dialogue);
		tap(btn_changeTheDate);
		tap(btn_Next);
		tap(btn_selectDate);
		
	}
///.......Auto Complete.......
	public void auto_Complete()
	{
		verticalScroll("", btn_Views);
		tap(btn_Views);
		
		tap(btn_AutoComplete);
		tap(btn_ScreenTop);
		
		
		tbx_country.sendKeys("india");
		int x = tbx_country.getLocation().getX();
		int y = tbx_country.getLocation().getY();
		System.out.println("x"+x+"y"+y);
		TouchAction action = new TouchAction(androiddriver).tap(x+120, y+360).release();
		action.perform();
		
		
	}
	
	@FindBy(id="com.fortysevendeg.android.swipelistview:id/example_row_b_action_1")
	public WebElement btn_Ant;
	public void elementSwipping()
	{
		int x=btn_Ant.getLocation().getX();
		int y=btn_Ant.getLocation().getY();
		System.out.println("x coordiante"+x+"y cordinate"+y);
		
	}
	
}
