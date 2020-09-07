package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchHotelPage {
	
	WebDriver driver;
	public SearchHotelPage(WebDriver driver) {
	this.driver=driver;
	 PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="location")
	WebElement location;
	
	@FindBy(id="hotels")
	WebElement hotel;
	
	@FindBy(id="room_type")
	WebElement roomType;
	
	@FindBy(id="room_nos")
	WebElement roomNumber;
	
	@FindBy(id="datepick_in")
	WebElement datePickIn;
	
	@FindBy(id="datepick_out")
	WebElement datePickOut;
	
	@FindBy(id="adult_room")
	WebElement adultRoom;
	
	@FindBy(id="child_room")
	WebElement childRoom;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement search;
	
	@FindBy(css="input[value='Reset']")
	WebElement reset;
	
	public void getLocation(String placeName) {
		location.click();
		Select s=new Select(location);
		s.selectByVisibleText(placeName);
		
	}
	
	public void getHotels(String hotelName) {
		hotel.click();
		Select s= new Select(hotel);
		s.selectByVisibleText(hotelName);
				
	}
	
	public void getRoomType(String type) {
	    roomType.click();
	    Select s= new Select(roomType);
	    s.selectByVisibleText(type);
	}
	
	public void getRoomNumbers(String value) {
		roomNumber.click();
		Select s= new Select(roomNumber);
		s.selectByValue(value);
		
	}
	
	public WebElement getDatePickIn() {
		datePickIn.clear();
		return datePickIn;
	}
	
	public WebElement getDatePickOut() {
		datePickOut.clear();
		return datePickOut;		
	}
	
	public void getAdultRoom(String value) {
		adultRoom.click();
		Select s=new Select(adultRoom);
		s.selectByValue(value);
				
	}
	
	public void getChildRoom(String value) {
		childRoom.click();
		Select s= new Select(childRoom);
		s.selectByValue(value);
	}
	
	public WebElement clickSearch() {
		return search;		
	}
	
	public WebElement clickReset() {
		return reset;		
	}
	
	

}
