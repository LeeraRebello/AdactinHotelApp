package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.ForgotPasswordFormPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class ForgotPasswordToApplicationTest extends BusinessFunctions{
	
	private WebDriver driver;
	private ForgotPasswordFormPage fp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private static Logger log=LogManager.getLogger(ForgotPasswordToApplicationTest.class.getName());
	
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
     	log.info("Password will be sent to email if the email id exist in the database");
	

	}

	@Test(dataProvider = "ForgotPasswordRecoveryEmailData")
	public void resetFunctionality(String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		forgotPassword(driver,email);
		fp = new ForgotPasswordFormPage(driver);
		fp.getEmailRecovery().sendKeys(email);
		fp.clickReset().click();
		
	}



	@Test(dataProvider = "LoginPage")
	public void goingBackToLoginPage(String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		log.debug("Opening Forgot Password page");
		forgotPassword(driver,email);
		fp = new ForgotPasswordFormPage(driver);
		fp.goBackToLoginPage().click();
		Thread.sleep(2000);
		log.info("Going back to login page");
		
		
	}


	
	@DataProvider(name = "ForgotPasswordRecoveryEmailData")
	public Object[][] readForgotPassword() throws IOException {
		sheetName = prop.getProperty("recoveryEmailSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
	

	@DataProvider(name = "LoginPage")
	public Object[][] readGoingBackToLogin() throws IOException {
		sheetName = prop.getProperty("recoveryEmailSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName,1);

	}
	

	
	@AfterTest
	public void tearDown() {
		driver.close();
	}


}
