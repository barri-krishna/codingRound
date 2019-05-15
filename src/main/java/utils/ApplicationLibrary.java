package utils;

import java.time.LocalDate;

import org.openqa.selenium.By;

public class ApplicationLibrary extends CommonLibrary {

	public void selectDateFromDatePicker(int numOfDaysAfter) {
		LocalDate todayDate = LocalDate.now();
		LocalDate targetdate = todayDate.plusDays(numOfDaysAfter);
		waitFor(500);
		int dayOfTargetDate = targetdate.getDayOfMonth();
		waitforElementVisible(getDriver().findElement(
				By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + dayOfTargetDate + "']")));
		getDriver()
				.findElement(
						By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + dayOfTargetDate + "']"))
				.click();
	}
}
