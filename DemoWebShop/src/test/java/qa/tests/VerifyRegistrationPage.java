package qa.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import objects.*;
import utils.*;
import base.BaseTest;

public class VerifyRegistrationPage extends BaseTest{
//	HomePageObjects HomePageObjects = new HomePageObjects(null);
//	HomePageObjects RegisterPageObjects = new HomePageObjects(null);
	
  @Test(priority=1)
  public void verifyAllFieldsAreRequired() throws InterruptedException {
	  
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  HomePageObjects.verifydemoWebShopIcon();
	  logger.info("Register into Registration page");
	  HomePageObjects.clickonregisterButtonText();
	  logger.info("Verifying if Registration Text is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  logger.info("Entering the Registaration Information");
	  logger.info("Check All Fields Are Required");
	  RegisterPageObjects.clickRegisterButton();
	  Thread.sleep(2000);
  }
  
  @Test(priority=2)
  public void verifyMailFormat() throws InterruptedException{	
	  
	  RegisterPageObjects.genderMaleRadioButton.click();
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  logger.info("Register into Registration page");
	  HomePageObjects.clickonregisterButtonText();
	  logger.info("Verifying if Registration Text is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  RegisterPageObjects.genderMaleRadioButton.click();
	  RegisterPageObjects.setFirstName();
	  RegisterPageObjects.lastNameTextField();
	  RegisterPageObjects.passwordField();
	  RegisterPageObjects.confirmPasswordField();
	  logger.info("Entering Wrong email format in email field");
	  RegisterPageObjects.wrongEmailFormat();
	  RegisterPageObjects.clickRegisterButton();
	  logger.info("Clicked on register Button");
	  RegisterPageObjects.clickRegisterButton();
	  logger.info("Error Message Display");
	  RegisterPageObjects.verifyWrongEmailErrorMessage();
	  Thread.sleep(2000);
  }
  
  @Test(priority=3)
  public void verifyPasswordLength() throws InterruptedException{	  
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  HomePageObjects.verifydemoWebShopIcon();
	  logger.info("Register into Registration page");
	  HomePageObjects.clickonregisterButtonText();
	  logger.info("Verifying if Registration Text is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  RegisterPageObjects.selectMaleGender();
	  RegisterPageObjects.setFirstName();
	  RegisterPageObjects.lastNameTextField();
	  RegisterPageObjects.emailField();
	  logger.info("Enter less than 6 character in password field");
	  RegisterPageObjects.fillUnmatchPassword();
	  RegisterPageObjects.confirmPasswordField();
	  RegisterPageObjects.clickRegisterButton();
	  logger.info("Clicked on register Button");
	  logger.info("Short Character Error Message Displaying");
	  RegisterPageObjects.verifyLessCharacterPasswordErrorMessage();
	  Thread.sleep(2000);
  }
  
  @Test(priority=4)
  public void verifyUnmatchPassword() throws InterruptedException {	  
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  HomePageObjects.verifydemoWebShopIcon();
	  logger.info("Register into Registration page");
	  HomePageObjects.clickonregisterButtonText();
	  logger.info("Verifying if Registration Text is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  RegisterPageObjects.selectMaleGender();
	  RegisterPageObjects.lastNameTextField();
	  RegisterPageObjects.lastNameTextField();
	  RegisterPageObjects.setEmail();
	  logger.info("Fill Unmatch Password");
	  RegisterPageObjects.passwordField();
	  RegisterPageObjects.fillUnmatchPassword();
	  RegisterPageObjects.clickRegisterButton();
	  logger.info("Clicked on register Button");
	  logger.info("Unmatch Error Message Displaying");
	  RegisterPageObjects.verifyUnmatchPasswordErrorMessage();
	  Thread.sleep(2000);
  }
}
