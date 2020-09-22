package adactinHotelApp.tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookedItineraryPage;
import adactinHotelApp.pageObejcts.SearchResultsPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchResultsToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private SearchResultsPage srp;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
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
	

	@Test(dataProvider="BookHotelValid")
	public void clickShowAll(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		Thread.sleep(2000);
		srp.getShowAll().click();
		Thread.sleep(2000);
			
	}
	
	@Test(dataProvider="BookHotelValid")
	public void cancelSelectedBookingAndCompringAgainstTotalOrders(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		
		searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		srp=new SearchResultsPage(driver);
		String orderID=srp.getOrderId();
		srp.getCheckbox().click();
		BookedItineraryPage bip=srp.bookedItineraryPage();
		int beforeRowCount=bip.getRowCount();
		System.out.println(beforeRowCount);
		String cancelledOrderId=bip.orderIdOfDeletedOrder(orderID);
		Assert.assertNull(cancelledOrderId);		
				
	}
	
	
	
	 @DataProvider(name = "BookHotelValid")
		public Object[][] readHotelValid() throws IOException {
			sheetName = prop.getProperty("validBookAHotelSheet");
			return ExcelDataUtils.readExcel(filePath, sheetName);
		}
  
  @AfterTest
	public void tearDown() {
		driver.close();
	}
}
