package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	
	@DataProvider(name="LoginData" )
	public String[][] providedata() throws IOException
	{
		String path =".\\testData\\LoginData.xlsx";
		ExcelUtilities excelutil = new ExcelUtilities(path);
		
		int rowCount = excelutil.getRowCount("Sheet1");
		int cellCount = excelutil.getCellCount("Sheet1", 1);
		
		System.out.println("RowCount:"+rowCount+" CellCount:"+cellCount);
		
		String loginData [][] = new String[rowCount][cellCount];
		for(int i=1; i<=rowCount;i++)
		{
			for(int j=0; j<cellCount;j++)
			{
				loginData[i-1][j] = excelutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
		
	}
	

}
