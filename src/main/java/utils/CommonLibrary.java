package utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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

import com.sun.javafx.PlatformUtil;

/**
 * @author krishna.barri
 *
 */
@SuppressWarnings("restriction")
public class CommonLibrary {

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

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

	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitforElementVisible(final WebElement webelement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), AppConstants.DRIVER_WAIT);
			wait.until(ExpectedConditions.visibilityOf(webelement));
			wait.pollingEvery(1, TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
//			logger.log("Found element in DOM...");
		} catch (TimeoutException e) {
			// LOGGER.error("Exception in fluentWait()" + e);
			// rep.report("Error", Status.FAIL, "Element locator '" +
			// locator.toString()
			// + "' did not match any elements after " + time + " seconds.",
			// Screenshot.TRUE);
		} catch (Exception e) {
			// LOGGER.error("Exception in fluentWait()" + e);
			// rep.reportinCatch(e);
		}
	}

	public void clickElement(WebElement webelement, String sReportText) {
		try {
			
			waitforElementVisible(webelement);
			new Actions(getDriver()).moveToElement(webelement).perform();
			webelement.click();
			// rep.report(sReportText, Status.PASS, " is clicked",
			// Screenshot.FALSE);
		} catch (Exception e) {
			// LOGGER.error("Exception in clickElement()" + e);
			// rep.reportinCatch(e);
			System.out.println(e.toString());
		}
	}

	public void setValue(WebElement webelement, String sValue) {
		try {
			waitforElementVisible(webelement);
			webelement.click();
			String elementValue = webelement.getAttribute("value");
			if (!elementValue.isEmpty() && elementValue != null) {
				webelement.clear();
			}
			webelement.sendKeys(sValue);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	public boolean isElementPresent(WebElement element) {
		return element.isDisplayed();
	}

}
