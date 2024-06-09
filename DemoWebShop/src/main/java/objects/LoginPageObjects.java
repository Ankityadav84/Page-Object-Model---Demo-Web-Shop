package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Constants;

public class LoginPageObjects {
	
	public WebDriver driver;
	
	public LoginPageObjects (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='Email']")
	public static WebElement email;
	
	@FindBy(xpath="//input[@id='Password']")
	public static WebElement password;
	
	@FindBy(xpath="//input[@id='RememberMe']")
	 public static WebElement rememberMe;
	
	@FindBy(xpath="//input[@id='Log in']")
	public static WebElement logInButton;
	
	@FindBy(xpath="//a[normalize-space()='Forgot password?']")
	public static WebElement forgotPassword;

	
	
	public static void setEmail() {
		email.sendKeys(Constants.email);
	}
	
	public static void setPassword() {
		password.sendKeys(Constants.password);
	}
	
	public static void clickOnRememberMe() {
		rememberMe.click();
	}
	
	public static void clickOnLogInButton() {
		logInButton.click();
	}
	
	public void clickOnForgotPassword() {
		forgotPassword.click();
	}
}
