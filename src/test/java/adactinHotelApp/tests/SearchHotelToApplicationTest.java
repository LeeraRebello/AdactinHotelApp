package adactinHotelApp.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import adactinHotelApp.pageObejcts.ChangePasswordFormPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;
import org.testng.Assert;

public class SearchHotelToApplicationTest extends BusinessFunctions {

	private WebDriver driver;
	private SearchHotelPage sp;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private String sheetName;
	private static Logger log=LogManager.getLogger(SearchHotelToApplicationTest.class.getName());

	@BeforeTest()
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "ValidLoginData")
	public void verifyTitle(String username, String password) throws IOException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);
		sp = new SearchHotelPage(driver);
		Assert.assertEquals(driver.getTitle(), "Adactin.com - Search Hotel");
		log.info("Title matched");

	}

	@Test(dataProvider = "SearchHotelValid", priority = 1)
	public void searchHotelWithValidData(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		log.debug("Opening Search Hotel page");
		searchHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum);
		Thread.sleep(2000);
		sp.clickSearch().click();
		Thread.sleep(2000);
		log.info("Hotel search completed");
	}

	@Test(dataProvider = "SearchHotelInvalid")
	public void searchHotelWithInValidData(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum);
		sp.clickSearch().click();
		log.error("Invalid data provided for searching hotel");
		

	}

	@Test(dataProvider = "SearchHotelValid")
	public void ResetSearchHotel(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum);
		sp.clickReset().click();
		

	}

	@Test(dataProvider = "ValidLoginData")
	public void showUsername(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		welcomeMenuInSearchHotel(driver, username, password);
		String expectedUsername = "Hello" + " " + username + "!";
		Assert.assertEquals(expectedUsername, sp.showUsername(), "Username is not as expected ");
		
	}
	
	
	@Test(dataProvider = "ValidLoginData")
	public void navigationToSearchHotel(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		welcomeMenuInSearchHotel(driver, username, password);
		sp.searchHotel().click();
		
	}

	@Test(dataProvider = "ValidLoginData")
	public void navigationToBookedItinerary(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		welcomeMenuInSearchHotel(driver, username, password);
		sp.bookedItinerary().click();
		

	}

	@Test(dataProvider = "ValidLoginData")
	public void navigationToChangePassword(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		welcomeMenuInSearchHotel(driver, username, password);
		sp.getChangePassword().click();
		Thread.sleep(2000);

	}

	@Test(dataProvider = "ValidLoginData")
	public void navigationToLogout(String username, String password) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		welcomeMenuInSearchHotel(driver, username, password);
		sp.logout().click();
		
	}

	@Test(dataProvider = "SearchHotelWithInvalidDate")
	public void searchHotelWithCheckInGreaterThanCheckOutDate(String username, String password, String location,
			String hotel, String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum,
			String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum);
		sp.clickSearch().click();
		Assert.assertEquals("Check-In Date shall be before than Check-Out Date", sp.showCheckInError());
		Assert.assertEquals("Check-Out Date shall be after than Check-In Date", sp.showCheckOutError());
		Thread.sleep(2000);

	}

	@Test(dataProvider = "SearchHotelWithDatesInPast")
	public void searchHotelWithDatesInPast(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum);
		sp.clickSearch().click();
		Thread.sleep(2000);

	}

	@DataProvider(name = "SearchHotelValid")
	public Object[][] readHotelWithValidData() throws IOException {
		sheetName = prop.getProperty("validSearchHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "SearchHotelInvalid")
	public Object[][] readHotelWithInvalidData() throws IOException {
		sheetName = prop.getProperty("invalidSearchHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] readValidLoginData() throws IOException {
		sheetName = prop.getProperty("validLoginSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "SearchHotelWithInvalidDate")
	public Object[][] readHotelWithCheckInGreaterThanCheckOut() throws IOException {
		sheetName = prop.getProperty("invalidSearchHotelSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName, 9);
	}

	@DataProvider(name = "SearchHotelWithDatesInPast")
	public Object[][] readHotelWithDatesInthePast() throws IOException {
		sheetName = prop.getProperty("invalidSearchHotelSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName, 10);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
