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

public class NewUserToApplicationTest extends BusinessFunctions{
	
	public WebDriver driver;
	public RegistrationPage rp;
	public String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;
	
	@BeforeTest
	public void initialize() throws IOException {
		driver=initializeDriver();
	}
	
  @Test(dataProvider="NewUser")
  public void newUserDetails(String username,String password,String confirmPassword,String fullname,String email) throws InterruptedException {
	  driver.get(prop.getProperty("url")); 
	  rp=new RegistrationPage(driver);
	  LoginPage lp=new LoginPage(driver);
	  lp.getNewUser();
	  rp.getUserName().sendKeys(username);
	  rp.getPassword().sendKeys(password);
	  rp.getConfirmPassword().sendKeys(confirmPassword);
	  rp.getFullName().sendKeys(fullname);
	  rp.getEmailAdd().sendKeys(email);
	  String captchaForm= rp.getCaptcha().getText();
	  Thread.sleep(3000);
	  rp.getCaptchaForm().sendKeys(captchaForm);
	  Thread.sleep(2000);
	  rp.clickCheckbox().click();
	  rp.clickRegister().click();
	  
	  
  }
  
  @Test(dataProvider="NewUser")
  public void newUserReset(String username,String password,String confirmPassword,String fullname,String email) throws InterruptedException {
	  driver.get(prop.getProperty("url")); 
	  rp=new RegistrationPage(driver);
	  LoginPage lp=new LoginPage(driver);
	  lp.getNewUser();
	  rp.getUserName().sendKeys(username);
	  rp.getPassword().sendKeys(password);
	  rp.getConfirmPassword().sendKeys(confirmPassword);
	  rp.getFullName().sendKeys(fullname);
	  rp.getEmailAdd().sendKeys(email);
	  String captchaForm= rp.getCaptcha().getText();
	  Thread.sleep(3000);
	  rp.getCaptchaForm().sendKeys(captchaForm);
	  Thread.sleep(2000);
	  rp.clickCheckbox().click();
	  rp.clickReset().click();
	  
	  
  }
  
  
  
  
  @DataProvider(name="NewUser")
  public Object[][] readNewUserWithValidDetails() throws IOException{
	  sheetName="NewUserRegistration";
	  return BusinessFunctions.readExcel(filePath, sheetName);
	  
  }
  
  @AfterTest
  public void tearDown() {
	  driver.close();
  }

}
