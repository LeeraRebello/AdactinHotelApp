package adactinHotelApp.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class BookingAHotelToApplicationTest extends BusinessFunctions {
	
	public WebDriver driver;
	public BookAHotelPage bp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	
	 @Test(dataProvider="BookHotelValid")
		public void bookHotelWithValidData(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bp=new BookAHotelPage(driver);
			bp.clickBookNow().click();
			Thread.sleep(2000);
			
			
		}
	
	
	 @Test(dataProvider="BookHotelInvalid")
		public void bookHotelWithInValidData(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bp=new BookAHotelPage(driver);
			bp.clickBookNow().click();
			Thread.sleep(2000);
			
		}
	

	 @Test(dataProvider="BookHotelValid")
		public void cancelFunctionality(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			bp=new BookAHotelPage(driver);
			bp.clickCancel().click();
			Thread.sleep(2000);
			
		}
	 
	 

	 @Test(dataProvider="BookHotelValid")
		public void checkingAllFields(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			
			bp=new BookAHotelPage(driver);
		    //bp.clickBookNow().click();
			Assert.assertEquals(location, bp.getLocation(),"Locations do not match");
			Assert.assertEquals(hotel, bp.getHotelName(),"Hotels do not match");
			Assert.assertEquals(roomType, bp.getRoomType(),"Room types do not match");
			Assert.assertEquals(roomNumber, bp.getRoomNumber(),"Room numbers do not match");
		
			Thread.sleep(2000);
		
		}
	
	 @Test(dataProvider="BookHotelValid")
		public void matchingTotalPrice(String username,String password,String location, String hotel, String roomType, String roomNumber,
				String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
				String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
			driver.get(prop.getProperty("url"));
			bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
			
			bp=new BookAHotelPage(driver);
		  
			Assert.assertEquals(bp.getTotalPrice(),bp.getMatchPrice(), "Total price is not as expected");
			
			Thread.sleep(2000);
		
		}
	
	 
	
	
	@DataProvider(name = "BookHotelValid")
	public Object[][] readHotelValid() throws IOException {
		sheetName = "BookHotelPositiveTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "BookHotelInvalid")
	public Object[][] readHotelInvalid() throws IOException {
		sheetName = "BookHotelNegativeTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}
	
  
  
  @AfterTest
	public void tearDown() {
		driver.close();
	}

}
