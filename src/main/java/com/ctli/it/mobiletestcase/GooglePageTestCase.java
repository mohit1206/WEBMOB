package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.mobilepage.GooglePage;
import com.ctli.it.testcases.TestNgInitialization;

public class GooglePageTestCase extends TestNgInitialization{
	@Test
	public void test()
	{
GooglePage gp=new GooglePage(mobiledriver);
gp.enter_data();


	}

	
}
