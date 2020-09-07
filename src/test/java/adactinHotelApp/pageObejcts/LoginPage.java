package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	 public LoginPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 
	 @FindBy(id="username")
	 WebElement username;
	 
	 @FindBy(id="password")
	 WebElement password;
	 
	 @FindBy(id="login")
	 WebElement login;
	 
	 @FindBy(xpath="//a[contains(text(),'Forgot Password')]")
	 WebElement forgotPassword;
	 
	 @FindBy(xpath="//a[contains(text(),'Click here')]")
	 WebElement resetPassword;
	 
	 
	 
	 @FindBy(xpath="//a[contains(text(),'New User Register Here')]")
	 WebElement newUser;
	 
	 public WebElement getUsername() {
		 return username;
	 }
	 
	 public WebElement getPassword() {
		 return password;
	 }
	 
	 public WebElement getLogin() {
		 return login;
	 }
	 
		
	 public ForgotPasswordFormPage getForgotPassword() {
		 forgotPassword.click();
		 ForgotPasswordFormPage fp=new ForgotPasswordFormPage(driver);
		 return fp;
	 }
	 
	 public ForgotPasswordFormPage getResetPassword() {
		 resetPassword.click();
		 ForgotPasswordFormPage fp=new ForgotPasswordFormPage(driver);
		 return fp;
	 }
	  
	
	 
	 public RegistrationPage getNewUser() {
		 newUser.click();
		 RegistrationPage rp=new RegistrationPage(driver);
		 return rp;
	 }
	 
	 

}
