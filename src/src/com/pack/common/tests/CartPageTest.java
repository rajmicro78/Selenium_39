package src.com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.CartPage;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.ProductListPage;

public class CartPageTest extends TestBaseSetup {
	private WebDriver driver;
	private ProductListPage productListPage;
	private ProductDetailPage productDetailPage;
	private CartPage cartPage;
	private String CurrentURL;
	@BeforeClass
	public void setUp() {
		driver=getDriver();
		CurrentURL= driver.getCurrentUrl();
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
	}
	
	@Test(groups ={"cart"},description="Adding products to cart and verifying the cart details")	
	public void cartPage() throws Exception {
		
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
	//	productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();	
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.cartPageDetail();
		cartPage.increaseqty();
		//cartpage.decreaseqty();
		//cartPage.cartcalculation();
		cartPage.addSpecialProduct();
		//cartPage.cartcalculation();
		cartPage.clickCheckout();
}

}