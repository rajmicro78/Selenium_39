package src.com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;

import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.ProductListPage;

public class ProductDetailPageTest extends TestBaseSetup {
	private WebDriver driver;
	private ProductListPage productListPage;
	private ProductDetailPage productDetailPage;

	@BeforeClass
	public void setUp() {
		driver=getDriver();
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
	}
	
	@Test (groups ={"productdetail"},description="Verify product detail page and its detail")
	public void verifyProductDetailPage() throws Exception {
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		productListPage.clickFirstProduct();
		
		productDetailPage = productListPage.clickproduct();
		//productDetailPage.productnumber();
		productDetailPage.productName();
		productDetailPage.finalPrice();
		//productDetailPage.productRating();
		
	}
	
}
