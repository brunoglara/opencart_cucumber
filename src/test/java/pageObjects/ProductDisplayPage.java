package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDisplayPage extends BasePage {

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Elements
	@FindBy(id = "button-cart")
	WebElement btnAddCartElement;
	
	@FindBy(xpath = "//div[contains(text(),'Success: You have added')]")
	WebElement msgSuccessAddCartElement;
	
	@FindBy(xpath = "//a[normalize-space()='shopping cart']")
	WebElement lnkCartElement;
	
	
	//Actions
	public void clickAddCart() {
		btnAddCartElement.click();
	}
	
	public boolean confirmMsgAddCart() {
		if(msgSuccessAddCartElement.isDisplayed()) {
			return true;
		}
		
		return false;
	}
	
	public void clickLinkCart() {
		lnkCartElement.click();
	}

}
