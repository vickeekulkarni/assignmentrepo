package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import Factory.DataProviderFactory;
import Utility.Utility;

public class HomePageObjects 
{
	
	public static String strFirstName = ""; 
	public static String strLastName = "";
	public static String strFullName = "";
	public static String strPassword = "";
	public static String EmailId = "";
	public static String MobileNo = "";
	public String SheetName = "";
	
	WebDriver driver;
	WebDriverWait wait;
	Utility objUtility;
	ExtentTest logger1;

	public HomePageObjects(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,100);
	}
	
	//Home Page Objects
	
	
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	WebElement RegisterLnk;
	
	@FindBy(xpath = "//a[@data-toggle='dropdown' and contains(text(),'My Account')]")
	WebElement MyAccountLnk;
	
	@FindBy(xpath = "//a[@class='btn btn-default btn-block form-group']")
	WebElement SignupBtn;
	
	
	//SignUp Form
	@FindBy(xpath = "//input[@name='firstname']")
	WebElement FirstName;
	
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement LastName;
	
	@FindBy(xpath = "//input[@name='phone']")
	WebElement MobileNumber;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement Email;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement Password;
	
	@FindBy(xpath = "//input[@name='confirmpassword']")
	WebElement ConfirmPassword;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement RegisterSignUpBtn;
	
	
	public boolean SignupUser(int count) throws InterruptedException
	{
		boolean bIsSignup = true;
		
		try 
		{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(SignupBtn));
			SignupBtn.click();			
			strFirstName = DataProviderFactory.getFileData().getData(SheetName, 1, count) +Utility.getRandomNumber(3);
			wait.until(ExpectedConditions.visibilityOf(FirstName));
			FirstName.sendKeys(strFirstName);
			System.out.println("First Name: " +strFirstName);
			strLastName = DataProviderFactory.getFileData().getData(SheetName, 2, count)+Utility.getRandomNumber(3);
			wait.until(ExpectedConditions.visibilityOf(LastName));
			LastName.sendKeys(strLastName);
			System.out.println("Last Name: " +strLastName);
			wait.until(ExpectedConditions.visibilityOf(MobileNumber));
			MobileNo = DataProviderFactory.getFileData().getData(SheetName, 6, count)+Utility.getRandomNumber(8);
			MobileNumber.sendKeys(MobileNo);
			System.out.println("Mobile Number: " +MobileNo);
			strPassword = DataProviderFactory.getFileData().getData(SheetName, 3, count)+Utility.getRandomNumber(3);
			wait.until(ExpectedConditions.visibilityOf(Password));
			Password.sendKeys(strPassword);
			System.out.println("Password set: " +strPassword);
			wait.until(ExpectedConditions.visibilityOf(ConfirmPassword));
			ConfirmPassword.sendKeys(strPassword);
			EmailId = strLastName +"." +strFirstName+ "@testauto.com";
			wait.until(ExpectedConditions.visibilityOf(Email));
			Email.sendKeys(EmailId);
			wait.until(ExpectedConditions.visibilityOf(RegisterSignUpBtn));
			RegisterSignUpBtn.click();
			
			
		}
		
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			bIsSignup = false;
			e.printStackTrace();
		}
		return bIsSignup;
		
	}
	

}
