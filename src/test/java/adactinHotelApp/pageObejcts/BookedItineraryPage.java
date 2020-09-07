package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookedItineraryPage {
	
	WebDriver driver;
	public BookedItineraryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="order_id_text")
	WebElement orderText;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement go;
	
	public WebElement getOrderText() {
		return orderText;
	}
	
	public WebElement clickGo() {
		return go;
	}

}
