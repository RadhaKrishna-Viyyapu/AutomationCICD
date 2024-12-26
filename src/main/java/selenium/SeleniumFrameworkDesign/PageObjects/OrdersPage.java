package selenium.SeleniumFrameworkDesign.PageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	 public OrdersPage(WebDriver driver){
		 
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }

	
	//pageFactory
	
	 //List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersProducts;
	
	public Boolean verifyOrdersDisplay(String item1)
	{
		Boolean match=ordersProducts.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(item1));
		return match;
	}
	


}
