package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.pageObejcts.BookingConfirmationPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;
import junit.framework.Assert;

import org.apache.logging.log4j.*;

public class BookingConfirmationToApplicationTest extends BusinessFunctions {
	
	public WebDriver driver;
	public BookingConfirmationPage bcp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		
	}
	


	 @Test(dataProvider="BookHotelValid")
		public void bookingConfirmation(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bcp.clickItinerary().click();
			
			
		}
	 
	 @Test(dataProvider="BookHotelValid")
		public void clickingOnSearchHotel(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bcp.clickSearchHotel().click();			
			
		}
	 
	 
	 @Test(dataProvider="BookHotelValid")
		public void clickingOnLogout(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bcp.clickLogout().click();			
			Thread.sleep(2000);
		}
	 

		 
	 @Test(dataProvider="BookHotelValid")
		public void matchingPriceWithData(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			//product of room numbers,days,price per night should match with total price
			Assert.assertEquals("Total price is not as expected", bcp.matchPrice(), bcp.getTotalPrice());
			Thread.sleep(2000);
			
		}
	 
	 @Test(dataProvider="BookHotelValid")
		public void matchingFinalPrice(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			//Adding gst to total price and matching with final billed price
			Assert.assertEquals("Final price is not as expected",bcp.matchFinalPrice(), bcp.getFinalPrice());
			Thread.sleep(2000);
			
		}
	 
	 
	 
	 	@Test(dataProvider="BookHotelValid")
	 	public void verifyingOrdeNumber(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException {
	 		driver.get(prop.getProperty("url"));
			bcp=new BookingConfirmationPage(driver);
			bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			Assert.assertNotNull("Order number should not be null", bcp.getOrderNumber());
	 		
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
