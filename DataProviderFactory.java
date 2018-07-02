package Factory;

import DataProvider.ConfigurationFile;
import DataProvider.ExcelConfig;

public class DataProviderFactory {

	public static ExcelConfig getFileData()
	{
		ExcelConfig config = new ExcelConfig("./TestData/MasterData.xls");
		return config ; 
	}
	
	public static ConfigurationFile getProperty()
	{
		ConfigurationFile config = new ConfigurationFile("./Configuration/project.properties");
		return config;
	}
	
	public static ExcelConfig getxls()
	{
		ExcelConfig config = new ExcelConfig("./TestData/MasterData.xls");
		return config ;
	}
	
	public static ExcelConfig ResultData()
	{
		ExcelConfig config = new ExcelConfig("./TestData/ResultData.xls");
		return config ; 
	}

	
	
}

