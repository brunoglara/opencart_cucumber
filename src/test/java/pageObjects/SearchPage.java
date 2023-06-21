package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	// Elements
	@FindBy(xpath = "//div[@class='product-thumb']//img")
	List<WebElement> searchedProducts;
	
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement txtNoProductMatchElement;

	
	
	//Actions
	public boolean isExistsProduct(String productName) {

		for (WebElement product : searchedProducts) {

			if (product.getAttribute("title").toUpperCase().equals(productName.toUpperCase())) {
				return true;
			}
		}

		return false;
	}
	
	public boolean nonExistsProduct() {
		if(txtNoProductMatchElement.isDisplayed()) {
			return true;
		}
		
		return false;
		
	}
	
	public void clickAtFirstProduct() {
		searchedProducts.get(0).click();
	}

}
