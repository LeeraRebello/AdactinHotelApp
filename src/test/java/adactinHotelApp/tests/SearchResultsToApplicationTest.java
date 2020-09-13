package adactinHotelApp.tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.pageObejcts.BookedItineraryPage;
import adactinHotelApp.pageObejcts.BookingConfirmationPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SearchResultsPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;
import junit.framework.Assert;

public class SearchResultsToApplicationTest extends BusinessFunctions {
	public SearchResultsPage sp;
	public LoginPage lp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}
	
	@Test(dataProvider="BookHotelValid")
	public void cancelSelectedHotel(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		srp.getCheckbox().click();
		srp.getCancelSelected().click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
			
	}
	
	@Test(dataProvider="BookHotelValid")
	public void dismissAlert(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		srp.getCheckbox().click();
		srp.getCancelSelected().click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);
		srp.getCheckbox().click();
		Thread.sleep(2000);
			
	}
	
	@Test(dataProvider="BookHotelValid")
	public void clickSearchHotel(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		srp.getSearchHotel().click();
		Thread.sleep(2000);
			
	}
	
	
	@Test(dataProvider="BookHotelValid")
	public void clickLogout(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		srp.logout().click();
		Thread.sleep(2000);
			
	}
	

	
	 @DataProvider(name = "BookHotelValid")
		public Object[][] readHotelValid() throws IOException {
			sheetName = "BookHotelPositiveTest";
			return ExcelDataUtils.readExcel(filePath, sheetName);
		}
  
  @AfterTest
	public void tearDown() {
		driver.close();
	}
}
