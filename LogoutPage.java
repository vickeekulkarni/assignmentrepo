package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Factory.BrowserFactory;
import Factory.DataProviderFactory;

public class LogoutPage 
{
	WebDriver d1;
	WebDriverWait wait;
	
		
	public LogoutPage(WebDriver driver) 
	{
		this.d1 = driver;
		wait = new WebDriverWait(driver, 100);
	}
	
	
	public void LogoutApplication(ExtentTest logger1, ExtentReports report1) throws InterruptedException
	{
		try 
		{
			Thread.sleep(2000);
			String $var = DataProviderFactory.ResultData().getData("TestResult001", 1, 1);
			WebElement wbLog = d1.findElement(By.xpath("//a[@data-toggle='dropdown']/i[contains(text(),'$var')]"));
			System.out.println("WebElement: " +wbLog);
			wait.until(ExpectedConditions.visibilityOf(wbLog));
			wbLog.click();
			System.out.println("Application Logged Out Successfully!");
			logger1.log(LogStatus.INFO, "Application Logged Out successfully!");
			report1.flush();
			
		} 
		catch (UnhandledAlertException e) 
		{
			e.printStackTrace();
			System.out.println("Excepetion in Logout Function");
			System.out.println(e);
			logger1.log(LogStatus.INFO, "Browser will exit now");
			report1.flush();
			BrowserFactory.quitApplication(d1);
			Assert.fail("Test Case Failed due to Excpetion in Logout Application!!");
		}
		
		d1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		d1.close();
		d1.quit();
	}

}
