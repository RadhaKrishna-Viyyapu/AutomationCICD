package selenium.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	 public CheckOutPage(WebDriver driver){
		 
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }

	
	//pageFactory
	@FindBy(css="[placeholder*='Country']")
	WebElement country;
	@FindBy(css=".ta-results button:nth-child(3)")
	WebElement selectCountry;
	By results=By.cssSelector(".ta-results");
	@FindBy(css=".action__submit")
	WebElement submit;
	By confirmationBox=By.cssSelector(".box");
	public void selectCountry(String countryName)
	{
		Actions action=new Actions(driver);
		action.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	public ConfirmationPage goToSubmitPage()
	{
		submit.click();
		waitForElementToAppear(confirmationBox);
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}


}
