package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage {
	
	WebDriver driver;
	 public ResetPasswordPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(id="username")
	 WebElement username;
	 
	 @FindBy(id="password")
	 WebElement password;
	 
	 @FindBy(id="login")
	 WebElement login;
	 
	 @FindBy(xpath="//a[contains(text(),'Click here')]")
	 WebElement resetPassword;
	 
	 @FindBy(id="emailadd_recovery")
	 WebElement recoveryEmail;
	 
	 @FindBy(xpath="//input[@value='Email Password']")
	 WebElement emailPassword;
	 
	 @FindBy(id="Reset")
	 WebElement reset;
	 

	 public WebElement getUsername() {
		 return username;
	 }
	 
	 public WebElement getPassword() {
		 return password;
	 }
	 
	 public WebElement getLogin() {
		 return login;
	 }
	 
	 public WebElement getResetPassword() {
		 return resetPassword;
	 }
	 public WebElement getEmailRecovery() {
		 return recoveryEmail;
	 }
	 
	 public WebElement clickEmailPassword() {
		 return emailPassword;
	 }
	 
	 public WebElement clickReset() {
		 return reset;
	 }

	  
	 
}
