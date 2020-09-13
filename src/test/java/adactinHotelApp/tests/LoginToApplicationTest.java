package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.ForgotPasswordFormPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.RegistrationPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class LoginToApplicationTest extends BusinessFunctions {
	public WebDriver driver;
	public LoginPage lp;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "ValidLoginData")
	public void validLoginCredentials(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
		Thread.sleep(2000);
	}

  
	@Test(dataProvider = "InValidLoginData")
	public void invalidLoginCredentials(String username,String password,String email) throws IOException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
	}

	
	@Test(dataProvider = "InValidLoginData")
	public void resetPasswordCredentials(String username, String password, String email) throws IOException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver,username,password,email);
		ForgotPasswordFormPage fp = new ForgotPasswordFormPage(driver);
		fp.clickEmailPassword().click();

	}

	
	@Test(dataProvider = "InValidLoginData")
	public void forgotPasswordCredentials(String username, String password, String email) throws IOException {
		driver.get(prop.getProperty("url"));
     	resetPassword(driver,username,password,email);
		ForgotPasswordFormPage fp = new ForgotPasswordFormPage(driver);
		fp.clickEmailPassword().click();

	}

	@Test(dataProvider = "InValidLoginData")
	public void resetFunctionality(String username, String password, String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver,username,password,email);
		ForgotPasswordFormPage fp = new ForgotPasswordFormPage(driver);
		fp.clickReset().click();
		Thread.sleep(2000);
	}

	@Test
	public void navigationToRegistration() {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		RegistrationPage rp = lp.getNewUser();

	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] readValidLoginData() throws IOException {
		sheetName = "LoginPositiveTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "InValidLoginData")
	public Object[][] readInvalidLoginData() throws IOException {
		sheetName = "LoginNegativeTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
