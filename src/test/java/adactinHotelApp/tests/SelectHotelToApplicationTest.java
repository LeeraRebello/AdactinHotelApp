package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;

public class SelectHotelToApplicationTest extends BusinessFunctions {
	public WebDriver driver;
	SelectHotelPage shp;
	public LoginPage lp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		
	}
	@Test(dataProvider="ValidLogin")
	public void validLoginCredentials(String username,String password) {
		driver.get(prop.getProperty("url")); 
		lp=new LoginPage(driver);
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLogin().click();
	}
	
    

	
  @Test
  public void selectHotelFunctionality() {
	  shp=new SelectHotelPage(driver);
	  shp.getRadioButton().click();
	  shp.clickContinue().click();
  }
  
  
  @DataProvider(name="ValidLogin")
  public Object[][] readValidLogin() throws IOException{
	 sheetName="ValidLogin";
	 return BusinessFunctions.readExcel(filePath, sheetName);
  }
  @AfterTest
	 public void tearDown() {
  driver.close();
	  }
	
}
