package views;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen {

	public static final String ERROR_MSG = "";

	@FindBy(linkText = "Your trips")
	public WebElement lnkYourTrips;

	@FindBy(id = "SignIn")
	public WebElement btnSignIn;

	@FindBy(id = "signInButton")
	public WebElement btnSignInOnPopup;

	@FindBy(xpath = "//div[@id='errors1']/span")
	public WebElement txtErrorMessage;

	@FindBy(xpath = "//iframe[@name='modal_window']")
	public WebElement loginFrame;

}
