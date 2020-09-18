package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchHotelPage {

	WebDriver driver;

	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="username_show")
	WebElement usernameShow;
	
	@FindBy(id = "location")
	WebElement location;

	@FindBy(id = "hotels")
	WebElement hotel;

	@FindBy(id = "room_type")
	WebElement roomType;

	@FindBy(id = "room_nos")
	WebElement roomNumber;

	@FindBy(id = "datepick_in")
	WebElement datePickIn;

	@FindBy(id = "datepick_out")
	WebElement datePickOut;

	@FindBy(id = "adult_room")
	WebElement adultRoom;

	@FindBy(id = "child_room")
	WebElement childRoom;
	
	@FindBy(xpath="//a[@href='ChangePassword.php']")
	WebElement changePassword;
	

	@FindBy(xpath = "//input[@value='Search']")
	WebElement search;

	@FindBy(css = "input[value='Reset']")
	WebElement reset;
	
	@FindBy(xpath="//a[contains(text(),'Search Hotel')]")
	WebElement searchHotel;
	
	@FindBy(xpath="//a[contains(text(),'Booked Itinerary')]")
	WebElement bookedItinerary;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logout;
	
	@FindBy(id="checkin_span")
	WebElement checkinError;
	
	@FindBy(id="checkout_span")
	WebElement checkoutError;
	
	public String showCheckInError() {
		return checkinError.getText();
	}
	
	public String showCheckOutError() {
		return checkoutError.getText();
	}
	
	public String showUsername() {
		return usernameShow.getAttribute("value");
	}
	
	public ChangePasswordFormPage changePassword() {
		changePassword.click();
		ChangePasswordFormPage cp=new ChangePasswordFormPage(driver);
		return cp;
	}
	
	public WebElement searchHotel() {
		return searchHotel;
	}
	
	public WebElement bookedItinerary() {
		return bookedItinerary;
	}
	
	public WebElement logout() {
		return logout;
	}
	
	public WebElement getChangePassword() {
		return changePassword;
	}

	public void getLocation(String placeName) {
		location.click();
		Select s = new Select(location);
		if (placeName.trim().isEmpty() || placeName.equals("")) {

			s.selectByIndex(0);

		} else {
			s.selectByVisibleText(placeName);
		}

	}

	public void getHotels(String hotelName) {
		hotel.click();
		Select s = new Select(hotel);
		if (hotelName.trim().isEmpty() || hotelName.equals("")) {

			s.selectByIndex(0);

		} else {
			s.selectByVisibleText(hotelName);
		}

	}

	public void getRoomType(String type) {
		roomType.click();
		Select s = new Select(roomType);
		if (type.trim().isEmpty() || type.equals("")) {

			s.selectByIndex(0);

		} else {
			s.selectByVisibleText(type);
		}

	}

	public void getRoomNumbers(String value) {
		roomNumber.click();
		Select s = new Select(roomNumber);
		s.selectByValue(value);

	}

	public WebElement getDatePickIn() {
		datePickIn.clear();
		return datePickIn;
	}
	
	public WebElement getCheckInDate() {
		return datePickIn;
	}
	
	public WebElement getCheckOut() {
		return datePickOut;
	}

	public WebElement getDatePickOut() {
		datePickOut.clear();
		return datePickOut;
	}

	public void getAdultRoom(String value) {
		adultRoom.click();
		Select s = new Select(adultRoom);
		s.selectByValue(value);

	}

	public void getChildRoom(String value) {
		childRoom.click();
		if (!value.isEmpty()) {

			Select s = new Select(childRoom);
			s.selectByValue(value);
		}

	}

	public WebElement clickSearch() {
		return search;
	}

	public WebElement clickReset() {
		return reset;
	}

}
