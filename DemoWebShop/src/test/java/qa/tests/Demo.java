package qa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
//public static WebDriver driver;
public class Demo extends BaseTest{
//	public static ExtentTest logger;
  @Test
  public void DemoWebOpen() {
//	  WebDriverManager.chromedriver().setup();
//	  WebDriver	driver = new ChromeDriver();
//	  driver.manage().window().maximize();
	  driver.get("https://www.tajhotels.com/en-in");
	  try {
	  driver.findElement(By.xpath("//span[text()='LOGIN / JOI']")).click();
	  }
	  catch(Exception e) {
		  logger.info("Register into Registration page");
	  }
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='senderMobile']"))).sendKeys("9327473542");
  }
}
