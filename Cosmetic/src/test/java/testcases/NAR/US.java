package testcases.NAR;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.Base;
import common.RetryAnalyzer;


public class US extends Base {
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public static void US_AGREEMENT() throws InterruptedException, IOException {

// COMMON 		      
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // IMPLICITLY WAIT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100)); // EXPLICIT WAIT
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		String fakeMailWeb;

// FAKEMAIL    
		System.out.println("US AGREEMNT START");
		driver.get(config.getProperty("FAKE_MAIL_URL"));
		System.out.println("FAKE MAIL WEB - PASS");
		fakeMailWeb = driver.getWindowHandle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("MAIL"))));
		String Mail = driver.findElement(By.xpath(US_loc.getProperty("MAIL"))).getText();
		driver.switchTo().newWindow(WindowType.TAB);
		System.out.println("MAIL COPIED: " + Mail);

// MARYKAY
		driver.get(config.getProperty("US_URL"));
		String mkWeb = driver.getWindowHandle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("RECRUITER_FIELD"))));
		System.out.println("US SIGNUP PAGE OPEN - PASS");
		WebElement email = driver.findElement(By.xpath(US_loc.getProperty("BY_EMAIL_RADIO_BUTTON")));
		js.executeScript("arguments[0].scrollIntoView(true);", email);
		email.click();
		driver.findElement(By.xpath(US_loc.getProperty("EMAIL_FIELD"))).sendKeys(Mail);
		js.executeScript("window.scroll(0,800)", "");
		driver.findElement(By.xpath(US_loc.getProperty("SIGNUP_BUTTON"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("VERIFICATION_CODE_FIELD"))));
		driver.switchTo().window(fakeMailWeb);
		System.out.println("BACK TO FAKE MAIL WEB");

// GET VERIFICATION CODE 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("RECEIVED_CODE")))).click();
		// OPEN(switch to) I-FRAME and COPY VERIFICATION CODE
		driver.switchTo().frame("iframeMail");
		String verificationCode = driver.findElement(By.xpath(US_loc.getProperty("VERIFICATION_CODE"))).getText();
		System.out.println("VERIFICATION CODE COPIED - PASS");
		driver.switchTo().window(mkWeb);

// RESETPASSWORD
		driver.findElement(By.xpath(US_loc.getProperty("VERIFICATION_CODE_FIELD"))).sendKeys(verificationCode);
		System.out.println("RESET PASSWORD PAGE OPEN ");
		driver.findElement(By.xpath(US_loc.getProperty("NEW_PASSWORD_FIELD"))).sendKeys(config.getProperty("PASSWORD"));
		driver.findElement(By.xpath(US_loc.getProperty("VERIFY_NEW_PASSWORD_FIELD"))).sendKeys(config.getProperty("PASSWORD"));
		driver.findElement(By.xpath(US_loc.getProperty("PASSWORD_SUBMIT_BUTTON"))).click();

// PERSONAL SECTION		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("CONFIRM_SSN_FIELD"))));
		System.out.println("SIGNUP PAGE - PASS");
		driver.findElement(By.xpath(US_loc.getProperty("FIRST_NAME_FIELD"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(US_loc.getProperty("MIDDLE_NAME_FIELD"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(US_loc.getProperty("LAST_NAME_FIELD"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(US_loc.getProperty("GENDER_FIELD"))).click();
		String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
		// convert 11 into string <-11 <-11.00 <-0.982346*29 = 28.8
		driver.findElement(By.xpath(US_loc.getProperty("DATE_OF_BIRTH_FIELD"))).sendKeys(month[(int) (Math.floor(Math.random() * 12))] + " "+ Integer.toString(((int) (Math.floor(Math.random() * 29)))) + ", "+ Integer.toString((1950 + (int) (Math.floor(Math.random() * 54)))));
		String SSN = RandomStringUtils.randomNumeric(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("SSN_FIELD")))).sendKeys("9" + SSN);
		driver.findElement(By.xpath(US_loc.getProperty("CONFIRM_SSN_FIELD"))).sendKeys("9" + SSN);
		driver.findElement(By.xpath(US_loc.getProperty("SAVE_AND_CONTINUE_BUTTON"))).click();

// ADDRESSSECTION
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(US_loc.getProperty("SPINNER"))));
		System.out.println("PERSONAL SECTION - PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("STREET_FIELD")))).sendKeys(config.getProperty("STREET_NAME"));
		driver.findElement(By.xpath(US_loc.getProperty("STREET_LINE_2_FIELD"))).sendKeys(config.getProperty("APT_BLDG_SUITE_NAME"));
		driver.findElement(By.xpath(US_loc.getProperty("CITY_FIELD"))).sendKeys(config.getProperty("CITY_NAME"));
		driver.findElement(By.xpath(US_loc.getProperty("STATE_FIELD"))).click();
		WebElement State = driver.findElement(By.xpath(US_loc.getProperty("STATE_TEXAS")));
		js.executeScript("arguments[0].scrollIntoView(true);", State);
		action.moveToElement(State).click().perform();
		driver.findElement(By.xpath(US_loc.getProperty("ZIP_CODE_FIELD"))).sendKeys(config.getProperty("ZIP_CODE"));
		driver.findElement(By.xpath(US_loc.getProperty("SAVE_AND_CONTINUE_BUTTON"))).click();

// CONTACTSECTION
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(US_loc.getProperty("SPINNER"))));
		System.out.println("ADDRESS SECTION - PASS");
		driver.findElement(By.xpath(US_loc.getProperty("MOBILE_FIELD"))).sendKeys(RandomStringUtils.randomNumeric(10));
		driver.findElement(By.xpath(US_loc.getProperty("PHONE_FIELD"))).sendKeys(RandomStringUtils.randomNumeric(10));
		List<WebElement> CONTACT_CHECKBOX = driver.findElements(By.xpath(US_loc.getProperty("PRIMARY_RADIO_BUTTON")));
		CONTACT_CHECKBOX.get(0).click();
		driver.findElement(By.xpath(US_loc.getProperty("SAVE_AND_CONTINUE_BUTTON"))).click();

// ADDITIONALSECTION
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(US_loc.getProperty("SPINNER"))));
		System.out.println("CONTACT SECTION - PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("ADDITIONAL_RADIO_BUTTON"))));
		List<WebElement> ADDITIONAL_RADIO_BUTTON = driver.findElements(By.xpath(US_loc.getProperty("ADDITIONAL_RADIO_BUTTON")));
		ADDITIONAL_RADIO_BUTTON.get(1).click();
		ADDITIONAL_RADIO_BUTTON.get(3).click();
		ADDITIONAL_RADIO_BUTTON.get(5).click();
		driver.findElement(By.xpath(US_loc.getProperty("WHAT_CATEGORY_PICKLIST"))).click();
		driver.findElement(By.xpath(US_loc.getProperty("HOW_LONG_PICKLIST"))).click();
		driver.findElement(By.xpath(US_loc.getProperty("WHAT_MOTIVATED_YOU_PICKLIST"))).click();
		driver.findElement(By.xpath(US_loc.getProperty("SAVE_AND_CONTINUE_BUTTON"))).click();
		
// LEGALSECTION
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(US_loc.getProperty("SPINNER"))));
		System.out.println("ADDITIONAL SECTION - PASS");
		js.executeScript("window.scroll(0,200)", "");
		WebElement container = driver.findElement(By.xpath(US_loc.getProperty("GENERAL_TERMS_AND_CONDITIONS_CONTAINER")));
		js.executeScript("arguments[0].scrollTop = arguments[1];", container, 10000);
		Thread.sleep(2000);
		List<WebElement> LEGAL_SECTION_CHECKBOXES = driver.findElements(By.xpath(US_loc.getProperty("ACCEPT_CONDITIONS_CHECKBOXES")));
		LEGAL_SECTION_CHECKBOXES.get(0).click();
		LEGAL_SECTION_CHECKBOXES.get(1).click();
		LEGAL_SECTION_CHECKBOXES.get(2).click();
		driver.findElement(By.xpath(US_loc.getProperty("SAVE_AND_CONTINUE_BUTTON"))).click();
		
// REVIEWSECTION
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(US_loc.getProperty("SPINNER"))));
		System.out.println("LEGAL SECTION - PASS");
		WebElement REVIEW_CHECKBOX = driver.findElement(By.xpath(US_loc.getProperty("I_CONFIRM_CHECKBOX")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView(true);", REVIEW_CHECKBOX);
		wait.until(ExpectedConditions.visibilityOf(REVIEW_CHECKBOX)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(US_loc.getProperty("SELECT_STARTUP_BUTTON"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("SUBMIT_BUTTON")))).click();

// STARTERKITS
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("CONTINUE_BUTTON"))));
		System.out.println("REVIEW SECTION - PASS");
		WebElement checkbox1 = driver.findElement(By.xpath(US_loc.getProperty("STARTER_KIT")));
		js.executeScript("arguments[0].scrollIntoView(true);", checkbox1);
		action.moveToElement(checkbox1).click(checkbox1).perform();
		driver.findElement(By.xpath(US_loc.getProperty("CONTINUE_BUTTON"))).click();

/* OPTIONAL ADD-ON
		driver.findElement((By.xpath(US_loc.getProperty("OPTIONAL_ADD_ON_CONTINUE_BUTTON")))).click();
		System.out.println("STARTER KIT SECTION - PASS");
*/	
		
// CHECKOUT BUTTON
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("CHECKOUT_BUTTON")))).click();
		System.out.println("OPTIONAL ADD ON SECTION - PASS");
		
// SHIPPING METHOD CONTINUE TO PAYMENT BUTTON
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("SHIPPING_HEADING"))));
		System.out.println("CHECKOUT SECTION - PASS");
		List <WebElement> CONTINUETOPAYMENTBUTTON = driver.findElements(By.xpath(US_loc.getProperty("CONTINUE_TO_PAYMENT_BUTTON")));
		js.executeScript("arguments[0].scrollIntoView(true);", CONTINUETOPAYMENTBUTTON.get(0));
		Thread.sleep(5000);
		driver.findElement(By.xpath(US_loc.getProperty("PAYMENT_BUTTON"))).click();
		
// PAYMENT SECTION
		Thread.sleep(5000);
		driver.findElement(By.xpath(US_loc.getProperty("PAYMENT_PAGE_CHECKBOX"))).click();
		System.out.println("SHIPPING METHOD SECTION - PASS");
		WebElement PAYMENT_BUTTON = driver.findElement(By.xpath(US_loc.getProperty("CREDIT_CARD_BUTTON")));
		wait.until(ExpectedConditions.visibilityOf(PAYMENT_BUTTON));
		js.executeScript("arguments[0].scrollIntoView(true);", PAYMENT_BUTTON);
		Thread.sleep(2000);
		PAYMENT_BUTTON.click();

// CREDITCARDSECTION
		Thread.sleep(10000);
		driver.switchTo().frame("wp-cl-custom-html-iframe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("EXPIRY_MONTH_FIELD"))));
		System.out.println("PAYMENT SECTION - PASS");
		driver.findElement(By.xpath(US_loc.getProperty("CARD_NUMBER_FIELD"))).sendKeys(config.getProperty("CARD_NUMBER41"));
		String credit_month = "" + (1 + (int) (Math.floor(Math.random() * 12)));
		String preMonth = credit_month.length() > 1 ? "" : "0"; // conditional statement & length function -> 3445 = 4																
		driver.findElement(By.xpath(US_loc.getProperty("EXPIRY_MONTH_FIELD"))).sendKeys(preMonth + credit_month);
		driver.findElement(By.xpath(US_loc.getProperty("EXPIRY_YEAR_FIELD"))).sendKeys("" + (25 + (int) (Math.floor(Math.random() * 16))));
		driver.findElement(By.xpath(US_loc.getProperty("CVV_FIELD"))).sendKeys("" + (100 + (int) (Math.floor(Math.random() * 100))));
		driver.findElement(By.xpath(US_loc.getProperty("SUBMIT_PAYMENT_BUTTON"))).click();
		String ORDER_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(US_loc.getProperty("ORDER_NUMBER")))).getText();
		System.out.println("ORDER PLACED - PASS");
		System.out.println(ORDER_NUMBER);
		
// SCREENSHOT
		Date currentdate = new Date();
		String screenshotfilename = currentdate.toString().replace(":", "-").replace(" ", "-").substring(4);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File(System.getProperty("user.dir")+"/MaryKay Screenshot/US ORDERS/"+screenshotfilename+".png"));

// CONFIRMATION MAIL    
		driver.switchTo().window(fakeMailWeb);
		System.out.println("BACK TO FAKE MAIL WEB");
		driver.findElement(By.xpath(US_loc.getProperty("BACK_BUTTON_OF_FAKE_MAIL"))).click();
	}
}
