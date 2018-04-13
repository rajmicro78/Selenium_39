package src.com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.CartPage;
import src.com.pack.common.pageobjects.CheckOutPage;
import src.com.pack.common.pageobjects.GiftCertificate;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.ProductListPage;


public class GiftCertificatePage extends TestBaseSetup {
	
	private WebDriver driver;
	private GiftCertificate giftCertificate;
	private ProductListPage productListPage;
	private ProductDetailPage productDetailPage;
	private CartPage cartPage;
	private CheckOutPage checkOutPage;
	public String uemail;
	public String upassword;
	public String sitename;
	String GCcode;
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		uemail = context.getCurrentXmlTest().getParameter("uEmail");
		upassword = context.getCurrentXmlTest().getParameter("uPassword");
		sitename = context.getCurrentXmlTest().getName();
	}

	@Test(priority=1,groups ={"buygiftcertificate"}, description="Buy gift certificate product")	 
	public void giftproductsearch() throws Exception {
		System.out.println("Gift search");
		HomePage homepage = new HomePage(driver);
		giftCertificate = homepage.giftCertificate();
		//19-3-18 Newly added Sitename
		giftCertificate.buygiftcertificate(sitename);
		productListPage = homepage.listMenu();
		Thread.sleep(2000);
		productListPage.clickFirstProduct();
		productDetailPage = productListPage.clickproduct();	
		cartPage = productDetailPage.addtoCart();
		productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		Thread.sleep(2000);
		checkOutPage.guestCheckout(uemail);
		checkOutPage.cardPaymentVisa();
		checkOutPage.orderNumber();
	}
	
	@Test(priority=2,groups ={"usegiftcertificate"}, description="Use gift certificate and create order ")	 
	public void usegiftcertificate() throws Exception {
		System.out.println("Use Gift certificate");
		HomePage homepage = new HomePage(driver);
		homepage.Login(uemail,upassword);
		giftCertificate = homepage.giftCertificate();
		GCcode = giftCertificate.readgiftcertificate();
		productListPage = homepage.listMenu();
		Thread.sleep(2000);
		productListPage.clickMenu();
		productListPage.clickFirstProduct();
		productDetailPage = productListPage.clickproduct();	
		cartPage = productDetailPage.addtoCart();
		productListPage.cartvalue();
		
		giftCertificate.applygc(GCcode);
		
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		Thread.sleep(2000);
		//checkOutPage.registeredUserCheckout(uemail,upassword);
		checkOutPage.cardPaymentVisa();
		checkOutPage.orderNumber();
	}
}
