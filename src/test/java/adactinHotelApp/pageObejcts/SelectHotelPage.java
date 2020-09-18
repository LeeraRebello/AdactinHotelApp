package adactinHotelApp.pageObejcts;

import org.apache.xmlbeans.impl.soap.Text;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectHotelPage {

		WebDriver driver;
		public SelectHotelPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//a[contains(text(),'Search Hotel')]")
		WebElement searchHotel;
		
		@FindBy(xpath="//a[contains(text(),'Booked Itinerary')]")
		WebElement bookedItinerary;
		
		@FindBy(xpath="//a[contains(text(),'Change Password')]")
		WebElement changePassword;
		
		@FindBy(xpath="//a[contains(text(),'Logout')]")
		WebElement logout;
		
		
		@FindBy(id="radiobutton_0")
		WebElement radioButton;
		
		@FindBy(xpath="//input[@type='submit']")
		WebElement continueClick;
		
		@FindBy(css="input[id='cancel']")
		WebElement cancel;
		
		@FindBy(css="input[id='hotel_name_0']")
		WebElement hotel;
		
		@FindBy(xpath="//input[@id='location_0']")
		WebElement location;
		
		@FindBy(xpath="//input[@id='arr_date_0']")
		WebElement arrivalDate;
		
		@FindBy(xpath="//input[@id='dep_date_0']")
		WebElement departureDate;
		
		@FindBy(xpath="//input[@id='rooms_0']")
		WebElement room;
		
		@FindBy(css="input[id='no_days_0']")
		WebElement noOfDays;
		
		@FindBy(css="input[id='room_type_0']")
		WebElement roomType;
		
		@FindBy(css="input[id='price_night_0']")
		WebElement pricePerNight;
		
		@FindBy(css="input[id='total_price_0']")
		WebElement totalPrice;
		
		@FindBy(xpath="//td[contains(text(),'Hello Username!')]")
		WebElement username;
		
		public String showUsername() {
			return username.getAttribute("value");
		}
        
		public WebElement getSearchHotel() {
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
		public WebElement getRadioButton() {
			return radioButton;
		}
		
		public WebElement clickContinue() {
			return continueClick;
		}
		
		public WebElement clickCancel() {
			WebDriverWait wait=new WebDriverWait(driver,10);
			return wait.until(ExpectedConditions.visibilityOf(cancel));
			
		}
		
		public String getLocation() {
			return location.getAttribute("value");
			
		}
		
		public String getHotel() {
			return hotel.getAttribute("value");
			
		}
		public String getRoomType() {
			return roomType.getAttribute("value");
			
		}
	
		public int getPrice(String text) {
			String[] parts=text.split(" ");
			String value=parts[2];
			int price=Integer.parseInt(value);
			return price;

			}
		
		public int getInteger(String text) {
			String[] parts=text.split(" ");
			String value=parts[0];
			int number=Integer.parseInt(value);
			return number;
		}
		
		public int getRoom() {
			String text=room.getAttribute("value");
			return getInteger(text);
			
		}
		
		public String matchRoomNumber() {
			String text=room.getAttribute("value");
			String[] parts=text.split(" ");
			String value= parts[0];
			return value;
		}
		
		public int getNoOfDays() {
			String text=noOfDays.getAttribute("value");
			return getInteger(text);
			
		}
		public int matchTotalPrice() {
			int roomNumber=getRoom();
			int noOfDays=getNoOfDays();
			int pricePerNight=getPricePerNight();
			int result= roomNumber*noOfDays*pricePerNight;
			return result;
		}
		
		
		public int getPricePerNight() {
			String text=pricePerNight.getAttribute("value");
		    return getPrice(text);
		}
		
		public int getTotalPrice() {
			String text=totalPrice.getAttribute("value");
		    return getPrice(text);
			
		}
		
		public String getArrivalDate() {
			return arrivalDate.getAttribute("value");
		}
		
		public String getDepartureDate() {
			return departureDate.getAttribute("value");
		}
		
	
}
