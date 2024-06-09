package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.*;
import utils.Constants;

public class RegisterPageObjects{
	
	public WebDriver driver;
	
	public RegisterPageObjects (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Another way to write elements without @FindBy Annotations
    //	By genderMaleRadioButton = By.xpath("//input[@id='gender-male']");
	
	@FindBy(xpath="//input[@id='gender-male']")
	public static WebElement genderMaleRadioButton;
	
	@FindBy(xpath="//input[@id='gender-male']")
	public static WebElement genderFemaleRadioButton;
	
	@FindBy(xpath="//input[@id='FirstName']")
	public static WebElement firstNameTextField;
	
	@FindBy(xpath="//input[@id='LastName']")
	public static WebElement lastNameTextField;
	
	@FindBy(xpath="//input[@id='Email']")
	public static WebElement emailField;
	
	@FindBy(xpath="//input[@id='Password']")
	public static WebElement passwordField;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	public static WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@id='register-button']")
	public static WebElement registerButton;
	
	@FindBy(xpath="//span[@for='Email']")
	public static WebElement wrongEmailErrorMessage;
	
	@FindBy(xpath="//span[@for='Password']")
	public static WebElement lessCharacterPasswordErrorMessage;
	
	@FindBy(xpath="//span[@for='ConfirmPassword']")
	public static WebElement unmatchPasswordErrorMessage;
	
	
	public static void selectMaleGender() {
		genderMaleRadioButton.click();
	}
	
	public void selectFemaleGender() {
		genderFemaleRadioButton.click();
	}
	
	public static void setFirstName() {
		firstNameTextField.sendKeys(Constants.firstName);
	}
	
	public static  void setLastName() {
		lastNameTextField.sendKeys(Constants.lastName);
	}
	
	public static void setEmail() {
		emailField.sendKeys(Constants.email);
	}
	
	public void setPassword(String pswrd) {
		passwordField.sendKeys(pswrd);
	}
	
	public void setConfirmPassword(String cPswrd) {
		confirmPasswordField.sendKeys(cPswrd);
	}
	
	public static void clickRegisterButton() {
		registerButton.click();
	}
	
	public static void verifyWrongEmailErrorMessage() {
		Assert.assertTrue(wrongEmailErrorMessage.isDisplayed(),"Error Message Not Found");
	}
	
	public static void verifyLessCharacterPasswordErrorMessage() {
		Assert.assertTrue(lessCharacterPasswordErrorMessage.isDisplayed(),"Error Message Not Found");
	}
	
	public static void verifyUnmatchPasswordErrorMessage() {
		Assert.assertTrue(unmatchPasswordErrorMessage.isDisplayed(),"Error Message Not Found");
	}

	public static void verifyRegisterPageIsLoaded() {
		// TODO Auto-generated method stub
		
	}

	public static void lastNameTextField() {
		// TODO Auto-generated method stub
		
	}

	public static void passwordField() {
		// TODO Auto-generated method stub
		
	}

	public static void confirmPasswordField() {
		// TODO Auto-generated method stub
		
	}

	public static void wrongEmailFormat() {
		// TODO Auto-generated method stub
		
	}

	public static void emailField() {
		// TODO Auto-generated method stub
		
	}

	public static void fillUnmatchPassword() {
		emailField.sendKeys(Constants.wrongemail);
	}

}
