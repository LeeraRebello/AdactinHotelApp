package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;
	 public RegistrationPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(id="username")
	 WebElement username;
	 
	 @FindBy(id="password")
	 WebElement password;
	 
	 @FindBy(id="re_password")
	 WebElement rePassword;
	 
	 @FindBy(id="full_name")
	 WebElement fullName;
	 
	 @FindBy(id="email_add")
	 WebElement email;
	 
	 @FindBy(id="captcha")
	 WebElement captcha;
	 
	 @FindBy(id="captcha-form")
	 WebElement captchaForm;
	 
	 @FindBy(xpath="//input[@type='checkbox']")
	 WebElement checkbox;
	 
	 @FindBy(id="Submit")
	 WebElement register;
	 
	 @FindBy(id="Reset")
	 WebElement reset;
	 
	 public WebElement getUserName() {
		 return username;
	 }
	 
	 public WebElement getPassword() {
		 return password;
	 }
	 
	 public WebElement getConfirmPassword() {
		 return rePassword;
	 }
	 
	 public WebElement getFullName() {
		 return fullName;
	 }
	 
	 public WebElement getEmailAdd() {
		 return email;
	 }
	 
	 public WebElement getCaptcha() {
		 return captcha;
	 }
	 
	 public WebElement getCaptchaForm() {
		 return captchaForm;
	 }
	 
	 public WebElement clickCheckbox() {
		 return checkbox;
	 }
	 
	 public WebElement clickRegister() {
		 return register;
	 }
	 
	 public WebElement clickReset() {
		 return reset;
	 }
	 
	 
	 
	 
}
