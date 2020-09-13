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
import junit.framework.Assert;

public class SelectHotelToApplicationTest extends BusinessFunctions {
	public WebDriver driver;
	SelectHotelPage shp;
	public String sheetName;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}

    @Test(dataProvider="SelectHotel")
	public void selectHotelFunctionality(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		shp=new SelectHotelPage(driver);
		shp.getRadioButton().click();
		shp.clickContinue().click();
		Thread.sleep(3000);
		
	}
	
	@Test(dataProvider="SelectHotel")
	public void selectHotelWithoutRadiobutton(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		shp=new SelectHotelPage(driver);
		shp.clickContinue().click();
		Thread.sleep(2000);
	}
	
	
	@Test(dataProvider="SelectHotel")
	public void cancelFunctionality(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		shp=new SelectHotelPage(driver);
		shp.clickCancel().click();
		Thread.sleep(2000);
	}
	
	
	@Test(dataProvider="SelectHotel")
	public void checkingAllFields(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		shp=new SelectHotelPage(driver);
		Assert.assertEquals("Locations do not match",location, shp.getLocation());
		Assert.assertEquals("Hotels do not match",hotel, shp.getHotel());
		Assert.assertEquals("Room types do not match",roomType, shp.getRoomType());
		Assert.assertEquals("Room numbers do not match",roomNumber, shp.getRoom());
		Assert.assertEquals("Arrival dates do not match", datePickIn, shp.getArrivalDate());
		Assert.assertEquals("Departure Dates do not match", datePickOut, shp.getDepartureDate());
		Thread.sleep(2000);
	}
	
	
	@DataProvider(name = "SelectHotel")
	public Object[][] readHotelWithValidData() throws IOException {
		sheetName = "SearchHotelPositiveTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}


	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
