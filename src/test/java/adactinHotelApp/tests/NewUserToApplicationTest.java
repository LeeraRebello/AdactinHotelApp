package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.RegistrationPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;
import junit.framework.Assert;

public class NewUserToApplicationTest extends BusinessFunctions {

	public WebDriver driver;
	public RegistrationPage rp;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "VerifyTitle")
	public void verifyTitle(String username, String password) throws InterruptedException, IOException {
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		lp.getNewUser();
		Assert.assertEquals(driver.getTitle(), "Adactin.com - New User Registration");

	}

	@Test(dataProvider = "NewUser")
	public void newUserDetails(String username, String password, String confirmPassword, String fullname, String email)
			throws InterruptedException, IOException {
		driver.get(prop.getProperty("url"));
		rp = new RegistrationPage(driver);
		newUserDetails(driver, username, password, confirmPassword, fullname, email);
		rp.clickRegister().click();
		
	}

	@Test(dataProvider = "NewUser")
	public void newUserReset(String username, String password, String confirmPassword, String fullname, String email)
			throws InterruptedException, IOException {
		driver.get(prop.getProperty("url"));
		rp = new RegistrationPage(driver);
		newUserDetails(driver, username, password, confirmPassword, fullname, email);
		rp.clickReset().click();
		

	}

	@DataProvider(name = "NewUser")
	public Object[][] readNewUserWithValidDetails() throws IOException {
		sheetName = "NewUserRegistration";
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}

	@DataProvider(name = "VerifyTitle")
	public Object[][] readTitle() throws IOException {
		sheetName = "LoginPositiveTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
