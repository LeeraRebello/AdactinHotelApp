package adactinHotelApp.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import adactinHotelApp.pageObejcts.BookAHotelPage;
import adactinHotelApp.pageObejcts.BookedItineraryPage;
import adactinHotelApp.pageObejcts.BookingConfirmationPage;
import adactinHotelApp.pageObejcts.ChangePasswordFormPage;
import adactinHotelApp.pageObejcts.ForgotPasswordFormPage;
import adactinHotelApp.pageObejcts.LoginAgainPage;
import adactinHotelApp.pageObejcts.LoginPage;
import adactinHotelApp.pageObejcts.RegistrationPage;
import adactinHotelApp.pageObejcts.ResetPasswordPage;
import adactinHotelApp.pageObejcts.SearchHotelPage;
import adactinHotelApp.pageObejcts.SearchResultsPage;
import adactinHotelApp.pageObejcts.SelectHotelPage;
import org.apache.logging.log4j.*;

public class BusinessFunctions {

	public WebDriver driver;
	public Properties prop;
	public LoginPage lp;
	public RegistrationPage urp;
	public SearchHotelPage sp;
	public BookAHotelPage bp;
	public SelectHotelPage shp;
	public BookingConfirmationPage bcp;
	public BookedItineraryPage bip;
	public SearchResultsPage srp;
	public LoginAgainPage lap;
	public ForgotPasswordFormPage fp;
	public ResetPasswordPage rp;
	public static Logger log=LogManager.getLogger(BusinessFunctions.class.getName());

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");

		prop.load(fis);

		String browserName = prop.getProperty("browser");
		// String browserName=System.getProperty("browser");

		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.contains("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "./libs/geckodriver.exe");
		} else if (browserName.contains("IE")) {
			System.setProperty("WebDriver.ie.driver", "./libs/IEDriverServer.exe");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void loginCredentials(WebDriver driver, String username, String password) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");
		prop.load(fis);
		lp = new LoginPage(driver);
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLogin().click();
	}
	
	public void forgotPassword(WebDriver driver,String email) throws IOException {	
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");
		prop.load(fis);
		lp = new LoginPage(driver);
		lp.getForgotPassword().click();
		fp=new ForgotPasswordFormPage(driver);
		
		
	}
	
	public void resetPassword(WebDriver driver,String username,String password,String email) throws IOException {	
		loginCredentials(driver,username,password);
		lp.getResetPassword().click();
				
	}
	
	
	public void welcomeMenuInSearchHotel(WebDriver driver,String username,String password) throws IOException {
		loginCredentials(driver,username,password);
	}
	

	public void changePassword(WebDriver driver,String username,String password,String currentPassword,String newPassword,
			String rePassword) throws IOException {		
		loginCredentials(driver,username,password);
		sp=new SearchHotelPage(driver);	
		ChangePasswordFormPage cp=sp.changePassword();
		cp.getCurrentPassword().sendKeys(currentPassword);
		cp.getNewPassword().sendKeys(newPassword);
		cp.getRePassword().sendKeys(rePassword);
		
		
		
		
	}
	
	
	public void searchHotel(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException {
		
		loginCredentials(driver,username,password);
	    sp = new SearchHotelPage(driver);
		sp.getLocation(location);
		sp.getHotels(hotel);
		sp.getRoomType(roomType);
		sp.getRoomNumbers(roomNumber);
		sp.getDatePickIn().sendKeys(datePickIn);
		sp.getDatePickOut().sendKeys(datePickOut);
		sp.getAdultRoom(adultNum);
		sp.getChildRoom(childNum);
		
		
		
	}
	

	public void welcomeMenuInSelectHotel(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException {
		searchHotelWithPositiveData(driver,username,password,location,hotel,roomType,roomNumber,datePickIn,datePickOut,
				adultNum,childNum);
		
		
		
	}
	
	public void searchHotelWithPositiveData(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException {
		
		loginCredentials(driver,username,password);
	    sp = new SearchHotelPage(driver);
		sp.getLocation(location);
		sp.getHotels(hotel);
		sp.getRoomType(roomType);
		sp.getRoomNumbers(roomNumber);
	    sp.getCheckInDate().sendKeys(datePickIn);
	    sp.getCheckOut().sendKeys(datePickOut);
		sp.getAdultRoom(adultNum);
		sp.getChildRoom(childNum);
		sp.clickSearch().click();
		
		
		
	}
	
	public void welcomeMenuInBookAHotel(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum) throws IOException {
		searchHotelWithPositiveData(driver,username,password,location,hotel,roomType,roomNumber,datePickIn,datePickOut,
				adultNum,childNum);
		shp=new SelectHotelPage(driver);
		shp.getRadioButton().click();
		shp.clickContinue().click();	
		
	}

	
	
	public void bookHotel(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {	
		searchHotelWithPositiveData(driver,username,password,location,hotel,roomType,roomNumber,datePickIn,datePickOut,
				adultNum,childNum);
		shp=new SelectHotelPage(driver);
		shp.getRadioButton().click();
		shp.clickContinue().click();
	    bp=new BookAHotelPage(driver);
		bp.getFirstName().sendKeys(firstName);
		bp.getLastName().sendKeys(lastName);
		bp.getBillingAddress().sendKeys(address);
		bp.getCreditCardNumber().sendKeys(ccNum);
		bp.getCreditCardType(ccType);
		bp.getCCExpiryMonth(expMonth);
		bp.getCCExpiryYear(expYear);
		bp.getCVV().sendKeys(cvv);
		
		
	}
	
	
	
	public void welcomeMenuInBookingConfirmation(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {
		bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bp.clickBookNow().click();
		
			
	}

	public void bookingConfirm(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {
		bookHotel(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bp=new BookAHotelPage(driver);
		bp.clickBookNow().click();
		bcp=new BookingConfirmationPage(driver);
	
	
	}
	
	public void welcomeMenuInBookedItinerary(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {
		bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bcp.clickItinerary().click();
		
		
			
	}
	
	
	public void bookedItinerary(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {
		bookingConfirm(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
				lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bcp=new BookingConfirmationPage(driver);
		String orderNumber=bcp.getOrderNumber();
		bcp.clickItinerary().click();
	    bip=new BookedItineraryPage(driver);
		bip.getOrderText().sendKeys(orderNumber);	
		
	}
	
	public void searchResults(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException {
		bookedItinerary(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
		bip=new BookedItineraryPage(driver);
		bip.clickGo().click();
		srp=new SearchResultsPage(driver);
		
		
		
	}
	public void loginAgain(WebDriver driver, String username, String password,String location, String hotel, String roomType, String roomNumber,
			String datePickIn, String datePickOut, String adultNum, String childNum,String firstName,String lastName,String address,String ccNum,
			String ccType,String expMonth,String expYear,String cvv) throws IOException{
				searchResults(driver,username,password,location, hotel, roomType, roomNumber, datePickIn, datePickOut, adultNum, childNum,firstName,
					lastName,address,ccNum,ccType,expMonth,expYear,cvv);
				srp=new SearchResultsPage(driver);
				srp.logout().click();
				lap=new LoginAgainPage(driver);		
				
			}
	
	

	public void newUserDetails(WebDriver driver, String username, String password, String confirmPassword,
			String fullname, String email) throws InterruptedException, IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");
		prop.load(fis);
	    urp = new RegistrationPage(driver);
		urp = new RegistrationPage(driver);
		lp = new LoginPage(driver);
		lp.getNewUser();
		urp.getUserName().sendKeys(username);
		urp.getPassword().sendKeys(password);
		urp.getConfirmPassword().sendKeys(confirmPassword);
		urp.getFullName().sendKeys(fullname);
		urp.getEmailAdd().sendKeys(email);
		String captchaForm = urp.getCaptcha().getText();
		Thread.sleep(3000);
		urp.getCaptchaForm().sendKeys(captchaForm);
		Thread.sleep(2000);
		//urp.clickCheckbox().click();

	}
	
	
	
}
