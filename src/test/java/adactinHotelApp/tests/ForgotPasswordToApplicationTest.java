package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.ForgotPasswordFormPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class ForgotPasswordToApplicationTest extends BusinessFunctions{
	
	public WebDriver driver;
	public ForgotPasswordFormPage fp;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;
	public String columnName;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
	
	@Test(dataProvider = "ForgotPasswordRecoveryEmailData")
	public void forgotPasswordCredentials(String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
     	forgotPassword(driver,email);
		fp = new ForgotPasswordFormPage(driver);
     	fp.getEmailRecovery().sendKeys(email);
     	fp.clickEmailPassword().click();	
     	Thread.sleep(2000);
	

	}
	
	@Test(dataProvider = "ResetPasswordRecoveryEmailData")
	public void resetPasswordCredentials(String username,String password,String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver,username,password,email);
		ForgotPasswordFormPage fp = new ForgotPasswordFormPage(driver);
		fp.getEmailRecovery().sendKeys(email);
		fp.clickEmailPassword().click();
		Thread.sleep(2000);

	}
	
	@Test(dataProvider = "ForgotPasswordRecoveryEmailData")
	public void resetFunctionality(String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		forgotPassword(driver,email);
		fp = new ForgotPasswordFormPage(driver);
		fp.getEmailRecovery().sendKeys(email);
		fp.clickReset().click();
		
	}


	
	@DataProvider(name = "ForgotPasswordRecoveryEmailData")
	public Object[][] readForgotPassword() throws IOException {
		sheetName = "RecoveryEmail";
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
	
	@DataProvider(name = "ResetPasswordRecoveryEmailData")
	public Object[][] readResetPassword() throws IOException {
		sheetName = "LoginNegativeTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
	

	
	@AfterTest
	public void tearDown() {
		driver.close();
	}


}
