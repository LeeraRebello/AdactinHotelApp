package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordFormPage {
	
	WebDriver driver;
	 public ForgotPasswordFormPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(id="emailadd_recovery")
	 WebElement recoveryEmail;
	 
	 @FindBy(xpath="//input[@value='Email Password']")
	 WebElement emailPassword;
	 
	 @FindBy(id="Reset")
	 WebElement reset;
	 
	 @FindBy(xpath="//a[contains(text(),'Go back to Login page')]")
	 WebElement goBack;
	 
	 public WebElement getEmailRecovery() {
		 return recoveryEmail;
	 }
	 
	 public WebElement clickEmailPassword() {
		 return emailPassword;
	 }
	 
	 public WebElement clickReset() {
		 return reset;
	 }
	 
	 public WebElement goBackToLoginPage() {
		 return goBack;
	 }

}
