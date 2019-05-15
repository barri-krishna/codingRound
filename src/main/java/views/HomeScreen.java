package views;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeScreen {

	@FindBy(id = "OneWay")
	public WebElement rdoTripType;

	@FindBy(id = "FromTag")
	public WebElement ddlFrom;

	@FindBy(id = "ToTag")
	public WebElement ddlTo;
	
	@FindBy(xpath = "//*[@id='ui-id-1']//li")
	public WebElement listOfFromSuggestions;
	
	@FindBy(xpath = "//*[@id='ui-id-2']//li")
	public WebElement listOfToSuggestions;
	
	@FindBy(id = "SearchBtn")
	public WebElement btnSearch;
	
	@FindBy(className = "searchSummary")
	public WebElement txtSearchSummary;
	
}
