package stepDefinitions;

import java.io.Console;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class steps {

	WebDriver driver;

	HomePage homePage;
	LoginPage loginPage;
	MyAccountPage mAccountPage;

	Logger logger; // for logging
	ResourceBundle rBundle; // for reading properties file
	String browser; // to store browser name
	List<HashMap<String, String>> datamap;

	/************************
	 * Hooks JUnit *
	 ************************/
	@Before
	public void setup() {

		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Import to use properties
		rBundle = ResourceBundle.getBundle("config");
		browser = rBundle.getString("browser");
	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======>" + scenario.getStatus());
		logger.info("Scenario " + scenario.getName() + " status ======>" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Screenshot Failed: " + scenario.getName());

		}

		driver.quit();
	}

	/************************
	 * Login.Feature *
	 ************************/

	@Given("User Launch a browser")
	public void user_launch_a_browser() {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}

		if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}

		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("opens the main URL of Open Cart")
	public void opens_the_main_url_of_open_cart() {

		driver.get(rBundle.getString("appURL"));
		driver.manage().window().maximize();
	}

	@When("User navigate to Login form")
	public void user_navigate_to_login_form() {
		homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();

		logger.info("Clicked on my account");
	}

	@When("User enters a valid credentials")
	public void user_enters_a_valid_credentials() {
		loginPage = new LoginPage(driver);

		loginPage.setEmail(rBundle.getString("email"));
		logger.info("Provided email");
		loginPage.setPassword(rBundle.getString("password"));
		logger.info("Provided password");

	}

	@When("submit to validate the credentials")
	public void submit_to_validate_the_credentials() {
		loginPage.clickLogin();
		logger.info("Clicked on login button");

	}

	@Then("User must be logged in")
	public void user_must_be_logged_in() {
		mAccountPage = new MyAccountPage(driver);

		Assert.assertTrue(mAccountPage.isMyAccountPageExists());
		logger.info("Validating user logged in");
	}

	@When("User enters a invalid credentials")
	public void user_enters_a_invalid_credentials() {
		loginPage = new LoginPage(driver);

		loginPage.setEmail("aaaaa@aaa.com");
		logger.info("Provided email");
		loginPage.setPassword("aaaaaaaaaaaaasdfasdfas");
		logger.info("Provided password");
	}

	@Then("User must not be logged in")
	public void user_must_not_be_logged_in() {

		mAccountPage = new MyAccountPage(driver);

		Assert.assertTrue(loginPage.isLoginError());

		logger.info("Validating user not logged in");
	}

	/************************
	 * LoginDDT.Feature *
	 ************************/
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		loginPage = new LoginPage(driver);

		loginPage.setEmail(email);
		logger.info("Provided email");
		loginPage.setPassword(password);
		logger.info("Provided password");
	}

	@Then("User must be {string} in")
	public void user_must_be_in(String status) {
		mAccountPage = new MyAccountPage(driver);

		if (status.equals("logged")) {

			Assert.assertTrue(mAccountPage.isMyAccountPageExists());
			logger.info("Validating user logged in");
		} else if (status.equals("not logged")) {

			Assert.assertTrue(loginPage.isLoginError());
			logger.info("Validating user not logged in");
		} else {
			Assert.fail();
		}

	}
	
	/************************
	 *LoginDDTExcel.Feature	*
	 ************************/
	@When("User enters Email and Password with Excel row {string}")
	public void user_enters_email_and_password_with_excel_row(String row) {
	    datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\Opencart_LoginData.xlsx", "Sheet1");
	    
	    int Index = Integer.parseInt(row) - 1;
	    String email = datamap.get(Index).get("username");
	    String password = datamap.get(Index).get("password");
	    
	    
	    loginPage = new LoginPage(driver);
	    
	   
	    
	    loginPage.setEmail(email);
	    loginPage.setPassword(password);
	        
	}

	@Then("Check user navigates to MyAccount Page with Excel row {string}")
	public void check_user_navigates_to_my_account_page_with_excel_row(String row) {
		int Index = Integer.parseInt(row) - 1;
		
		String expectResponse = datamap.get(Index).get("response");
		
		mAccountPage = new MyAccountPage(driver);

		if (expectResponse.equals("Valid")) {

			Assert.assertTrue(mAccountPage.isMyAccountPageExists());
			logger.info("Validating user logged in");
		} else if (expectResponse.equals("Invalid")) {

			Assert.assertTrue(loginPage.isLoginError());
			logger.info("Validating user not logged in");
		} else {
			Assert.fail();
		}
	}
}
