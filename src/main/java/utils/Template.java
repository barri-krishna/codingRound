package utils;

import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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

	@BeforeTest
	public void setBaseURL() {
		appLib = new ApplicationLibrary();
		System.out.println(this.getClass().getSimpleName());
		exceldata = ExcelDataHandler.getTestData(this.getClass().getSimpleName());
		appLib.invokeBrowser(AppConstants.CHROME);
		appLib.getDriver().get(AppConstants.URL);
		final Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
//		PropertyConfigurator.configure(AppConstants.PARENTFOLDER_PATH+AppConstants.LOG4J_PROPERTIES);
		LOGGER.info("Log4j is intialized");
	}

	@AfterTest
	public void closingBrowserWindow() {
		appLib.getDriver().quit();
	}
}
