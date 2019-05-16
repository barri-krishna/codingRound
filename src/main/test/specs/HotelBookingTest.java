package specs;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.Template;
import views.HotelsScreen;

public class HotelBookingTest extends Template{

	@Test
	public void shouldBeAbleToSearchForHotels() {

		// Initializing Hotels Screen Page Objects
		hotelsScreen = PageFactory.initElements(appLib.getDriver(), HotelsScreen.class);

		// Navigating to Hotels Search Screen
		appLib.clickElement(hotelsScreen.hotelLink, "Clicking on Hotels Tab");

		// Entering From location
		appLib.setValue(hotelsScreen.txtLocality, exceldata.get("HotelLocation"));

		// Picking first value from suggestions
		appLib.clickElement(hotelsScreen.localitySuggestions, "Picking first locality Suggestion");

		// Picking up Check-In Date from date picker
		appLib.selectDateFromDatePicker(Integer.parseInt(exceldata.get("CheckInDay")));

		// Picking up Check-Out Date from date picker
		appLib.selectDateFromDatePicker(Integer.parseInt(exceldata.get("CheckOutDay")));

		// Picking Traveller's/Guest information
		new Select(hotelsScreen.travellerSelection).selectByVisibleText(exceldata.get("TravellersInformation"));

		// Clicking on Search button
		appLib.clickElement(hotelsScreen.btnSearch, "Click Search button");

		// Verify Search Summary is displayed
		appLib.waitforElementVisible(hotelsScreen.txtSearchSummary);
		Assert.assertTrue(appLib.isElementPresent(hotelsScreen.txtSearchSummary));

	}

}
