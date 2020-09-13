package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordFormPage {
	WebDriver driver;

	public ChangePasswordFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="current_pass")
	WebElement currentPassword;
	
	@FindBy(id="new_password")
	WebElement newPassword;
	
	
	@FindBy(id="re_password")
	WebElement rePassword;
	
	@FindBy(id="Submit")
	WebElement submit;
	
	public WebElement getCurrentPassword() {
		return currentPassword;
	}

	public WebElement getNewPassword() {
		return newPassword;
	}

	public WebElement getRePassword() {
		return rePassword;
	}
	
	public WebElement clickSubmit() {
		return submit;
	}


}
