package adactinHotelApp.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.resources.BusinessFunctions;

public class SearchHotelToApplicationTest extends BusinessFunctions {

	public WebDriver driver;
	public SearchHotelPage sp;
	public LoginPage lp;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;
    
	
	@BeforeTest()
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
	

	@Test(dataProvider="SearchValidHotelData", dependsOnMethods="validLoginCredentials")
	public void searchHotelWithValidData(String location,String hotel,String roomType,String roomNumber,
			String datePickIn,String datePickOut,String adultNum,String childNum) {
		  sp=new SearchHotelPage(driver);
		  sp.getLocation(location);	
		  sp.getHotels(hotel);
		  sp.getRoomType(roomType);
		  sp.getRoomNumbers(roomNumber);
		  sp.getDatePickIn().sendKeys(datePickIn);
		  sp.getDatePickOut().sendKeys(datePickOut);
		  sp.getAdultRoom(adultNum);
		  sp.getChildRoom(childNum);
		  sp.clickSearch().click();
		
	}
	@Test(dataProvider="SearchInvalidHotelData", dependsOnMethods="validLoginCredentials")
	public void searchHotelWithInValidData(String location,String hotel,String roomType,String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) {
		sp = new SearchHotelPage(driver);
		sp.getLocation(location);
		sp.getHotels(hotel);
		sp.getRoomType(roomType);
		sp.getRoomNumbers(roomNumber);
		sp.getDatePickIn().sendKeys(datePickIn);
		sp.getDatePickOut().sendKeys(datePickOut);
		sp.getAdultRoom(adultNum);
		sp.getChildRoom(childNum);
	    sp.clickSearch().click();
		
	}
	

	@Test(dataProvider="SearchValidHotelData", dependsOnMethods="validLoginCredentials")
	public void resetDetails(String location,String hotel,String roomType,String roomNumber,
			String datePickIn,String datePickOut,String adultNum,String childNum) {
		  sp=new SearchHotelPage(driver);
		  sp.getLocation(location);	
		  sp.getHotels(hotel);
		  sp.getRoomType(roomType);
		  sp.getRoomNumbers(roomNumber);
		  sp.getDatePickIn().sendKeys(datePickIn);
		  sp.getDatePickOut().sendKeys(datePickOut);
		  sp.getAdultRoom(adultNum);
		  sp.getChildRoom(childNum);
		  sp.clickReset().click();
		
	}

	
	  @DataProvider(name="ValidLogin")
	  public Object[][] readValidLogin() throws IOException{
		 sheetName="ValidLogin";
		 return BusinessFunctions.readExcel(filePath, sheetName);
	  }
	 
	  @DataProvider(name="SearchValidHotelData")
	  public Object[][] readSearchValidHotelData() throws IOException{
		 sheetName="SearchHotelValid";
		 return BusinessFunctions.readExcel(filePath, sheetName);
	  }
	  
	  @DataProvider(name="SearchInvalidHotelData")
	  public Object[][] readSearchInvalidHotelData() throws IOException{
			 sheetName="SearchHotelInValid";
			 return BusinessFunctions.readExcel(filePath, sheetName);
		  }
		  
	 @AfterTest
	 public void tearDown() {
     driver.close();
	  }
	
	

}
