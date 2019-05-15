package specs;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.AppConstants;
import utils.ApplicationLibrary;
import utils.ExcelDataHandler;
import views.HomeScreen;

public class FlightBookingTest {

	protected ApplicationLibrary appLib;
	protected HomeScreen homeScreen;
	protected Map<String, String> exceldata;

	@BeforeTest
	public void setBaseURL() {
		appLib = new ApplicationLibrary();
		exceldata = ExcelDataHandler.getTestData(this.getClass().getSimpleName());
		appLib.invokeBrowser(AppConstants.CHROME);
		appLib.getDriver().get(AppConstants.URL);
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		// Initializing Home Screen Page Objects
		homeScreen = PageFactory.initElements(appLib.getDriver(), HomeScreen.class);

		// Selecting One way trip
		appLib.clickElement(homeScreen.rdoTripType, "Click on Trip-Type radio button");

		// Entering From location
		appLib.setValue(homeScreen.ddlFrom, exceldata.get("From"));

		// Picking first value from suggestions
		appLib.clickElement(homeScreen.listOfFromSuggestions, "Picking first FROM Suggestions");

		// Entering To location
		appLib.setValue(homeScreen.ddlTo, exceldata.get("To"));
		
		// Picking first value from suggestions
		appLib.clickElement(homeScreen.listOfToSuggestions, "Picking first TO Suggestions");

		// Selecting date of journey
		appLib.selectDateFromDatePicker(Integer.parseInt(exceldata.get("DepartOn")));

		// Clicking on Search button
		appLib.clickElement(homeScreen.btnSearch, "Click Search button");

		// Verify Search Summary is displayed
		appLib.waitforElementVisible(homeScreen.txtSearchSummary);
		Assert.assertTrue(appLib.isElementPresent(homeScreen.txtSearchSummary));
		
		appLib.getDriver().quit();

	}

}
