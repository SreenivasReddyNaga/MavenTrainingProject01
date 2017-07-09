package org.commonlibrary;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverUtils {

	private WebDriver driver;

	public WebDriverUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void selectOptionByText(WebElement objProperty, String optionToSelect) {
		boolean optionStatus = false;
		Select country = new Select(objProperty);
		List<WebElement> allOptions = country.getOptions();

		int optionCount = allOptions.size();
		Reporter.log("Options Count " + optionCount);
		if (optionCount > 0) {

			for (WebElement option : allOptions) {
				Reporter.log("Option in Dropdown " + option.getText());
				if (option.getText().equals(optionToSelect)) {
					Reporter.log("Option witn " + optionToSelect + "available in Dropdown ");
					country.selectByVisibleText(optionToSelect);
					optionStatus = true;
					break;
				}
			}
			if (!optionStatus) {
				Reporter.log("Option witn " + optionToSelect + " not available in Dropdown ");
			}

		} else {
			Reporter.log("There were no options in dropdown");
		}
	}

	public void selectWindow(String expectedTitle) {

		Set<String> wndHandles = driver.getWindowHandles();

		Reporter.log("Handles Count " + wndHandles.size());

		for (String wndHandle : wndHandles) {
			Reporter.log("Window Handle" + wndHandle);
			String actualTitle = driver.switchTo().window(wndHandle).getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				driver.switchTo().window(wndHandle);
				Reporter.log("Expected window got selected");
				break;
			}
		}
	}

	public WebElement waitForElementVisible(WebElement objProperty) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOf(objProperty));

	}

	public WebElement waitForElementPresence(By by) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement waitForElementClickable(WebElement objProperty) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(objProperty));
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;

		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public boolean isTextPresent(String expectedText) {

		String bodyText = driver.findElement(By.tagName("body")).getText();

		if (bodyText.contains(expectedText)) {
			return true;
		} else {
			return false;
		}

	}

	public void getAlert() {
		if (isAlertPresent()) {
			Reporter.log("There is alert present", true);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} else {
			Reporter.log("There were no alerts present", true);
		}
	}

	public String getTextFromAlert() {
		String alertMessage = null;
		if (isAlertPresent()) {
			Reporter.log("There is alert present", true);
			Alert alert = driver.switchTo().alert();
			alertMessage = alert.getText();			
		} else {
			Reporter.log("There were no alerts present", true);
		}

		return alertMessage;
	}
	
	

}

