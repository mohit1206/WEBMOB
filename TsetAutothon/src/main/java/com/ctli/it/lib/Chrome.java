package com.ctli.it.lib;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Chrome {
	@Test
	public void test() throws Exception{
		
		AppiumServerStarting aps=new AppiumServerStarting();
		aps.appiumStart();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "e754d6d3");
		capabilities.setCapability("newCommandTimeout", 120);
		capabilities.setCapability("chromedriverExecutable", "C:\\LastHope\\TestAutothon\\Resources\\chromedriver.exe");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("launchTimeout", "100000");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.navigate().to("http://www.google.com");
		System.out.println("Android Device browser called successfully");
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability("deviceName", "e754d6d3");
		ds.setCapability(CapabilityType.VERSION, "6.0.1");
		ds.setCapability("platformName", "Android");
		ds.setCapability("browserName", "Chrome");
		//WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), ds);
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.get("http://www.google.com");
	
		
	}
}
