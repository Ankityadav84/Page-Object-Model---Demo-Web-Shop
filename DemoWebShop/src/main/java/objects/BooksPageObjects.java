package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BooksPageObjects {
	
	public WebDriver driver;
	
	//CONSTRUCTOR
	public BooksPageObjects (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//h1[normalize-space()='Books']")
	protected
	WebElement booksHeading;
	
	public void lessCharacterPasswordErrorMessage() {
		Assert.assertTrue(booksHeading.isDisplayed(),"Error Message Not Found");
	}
}
