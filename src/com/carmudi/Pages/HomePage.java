package com.carmudi.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.carmudi.Utilities.Utilities;

public class HomePage extends BasePage{

	

	private By priceItemLocator = By.xpath("//div[@id='facet_price']/div[@class='title']");
	private By fromPriceTextBoxLocator = By.xpath("//div[@id='facet_price']/div[2]/div/div[1]/label/input");
	private By toPriceTextBoxLocator = By.xpath("//div[@id='facet_price']/div[2]/div/div[2]/label/input");
	private By okButtonLocator = By.xpath("//div[@id='facet_price']/div[2]/div/a");
	
	private By vehiclePricesLocator=By.xpath("//*[@id='catalog-index']/section/section/div[@data-layer-category='listing_box']/div[2]/div/div/h6/a");
	private By searchCriteriaForPricesLocator=By.xpath("//*[@id='catalog-index']/header/div/div/ul/li[3]/a/span");
	
	private static String NO_MATCHES_FOUND_MESSAGE="Sorry, no matches found.";
	private static String SUGGESTED_ALTERNATIVES_MESSAGE="Suggested alternatives:";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void enterPricesRange(String fromPrice, String toPrice) {

		selectPriceItem();
		waiting(3);
		setFromPrice(fromPrice);
		setToPrice(toPrice);
		selectOkButton();
	}

	private void selectPriceItem() {
		driverImplicitWait(3);
		fluentWait(driver.findElement(priceItemLocator));
		scrollToElement(priceItemLocator).click();
	}

	private void setFromPrice(String fromPrice) {
		driver.findElement(fromPriceTextBoxLocator).sendKeys(fromPrice);
	}

	private void setToPrice(String toPrice) {
		driver.findElement(toPriceTextBoxLocator).sendKeys(toPrice);
	}

	private void selectOkButton() {
		driver.findElement(okButtonLocator).click();
	}
	
	private String getEnteredToPrice(){
		return driver.findElement((toPriceTextBoxLocator)).getAttribute("value");
	}
	
	
	public void validateToPriceIsSetToDefaultValue(String fromPrice){
	
		String enteredToPrice=getEnteredToPrice();
		Assert.assertEquals(enteredToPrice, fromPrice,"The price is not set to default value");
	}
	
	public void validatePricesWithinRange(String fromPrice,String toPrice){
		
		String vehichlePrice=null;
		for(WebElement element :driver.findElements(vehiclePricesLocator) ){
			vehichlePrice=element.getText();
			int priceTobeChecked=Utilities.removeCurrencyFromPrice(vehichlePrice);
			boolean checkPricesWithinRange=Utilities.checkPriceWithinRange(Integer.parseInt(fromPrice),Integer.parseInt(toPrice),priceTobeChecked );
			Assert.assertTrue(checkPricesWithinRange, "The prices is not in range");
		}
	}
	
	public void validateErrorMessagesForNoMatches(){
		List<String> notificationMessages=getAlertNotificationsMessages();
		Assert.assertTrue(notificationMessages.contains(NO_MATCHES_FOUND_MESSAGE));
		Assert.assertTrue(notificationMessages.contains(SUGGESTED_ALTERNATIVES_MESSAGE));
	}
	
}
