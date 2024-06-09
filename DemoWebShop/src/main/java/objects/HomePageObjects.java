package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.*;

import base.BaseTest;
import utils.Constants;

public class HomePageObjects{
	
	public WebDriver driver;
	
	public HomePageObjects (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='gender-male']")
	public static
	WebElement genderMaleRadioButton;
	
	@FindBy(xpath="//input[@id='gender-male']")
	WebElement genderFemaleRadioButton;
	
	@FindBy(xpath="//input[@id='FirstName']")
	public static
	 WebElement firstNameTextField;
	
	@FindBy(xpath="//input[@id='LastName']")
	public static
	 WebElement lastNameTextField;
	
	@FindBy(xpath="//input[@id='Email']")
	static
	 WebElement emailField;
	
	@FindBy(xpath="//input[@id='Password']")
	 WebElement passwordField;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	 WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@id='register-button']")
	static
	 WebElement registerButton;
	
	@FindBy(xpath="//span[@for='Email']")
	static
	WebElement wrongEmailErrorMessage;
	
	@FindBy(xpath="//span[@for='Password']")
	static
	 WebElement lessCharacterPasswordErrorMessage;
	
	@FindBy(xpath="//span[@for='ConfirmPassword']")
	static
	 WebElement unmatchPasswordErrorMessage;
	
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
		// TODO Auto-generated method stub
		emailField.sendKeys(Constants.wrongemail);
	}


	// CATEGORIES ELEMENTS
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Books']")
	WebElement books;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Computers']")
	WebElement computers;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Electronics']")
	WebElement electronics;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Apparel & Shoes']")
	WebElement apparelShoes;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Digital downloads']")
	WebElement digitalDownloads;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Jewelry']")
	WebElement jewelry;
	
	@FindBy(xpath="//li[@class='inactive']//a[normalize-space()='Gift Cards']")
	WebElement giftCards;
	
	public void clickonBooks() 
	{
		books.click();
	}
	
	public void clickonComputers() 
	{
		computers.click();
	}
	
	public void clickonElectronics() 
	{
		electronics.click();
	}
	
	public void clickonApparelShoes() 
	{
		apparelShoes.click();
	}
	
	public void clickonDigitalDownloads() 
	{
		digitalDownloads.click();
	}
	
	public void clickonJewelry() 
	{
		jewelry.click();
	}
	
	public void clickonGiftCards() 
	{
		giftCards.click();
	}
	
	
	// HEADER ELEMENTS
	@FindBy(xpath="//img[@alt='Tricentis Demo Web Shop']")
	static
	WebElement demoWebShopIcon;
	
	@FindBy(xpath="//a[@class='ico-register']")
	static
	WebElement registerButtonText;
	
	@FindBy(xpath="//a[@class='ico-login']")
	static WebElement loginButtonText;
	
	@FindBy(xpath="//span[normalize-space()='Shopping cart']")
	static WebElement shoppingCartButtonText;
	
	@FindBy(xpath="//span[normalize-space()='Wishlist']")
	static WebElement wishlistButtonText;
	
	@FindBy(xpath="//input[@id='small-searchterms']")
	static WebElement searchBar;
	
	@FindBy(xpath="//input[@value='Search']")
	static WebElement searchBarButton;
	

	public static void verifydemoWebShopIcon() {
		Assert.assertTrue(demoWebShopIcon.isDisplayed(),"Error Message Not Found");
	}
	
	public static void clickonDemoWebShopIcon() 
	{
		demoWebShopIcon.click();
	}
	
	public static void clickonregisterButtonText() 
	{
		registerButtonText.click();
	}	
	
	public static void clickonloginButtonText() 
	{
		loginButtonText.click();
	}
	
	public static void clickonshoppingCartButtonText() 
	{
		shoppingCartButtonText.click();
	}
	
	public static void clickonwishlistButtonText() 
	{
		wishlistButtonText.click();
	}
	
	public void clickonsearchBarButton() 
	{
		searchBarButton.click();
	}
	
	public static void enterTextOnSearchBar(String text) {
		searchBar.sendKeys(text);
	}
	
	
	// POPULAR TAGS ELEMENTS
	@FindBy(xpath="//a[normalize-space()='Tricentis']")
	WebElement manufacturesTricentis;
	
	@FindBy(xpath="//a[normalize-space()='View all']")
	WebElement popularTags;
	
	
	public void clickOnManufacturesTricentis() {
		manufacturesTricentis.click();
	}
	
	public void clickOnPopularTags() {
		popularTags.click();
	}
	
	
	// TOP MENU ELEMENTS	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/books']")
	WebElement topMenuBooks;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/computers']")
	WebElement topMenuComputers;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/electronics']")
	 WebElement topMenuElectronics;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/apparel-shoes']")
	 WebElement topMenuApparel_Shoes;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/digital-downloads']")
	 WebElement topMenuDigital_Downloads;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/jewelry']")
	 WebElement topMenuJewelry;
	
	@FindBy(xpath="//ul[@class='top-menu']//a[@href='/gift-cards']")
	 WebElement topMenuGift_Cards;

	
	public void clickOntopMenuBooks() {
		topMenuBooks.click();
	}
	
	public void clickOntopMenuComputers() {
		topMenuComputers.click();
	}
	
	public void clickOntopMenuElectronics() {
		topMenuElectronics.click();
	}
	
	public void clickOntopMenuApparel_Shoes() {
		topMenuApparel_Shoes.click();
	}
	
	public void clickOntopMenuDigital_Downloads() {
		topMenuDigital_Downloads.click();
	}
	
	public void clickOntopMenuJewelry() {
		topMenuJewelry.click();
	}
	
	public void clickOntopMenuGift_Cards() {
		topMenuGift_Cards.click();
	}
}
