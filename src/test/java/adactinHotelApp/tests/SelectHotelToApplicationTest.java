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

public class SelectHotelToApplicationTest extends BusinessFunctions {
	public WebDriver driver;
	SelectHotelPage shp;
	public LoginPage lp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "ValidLogin")
	public void validLoginCredentials(String username, String password) throws IOException {
		driver.get(prop.getProperty("url"));
		loginCredentials(driver, username, password);

	}

	@Test(dataProvider = "SearchValidHotelData", dependsOnMethods = "validLoginCredentials")
	public void searchHotelWithValidData(String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		SearchHotelPage sp = new SearchHotelPage(driver);
		searchHotel(driver, location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		Thread.sleep(2000);

	}

	@Test(dependsOnMethods = "searchHotelWithValidData")
	public void selectHotelFunctionality() {
		shp = new SelectHotelPage(driver);
		shp.getRadioButton().click();
		shp.clickContinue().click();
	}

	@DataProvider(name = "ValidLogin")
	public Object[][] readValidLogin() throws IOException {
		sheetName = "ValidLogin";
		return BusinessFunctions.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "SearchValidHotelData")
	public Object[][] readSearchValidHotelData() throws IOException {
		sheetName = "SearchHotelValid";
		return BusinessFunctions.readExcel(filePath, sheetName);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
