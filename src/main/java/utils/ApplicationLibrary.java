package utils;

import java.time.LocalDate;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;

public class ApplicationLibrary extends CommonLibrary {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	public void selectDateFromDatePicker(int daysAfterfromNow) {
		try {
			logger.info("Picking date through date picker");
			LocalDate todayDate = LocalDate.now();
			LocalDate targetdate = todayDate.plusDays(daysAfterfromNow);
			waitFor(AppConstants.SLEEP_MS);
			int targetDate = targetdate.getDayOfMonth();
			waitforElementVisible(getDriver().findElement(
					By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + targetDate + "']")));
			getDriver()
					.findElement(
							By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + targetDate + "']"))
					.click();
		} catch (Exception e) {
			logger.error("Failed at pickinf date from date picker "+e.getMessage());
			Assert.fail("Failed at pickinf date from date picker "+e.getMessage());
		}
	}
}
