package common;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

		public static WebDriver driver;
		public static Properties config = new Properties();
		public static Properties PE_loc = new Properties();
		public static Properties BR_loc = new Properties();
		public static Properties US_loc = new Properties();		
		
		@BeforeMethod
		public static void launchBrowser() throws IOException {
			
			if(driver==null) {
				FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
				FileReader PE = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\PE_Locators.properties");
				FileReader BR = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\BR_Locators.properties");
				FileReader US = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\US_Locators.properties");
				config.load(fr);
				PE_loc.load(PE);
				BR_loc.load(BR);
				US_loc.load(US);
			}
			
			if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				driver.manage().deleteAllCookies();
				
			}
			
			else if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			
			else if(config.getProperty("browser").equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			
			System.out.println("BROWSER START");
			
		}

		@AfterMethod
		public void closingBrowser() throws InterruptedException {
//			driver.quit();
			System.out.println("BROWSER CLOSED");
		}
	}


