package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BaseTest {
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	// Initializing the Extent Report
	@BeforeTest
	public void beforeTest() {
		setupDriver();
		driver.manage().window().maximize();
//		driver.get(Constants.url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/DemoWebshop Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("DemoWebShop Automation Report");
		sparkReporter.config().setReportName("Automation Tests Results by Ankit Yadav");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("UserName", "ANKIT YADAV");
		extent.setSystemInfo("HostName", "AnkitSDEV00680 ");
	}

	@BeforeMethod
	public void beforeMethod(Method testMethod) {
		logger = extent.createTest(testMethod.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
//			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));			
			String screenshotPath = getScreenShot(result.getName());
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
			System.out.println("***************  REPORT GENERATED WITH ATTACH SCREENSHOT  *******************");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.YELLOW));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		extent.flush();
		Thread.sleep(1000);
		System.out.println("***************  BROWSER CLOSE   *******************");
//		driver.quit();
	}

	// OTHERS METHODS - capture the screenshot
	public String getScreenShot(String methodName) throws IOException {
		String dateName = new SimpleDateFormat(" yyyy-MM-dd hh-mm-ss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + methodName + dateName + ".png";
		try {
			FileUtils.copyFile(source,
					new File(System.getProperty("user.dir") + "/screenshots/" + methodName + dateName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

	// DRIVER SETUP
	public void setupDriver() {
		if (Constants.browser.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("incognito");
//			option.addArguments("headless"); 
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
		} else if (Constants.browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Constants.browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		System.out.println("***************  BROWSER START   *******************");
	}
}
