package adactinHotelApp.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import adactinHotelApp.pageObejcts.LoginPage;


public class BusinessFunctions {
	
	public WebDriver driver;
	public Properties prop;
	
	
	
	public WebDriver initializeDriver() throws IOException {
		
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");
	
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");		
	   //String browserName=System.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver","./libs/chromedriver.exe");
			driver=new ChromeDriver();	
		}else if(browserName.contains("firefox")) {
			System.setProperty("WebDriver.gecko.driver","./libs/geckodriver.exe");
		}else if(browserName.contains("IE")) {
			System.setProperty("WebDriver.ie.driver","./libs/IEDriverServer.exe");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	public void loginCredentials(WebDriver driver,String username,String password) throws IOException {
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\adactinHotelApp\\resources\\data.properties");
		prop.load(fis);
		LoginPage lp=new LoginPage(driver);
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLogin().click();
	}
	
	
	public static Object[][] readExcel(String filePath,String sheetName) throws IOException {
		
		FileInputStream inputStream=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet worksheet= workbook.getSheet(sheetName);
		
		int rowCount=worksheet.getLastRowNum();
		int column=worksheet.getRow(0).getLastCellNum();
		Object[][] data= new Object[rowCount][column];
		for(int i=1;i<=rowCount;i++) {
			XSSFRow row=worksheet.getRow(i);
			for(int j=0;j<column;j++) {
				XSSFCell cell=row.getCell(j);
				DataFormatter formatter=new DataFormatter();
				String value=formatter.formatCellValue(cell);
				data[i-1][j]=value;
				
			}
		}
		
		return data;
	}
	

}