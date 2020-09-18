package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

	WebDriver driver;
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Show all')]")
	WebElement showAll;
	
	@FindBy(xpath="//input[@name='ids[]']")
	WebElement checkbox;
	
	@FindBy(id="search_hotel")
	WebElement searchHotel;
	
	@FindBy(xpath="//input[@value='Cancel Selected']")
	WebElement cancelSelected;
	
	@FindBy(id="logout")
	WebElement logout;
	
	@FindBy(id="hotel_name_374880")
	WebElement hotelName;
	
	@FindBy(id="location_374880")
	WebElement location;
	
	@FindBy(id="rooms_374880")
	WebElement room;
	
	@FindBy(id="first_name_374880")
	WebElement firstName;
	
	@FindBy(id="last_name_374880")
	WebElement lastName;
	
	@FindBy(id="arr_date_374880")
	WebElement arrDate;
	
	@FindBy(id="dep_date_374880")
	WebElement depDate;
	
	@FindBy(id="no_days_374880")
	WebElement noOfDays;
	
	@FindBy(id="room_type_374880")
	WebElement roomType;
	
	@FindBy(id="price_night_374880")
	WebElement pricePerNight;
	
	@FindBy(id="total_price_374880")
	WebElement totalPrice;
	
	public WebElement getShowAll() {
		return showAll;
	}
	
	public WebElement getCheckbox() {
		return checkbox;
	}

	public WebElement getCancelSelected() {
		return cancelSelected;
	}
	public WebElement getSearchHotel() {
		return searchHotel;
	}
	
	public WebElement logout() {
		return logout;
	}
	
}
