package adactinHotelApp.pageObejcts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookAHotelPage {

	WebDriver driver;
	
	public BookAHotelPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath="//a[contains(text(),'Back')]")
	WebElement backLink;
	
	@FindBy(id="username_show")
	WebElement showUsername;
	
	@FindBy(xpath="//a[contains(text(),'Search Hotel')]")
	WebElement searchHotel;
	
	@FindBy(xpath="//a[contains(text(),'Booked Itinerary')]")
	WebElement bookedItinerary;
	
	@FindBy(xpath="//a[contains(text(),'Change Password')]")
	WebElement changePassword;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logout;;
	
	@FindBy(id="hotel_name_dis")
	WebElement hotelName;
	
	@FindBy(id="location_dis")
	WebElement location;
	
	@FindBy(id="room_type_dis")
	WebElement roomType;
	
	@FindBy(id="room_num_dis")
	WebElement roomNum;
	
	@FindBy(id="total_days_dis")
	WebElement totalDays;
	
	@FindBy(id="price_night_dis")
	WebElement pricePerNight;
	
	@FindBy(id="total_price_dis")
	WebElement totalPrice;
	
	@FindBy(id="gst_dis")
	WebElement gst;
	
	@FindBy(id="final_price_dis")
	WebElement finalPrice;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="address")
	WebElement address;
	
	@FindBy(id="cc_num")
	WebElement creditCardNumber;
	
	@FindBy(id="cc_type")
	WebElement creditCardType;
	
	@FindBy(id="cc_exp_month")
	WebElement expiryMonth;
	
	@FindBy(id="cc_exp_year")
	WebElement expiryYear;
	
	@FindBy(id="cc_cvv")
	WebElement cvv;
	
	@FindBy(xpath="//input[@value='Book Now']")
	WebElement bookNow;
	
	@FindBy(css="input[name='cancel']")
	WebElement cancel;
	
	public WebElement getBackLink() {
		return backLink;
	}
	
	public String showUsername() {
		return showUsername.getAttribute("value");
	}
	
	public WebElement getsearchHotel() {
		return searchHotel;
	}
	
	public WebElement getBookedItinerary() {
		return bookedItinerary;
	}
	
	public WebElement getChangePassword() {
		return changePassword;
	}
	
	public WebElement getLogout() {
		return logout;
	}
	
	public String getLocation() {
		return location.getAttribute("value");
		
	}
	
	public String getHotelName() {
		return hotelName.getAttribute("value");
		
	}
	
	public String getRoomType() {
		return roomType.getAttribute("value");
		
	}
	
	public String getRoomNumber() {
		String text=roomNum.getAttribute("value");
		String[] parts=text.split(" ");
		String value=parts[0];
		//int number=Integer.parseInt(value);
		return value;
		
	}
	
	public int getTotalDays() {
		String text=totalDays.getAttribute("value");
		String[] parts=text.split(" ");
		String value=parts[0];
		int number=Integer.parseInt(value);
		return number;
		
	}
	
	public int getInteger(String text) {
		String[] parts=text.split(" ");
		String value=parts[0];
		int number=Integer.parseInt(value);
		return number;
	}
	
	public int getPrice(String text) {
		String[] parts=text.split(" ");
		String value=parts[2];
		int price=Integer.parseInt(value);
		return price;
	}
	
	
	public int getPricePerNight() {
	
		String text= pricePerNight.getAttribute("value");
		return getPrice(text);
		
	}
	
	
	public int getTotalPrice() {
		String text=totalPrice.getAttribute("value");
		return getPrice(text);
	}
	
	public double getGST() {
		String text=gst.getAttribute("value");
		String[] parts=text.split(" ");
		String value=parts[2];
		double price= Double.parseDouble(value);
		return price;
		
	}
	
	public double getFinalPrice() {
		String text=finalPrice.getAttribute("value");
		String[] parts=text.split(" ");
		String value=parts[2];
		double price=Double.parseDouble(value);
		return price;
		
	}
	
	public int matchTotalPrice() {
		
		int roomNumber=Integer.parseInt(getRoomNumber());
		int totalDays=getTotalDays();
		int price=getPricePerNight();
		int result=roomNumber*totalDays*price;
		return result;
		
	}
	
    public double matchFinalPrice() {
		int total=getTotalPrice();
		double gst=getGST();
		double finalPrice=total+gst;
		return finalPrice;
	}
	public WebElement getFirstName() {
		firstName.clear();
		return firstName;
	}
	
	public WebElement getLastName() {
		lastName.clear();
		return lastName;
	}
	
	public WebElement getBillingAddress() {
		address.clear();
		return address;
	}
	
	public WebElement getCreditCardNumber() {
		creditCardNumber.clear();
		return creditCardNumber;
	}
	
	
	public void getCreditCardType(String type) {
	
	    creditCardType.click();
		Select s=new Select(creditCardType);
		s.selectByValue(type);
	}
	

	public void getCCExpiryMonth(String month) {
		expiryMonth.click();
		Select s=new Select(expiryMonth);
		s.selectByValue(month);
	}
	
	
	public void getCCExpiryYear(String year) {
		expiryYear.click();;
		Select s=new Select(expiryYear);
		s.selectByValue(year);
	}
	
	public WebElement getCVV() {
		cvv.clear();
		return cvv;
	}
	
	public WebElement clickBookNow() {
		return bookNow;
	}
	
	public WebElement clickCancel() {
		return cancel;
	}
}
