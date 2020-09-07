package adactinHotelApp.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {

		WebDriver driver;
		public SelectHotelPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
		
		@FindBy(id="radiobutton_0")
		WebElement radioButton;
		
		@FindBy(xpath="//input[@type='submit']")
		WebElement continueClick;
		
		@FindBy(css="input[name='cancel']")
		WebElement cancel;
		
		public WebElement getRadioButton() {
			return radioButton;
		}
		
		public WebElement clickContinue() {
			return continueClick;
		}
		
		public WebElement clickCancel() {
			return cancel;
		}
}
