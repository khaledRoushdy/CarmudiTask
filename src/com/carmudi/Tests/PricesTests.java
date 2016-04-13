package com.carmudi.Tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.carmudi.AbstractSelenium.AbstractSelenium;
import com.carmudi.ExcelUtilities.ExcelUtils;
import com.carmudi.Pages.HomePage;


public class PricesTests {

	
	
	WebDriver driver=AbstractSelenium.getBrowser("Chrome");
	HomePage homePage= new HomePage(driver);
	String fromPrice = null;
	String toPrice = null;
	
	@BeforeTest
	public void initialize() throws Exception {
		
		driver.get("http://www.carmudi.ae/all/");
		driver.manage().window().maximize();
		String excelFile=System.getProperty("user.dir") + "\\TestData.xlsx";
		ExcelUtils.setExcelFile(excelFile, "Data");
	}

	@Test
	public void checkResultsForValidRangesOfPrices() throws Exception {

		fromPrice = ExcelUtils.getCellData(1, 1);
		toPrice = ExcelUtils.getCellData(1, 2);
		homePage.enterPricesRange(fromPrice, toPrice);
		homePage.driverImplicitWait(10);
		homePage.validatePricesWithinRange(fromPrice, toPrice);
	}

	@Test
	public void checkResultsForValidPricesWithNoMatches() throws Exception {

		fromPrice = ExcelUtils.getCellData(2, 1);
		toPrice = ExcelUtils.getCellData(2, 2);
		homePage.enterPricesRange(fromPrice, toPrice);
		homePage.driverImplicitWait(10);
		homePage.validateErrorMessagesForNoMatches();
	}

	@Test
	public void checkResultsForInvalidRangeOfPrices() throws Exception {
		fromPrice = ExcelUtils.getCellData(3, 1);
		toPrice = ExcelUtils.getCellData(3, 2);
		homePage.enterPricesRange(fromPrice,toPrice);
		homePage.driverImplicitWait(7);
		homePage.validateToPriceIsSetToDefaultValue(fromPrice);

	}
	
	@Test
	public void checkTitleName(){

		Assert.assertEquals(driver.getTitle(), "Carmudi","The title is not correct");
	}
	
	@AfterMethod
	public void goToHomePage(){
		driver.get("http://www.carmudi.ae/all/");
	}

	@AfterTest
	public void tearDown() {
		AbstractSelenium.closeAllDriver();
	}

}
