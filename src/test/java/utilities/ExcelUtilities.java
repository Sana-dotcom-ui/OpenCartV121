package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities 
{
	public String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow wr;
	public XSSFCell wc;
	
	public ExcelUtilities(String path) {
		
		this.path= path;
		// TODO Auto-generated constructor stub
	}

	public int getRowCount(String sheetname) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		int rowCount =  ws.getLastRowNum();
		
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetname, int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		wr = ws.getRow(rownum);
		int cellCount = wr.getLastCellNum();
		
		wb.close();
		fi.close();
		
		return cellCount;
		
	}
	
	public String getCellData(String sheetname, int rownum,int cellnum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
		wr = ws.getRow(rownum);
		String cellData;
		
		try 
		{
			cellData = wr.getCell(cellnum).toString();
		}
		catch (Exception e) {
			// TODO: handle exception
			cellData ="";
		}
		
		wb.close();
		fi.close();
		return cellData;
		
	}
	
}
