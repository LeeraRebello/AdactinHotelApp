package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class LoginToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private LoginPage lp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private String rowNumber;
	
	private static Logger log=LogManager.getLogger(LoginToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "ValidLoginData")
	public void validLoginCredentials(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
		log.info("Login successful");

	}

	@Test(dataProvider = "InvalidLoginData")
	public void invalidLoginCredentials(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
		Thread.sleep(2000);
		log.error("Invalid login");
	}

	@Test
	public void navigationToForgotPassword() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		lp.getForgotPassword().click();
		log.info("Forgot Password page is displayed");
		
		

	}

	@Test(dataProvider = "ResetPassword")
	public void navigationToResetPassword(String username, String password, String email)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver, username, password, email);
		log.info("Reset Password page is displayed");

	}

	@Test
	public void navigationToRegistration() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		lp.getNewUser();
		log.info("New user Registartion page will be displayed");

	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] readValidLoginData() throws IOException {
		sheetName=prop.getProperty("validLoginSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "InvalidLoginData")
	public Object[][] readInvalidLoginData() throws IOException {
		sheetName=prop.getProperty("invalidLoginSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
	
	@DataProvider(name = "ResetPassword")
	public Object[][] readResetPassword() throws IOException {
		sheetName=prop.getProperty("resetPasswordSheet");
		//rowNumber=prop.getProperty("rowForResetPassword");
		//int row=Integer.parseInt(rowNumber);
		return ExcelDataUtils.readSingleRow(filePath, sheetName,1);

	}



	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
