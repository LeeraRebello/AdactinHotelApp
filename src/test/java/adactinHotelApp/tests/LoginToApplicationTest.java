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
import junit.framework.Assert;

public class LoginToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private LoginPage lp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private String rowNumber;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "ValidLoginData")
	public void validLoginCredentials(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);

	}

	@Test(dataProvider = "InvalidLoginData")
	public void invalidLoginCredentials(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
		Thread.sleep(2000);
	}

	@Test
	public void navigationToForgotPassword() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		lp.getForgotPassword().click();

	}

	@Test(dataProvider = "ResetPassword")
	public void navigationToResetPassword(String username, String password, String email)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver, username, password, email);

	}

	@Test
	public void navigationToRegistration() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		lp.getNewUser();

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
