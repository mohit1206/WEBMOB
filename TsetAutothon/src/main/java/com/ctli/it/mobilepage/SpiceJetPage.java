package com.ctli.it.mobilepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctli.it.lib.MobileBaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SpiceJetPage extends MobileBaseClass {

	public SpiceJetPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
@FindBy(id="ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1")
public WebElement ddl_departCity;

@FindBy(id="ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1")
public WebElement ddl_ArrivalCity;

@FindBy(xpath="(//button[@type=\"button\"])[1]")
public WebElement btn_departDate;
@FindBy(xpath="(//button[@type=\"button\"])[2]")
public WebElement btn_ArrivalDate;
@FindBy(id="ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1")
public WebElement ddl_departDate;
//@FindBy(xpath="//a[text()='23']")
//public WebElement btn_DateDept;
//@FindBy(xpath="//a[text()='23']")
//public WebElement btn_DateDept;



public void selectCityAndDate()
{
	setDropdown(ddl_departCity, "Belagavi (IXG)");
	setDropdown(ddl_ArrivalCity, "Pune (PNQ)");
	btn_departDate.click();
	btn_departDate.click();
	
	
}





	
	
}
