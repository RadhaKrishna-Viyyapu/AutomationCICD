package selenium.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.SeleniumFrameworkDesign.PageObjects.CartPage;
import selenium.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import selenium.TestComponents.BaseTest;
import selenium.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

		@Test(groups= {"Errorhandling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation()
		{
		landingPage.loginApplication("radhakrishnaviyyapu@gmail.com", "Viyyau@159");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		System.out.println(landingPage.getErrorMessage());
		}
		
		@Test(groups= {"Errorhandling"})
		public void ProductErrorValidation() throws IOException, InterruptedException
		{
		String item1="ADIDAS ORIGINAL";
		//LandingPage
		ProductCatalogue productCatalogue=landingPage.loginApplication("radhakrishnaviyyapu@gmail.com", "Viyyapu@159");
		//ProductCatalogue
		@SuppressWarnings("unused")
		List<WebElement>products=productCatalogue.getProducts();
		productCatalogue.addToCart(item1);
		CartPage cartPage=productCatalogue.goToCart();
		//CartPage
		Boolean match=cartPage.verifyProductsDisplay("Laptop");
		Assert.assertFalse(match);
		
		}
		
	


	

}
