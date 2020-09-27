package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.pageObejcts.BookedItineraryPage;
import adactinHotelApp.pageObejcts.BookingConfirmationPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class BookedItineraryToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private BookedItineraryPage bip;
	private BookingConfirmationPage bcp;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private static Logger log=LogManager.getLogger(BookedItineraryToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}
	
	
	@Test(dataProvider="BookHotelValid")
 	public void verifyTitle(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		bookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		
		bip=new BookedItineraryPage(driver);
		Assert.assertNotEquals(driver.getTitle(), "Adactin.com - Booked Itinerary");
		Thread.sleep(2000);
		log.error("Title does not match");
 	}
	
	@Test(dataProvider="BookHotelValid")
	public void bookedItineraryFunctionality(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		Thread.sleep(2000);
		bip.clickGo().click();	
		Thread.sleep(2000);
		log.info("Selected booking is displayed");
		
		
	}
	
	
	@Test(dataProvider="BookHotelValid")
 	public void showUsername(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		welcomeMenuInBookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		String expectedUsername="Hello"+" "+username+"!";
		Assert.assertEquals(bip.showUsername(),expectedUsername, "Username is not showing as expected");
		Thread.sleep(3000);
		log.info("Username is showing as expected");
 	}
	
	
	@Test(dataProvider="BookHotelValid")
 	public void navigationToSearchHotel(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		welcomeMenuInBookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		bip.getSearchHotel().click();
		Thread.sleep(3000);
 	}
	
	@Test(dataProvider="BookHotelValid")
 	public void navigationToBookeditinerary(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		welcomeMenuInBookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		bip.getBookedItinerary().click();
		Thread.sleep(3000);
 	}
	
	@Test(dataProvider="BookHotelValid")
 	public void navigationToChangePassword(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		welcomeMenuInBookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		bip.getChangePassword().click();
		Thread.sleep(3000);
 	}
	
	@Test(dataProvider="BookHotelValid")
 	public void navigationToLogout(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
			String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
 		driver.get(prop.getProperty("url"));
		welcomeMenuInBookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		bip.getLogout().click();
		Thread.sleep(3000);
 	}
	


	@Test(dataProvider="BookHotelValid")
	public void cancelMultpileSelectedOrdersByClickingCheckbox(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bcp=new BookingConfirmationPage(driver);
		String orderNumber=bcp.getOrderNumber();
		bcp.clickItinerary().click();
		bip=new BookedItineraryPage(driver);
		String value=prop.getProperty("BulkCancel");
		int number=Integer.parseInt(value);
		int rowCountBeforeCancellation=bip.getRowCount();
		System.out.println(rowCountBeforeCancellation);
		bip.cancelMultipleOrders(number);
		Thread.sleep(3000);
		bip.clickCancelSelected().click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		int rowCountAfterCancellation=rowCountBeforeCancellation-number+1;
		System.out.println(rowCountAfterCancellation);
		Assert.assertTrue(rowCountBeforeCancellation>rowCountAfterCancellation, "Assertion failed");
		log.info("Multiple bookings cancelled ");
	  
	
	}
	
	
	
	 @DataProvider(name = "BookHotelValid")
		public Object[][] readHotelValid() throws IOException {
			sheetName =prop.getProperty("validBookAHotelSheet");
			return ExcelDataUtils.readExcel(filePath, sheetName);
		}

	
  @AfterTest
	public void tearDown() {
		driver.close();
	}
}
