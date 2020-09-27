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
import org.testng.Assert;

import org.apache.logging.log4j.*;

public class BookingConfirmationToApplicationTest extends BusinessFunctions {

	private WebDriver driver;
	private BookingConfirmationPage bcp;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private static Logger log=LogManager.getLogger(BookingConfirmationToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "BookHotelValid")
	public void verifyTitle(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		Assert.assertNotEquals(driver.getTitle(), "Adactin.com - Hotel Booking Confirmation");
        log.error("Title does not match");
	}

	@Test(dataProvider = "BookHotelValid")
	public void bookingConfirmation(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		Thread.sleep(2000);
		bcp.clickItinerary().click();
		Thread.sleep(2000);
		log.info("Booking confirmed and Booked itinerary page will be displayed");

	}

	@Test(dataProvider = "BookHotelValid")
	public void clickingOnSearchHotel(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp.clickSearchHotel().click();

	}

	@Test(dataProvider = "BookHotelValid")
	public void clickingOnLogout(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp.clickLogout().click();
		Thread.sleep(2000);
	}

	@Test(dataProvider = "BookHotelValid")
	public void matchingPriceWithData(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		// product of room numbers,days,price per night should match with total price
		Assert.assertNotEquals(bcp.getTotalPrice(), bcp.matchTotalPrice(), "Totals price is as expected");
        log.error("Total price is not as expected");
		Thread.sleep(2000);

	}

	@Test(dataProvider = "BookHotelValid")
	public void matchingFinalPrice(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		// Adding gst to total price and matching with final billed price
		Assert.assertEquals(bcp.getFinalPrice(), bcp.matchFinalPrice(), 0.001, "Final price is not as expected");
		Thread.sleep(2000);
		log.info("Final price is as expected");
	}

	@Test(dataProvider = "BookHotelValid")
	public void verifyingOrdeNumber(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException {
		driver.get(prop.getProperty("url"));
		bcp = new BookingConfirmationPage(driver);
		bookingConfirm(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut,
				adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		Assert.assertNotNull("Order number should not be null", bcp.getOrderNumber());
        log.info("Order number is generated");
	}

	@Test(dataProvider = "BookHotelValid")
	public void showUsername(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp = new BookingConfirmationPage(driver);
		String expectedUsername = "Hello" + " " + username + "!";
		Assert.assertEquals(bcp.showUsername(), expectedUsername, "Username is not showing as expected");
		log.info("Username is showing as expected");
	}

	@Test(dataProvider = "BookHotelValid")
	public void navigationToSearchHotel(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp = new BookingConfirmationPage(driver);
		bcp.getSearchHotel().click();

	}

	@Test(dataProvider = "BookHotelValid")
	public void navigationToBookedItinerary(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp = new BookingConfirmationPage(driver);
		bcp.getBookedItinerary().click();

	}

	@Test(dataProvider = "BookHotelValid")
	public void navigationToChangePassword(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp = new BookingConfirmationPage(driver);
		bcp.getChangePassword().click();

	}

	@Test(dataProvider = "BookHotelValid")
	public void navigationToLogout(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookingConfirmation(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bcp = new BookingConfirmationPage(driver);
		bcp.getLogout().click();

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
