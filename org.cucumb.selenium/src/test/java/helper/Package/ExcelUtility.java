package helper.Package;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtility {

	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	public ExcelUtility(String xlFilePath) throws Exception, IOException {
		try {
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
		}catch(IOException e) {
			e.printStackTrace();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum)

	{

		int col_Num = -1;
		try {

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}

			row = sheet.getRow(rowNum);
			cell = row.getCell(col_Num);

			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + col_Num + " does not exist  in Excel";
		}
	}

	@SuppressWarnings("deprecation")
	public void putCellData(String sheetName, String colName, int rowNum, String strCellValue)


	{

		int col_Num = -1;
		try
		{

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for(int i = 0; i < row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			
			
			
				   
			try { 
			row = sheet.getRow(rowNum);
			cell = row.getCell(col_Num);
			}
			catch(Exception e) {
				
				
				sheet.createRow(rowNum);
				
			}
			
			
			sheet.getRow(rowNum).createCell(col_Num).setCellValue(strCellValue);
			
			FileOutputStream fout = new FileOutputStream("C:\\Users\\Adhitya\\eclipse-workspace\\org.cucumb.selenium\\data\\flipkart.xlsx");
			
			workbook.write(fout);
			
			fout.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println( "row " + rowNum + " or column " + col_Num + " does not exist  in Excel");

		}
	}
}