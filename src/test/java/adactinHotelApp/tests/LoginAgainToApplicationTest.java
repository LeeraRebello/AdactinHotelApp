package adactinHotelApp.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adactinHotelApp.pageObejcts.LoginAgainPage;
import adactinHotelApp.resources.BusinessFunctions;
import adactinHotelApp.utils.ExcelDataUtils;

public class LoginAgainToApplicationTest extends BusinessFunctions {
	private WebDriver driver;
	private LoginAgainPage lap;
	private String sheetName;
	private String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx";
	private static Logger log=LogManager.getLogger(LoginAgainToApplicationTest.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();

	}
	
	@Test(dataProvider="BookHotelValid")
	public void loginAgainToApplication(String username,String password,String location, String hotel, String roomType, String roomNumber,
	String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,
	String lastName,String address,String ccNum,String ccType,String expMonth,String expYear,String cvv) throws IOException, InterruptedException {
		driver.get(prop.getProperty("url"));
		log.debug("Trying to logout from the application");
		loginAgain(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		lap=new LoginAgainPage(driver);
		Thread.sleep(2000);
		lap.clickLoginAgain().click();
		log.info("Login page will be displayed again");
		Thread.sleep(2000);
		
		
		
			
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
