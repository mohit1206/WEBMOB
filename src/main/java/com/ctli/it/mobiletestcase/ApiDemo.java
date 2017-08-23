package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.mobilepage.ApiDemoPage;
import com.ctli.it.testcases.TestNgInitialization;
import com.thoughtworks.selenium.webdriven.commands.Click;

public class ApiDemo extends TestNgInitialization{
@Test
public void demoApp()
{
	ApiDemoPage ap=new ApiDemoPage(mobiledriver);
	//ap.click_views();
	//ap.auto_Complete();
	ap.elementSwipping();
	
	
	
}
}
