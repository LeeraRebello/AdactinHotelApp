package adactinHotelApp.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtils {

	
	public static Object[][] readExcel(String filePath, String sheetName) throws IOException {

		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet worksheet = workbook.getSheet(sheetName);

		int rowCount = worksheet.getLastRowNum();
		int column = worksheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][column];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = worksheet.getRow(i);
			for (int j = 0; j < column; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter formatter = new DataFormatter();
				String value = formatter.formatCellValue(cell);
				data[i - 1][j] = value;

			}
		}

		return data;
	}
	
	

	
	public static Object[][] readSingleRow(String filePath, String sheetName,int rowNumber) throws IOException {

		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\adactinHotelApp\\resources\\TestData_AdactinHotelApp.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet worksheet = workbook.getSheet(sheetName);

		int rowCount = worksheet.getLastRowNum();
		int column = worksheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[1][column];
		for (int i = 1; i<rowCount;i++) {
			XSSFRow row = worksheet.getRow(rowNumber);
			for (int j = 0; j < column; j++) {
				
					    XSSFCell cell = row.getCell(j);
					    		DataFormatter formatter = new DataFormatter();
								String value = formatter.formatCellValue(cell);
								data[i - 1][j] = value;
			}
			 break;
	
		}
		return data;
	}
		
	
	
}
	
	
	

