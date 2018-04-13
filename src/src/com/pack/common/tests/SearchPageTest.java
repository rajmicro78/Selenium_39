package src.com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.CartPage;
import src.com.pack.common.pageobjects.CheckOutPage;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.SearchPage;
import src.com.pack.common.pageobjects.ProductListPage;

public class SearchPageTest extends TestBaseSetup {
	private WebDriver driver;
	private SearchPage searchPage;
	private ProductListPage productListPage;
	private ProductDetailPage productDetailPage;
	private CartPage cartPage;
	private CheckOutPage checkOutPage;
	public String uemail;
	public String upassword;
	public String sitename;
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		uemail = context.getCurrentXmlTest().getParameter("uEmail");
		upassword = context.getCurrentXmlTest().getParameter("uPassword");
		sitename = context.getCurrentXmlTest().getName();
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
        
	}
	
	@Test(priority=2,groups ={"productsearch"}, description="Search normal product")	 
	public void productsearch() throws Exception {
		System.out.println("product search");
		HomePage homepage = new HomePage(driver);
		searchPage = homepage.search();
		searchPage.productsearch();
	}
	@Test(priority=1,groups ={"searchwithproduct"}, description="Search with product name")	 
	public void searchproduct() throws Exception {
		System.out.println("Search With product name");
		HomePage homepage = new HomePage(driver);
		searchPage = homepage.search();
		
		searchPage.searchproductname();
	}
	
	
}


