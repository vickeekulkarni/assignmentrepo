package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Factory.BrowserFactory;
import Utility.Utility;

public class LoginPage 
{
	public static String EmailId = "";
	public static String strPassword = "";
	
	WebDriver driver;
	WebDriverWait wait;
	Utility objUtility;
	ExtentTest logger1;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement LoginEmail;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement LoginPassword;
	
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	WebElement LoginBtn;
	
	
	public void LoginApplication(String usrname, String pwd, ExtentTest logger1,ExtentReports report1)
	{
		try 
		{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(LoginEmail));
			LoginEmail.sendKeys(usrname);
			wait.until(ExpectedConditions.visibilityOf(LoginPassword));
			LoginPassword.sendKeys(pwd);
			wait.until(ExpectedConditions.visibilityOf(LoginBtn));
			LoginBtn.click();
			System.out.println("User loggedin Successfully!");
			logger1.log(LogStatus.PASS, "Login Successful");
			
		} 
		catch (InterruptedException e) 
		{
			if (driver.getPageSource().contains("Invalid Email or Password"))
			{
				System.out.println("Invalid username or passwd");
				logger1.log(LogStatus.INFO, "Browser will exit now");
				report1.flush();
				BrowserFactory.quitApplication(driver);
				Assert.fail("Test Case Failed due to Invalid Username or Passwd !!");	
				
			}
			else
			{			
				System.out.println("Excpetion in Login loginApplication");
				System.out.println(e);	
				logger1.log(LogStatus.INFO, "Browser will exit now");
				report1.flush();
				BrowserFactory.quitApplication(driver);
				Assert.fail("Test Case Failed due to Excpetion in login Application!!");
			}	
		}
		
	}

}
