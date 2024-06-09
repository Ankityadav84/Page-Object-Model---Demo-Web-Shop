package qa.tests;

import org.testng.annotations.Test;
import utils.*;
import objects.*;
import base.BaseTest;

public class CreateNewUser extends BaseTest{

  @Test
  public void sampleMethodForEnteringRegistration() {
	  
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  HomePageObjects.verifydemoWebShopIcon();
	  logger.info("Register into Registration page");
	  RegisterPageObjects.clickRegisterButton();
	  logger.info("Verifying if Registration Text is present or not");
	  RegisterPageObjects.verifyRegisterPageIsLoaded();
	  logger.info("Entering the Registaration Information");
	  logger.info("Selelct gender");
	  RegisterPageObjects.setFirstName();
	  RegisterPageObjects.setLastName();
	  RegisterPageObjects.emailField();
	  RegisterPageObjects.passwordField();
	  RegisterPageObjects.confirmPasswordField();
  }
}
