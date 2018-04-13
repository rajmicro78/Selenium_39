package src.com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.CartPage;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.LivePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.ProductListPage;
import src.com.pack.common.pageobjects.SearchPage;
import src.com.pack.common.pageobjects.registration;

public class LiveTest extends TestBaseSetup  {
	private WebDriver driver;
	HomePage objhomePage;
	ProductListPage productlistpage;
	LivePage livepage;
	registration registrationpages;
	ProductDetailPage productdetailpage;
	
	CartPage cartPage;
	public String sitename;
	private String alphabetical;
	private String mostavailable;
	private String mostsold;
	private String lowestprice;
	private String highestprice;
	private String newestproduct;
	private String highestrebatt;
	private String searchterm1;
	private String searchterm2;
	private SearchPage SearchPage;
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		objhomePage = new HomePage(driver);
		productlistpage = new ProductListPage(driver);
		//registrationpages =new registration(driver);
		productdetailpage = new ProductDetailPage(driver);
		livepage = new LivePage(driver);
		driver.manage().deleteAllCookies();
		sitename = context.getCurrentXmlTest().getName();
		switch (sitename) {
		case "CP-Sweden":
			alphabetical="A till Ö";
			mostavailable="Tillgänglighet";
			mostsold="Bästsäljare";
			lowestprice ="Lägsta pris";
			highestprice="Högsta pris";
			newestproduct="Senast inkomna";
			highestrebatt= "Högsta rabatt";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
			break;
		case "CP-Finland":
			alphabetical="A–Ö";
			mostavailable="Saatavuus";
			mostsold="Suositut";
			lowestprice ="Alin hinta";
			highestprice="Korkein hinta";
			newestproduct="Uusimmat";
			highestrebatt= "Suurin alennus";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
			break;
		case "BliVakker":
			alphabetical="A til Å";
			mostavailable="Mest tilgjengelig";
			mostsold="Mest solgt";
			lowestprice ="Laveste pris";
			highestprice="Høyeste pris";
			newestproduct="Nyeste produkter";
			highestrebatt= "Høyest rabatt";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
		break;
		case "Brandsdal":
			alphabetical="A til Å";
			mostavailable="Mest tilgjengelig";
			mostsold="Mest solgt";
			lowestprice ="Laveste pris";
			highestprice="Høyeste pris";
			newestproduct="Nyeste produkter";
			highestrebatt= "Høyest rabatt";
			searchterm1= "jakker";
			searchterm2= "tshirt";
		break;
		case "CP-Austria":
			alphabetical="A bis Z";
			mostavailable="Verfügbarkeit";
			mostsold="BESTSELLER";
			lowestprice ="Niedrigster Preis";
			highestprice="Höchster Preis";
			newestproduct="Neueste Artikel";
			highestrebatt= "Höchster Rabatt";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
			break;
		case "CP-Germany":
			alphabetical="A bis Z";
			mostavailable="Verfügbarkeit";
			mostsold="BESTSELLER";
			lowestprice ="Niedrigster Preis";
			highestprice="Höchster Preis";
			newestproduct="Neueste Artikel";
			highestrebatt= "Höchster Rabatt";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
			break;
		case "CP-Denmark":
			alphabetical="A til Å";
			mostavailable="Mest tilgængelig";
			mostsold="Mest solgt";
			lowestprice ="Laveste pris";
			highestprice="Højeste pris";
			newestproduct="Nyeste produkter";
			highestrebatt= "Højeste rabat";
			searchterm1= "Shampoo";
			searchterm2= "Cream";
			break;
		}
	}
	@Test(priority=1,groups ={"HomePage"}, description="Home Page Check")
	public void HomepageTest() throws Exception  {
		livepage.Livesite(appURL);
		Reporter.log("Logo Title-"+objhomePage.verifyCompanyLogo());
		//Assert.assertTrue(objhomePage.verifySearchBoxPresent());
		Reporter.log("Search Box Present -" +objhomePage.verifySearchBoxPresent());
		//Assert.assertTrue(objhomePage.verifySearchBoxbutton());
		Reporter.log("Search Button Present -" +objhomePage.verifySearchBoxbutton());
		//Assert.assertTrue(objhomePage.verifymypageicon());
		Reporter.log("MyPage icon  -" +objhomePage.verifymypageicon());
		//Assert.assertTrue(objhomePage.verifyinfoicon());
		Reporter.log("Infocenter icon  -" +objhomePage.verifyinfoicon());
		//Assert.assertTrue(objhomePage.verifyoffericon());
		Reporter.log("Offer icon  -" +objhomePage.verifyoffericon());
		//Assert.assertTrue(objhomePage.verifywishlisticon());
		Reporter.log("Wishlist icon  -" +objhomePage.verifywishlisticon());
		//Assert.assertTrue(objhomePage.verifycarticon());
		Reporter.log("Cart icon  -" +objhomePage.verifycarticon());
		Assert.assertTrue(objhomePage.topuspbar());
		Reporter.log("Top Usp Bar  -" +objhomePage.topuspbar());
		Assert.assertTrue(objhomePage.banner());
		Reporter.log("Main Banner  -" +objhomePage.banner());
		Assert.assertTrue(objhomePage.recommendedproduct());
		Reporter.log("Recommended Product  -" +objhomePage.recommendedproduct());
		Assert.assertTrue(objhomePage.promobanner());
		Reporter.log("Promo Banner  -" +objhomePage.promobanner());
		Assert.assertTrue(objhomePage.instagramfeed());
		Reporter.log("Instagram Feed  -" +objhomePage.instagramfeed());
		Assert.assertTrue(objhomePage.trustpilot());
		Reporter.log("Trustpilot  -" +objhomePage.trustpilot());
		//Assert.assertTrue(objhomePage.newsletter());
		//Reporter.log("Newsletter  -" +objhomePage.newsletter());
		Assert.assertTrue(objhomePage.bottomuspbar());
		Reporter.log("Bottom Usp Bar  -" +objhomePage.bottomuspbar());
		Assert.assertTrue(objhomePage.footer());
		Reporter.log("Footer  -" +objhomePage.footer());
		objhomePage.listMenuName();
		
	}
	@Test(priority=2,groups ={"Search"}, description="Search Functionality")
	public void SearchFunctionality() throws Exception  {
		livepage.Livesite(appURL);
		HomePage homepage = new HomePage(driver);
		SearchPage = homepage.search();
		SearchPage.searchwithterm(searchterm1,sitename);
		SearchPage.sortoption(lowestprice,sitename);	
		SearchPage.searchwithterm(searchterm2,sitename);
		SearchPage.sortoption(highestrebatt, sitename);	
	}
	@Test(priority=3,groups ={"ProductList"}, description="Check Product List Page")
	public void productList() throws Exception{
		livepage.Livesite(appURL);
		objhomePage.clickmenu2();
		productlistpage.checkimage();
	}
	@Test(priority=4,groups ={"Registration"}, description="Check Registration Page")
	public void register() throws Exception{
		String registrationpage = appURL+"/register";
		driver.navigate().to(registrationpage);
		//livepage.Livesite(registrationpage);
		livepage.verifyregistrationpage();
	}
	@Test(priority=5,groups ={"ProductDetail"}, description="Check Product Detail Page")
	public void productdetail() throws Exception{
		livepage.Livesite(appURL);
		productlistpage.clickMenu();
		Thread.sleep(2000);
		productlistpage.clickproduct();
		productdetailpage.productName();
		productdetailpage.wishlistBtn();
		productdetailpage.productRating();
		productdetailpage.productDescription();
		productdetailpage.productImage();
		productdetailpage.productReviewbtn();
		productdetailpage.productSaleprice();
		productdetailpage.productQtydropdown();
		//productdetailpage.productrecommended();
		productdetailpage.productaddtocartbtn();
	}
	@Test(priority=6,groups ={"Cartpage"}, description="Check cart page")
	public void cartpage() throws Exception{
		livepage.Livesite(appURL);
		productlistpage.clickMenu();
		productlistpage.clickproduct();
		cartPage = productdetailpage.addtoCart();
		cartPage.cartPageDetail();
		cartPage.increaseqty();
		//cartPage.addSpecialProduct();
		cartPage.clickCheckout();
	}

}
