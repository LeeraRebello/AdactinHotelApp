package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.ChangePasswordFormPage;
import adactinHotelApp.pageObejcts.ForgotPasswordFormPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class ChangePasswordToApplicationTest extends BusinessFunctions  {
	private WebDriver driver;
	private ChangePasswordFormPage cp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private static Logger log=LogManager.getLogger(ChangePasswordToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
 
	
	
	@Test(dataProvider="ChangePasswordData",priority=1)
	public void changePasswordCredentials(String username,String password,String currentPassword,
			String newPassword,String rePassword) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		changePassword(driver,username,password,currentPassword,newPassword,rePassword);
		cp = new ChangePasswordFormPage(driver);
		Thread.sleep(2000);
		cp.clickSubmit().click();
		Thread.sleep(2000);
		log.info("Password updated successfully");
		
	}
	@Test(dataProvider="ChangePasswordWithCurrentPassword",priority=3)
	public void changePasswordByGivingCurrentPassword(String username,String password,String currentPassword,
			String newPassword,String rePassword) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		changePassword(driver,username,password,currentPassword,newPassword,rePassword);
		cp = new ChangePasswordFormPage(driver);
		Thread.sleep(2000);
		cp.clickSubmit().click();
		Thread.sleep(2000);
		log.error("Password changed");
		
	}
	
	@Test(dataProvider="ChangePasswordWithoutMatch",priority=2)
	public void changePasswordWithoutMatch(String username,String password,String currentPassword,
			String newPassword,String rePassword) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		changePassword(driver,username,password,currentPassword,newPassword,rePassword);
		cp = new ChangePasswordFormPage(driver);
		Thread.sleep(2000);
		cp.clickSubmit().click();
		Thread.sleep(2000);
		log.error("Password changed");
	}
	
	
	
	@DataProvider(name = "ChangePasswordData")
	public Object[][] readChangePassword() throws IOException {
		sheetName = prop.getProperty("changePasswordSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName,1);

	}

	@DataProvider(name = "ChangePasswordWithCurrentPassword")
	public Object[][] readChangePasswordByCurrentPassword() throws IOException {
		sheetName = prop.getProperty("changePasswordSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName,2);

	}
	
	@DataProvider(name = "ChangePasswordWithoutMatch")
	public Object[][] readChangePasswordWithoutMatch() throws IOException {
		sheetName = prop.getProperty("changePasswordSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName,3);

	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
