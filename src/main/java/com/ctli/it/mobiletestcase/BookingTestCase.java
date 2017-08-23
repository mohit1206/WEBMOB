package com.ctli.it.mobiletestcase;

import org.testng.annotations.Test;

import com.ctli.it.mobilepage.BookingFilterPage;
import com.ctli.it.mobilepage.BookingPage;
import com.ctli.it.testcases.TestNgInitialization;

public class BookingTestCase extends TestNgInitialization {
	@Test
	public void test() throws Exception
	{
		BookingPage bp=new BookingPage(mobiledriver);
		bp.selctplace();
		bp.DateSelectCheckIn();
		bp.DateSelectCheckOut();
		bp.selctAdults();
		BookingFilterPage bfp=new BookingFilterPage(mobiledriver);
		bfp.clickFilter();
		bfp.selectBudget();
		
	}

}
