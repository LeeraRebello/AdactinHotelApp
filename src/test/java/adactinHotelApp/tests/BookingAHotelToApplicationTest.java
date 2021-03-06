package adactinHotelApp.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class BookingAHotelToApplicationTest extends BusinessFunctions {

	private WebDriver driver;
	private BookAHotelPage bp;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private static Logger log=LogManager.getLogger(BookingAHotelToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "BookHotel")
	public void verifyTitle(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		Assert.assertEquals(driver.getTitle(), "Adactin.com - Book A Hotel");
		log.info("Title matched");

	}

	@Test(dataProvider = "BookHotelValid")
	public void bookHotelWithValidData(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		log.debug("Opening Book A Hotel page");
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		bp.clickBookNow().click();
		log.info("Hotel booked");
		Thread.sleep(2000);

	}

	@Test(dataProvider = "BookHotelInvalid")
	public void bookHotelWithInValidData(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		bp.clickBookNow().click();
		Thread.sleep(2000);
		log.error("Provided data is invalid to book the hotel");

	}

	@Test(dataProvider = "BookHotelValid")
	public void cancelFunctionality(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		bp.clickCancel().click();

	}

	@Test(dataProvider = "BookHotelValid")
	public void backLink(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		bp.getBackLink().click();
		log.info("Navigating to Search Hotel");
		Thread.sleep(2000);

	}

	@Test(dataProvider = "BookHotelValid")
	public void checkingAllFields(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);

		bp = new BookAHotelPage(driver);
		Assert.assertEquals(bp.getLocation(), location, "Locations do not match");
		Assert.assertEquals(bp.getHotelName(), hotel, "Hotels do not match");
		Assert.assertEquals(bp.getRoomType(), roomType, "Room types do not match");
		Assert.assertEquals(bp.getRoomNumber(), roomNumber, "Room numbers do not match");
		log.info("Fields matched");

	}

	@Test(dataProvider = "BookHotelValid")
	public void matchingTotalPrice(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);

		bp = new BookAHotelPage(driver);

		Assert.assertNotEquals(bp.getTotalPrice(), bp.matchTotalPrice(), "Total price is as expected");
		Thread.sleep(2000);
		log.error("Total price is not as expected");

	}

	@Test(dataProvider = "BookHotelValid")
	public void matchingFinalPrice(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		Assert.assertEquals(bp.getFinalPrice(), bp.matchFinalPrice(), 0.001, "Final price is not as expected");
		Thread.sleep(2000);
		log.info("Final price is as expected");

	}

	@Test(dataProvider = "BookHotel")
	public void showingUsername(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		bp = new BookAHotelPage(driver);
		String expectedUsername = "Hello" + " " + username + "!";
		Assert.assertEquals(bp.showUsername(), expectedUsername, "Username is not showing as expected");
        log.info("Username is showing as expected");
	}

	@Test(dataProvider = "BookHotel")
	public void navigationToSearchHotel(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		bp = new BookAHotelPage(driver);
		bp.getsearchHotel().click();

	}

	@Test(dataProvider = "BookHotel")
	public void navigationToBookedItinerary(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		bp = new BookAHotelPage(driver);
		bp.getBookedItinerary().click();

	}

	@Test(dataProvider = "BookHotel")
	public void navigationToChangePassword(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		bp = new BookAHotelPage(driver);
		bp.getChangePassword().click();

	}

	@Test(dataProvider = "BookHotel")
	public void navigationToLogout(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		welcomeMenuInBookAHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		bp = new BookAHotelPage(driver);
		bp.getLogout().click();

	}

	@Test(dataProvider = "BookHotelCCExpiry")
	public void checkingCCExpiryYearInPast(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum,
			String firstName, String lastName, String address, String ccNum, String ccType, String expMonth,
			String expYear, String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		bookHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum,
				childNum, firstName, lastName, address, ccNum, ccType, expMonth, expYear, cvv);
		bp = new BookAHotelPage(driver);
		bp.clickBookNow().click();
		Thread.sleep(2000);
		log.error("Credit card expiry field is accepting the year in past");

	}

	@DataProvider(name = "BookHotelValid")
	public Object[][] readHotelValid() throws IOException {
		sheetName = prop.getProperty("validBookAHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "BookHotelInvalid")
	public Object[][] readHotelInvalid() throws IOException {
		sheetName = prop.getProperty("invalidBookAHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "BookHotel")
	public Object[][] readHotel() throws IOException {
		sheetName = prop.getProperty("validSearchHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "BookHotelCCExpiry")
	public Object[][] readHotelCCExpiry() throws IOException {
		sheetName = prop.getProperty("invalidBookAHotelSheet");
		return ExcelDataUtils.readSingleRow(filePath, sheetName, 10);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
