package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAHotelPage {

	WebDriver driver;
	public BookAHotelPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
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
	
	public WebElement getFirstName() {
		return firstName;
	}
	
	public WebElement getLastName() {
		return lastName;
	}
	
	public WebElement getBillingAddress() {
		return address;
	}
	
	public WebElement getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public WebElement getCreditCardType() {
		return creditCardType;
	}
	
	public WebElement getCCExpiryMonth() {
		return expiryMonth;
	}
	
	public WebElement getCCExpiryYear() {
		return expiryYear;
	}
	
	public WebElement getCVV() {
		return cvv;
	}
	
	public WebElement clickBookNow() {
		return bookNow;
	}
	
	public WebElement clickCancel() {
		return cancel;
	}
}
