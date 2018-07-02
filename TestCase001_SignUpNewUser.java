package TestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Factory.BrowserFactory;
import Factory.DataProviderFactory;
import PageObjects.HomePageObjects;
import Utility.GetDate;

public class TestCase001_SignUpNewUser 
{
	public WebDriver driver;
	public WebDriverWait dwait;
	
	public HomePageObjects HomePage;
	
	public static String Logfilename;
	ExtentReports report1;
	ExtentTest logger1;
	
	@BeforeClass
	public void setup() throws MalformedURLException, InterruptedException 
	{
		System.out.println("TeastCase_001_SignUp New Person record!!");
		Logfilename = "./Report01/" + "TC001_SignUp New Person record" + GetDate.gettodaysdate() + ".html";
		report1 = new ExtentReports(Logfilename);
		System.out.println("****************");
		logger1 = report1.startTest("TC001_SignUp Person record");
		driver = BrowserFactory.getBrowser(DataProviderFactory.getProperty().getBrowser());
		String strURL = DataProviderFactory.getFileData().getData("URL", 3, 1);
		driver.get(strURL);
		System.out.println("URL: " +strURL);
		if (DataProviderFactory.getProperty().getBrowser().equalsIgnoreCase("IE"))
		{
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			Thread.sleep(3000);
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		logger1.log(LogStatus.INFO, "Browser Launched and URL Loaded!");
		System.out.println("Browser Launched URL Loaded Successfully!");
	}
	
	
	@AfterClass
	public void tearDown() throws Exception 
	{
		report1.flush();
		System.out.println("Browser is getting closed!");
		logger1.log(LogStatus.PASS, "Browser Closed Successfully Success");
		logger1.log(LogStatus.INFO, "End of Test Case");
		driver.quit();
		report1.endTest(logger1);
	}
	
	
	@Test
	public void SignUpNewUserRecord () throws InterruptedException, IOException
	{
		logger1.log(LogStatus.INFO, "Creating New User for the application!");
		HomePage = PageFactory.initElements(driver, HomePageObjects.class);
		String TestResultSheetName = "TestResult001";
		HomePage.SheetName = "UserData";
		int TotalNumberOfUsers = DataProviderFactory.getxls().getcols(HomePage.SheetName);
		TotalNumberOfUsers = TotalNumberOfUsers - 1;
		
		logger1.log(LogStatus.INFO, "Number of Identity Creation in Self-Service Application : " + TotalNumberOfUsers);
		System.out.println("No. of Users: " +TotalNumberOfUsers);
		if (TotalNumberOfUsers == 0) 
		{
			logger1.log(LogStatus.ERROR, "Data Missing");
			logger1.log(LogStatus.FAIL, "Enter Identity details to Creat");
			DataProviderFactory.ResultData().setData(TestResultSheetName, 1, 0, "FALIED: No data provided!");
			report1.flush();
			Assert.fail("Test Case Failed due to No Data Provided!!");
		}
		
		for (int nCount =1; nCount<=TotalNumberOfUsers; nCount++)
		{
			try
			{
				if(HomePage.SignupUser(nCount))
				{
					logger1.log(LogStatus.PASS, "Customer Account created successfully!");
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 0, "PASS:User Added Successfully!");
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 1, HomePageObjects.strFirstName);
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 2, HomePageObjects.strLastName);
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 3, HomePageObjects.EmailId);
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 4, HomePageObjects.strPassword);
					DataProviderFactory.ResultData().setData(TestResultSheetName, nCount, 5, HomePageObjects.MobileNo);
					report1.flush();
				}
				
				else
				{
					logger1.log(LogStatus.FAIL, "Customer Account created Failed!");
					DataProviderFactory.ResultData().setData(TestResultSheetName, 1, 0, "FAILED to creat teh Customer Account!");
					report1.flush();
					Assert.fail("Customer Account creation Failed!");
					System.out.println("Failed to creat Customer Account");
				}
			}
			
			catch(AssertionError Exception)
			{
				System.out.println(Exception);
				System.out.println("Unable to create Person record!");
				DataProviderFactory.ResultData().setData(TestResultSheetName, 1, 0, "ERROR");
				report1.flush();
				Assert.fail("Test Case Failed because Assertion failure !!");
			}
		}
		
	}

}
