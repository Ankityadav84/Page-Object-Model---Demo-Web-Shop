package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch extends BaseTest{

	public WebElement getWebElement(String identifierType, String identifierValue) {
		switch(identifierType) {

		case "XPATH":
			return driver.findElement(By.xpath(identifierValue));
		case "CSS":
			return driver.findElement(By.cssSelector(identifierValue));
		case "ID":
			return driver.findElement(By.id(identifierValue));
		case "NAME":
			return driver.findElement(By.name(identifierValue));
		case "TAGNAME":
			return driver.findElement(By.tagName(identifierValue));
		default:
			return null;
		}
	}
	public List<WebElement> getWebElements(String identifierType, String identifierValue) {
		switch(identifierType) {
		
		case "XPATH":
			return driver.findElements(By.xpath(identifierValue));
		case "CSS":
			return driver.findElements(By.cssSelector(identifierValue));
		case "ID":
			return driver.findElements(By.id(identifierValue));
		case "NAME":
			return driver.findElements(By.name(identifierValue));
		case "TAGNAME":
			return driver.findElements(By.tagName(identifierValue));
		default:
			return null;
		}
	}
}
