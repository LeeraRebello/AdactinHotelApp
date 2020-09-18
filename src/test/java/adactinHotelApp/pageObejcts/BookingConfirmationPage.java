package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingConfirmationPage {

	WebDriver driver;
	public BookingConfirmationPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username_show")
	WebElement showUserName;
	
	@FindBy(xpath="//a[contains(text(),'Search Hotel')]")
	WebElement searchHotelInMenu;
	
	@FindBy(xpath="//a[contains(text(),'Booked Itinerary')]")
	WebElement bookedItinerary;
	
	@FindBy(xpath="//a[contains(text(),'Change Password')]")
	WebElement changePassword;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logoutInMenu;
	

	
	
	@FindBy(id="hotel_name")
	WebElement hotelName;
	
	@FindBy(id="location")
	WebElement location;
	
	@FindBy(id="room_type")
	WebElement roomType;
	
	@FindBy(id="arrival_date")
	WebElement arrivalDate;
	
	@FindBy(id="departure_text")
	WebElement departureDate;
	
	@FindBy(id="total_rooms")
	WebElement totalRooms;
	
	@FindBy(id="adults_room")
	WebElement adult;
	
	@FindBy(id="children_room")
	WebElement child;
	
	@FindBy(id="order_no")
	WebElement orderNumber;
	
	@FindBy(id="search_hotel")
	WebElement searchHotel;
	
	@FindBy(id="my_itinerary")
	WebElement myItinerary;
	
	@FindBy(id="logout")
	WebElement logout;
	
	@FindBy(xpath="//input[@id='room_num_dis']")
	WebElement roomNumbers;
	
	@FindBy(css="input[id='total_days_dis']")
	WebElement totalDays;
	
	@FindBy(css="input[id='price_night_dis']")
	WebElement pricePerNight;
	
	@FindBy(css="input[id='total_price_dis']")
	WebElement totalPrice;
	
	@FindBy(css="input[id='gst_dis']")
	WebElement gst;
	
	@FindBy(css="input[id='final_price_dis']")
	WebElement finalPrice;
	
	//@FindBy(id="first_name")
	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstName;
	
	//@FindBy(id="last_name")
	@FindBy(xpath="//input[@id='last_name']")
	WebElement lastName;
	
	
	//@FindBy(id="address")
	@FindBy(xpath="//textarea[@id='address']")
	WebElement address;
	
	
	
	public String showUsername() {
		return showUserName.getAttribute("value");
	}
    
	public WebElement getSearchHotel() {
		return searchHotelInMenu;
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
	
	
	public String getFirstName() {
		return firstName.getAttribute("value");
	}
	
	public String getLastName() {
		return lastName.getAttribute("value");
		
	}
	
	public String getAddress() {
		return address.getText();
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
	
	public int getRoomNumber() {
		String text=roomNumbers.getAttribute("value");
		return getInteger(text);
	}
	
	public int getTotalDays() {
		String text=totalDays.getAttribute("value");
		return getInteger(text);
	}
	
	
	public int getPricePerNight() {
		String text=pricePerNight.getAttribute("value");
		return getPrice(text);
	}
	
	public int getTotalPrice() {
		String text=totalPrice.getAttribute("value");
		int price=getPrice(text);
		return price;
		
	}
	
	public double getGST() {
		String text=gst.getAttribute("value");
		//return getPrice(text);
		String[] parts=text.split(" ");
		String value=parts[2];
		double price=Double.parseDouble(value);
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
		int roomNumber = getRoomNumber();
		int totalDays= getTotalDays();
		int price = getPricePerNight();
		int result= roomNumber*totalDays*price;
		return result;
	}
	
	public double calculateGST() {
		double gst= (getTotalPrice()*10)/100;
		return gst;
		
		
	}
	 
	public double matchFinalPrice() {
		int total=getTotalPrice();
		double gst=getGST();
		double finalPrice=total+gst;
		return finalPrice;
	}
	
	
	public String getOrderNumber() {
		String text=orderNumber.getAttribute("value");
		return text;
	}
	
	public WebElement clickSearchHotel() {
		return searchHotel;
	}
	
	public WebElement clickItinerary() {
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(myItinerary));
		return myItinerary;
	}
	
	public WebElement clickLogout() {
		return logoutInMenu;
	}
}
