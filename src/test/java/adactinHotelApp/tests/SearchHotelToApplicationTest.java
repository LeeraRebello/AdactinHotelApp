package adactinHotelApp.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
import junit.framework.Assert;

public class SearchHotelToApplicationTest extends BusinessFunctions {

	public WebDriver driver;
	public SearchHotelPage sp;
	public String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	public String sheetName;

	@BeforeTest()
	public void initialize() throws IOException {
		driver = initializeDriver();

	}


   @Test(dataProvider="SearchHotelValid")
	public void searchHotelWithValidData(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver, username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		Thread.sleep(2000);
	}
	
	
	@Test(dataProvider = "SearchHotelInvalid")
	public void searchHotelWithInValidData(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickSearch().click();
		Thread.sleep(2000);

	}

	

	@Test(dataProvider = "SearchHotelValid")
	public void ResetSearchHotel(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
		sp.clickReset().click();
		Thread.sleep(2000);

	}
	
	
	
	@Test(dataProvider = "SearchHotelValid")
	public void showUsername(String username,String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum)
			throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		searchHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum);
	    String expectedUsername="Hello"+" "+username+"!";
		Assert.assertEquals("Username not showing as expected",expectedUsername, sp.showUsername());
		Thread.sleep(2000);

	}
	
/*	
	
	@Test(dataProvider="ValidLoginData")
	public void changePassword(String username,String password,String currentPassword,
			String newPassword,String rePassword) throws IOException {
		driver.get(prop.getProperty("url"));
		sp = new SearchHotelPage(driver);
		changePassword(driver,username,password,currentPassword,newPassword,rePassword);
		ChangePasswordFormPage cp=new ChangePasswordFormPage(driver);
		cp.clickSubmit().click();
	}
	
*/
	@DataProvider(name = "SearchHotelValid")
	public Object[][] readHotelWithValidData() throws IOException {
		sheetName = "SearchHotelPositiveTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}

	@DataProvider(name = "SearchHotelInvalid")
	public Object[][] readHotelWithInvalidData() throws IOException {
		sheetName = "SearchHotelNegativeTest";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}
	
	@DataProvider(name = "ValidLoginData")
	public Object[][] readValidPasswordData() throws IOException {
		sheetName = "ChangePassword";
		return ExcelDataUtils.readExcel(filePath, sheetName);
	}


	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
