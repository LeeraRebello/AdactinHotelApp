package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.ResetPasswordPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class ResetPasswordToApplicationTest extends BusinessFunctions {

	private WebDriver driver;
	private ResetPasswordPage rp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private static Logger log=LogManager.getLogger(ResetPasswordToApplicationTest.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
	
  
  @Test(dataProvider = "ResetPasswordRecoveryEmailData")
	public void resetPasswordCredentials(String username,String password,String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		resetPassword(driver,username,password,email);
		rp=new ResetPasswordPage(driver);
		rp.getEmailRecovery().sendKeys(email);
		rp.clickEmailPassword().click();
		Thread.sleep(2000);
		log.info("New Password will be sent to email if the email exist in the database");

	}
  
  
  @Test(dataProvider = "ResetPassword")
 	public void resetFunctionality(String username,String password,String email) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
 		resetPassword(driver,username,password,email);
 		rp=new ResetPasswordPage(driver);
 		rp.getEmailRecovery().sendKeys(email);
 		rp.clickReset().click();
 		Thread.sleep(2000);

 	}
   
  @Test(dataProvider = "ResetPassword")
	public void backToLogin(String username,String password,String email) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		log.debug("Opening reset password page");
		resetPassword(driver,username,password,email);
		rp=new ResetPasswordPage(driver);
		rp.goBackToLoginPage().click();
		log.info("Back to login page");
		Thread.sleep(2000);
		

	}
  
  @DataProvider(name = "ResetPasswordRecoveryEmailData")
	public Object[][] readResetPassword() throws IOException {
		sheetName = prop.getProperty("resetPasswordSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);

	}
  
  
  @DataProvider(name = "ResetPassword")
	public Object[][] readReset() throws IOException {
		sheetName = prop.getProperty("resetPasswordSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName,1);

	}
  

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
