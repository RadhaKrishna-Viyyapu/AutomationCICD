package selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.SeleniumFrameworkDesign.PageObjects.CartPage;
import selenium.SeleniumFrameworkDesign.PageObjects.OrdersPage;

public  class AbstractComponent {
	WebDriver driver;
	

	public AbstractComponent(WebDriver driver) 
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	By itemsAppear=By.cssSelector(".cartWrap");
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	public void waitForElementToDisappear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCart() {
		cartHeader.click();
		waitForElementToAppear(itemsAppear);
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goTorders()
	{
		ordersHeader.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
		
	}

}
