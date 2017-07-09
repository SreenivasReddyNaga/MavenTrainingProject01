package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(linkText="Logout")
	private WebElement linkLogout;

	@FindBy(xpath="//span[text()='New']")
	private WebElement linkNew;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void logout(){
		linkLogout.click();
	}

	public boolean isDisplayed(){
		return linkNew.isDisplayed();
	}
	public WebElement getLinkNew() {
		return linkNew;
	}

}








