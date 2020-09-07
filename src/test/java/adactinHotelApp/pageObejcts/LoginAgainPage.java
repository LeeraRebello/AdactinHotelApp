package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginAgainPage {

	WebDriver driver;
	public LoginAgainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Click here to login again']")
	WebElement loginAgain;
	
	public WebElement clickLoginAgain() {
		return loginAgain;
	}
	
}
