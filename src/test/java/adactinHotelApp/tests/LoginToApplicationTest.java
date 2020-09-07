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

public class LoginToApplicationTest extends BusinessFunctions {
	public WebDriver driver;
	public LoginPage lp;
	public String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;
	
	@BeforeTest
	public void initialize() throws IOException {
		driver=initializeDriver();
	}
	
	 @Test(dataProvider="ValidLoginData")
	  public void validLoginCredentials(String username,String password) throws IOException {
		  driver.get(prop.getProperty("url")); 
		  loginCredentials(driver,username,password);
	  }
	 
	  @Test(dataProvider="InvalidLoginData")
	  public void invalidLoginCredentials(String username,String password) throws IOException {
		 driver.get(prop.getProperty("url"));
		 loginCredentials(driver,username,password);
	 }
	
	 @Test(dataProvider="ForgotPassword")
	 public void resetPasswordCredentials(String username,String password,String email) throws IOException {
		 driver.get(prop.getProperty("url"));
		 lp=new LoginPage(driver);
		 lp.getUsername().sendKeys(username);
		 lp.getPassword().sendKeys(password);
		 lp.getLogin().click();
		 ForgotPasswordFormPage fp=lp.getForgotPassword();
		 fp.getEmailRecovery().sendKeys(email);
		 fp.clickEmailPassword().click();
		 
	 }
	 
	 @Test(dataProvider="ForgotPassword")
	 public void forgotPasswordCredentials(String username,String password,String email) throws IOException {
		 driver.get(prop.getProperty("url"));
		 lp=new LoginPage(driver);
		 lp.getUsername().sendKeys(username);
		 lp.getPassword().sendKeys(password);
		 lp.getLogin().click();
		 
		 loginCredentials(driver,username,password);
		 ForgotPasswordFormPage fp=lp.getForgotPassword();
		 fp.getEmailRecovery().sendKeys(email);
		 fp.clickEmailPassword().click();
		 
	 }	
	 
	 @Test(dataProvider="ForgotPassword")
	 public void resetFunctionality(String username,String password,String email) throws IOException {
		 driver.get(prop.getProperty("url"));
		 lp=new LoginPage(driver);
		 lp.getUsername().sendKeys(username);
		 lp.getPassword().sendKeys(password);
		 lp.getLogin().click();
		 
		 loginCredentials(driver,username,password);
		 ForgotPasswordFormPage fp=lp.getForgotPassword();
		 fp.getEmailRecovery().sendKeys(email);
		 fp.clickReset().click();
		
	 }
	 
	 @Test
	 public void navigationToRegistration() {
		 driver.get(prop.getProperty("url"));
		 lp=new LoginPage(driver);
		 RegistrationPage rp=lp.getNewUser();
		 
		 
		 
	 }
	 
	 
	 @DataProvider(name="ValidLoginData")
	  public Object[][] readValidLoginData() throws IOException{
		 sheetName="ValidLogin";
		 return BusinessFunctions.readExcel(filePath, sheetName);
	  }
	 
	 @DataProvider(name="InvalidLoginData")
	  public Object[][] readInvalidLoginData() throws IOException{
		 sheetName="InvalidLogin";
		 return BusinessFunctions.readExcel(filePath, sheetName);
		   
	  }
	 
	 @DataProvider(name="ForgotPassword")
	 public Object[][] readForgotPasswordData() throws IOException{
		 sheetName="RecoveryEmail";
		 return BusinessFunctions.readExcel(filePath, sheetName);
		 
	 }
	  
	  @AfterTest
	  public void tearDown() {
		  driver.close();
	  }
	
	

}
