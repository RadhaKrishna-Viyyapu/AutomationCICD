package selenium.SeleniumFrameworkDesign.PageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	 public CartPage(WebDriver driver){
		 
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }

	
	//pageFactory
	
	 //List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public Boolean verifyProductsDisplay(String item1)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(item1));
		return match;
	}
	public CheckOutPage goToCheckOutPage()
	{
		checkOut.click();
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;
	}


}
