package specs;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.Template;
import views.HomeScreen;

public class FlightBookingTest extends Template {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		// Initializing Home Screen Page Objects
		logger.info("Initializing Home Screen Page Objects");
		homeScreen = PageFactory.initElements(appLib.getDriver(), HomeScreen.class);

		// Selecting One way trip
		logger.info("Selecting One way trip");
		appLib.clickElement(homeScreen.rdoTripType, "Click on Trip-Type radio button");

		// Entering From location
		logger.info("Entering From location");
		appLib.setValue(homeScreen.ddlFrom, exceldata.get("From"));

		// Picking first value from suggestions
		logger.info("Picking first value from suggestions");
		appLib.clickElement(homeScreen.listOfFromSuggestions, "Picking first FROM Suggestions");

		// Entering To location
		logger.info("Entering To location");
		appLib.setValue(homeScreen.ddlTo, exceldata.get("To"));

		// Picking first value from suggestions
		logger.info("Picking first value from suggestions");
		appLib.clickElement(homeScreen.listOfToSuggestions, "Picking first TO Suggestions");

		// Selecting date of journey
		logger.info("Selecting date of journey");
		appLib.selectDateFromDatePicker(Integer.parseInt(exceldata.get("DepartOn")));

		// Clicking on Search button
		logger.info("Clicking on Search button");
		appLib.clickElement(homeScreen.btnSearch, "Click Search button");

		// Verify Search Summary is displayed
		logger.info("Verify Search Summary is displayed");
		appLib.waitforElementVisible(homeScreen.txtSearchSummary);
		Assert.assertTrue(appLib.isElementPresent(homeScreen.txtSearchSummary));
	}

}
