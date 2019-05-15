package specs;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.AppConstants;
import utils.ApplicationLibrary;
import utils.ExcelDataHandler;
import views.LoginScreen;

public class SignInTest {

	protected ApplicationLibrary appLib;
	protected LoginScreen loginScreen;
	protected Map<String, String> exceldata;

	@BeforeTest
	public void setBaseURL() {
		appLib = new ApplicationLibrary();
		exceldata = ExcelDataHandler.getTestData(this.getClass().getSimpleName());
		appLib.invokeBrowser(AppConstants.CHROME);
		appLib.getDriver().get(AppConstants.URL);
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		// Initializing Hotels Screen Page Objects
		loginScreen = PageFactory.initElements(appLib.getDriver(), LoginScreen.class);

		// Clicking on Your trips link
		appLib.clickElement(loginScreen.lnkYourTrips, "Clicking on Your trips Link");

		// Clicking on Sign In button
		appLib.clickElement(loginScreen.btnSignIn, "Clicking on Sign In button");
		
		// Clicking on Sign In button in pop-up
		switchToFrame(loginScreen.loginFrame);
		appLib.clickElement(loginScreen.btnSignInOnPopup, "Clicking on Sign In button in popup");

		// Validating Error Message
		Assert.assertTrue(loginScreen.txtErrorMessage.getText().contains(exceldata.get("ErrorMsg")));

	}
	
	public void switchToFrame(WebElement frame) {
		appLib.waitforElementVisible(frame);
		appLib.getDriver().switchTo().frame(frame);
	}
}
