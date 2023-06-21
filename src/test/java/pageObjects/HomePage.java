package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Elements
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(name = "search")
	WebElement txtSearch;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;
	
	//Actions
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void setSearch(String product) {
		txtSearch.sendKeys(product);
	}
	
	public void clickSearch() {
		btnSearch.click();
	}
}
