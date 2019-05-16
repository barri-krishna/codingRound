package utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.javafx.PlatformUtil;

/**
 * @author krishna.barri
 *
 */
@SuppressWarnings("restriction")
public class CommonLibrary {

	private WebDriver driver;
	
	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Setting driver path with respect to OS
	 */
	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver",
					AppConstants.PARENTFOLDER_PATH + AppConstants.RESOURCES_CHROME_MAC_DIR);
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver",
					AppConstants.PARENTFOLDER_PATH + AppConstants.RESOURCES_CHROME_WIN_DIR);
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver",
					AppConstants.PARENTFOLDER_PATH + AppConstants.RESOURCES_CHROME_LINUX_DIR);
		}
	}

	/**
	 * Invokes the browser
	 * @param requiredBrowserName: Browser type to initialize
	 */
	public void invokeBrowser(String requiredBrowserName) {
		try {
			setDriverPath();
			if (driver == null) {
				switch (requiredBrowserName) {
				case AppConstants.CHROME: {
					setDriver(new ChromeDriver());
					break;
				}
				case AppConstants.FIREFOX: {
					setDriver(new FirefoxDriver());
					break;
				}
				case AppConstants.IE: {
					setDriver(new InternetExplorerDriver());
					break;
				}
				default: {
					setDriver(new ChromeDriver());
				}
				}
			}
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hard wait for iTime
	 * @param iTime: Wait time in milliseconds
	 */
	public void waitFor(int iTime) {
		try {
			logger.info("Sleep for " +iTime);
			Thread.sleep(iTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.info("Failed at Sleep for " +iTime);
			Assert.fail("Failed at Sleep for " +iTime);
		}
	}

	/**
	 * Wait for the element to visible
	 * @param webelement: Element to wait for visible
	 */
	public void waitforElementVisible(final WebElement webelement) {
		try {
			logger.info("Waiting for element to present");
			WebDriverWait wait = new WebDriverWait(getDriver(), AppConstants.DRIVER_WAIT);
			wait.until(ExpectedConditions.visibilityOf(webelement));
			wait.pollingEvery(1, TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
			logger.info("Element is visible in DOM");
		} catch (TimeoutException e) {
			logger.error("Failed at waiting for visibility of Element " +e.getMessage());
			Assert.fail("Failed at waiting for visibility of Element " +e.getMessage());
		} catch (Exception e) {
			logger.error("Failed at waitforElementVisible() " +e.getMessage());
			Assert.fail("Exception in waitforElementVisible() " +e.getMessage());
		}
	}

	/**
	 * Clicking the webelement
	 * @param webelement: Element to click
	 * @param sReportText: Log text on what element we click
	 */
	public void clickElement(WebElement webelement, String sReportText) {
		try {
			logger.info(sReportText);
			waitforElementVisible(webelement);
			new Actions(getDriver()).moveToElement(webelement).perform();
			webelement.click();
		} catch (Exception e) {
			logger.error("Failed at clicking the element " +e.getMessage());
			Assert.fail("Not able to Click Element");
		}
	}

	/**
	 * Setting value in element
	 * @param webelement: Element to set value
	 * @param sValue: Value to set in Element
	 */
	public void setValue(WebElement webelement, String sValue) {
		try {
			logger.info("Setting value to Element");
			waitforElementVisible(webelement);
			webelement.click();
			String elementValue = webelement.getAttribute("value");
			if (!elementValue.isEmpty() && elementValue != null) {
				webelement.clear();
			}
			webelement.sendKeys(sValue);
		} catch (Exception e) {
			logger.error("Failed at setting the value to element " +e.getMessage());
			Assert.fail("Not able to Set Value " +e.getMessage());
		}
	}
	
	
	/**
	 * To check element visible in DOM
	 * @param element: Element to check for presence in DOM
	 * @return: True if available in DOM else False
	 */
	public boolean isElementPresent(WebElement element) {
		return element.isDisplayed();
	}

}
