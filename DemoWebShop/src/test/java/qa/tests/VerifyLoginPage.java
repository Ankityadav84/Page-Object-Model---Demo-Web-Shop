package qa.tests;

import org.testng.annotations.Test;

import objects.*;
import base.BaseTest;
import utils.*;

public class VerifyLoginPage extends BaseTest{

  @Test
  public void verifyLoginPageObjects() {	  
	  logger.info("Opening DemoWebshop Home Page");
	  logger.info("Verifying if DemoWebShop Home Icon is present or not");
	  HomePageObjects.verifyRegisterPageIsLoaded();
	  logger.info("Click on Login Button on Homepage");
	  HomePageObjects.clickonloginButtonText();
	  logger.info("Open Login Page ");
	  logger.info("Fill email");
	  LoginPageObjects.setEmail();
	  logger.info("Fill password");
	  LoginPageObjects.setPassword();
	  logger.info("Click on remember me");
	  LoginPageObjects.clickOnRememberMe();
	  logger.info("Click on login button");
	  LoginPageObjects.clickOnLogInButton();
  }
}
