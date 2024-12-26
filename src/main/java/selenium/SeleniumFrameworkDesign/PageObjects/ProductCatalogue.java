package selenium.SeleniumFrameworkDesign.PageObjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	 public ProductCatalogue(WebDriver driver){
		 
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }

	
	//pageFactory
	
	 //List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productsBy =By.cssSelector(".mb-3");
	By addTocart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	By elementDisappear=By.cssSelector(".ngx-spinner-overlay.ng-tns-c31-26.ng-trigger.ng-trigger-fadeIn.ng-star-inserted.ng-animating");
	
	
	public List<WebElement> getProducts() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String item1)
	{
		WebElement prod=getProducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(item1)).findFirst().orElse(null);
		return prod;
	}
	public void addToCart(String item1) throws InterruptedException
	{
		WebElement prod=getProductByName(item1);
		prod.findElement(addTocart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(elementDisappear);
		Thread.sleep(3000);
	}


}
