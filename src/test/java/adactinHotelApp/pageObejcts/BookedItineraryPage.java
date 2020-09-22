package adactinHotelApp.pageObejcts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookedItineraryPage {
	
	WebDriver driver;
	public BookedItineraryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="username_show")
	WebElement showUserName;
	
	@FindBy(xpath="//a[contains(text(),'Search Hotel')]")
	WebElement searchHotelInMenu;
	
	@FindBy(xpath="//a[contains(text(),'Booked Itinerary')]")
	WebElement bookedItinerary;
	
	@FindBy(xpath="//a[contains(text(),'Change Password')]")
	WebElement changePassword;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logoutInMenu;
	
	
	@FindBy(id="order_id_text")
	WebElement orderText;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement go;
	
   @FindBy(xpath="(//table[@class='login']/tbody/tr)[2]/td/table/tbody/tr/td[2]/input")
   WebElement orderIDText;
   
   @FindBy(xpath="(//table[@class='login']/tbody/tr)[2]/td/table/tbody/tr/td[2]")
   WebElement orderID;
   
 
   @FindBy(xpath="(//table[@class='login']/tbody/tr)[2]/td/table/tbody/tr/td[2]/input/parent::td/preceding-sibling::td/input")
   WebElement checkBox;
   
   @FindBy(xpath="//input[@value='Cancel Selected']")
   WebElement cancelSelected;
   
   
   
   
   
   @FindBy(xpath="(//table[@class='login']/tbody/tr)[2]")
   WebElement table;
   
   
   
   
   public WebElement getTable() {
	   return table;
   }
   
   public int getRowCount() {
	  return table.findElements(By.xpath("//td/table/tbody/tr")).size();

   }

   
   public int  getColumnCount() {
	   return table.findElements(By.xpath("//td/table/tbody/tr/td[1]")).size();
   }
   
   public String orderIdOfDeletedOrder(String orderId) {
	   int count= driver.findElements(By.xpath("(//table[@class='login']/tbody/tr)[2]/td/table/tbody/tr/td[2]")).size();
	   for(int i=0; i<count-1;i++) {
		   String text= table.findElements(By.xpath("//td/table/tbody/tr/td[2]/input")).get(i).getAttribute("value");
		   if(text.equals(orderId)) {
			   System.out.println("Not deleted");
		   }
		   
	   }
	   return null;
   }

   public void cancelMultipleOrders(int number) {
	   int count=getColumnCount();
	   WebDriverWait wait= new WebDriverWait(driver,20);
	   for(int i=0;i<count;i++) {
		  while(i<number)
		   {
			   WebElement element=wait.until(ExpectedConditions.elementToBeClickable(table.findElements(By.xpath("//td/table/tbody/tr/td[2]/preceding-sibling::td/input")).get(i)));
			   if(!element.isSelected()) {
				   element.click();
				   break;
			   }
				   
		   } 
	   }
   }
   
   public void cancelSingleBooking(String orderId) {
	   int count=getColumnCount();
	   WebDriverWait wait= new WebDriverWait(driver,20);
	   for(int i=0; i<count;i++) {
		   String value= table.findElements(By.xpath("//td/table/tbody/tr/td[3]/input")).get(i).getAttribute("value");
		   if(value.contains(orderId)) {
		   WebElement element= wait.until(ExpectedConditions.elementToBeClickable(table.findElement(By.xpath("//td/table/tbody/tr/td[3]/input"))));
			 if(!element.isSelected())
				 element.click();
		   }
	   }
   }
   
   
	public WebElement getOrderText() {
		return orderText;
	}
	
	public WebElement clickGo() {
		return go;
	}
	
	public String getCancellationOrderID(){
		return orderIDText.getAttribute("value");
	}
	
	
	public WebElement getCancellationCheckBox() {
		return checkBox;
	}
	
	public WebElement clickCancelSelected() {
		return cancelSelected;
	}
	
	public String showUsername() {
		return showUserName.getAttribute("value");
	}
    
	public WebElement getSearchHotel() {
		return searchHotelInMenu;
	}
	
	public WebElement getBookedItinerary() {
		 return bookedItinerary;
    }
	 
	public WebElement getChangePassword() {
		return changePassword;
	}
	
    public WebElement getLogout() {
    	return logoutInMenu;
    }

}
