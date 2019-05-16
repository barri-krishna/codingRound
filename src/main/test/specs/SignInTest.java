package specs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.Template;
import views.LoginScreen;

public class SignInTest extends Template {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		// Initializing Hotels Screen Page Objects
		logger.info("Initializing Hotels Screen Page Objects");
		loginScreen = PageFactory.initElements(appLib.getDriver(), LoginScreen.class);

		// Clicking on Your trips link
		logger.info("Clicking on Your trips link");
		appLib.clickElement(loginScreen.lnkYourTrips, "Clicking on Your trips Link");

		// Clicking on Sign In button
		logger.info("Clicking on Sign In button");
		appLib.clickElement(loginScreen.btnSignIn, "Clicking on Sign In button");

		// Clicking on Sign In button in pop-up
		logger.info("Clicking on Sign In button in pop-up");
		switchToFrame(loginScreen.loginFrame);
		appLib.clickElement(loginScreen.btnSignInOnPopup, "Clicking on Sign In button in popup");

		// Validating Error Message
		logger.info("Validating Error Message");
		Assert.assertTrue(loginScreen.txtErrorMessage.getText().contains(exceldata.get("ErrorMsg")));

	}

	public void switchToFrame(WebElement frame) {
		logger.info("Switcing to iFrame");
		appLib.waitforElementVisible(frame);
		appLib.getDriver().switchTo().frame(frame);
		logger.info("Switching to iframe completed successfully");
	}
}
