package TestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Factory.BrowserFactory;
import Factory.DataProviderFactory;
import PageObjects.LoginPage;
import PageObjects.LogoutPage;
import Utility.GetDate;

public class TestCase002_BookFlight 
{
	public WebDriver driver;
	public WebDriverWait dwait;
	
	public LoginPage LoginObj;
	public LogoutPage LogOutObj;
	
	public static String Logfilename;
	ExtentReports report1;
	ExtentTest logger1;
	
	
	@BeforeClass
	public void setup() throws MalformedURLException, InterruptedException 
	{
		System.out.println(" Test Case TC002_Booking the Flight Started");
		Logfilename = "./Reports/" + "TC002_Booking the Flight for a Person" + GetDate.gettodaysdate() + ".html";
		report1 = new ExtentReports(Logfilename);
		System.out.println("************************");
		logger1 = report1.startTest("TC002_Booking the Flight for a Person");
		driver = BrowserFactory.getBrowser(DataProviderFactory.getProperty().getBrowser());
		String strURl = DataProviderFactory.getxls().getData("URL", 3, 1);
		driver.get(strURl);
		System.out.println("URL Loaded: " +strURl);
		if (DataProviderFactory.getProperty().getBrowser().equalsIgnoreCase("IE"))
		{
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			Thread.sleep(3000);
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		LoginObj = PageFactory.initElements(driver, LoginPage.class);
		dwait = new WebDriverWait(driver, 100);
		logger1.log(LogStatus.INFO, "Login Started");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		/*String uname = DataProviderFactory.ResultData().getData("TestResult001", 1, 3);
		String passwd = DataProviderFactory.ResultData().getData("TestResult001", 1, 4);*/
		String uname = "user@phptravels.com";
		String passwd = "demouser";
		LoginObj.LoginApplication(uname, passwd, logger1, report1);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
		
		
	}
	
	@AfterClass
	public void tearDown() throws Exception 
	{
		LogOutObj = PageFactory.initElements(driver, LogoutPage.class);
		report1.flush();
		System.out.println("Test Case going to Logout");
		logger1.log(LogStatus.INFO, "Logout Started");
		LogOutObj.LogoutApplication(logger1, report1);
		Thread.sleep(5000);
		logger1.log(LogStatus.PASS, "Logout Success");
		logger1.log(LogStatus.INFO, "End of Test Case");
		report1.flush();
		report1.endTest(logger1);
		
	}
	
	@Test
	public void bookingFlight() throws InterruptedException, IOException
	{
		System.out.println("Test Case Execution Started!!");
		Thread.sleep(10000);
	}
}
