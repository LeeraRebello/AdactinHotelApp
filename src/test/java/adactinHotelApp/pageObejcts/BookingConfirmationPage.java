package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingConfirmationPage {

	WebDriver driver;
	public BookingConfirmationPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="order_no")
	WebElement orderNumber;
	
	@FindBy(id="search-hotel")
	WebElement searchHotel;
	
	@FindBy(id="my_itinerary")
	WebElement myItinerary;
	
	@FindBy(id="logout")
	WebElement logout;
	
	
	public WebElement getOrderNumber() {
		return orderNumber;
	}
	
	public WebElement clickSearchHotel() {
		return searchHotel;
	}
	
	public WebElement clickItinerary() {
		return myItinerary;
	}
	
	public WebElement clickLogout() {
		return logout;
	}
}
