package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(id = "dbvdv")
	private WebElement inputUserName;

	@FindBy(name = "pwd")
	private WebElement inputPassword;

	@FindBy(xpath = "//a[@id='loginButton']/div")
	private WebElement linkLogin;
	
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage login(String userName, String password) {
		inputUserName.sendKeys(userName);
		inputPassword.sendKeys(password);
		linkLogin.click();
		return new HomePage(driver);
	}
}









