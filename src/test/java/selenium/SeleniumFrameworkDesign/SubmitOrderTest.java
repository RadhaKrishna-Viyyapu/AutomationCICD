package selenium.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.SeleniumFrameworkDesign.PageObjects.CartPage;
import selenium.SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import selenium.SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import selenium.SeleniumFrameworkDesign.PageObjects.OrdersPage;
import selenium.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import selenium.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	String item1="ADIDAS ORIGINAL";

		@Test(dataProvider="getData", groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
		//String item1="ADIDAS ORIGINAL";
		String countryName="india";
		//LandingPage
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		//ProductCatalogue
		@SuppressWarnings("unused")
		List<WebElement>products=productCatalogue.getProducts();
		productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCart();
		//CartPage
		Boolean match=cartPage.verifyProductsDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOutPage();
		//CheckOutPage
		checkOutPage.selectCountry(countryName);;
		ConfirmationPage confirmationPage=checkOutPage.goToSubmitPage();;
		//ConfirmationPage
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confirmMessage);

	    }  
		
		@Test(dependsOnMethods={"submitOrder"})
		public void OrderHistoryTest()
		{
			ProductCatalogue productCatalogue=landingPage.loginApplication("radhakrishnaviyyapu@gmail.com", "Viyyapu@159");
			OrdersPage ordersPage=	productCatalogue.goTorders();
			Assert.assertTrue(ordersPage.verifyOrdersDisplay(item1));
		}
		
	
		
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
	
		
		
		/*@DataProvider
		public Object[][] getData()
		{
			return new Object[] [] {{"radhakrishnaviyyapu@gmail.com","Viyyapu@159","ADIDAS ORIGINAL"},{"shetty@gmail.com","Iamking@000","ZARA COAT 3"}};
		}*/
		

}
