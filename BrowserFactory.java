package Factory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BrowserFactory 
{
	
	public static WebDriver getBrowser(String browserName) throws MalformedURLException
	{
		WebDriver driver=null;
		RemoteWebDriver driver1=null;
		
		if(browserName.equalsIgnoreCase("Firefox"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
	        capabilities.setCapability("requireWindowFocus", true);
	        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); //Accept unexpected alerts
			driver=new FirefoxDriver();
		}
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".//lib//chromedriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-web-security");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
	        capabilities.setCapability("requireWindowFocus", true);
	        
	        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); //Accept unexpected alerts
            driver = new ChromeDriver();
           
		}
		if(browserName.equalsIgnoreCase("IE"))
		{
			//System.setProperty("webdriver.ie.driver","C://01_Tools//IEDriverServer//IEDriverServer.exe");
			File file = new File(".//lib//IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					
			System.setProperty("webdriver.ie.driver.logfile",".//lib//temp.txt");
			System.setProperty("webdriver.ie.driver.loglevel","INFO");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
	        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
	        capabilities.setCapability("requireWindowFocus", true);
	        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); //Accept unexpected alerts
	        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	        driver=new InternetExplorerDriver();
	        driver.manage().deleteAllCookies();
	      
		}
		if(browserName.equalsIgnoreCase("Remote"))
		{
			//System.setProperty("webdriver.ie.driver","C://01_Tools//IEDriverServer//IEDriverServer.exe");
			
			//File file = new File("C:/TestAutomation/IEDriverServer.exe");
			//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			System.out.println("I am here 1");		
			DesiredCapabilities capabilities = new DesiredCapabilities().internetExplorer();
			capabilities.setBrowserName("internet explorer");
			capabilities.setPlatform(Platform.WIN8);
			System.out.println("I am here 2");
	        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
	        capabilities.setCapability("requireWindowFocus", true);
	        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE); //Accept unexpected alerts
	        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	        System.out.println("I am here 3");
	        driver1=new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"),capabilities);
	        System.out.println("I am here 4");
	        return driver1;
	        //driver.manage().deleteAllCookies();
	      
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void quitApplication(WebDriver driver)
	{
		driver.quit();
	//	tryToKillByName("iexplore.exe");
		    
	}

}
