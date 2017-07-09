package org.maven;

import java.util.Map;

import org.commonlibrary.GenericConfigClass;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends GenericConfigClass {

	@Test(dataProvider = "row-based-data")
	public void actiTimeLogin(Map<String, String> loginData) {
		LoginPage loginPage = new LoginPage(driver);
		boolean isLinkNewAvailable = loginPage.login
				(loginData.get("UserName"), loginData.get("Password")).isDisplayed();
		/*Assert.assertTrue(homePage.getLinkNew().isDisplayed(),
				"Link New Not availble in HomePage");*/
		
		Assert.assertTrue(isLinkNewAvailable,"Link New Not availble in HomePage");
		new HomePage(driver).logout();
	}
}






