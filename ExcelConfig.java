package DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hslf.usermodel.HSLFSheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelConfig {
	
	HSSFWorkbook wb ;
	HSSFSheet sh; 
	FileInputStream fp;
	
	String abPath;
	
	public ExcelConfig(String path)
	{
		abPath=path;
		try
		{
		fp = new FileInputStream(new File(path));
		wb = new HSSFWorkbook(fp);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(String sheetname, int row, int column)
	{
		DataFormatter formatter = new DataFormatter();
		Cell cell = wb.getSheet(sheetname).getRow(row).getCell(column);
		String data = formatter.formatCellValue(cell);
		return data; 
	}
	
	public int getrows(String sheetname)
	{
		sh=wb.getSheet(sheetname);
		int numberOfRows = sh.getPhysicalNumberOfRows();
		return numberOfRows;
		
	}
	
	public int getcols(String sheetname)
	{
		sh=wb.getSheet(sheetname);
		int noOfColumns = sh.getRow(0).getPhysicalNumberOfCells();
		
		return noOfColumns;
		
	}
	
	public void setData(String sheetname, int row, int column, String value) throws IOException
	{
		 sh = wb.getSheet(sheetname);
		 Cell cell = null; 
		 cell = sh.getRow(row).getCell(column);   
		 cell.setCellValue(value); 
		 fp.close();
		 FileOutputStream fileOut = new FileOutputStream(new File(abPath));
		//write this workbook to an Outputstream.
		wb.write(fileOut);	
		fileOut.flush();
		fileOut.close();	 
		
	}
}
