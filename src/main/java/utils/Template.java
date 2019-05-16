package utils;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import views.HomeScreen;
import views.HotelsScreen;
import views.LoginScreen;

public class Template {
	protected ApplicationLibrary appLib;
	protected HomeScreen homeScreen;
	protected HotelsScreen hotelsScreen;
	protected LoginScreen loginScreen;
	protected Map<String, String> exceldata;
	protected Logger logger;

	/**
	 * Initializing browser
	 */
	@BeforeTest
	public void setBaseURL() {
		appLib = new ApplicationLibrary();
		System.out.println(this.getClass().getSimpleName());
		exceldata = ExcelDataHandler.getTestData(this.getClass().getSimpleName());
		appLib.invokeBrowser(AppConstants.CHROME);
		appLib.getDriver().get(AppConstants.URL);
		logger = Logger.getLogger(this.getClass().getSimpleName());
		logger.info("Log4j is intialized");
	}

	/**
	 * Driver Quit
	 */
	@AfterTest
	public void closingBrowserWindow() {
		appLib.getDriver().quit();
		logger.info("Driver closed successfully");
	}
}
