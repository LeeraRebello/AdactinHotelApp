package adactinHotelApp.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;
import org.testng.Assert;

public class SelectHotelToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private SelectHotelPage shp;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "SelectHotel")
	public void verifyTitle(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		Assert.assertEquals(driver.getTitle(), "Adactin.com - Select Hotel");
	}

	@Test(dataProvider = "SelectHotel")
	public void selectHotelFunctionality(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		Thread.sleep(2000);
		shp.getRadioButton().click();
		Assert.assertTrue(shp.getRadioButton().isSelected());
		shp.clickContinue().click();
		Thread.sleep(3000);

	}

	@Test(dataProvider = "SelectHotel")
	public void selectHotelWithoutRadiobutton(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		shp.clickContinue().click();
		Thread.sleep(2000);
	}

	@Test(dataProvider = "SelectHotel")
	public void cancelFunctionality(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		Thread.sleep(2000);
		shp.clickCancel().click();

	}

	@Test(dataProvider = "SelectHotel")
	public void checkingAllFields(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		Assert.assertEquals(shp.getLocation(), location, "Locations do not match");
		Assert.assertEquals(shp.getHotel(), hotel, "Hotels do not match");
		Assert.assertEquals(shp.getRoomType(), roomType, "Room types do not match");
		Assert.assertEquals(shp.matchRoomNumber(), roomNumber, "Room numbers do not match");

	}

	@Test(dataProvider = "SelectHotel")
	public void showingUsername(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		String expectedUsername = "Hello" + " " + username + "!";
		Assert.assertNotEquals(shp.showUsername(), expectedUsername, "Username is showing as expected");
		Thread.sleep(2000);
	}

	@Test(dataProvider = "SelectHotel")
	public void navigationToSearchHotel(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		shp = new SelectHotelPage(driver);
		welcomeMenuInSelectHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp.getSearchHotel().click();

	}

	@Test(dataProvider = "SelectHotel")
	public void navigationToBookedItinerary(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		shp = new SelectHotelPage(driver);
		welcomeMenuInSelectHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp.getBookedItinerary().click();

	}

	@Test(dataProvider = "SelectHotel")
	public void navigationToChangePassword(String username, String password, String location, String hotel,
			String roomType, String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		shp = new SelectHotelPage(driver);
		welcomeMenuInSelectHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp.getChangePassword().click();

	}

	@Test(dataProvider = "SelectHotel")
	public void navigationToLogout(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		shp = new SelectHotelPage(driver);
		welcomeMenuInSelectHotel(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp.getLogout().click();

	}

	@Test(dataProvider = "SelectHotel")
	public void matchingTotalPrice(String username, String password, String location, String hotel, String roomType,
			String roomNumber, String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotelWithPositiveData(driver, username, password, location, hotel, roomType, roomNumber, datePickIn,
				datePickOut, adultNum, childNum);
		shp = new SelectHotelPage(driver);
		Assert.assertNotEquals(shp.getTotalPrice(), shp.matchTotalPrice(), "Total price is as expected");
		Thread.sleep(3000);

	}

	@DataProvider(name = "SelectHotel")
	public Object[][] readHotelWithValidData() throws IOException {
		sheetName = prop.getProperty("validSearchHotelSheet");
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
