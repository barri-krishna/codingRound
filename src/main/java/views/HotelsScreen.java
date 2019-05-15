package views;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsScreen {

	@FindBy(linkText = "Hotels")
	public WebElement hotelLink;

	@FindBy(id = "Tags")
	public WebElement txtLocality;

	@FindBy(id = "SearchHotelsButton")
	public WebElement btnSearch;

	@FindBy(id = "travellersOnhome")
	public WebElement travellerSelection;

	@FindBy(xpath = "//*[@id='ui-id-1']//li//a")
	public WebElement localitySuggestions;

	@FindBy(className = "searchSummary")
	public WebElement txtSearchSummary;
}
