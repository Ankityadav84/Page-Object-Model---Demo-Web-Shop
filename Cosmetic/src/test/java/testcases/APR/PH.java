package testcases.APR;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import common.Base;
import common.RetryAnalyzer;

public class PH extends Base {

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public static void peruAgreement() throws InterruptedException, IOException {
//	COMMON
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().deleteAllCookies();
		String fakeMailWeb;
		String mkWeb;
		String verificationCode;
		String[] consultantID = { "183269", "183269" };
		
		driver.get(config.getProperty("FAKE_MAIL_URL"));
		System.out.println("FAKE MAIL OPEN");
		fakeMailWeb = driver.getWindowHandle();
		driver.findElement(By.xpath(PE_loc.getProperty("Copy_Mail_Button"))).click();
		String Mail = driver.findElement(By.xpath(PE_loc.getProperty("Mail"))).getText();
		driver.switchTo().newWindow(WindowType.TAB);
		System.out.println("MAIL COPIED: "+Mail);

//	MARYKAY() THROWS INTERRUPTEDEXCEPTION 																
		driver.get(config.getProperty("PE_URL"));
		Thread.sleep(5000);
		mkWeb = driver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Consultant_Number"))))
				.sendKeys(consultantID[(int) (Math.floor(Math.random() * 2))]);
		System.out.println("PERU SIGNUP PAGE OPEN");
		WebElement recruiter = driver.findElement(By.xpath(PE_loc.getProperty("Recruiter_Button"))); 
		js.executeScript("arguments[0].scrollIntoView(true);", recruiter);
		recruiter.click();
		Thread.sleep(8000);
		try {

			boolean actualPara = driver.findElement(By.xpath(PE_loc.getProperty("Recruiter_Error"))).isDisplayed();
			boolean True = true;
			if (actualPara == True) {
				recruiter.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Recruiter_Name"))));
				WebElement email = driver.findElement(By.xpath(PE_loc.getProperty("By_Email")));
				js.executeScript("arguments[0].scrollIntoView(true);", email);
				email.click();
				driver.findElement(By.xpath(PE_loc.getProperty("Email"))).sendKeys(Keys.CONTROL + "V");
//	THIS WILL USE TO EXECUTE JAVASCRIPT CODE IN JAVA LANGUAGE
				js.executeScript("window.scroll(0,800)", "");
				driver.findElement(By.xpath(PE_loc.getProperty("Register_Button"))).click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Verification_Code_Field"))));
				driver.switchTo().window(fakeMailWeb);
				System.out.println("BACK TO FAKE MAIL WEB");
			}
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Recruiter_Name"))));
			WebElement email = driver.findElement(By.xpath(PE_loc.getProperty("By_Email")));
			js.executeScript("arguments[0].scrollIntoView(true);", email);
			email.click();
			driver.findElement(By.xpath(PE_loc.getProperty("Email"))).clear();
			driver.findElement(By.xpath(PE_loc.getProperty("Email"))).sendKeys(Keys.CONTROL + "V");
//  THIS WILL USE TO EXECUTE JAVASCRIPT CODE IN JAVA LANGUAGE
			js.executeScript("window.scroll(0,800)", "");
			driver.findElement(By.xpath(PE_loc.getProperty("Register_Button"))).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Verification_Code_Field"))));
			driver.switchTo().window(fakeMailWeb);
			System.out.println("BACK TO FAKE MAIL WEB");
		}

//	GETVERIFICATIONCODE() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Received_Code")))).click();
		// OPEN(switch to) I-FRAME and COPY VERIFICATION CODE
		driver.switchTo().frame("iframeMail");
		verificationCode = driver.findElement(By.xpath(PE_loc.getProperty("Verification_Code"))).getText();
		System.out.println("VERIFICATION CODE COPIED ");
		driver.switchTo().window(mkWeb);

//  RESETPASSWORD() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		System.out.println("RESET PASSWORD PAGE OPEN ");
		driver.findElement(By.xpath(PE_loc.getProperty("Verification_Code_Field"))).sendKeys(verificationCode);
		driver.findElement(By.xpath(PE_loc.getProperty("Password_Field"))).sendKeys(config.getProperty("Password"));
		driver.findElement(By.xpath(PE_loc.getProperty("Confirm_Password_Field")))
				.sendKeys(config.getProperty("Password"));
		driver.findElement(By.xpath(PE_loc.getProperty("Submit_Password_Button"))).click();

//	RECRUITERSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PE_loc.getProperty("Spinner"))));
		System.out.println("SIGNUP SUCCESSFULLY");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Recruiter_Subsidary"))));
		WebElement CONTINUE_BUTTON = driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button")));
		js.executeScript("arguments[0].scrollIntoView(true);", CONTINUE_BUTTON);
		CONTINUE_BUTTON.click();

//	IDENTIFICATIONSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PE_loc.getProperty("Spinner"))));
		System.out.println("RECRUITER SECTION PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Documento_Identidad"))))
				.sendKeys(RandomStringUtils.randomNumeric(8));
		driver.findElement(By.xpath(PE_loc.getProperty("Nacionalidad"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Tipo_Doc_Identidad"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Comprobante_de_pago"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Numero_de_identificacion")))
				.sendKeys("15" + RandomStringUtils.randomNumeric(9));
		WebElement IDENTIFCATION_CONTINUE_BUTTON = driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button")));
		js.executeScript("arguments[0].scrollIntoView(true);", IDENTIFCATION_CONTINUE_BUTTON);
		IDENTIFCATION_CONTINUE_BUTTON.click();

//	PERSONALSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PE_loc.getProperty("Spinner"))));
		System.out.println("IDENTIFICATION SECTION PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Date_Of_Birth"))));
		driver.findElement(By.xpath(PE_loc.getProperty("Nombre"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(PE_loc.getProperty("Apellido"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(PE_loc.getProperty("Estado_Civil"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Sexo"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Education_Level"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Date_Of_Birth"))).click();
		String[] month = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Dic" };
		// int <-math.floor <-math.random
		// convert 28 into string <-28 <-28.00 <-0.982346*29 = 28.8
		driver.findElement(By.xpath(PE_loc.getProperty("Date_Of_Birth")))
				.sendKeys(Integer.toString(((int) (Math.floor(Math.random() * 29)))) + " "
						+ month[(int) (Math.floor(Math.random() * 12))] + ". "
						+ Integer.toString((1950 + (int) (Math.floor(Math.random() * 54)))));
		WebElement PERSONAL_CONTINUE_BUTTON = driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button")));
		js.executeScript("arguments[0].scrollIntoView(true);", PERSONAL_CONTINUE_BUTTON);
		PERSONAL_CONTINUE_BUTTON.click();

//	ADDRESSSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("Spinner")));
		System.out.println("PERSONAL SECTION PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Referencia"))));
		driver.findElement(By.xpath(PE_loc.getProperty("Residence_Type_Field"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Residence_Type"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Departmanto_Field"))).sendKeys("__");
		driver.findElement(By.xpath(PE_loc.getProperty("Departmanto_ANCASH"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Provincia_Field"))).sendKeys("__");
		driver.findElement(By.xpath(PE_loc.getProperty("Provincia_AIJA"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Distrito_Field"))).sendKeys("__");
		driver.findElement(By.xpath(PE_loc.getProperty("Distrito_AIJA"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Postal_Code_Field"))).sendKeys("__");
		driver.findElement(By.xpath(PE_loc.getProperty("Postal_Code_AIJA"))).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Direccion"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(PE_loc.getProperty("Referencia"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))));
		driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))).click();

// CONTACTSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("Spinner")));
		System.out.println("ADDRESS SECTION PASS");
		driver.findElement(By.xpath(PE_loc.getProperty("Home_Phone"))).sendKeys(RandomStringUtils.randomNumeric(9));
		driver.findElement(By.xpath(PE_loc.getProperty("Cell_Phone"))).sendKeys("9" + RandomStringUtils.randomNumeric(8));
		driver.findElement(By.xpath(PE_loc.getProperty("Office_Phone"))).sendKeys(RandomStringUtils.randomNumeric(9));
		List<WebElement> elements = driver.findElements(By.xpath(PE_loc.getProperty("Radio_Button")));
		elements.get(1).click();
		driver.findElement(By.xpath(PE_loc.getProperty("Checkbox"))).click();
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))));
		driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))).click();
		
// LEGALSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PE_loc.getProperty("Spinner"))));
		System.out.println("ADDITONAL SECTION PASS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Continue_Button"))));
		// THIS WILL USE TO EXECUTE JAVASCRIPT CODE IN JAVA LANGUAGE
		js.executeScript("window.scroll(0,200)", "");
		WebElement container = driver.findElement(By.xpath(PE_loc.getProperty("Container")));
		js.executeScript("arguments[0].scrollTop = arguments[1];", container, 20000);
		Thread.sleep(1000);
		List<WebElement> lelements = driver.findElements(By.xpath(PE_loc.getProperty("Legal_Checkboxes")));
		lelements.get(0).click();
		lelements.get(1).click();
		lelements.get(2).click();
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))));
		driver.findElement(By.xpath(PE_loc.getProperty("Continue_Button"))).click();

//	REVIEWSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PE_loc.getProperty("Spinner"))));
		System.out.println("LEGAL SECTION PASS");
		List<WebElement> checkbox = driver.findElements(By.xpath(PE_loc.getProperty("Review_Checkboxes")));
		Thread.sleep(6000);
		wait.until(ExpectedConditions.visibilityOfAllElements(checkbox));
		checkbox.get(0).click();
		checkbox.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(PE_loc.getProperty("Buy_Starter_Kit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Send_Button")))).click();

// STARTERKITSPAGE() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Commerce_Continue_Button"))));
		System.out.println("REVIEW SECTION PASS");
		System.out.println("STARTER KIT PAGE OPEN");
		WebElement checkbox1 = driver.findElement(By.xpath(PE_loc.getProperty("Starter_Kit_Radio_Button")));
		js.executeScript("arguments[0].scrollIntoView(true);", checkbox1);
		Actions action = new Actions(driver);
		action.moveToElement(checkbox1).click(checkbox1).perform();
		driver.findElement(By.xpath(PE_loc.getProperty("Commerce_Continue_Button"))).click();

//	SHOOPINGBAGSECTIONS() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		WebElement shoopingBagButton = driver.findElement((By.xpath(PE_loc.getProperty("Shooping_Bag_Button"))));
		js.executeScript("arguments[0].scrollIntoView(true);", shoopingBagButton);
		wait.until(ExpectedConditions.visibilityOf(shoopingBagButton));
		System.out.println("STARTER KIT SECTION PASS");
		shoopingBagButton.click();

//	BOLSASECTION() THROWS INTERRUPTEDEXCEPTION {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Bolsa_Da_Compras_Button"))))
				.click();
		System.out.println("SHOOPING BAG SECTIONS PASS");

//	PRECONOCIMIENTOS() THROWS INTERRUPTEDEXCEPTION {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Promotional_Checkbox"))))
				.click();
		System.out.println("BOLSA SECTION PASS");
		WebElement continuarbutton = driver.findElement(By.xpath(PE_loc.getProperty("Recognitions_Continue_Button")));
		js.executeScript("arguments[0].scrollIntoView(true);", continuarbutton);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Recognitions_Continue_Button")))).click();
		
// INFORMACIONSECTION() THROWS INTERRUPTEDEXCEPTION {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(3000);
		WebElement continueToPaymentButton = driver
				.findElement(By.xpath(PE_loc.getProperty("Continue_With_Payment_Button")));
		wait.until(ExpectedConditions.visibilityOf(continueToPaymentButton));
		System.out.println("RECONOCIMIENTOS SECTION PASS");
		Thread.sleep(3000);
		js.executeScript("window.scroll(0,900)", "");
		js.executeScript("arguments[0].scrollIntoView(true);", continueToPaymentButton);
		continueToPaymentButton.click();

// PAYMENTSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(5000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Payment_Section_Checkbox1"))))
				.click();
		System.out.println("INFORMACION SECTION PASS");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Payment_Section_Checkbox2"))))
				.click();
		WebElement button = driver.findElement(By.xpath(PE_loc.getProperty("Credit_Card_Button")));
		wait.until(ExpectedConditions.visibilityOf(button));
		js.executeScript("arguments[0].scrollIntoView(true);", button);
		Thread.sleep(3000);
		button.click();

// CREDITCARDSECTION() THROWS INTERRUPTEDEXCEPTION {
		Thread.sleep(15000);
		driver.switchTo().frame(PE_loc.getProperty("IFrame"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Payment_Method")))).click();
		System.out.println("PAYMENT SECTION PASS");
		driver.findElement(By.xpath(PE_loc.getProperty("Payment_Method_Continue_Button"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(PE_loc.getProperty("Card_Number_Field"))).sendKeys(config.getProperty("Card_Number1"));
		driver.findElement(By.xpath(PE_loc.getProperty("Card_Number_Field"))).sendKeys(config.getProperty("Card_Number2"));
		driver.findElement(By.xpath(PE_loc.getProperty("Card_Number_Field"))).sendKeys(config.getProperty("Card_Number3"));
		driver.findElement(By.xpath(PE_loc.getProperty("Card_Number_Field"))).sendKeys(config.getProperty("Card_Number4"));
		String month1 = "" + (1 + (int) (Math.floor(Math.random() * 12)));
		String preMonth = month1.length() > 1 ? "" : "0"; // conditional statement & length function -> 3445 = 4 digit
		driver.findElement(By.xpath(PE_loc.getProperty("Expiry_Date"))).sendKeys(preMonth + month1);
		Thread.sleep(1000);
		driver.findElement(By.xpath(PE_loc.getProperty("Expiry_Date")))
				.sendKeys("20" + (25 + (int) (Math.floor(Math.random() * 16))));
		driver.findElement(By.xpath(PE_loc.getProperty("CVC")))
				.sendKeys("" + (100 + (int) (Math.floor(Math.random() * 100))));
		driver.findElement(By.xpath(PE_loc.getProperty("First_Name"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(PE_loc.getProperty("Last_Name"))).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath(PE_loc.getProperty("Pay_Button"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PE_loc.getProperty("Order_Number_Message"))));

// SCREENSHOT() THROWS IOEXCEPTION {
		Date currentdate = new Date();
		String screenshotfilename = currentdate.toString().replace(":", "-").replace(" ", "-").substring(4);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir")+"/MaryKay Screenshot/PE ORDERS/"+screenshotfilename+".png"));
		System.out.println("CREDIT CARD SECTION PASS");
		System.out.println("SCREENSHOT CAPTURE");
		driver.switchTo().window(fakeMailWeb);

// CONFIRMATIONMAIL() THROWS INTERRUPTEDEXCEPTION {
		driver.findElement(By.xpath(PE_loc.getProperty("Back_Button_Of_Fake_Mail"))).click();
		System.out.println("FAKE MAIL WEB OPEN");
//		wait.until(ExpectedConditions.visibilityOfElementPE_located(By.xpath(PE_loc.getProperty("Received_Mail")))).click();
		System.out.println("MARY KAY ORDER CONFIRMATION MAIL RECEIVED");
	}
}
