package pageObjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//Elements
	@FindBy(xpath = "//td[@class='text-center']//img")
	List<WebElement> addedProducts;
	
	
	//Actions
	public boolean isProductAddtoCart(String nameProduct) {
		for (WebElement product : addedProducts) {
			if(product.getAttribute("title").toUpperCase().equals(nameProduct.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
