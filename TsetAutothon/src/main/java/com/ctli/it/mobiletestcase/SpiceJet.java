package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.mobilepage.SpiceJetPage;
import com.ctli.it.testcases.TestNgInitialization;

public class SpiceJet extends TestNgInitialization {
	
	@Test
	public void test()
	{
		SpiceJetPage sp=new SpiceJetPage(mobiledriver);
		sp.selectCityAndDate();
		System.out.println("Ankit");
	}

}
