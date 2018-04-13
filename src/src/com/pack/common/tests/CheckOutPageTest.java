package src.com.pack.common.tests;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.CartPage;
import src.com.pack.common.pageobjects.CheckOutPage;
import src.com.pack.common.pageobjects.GiftCertificate;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.ProductDetailPage;
import src.com.pack.common.pageobjects.ProductListPage;
public class CheckOutPageTest extends TestBaseSetup {
	private WebDriver driver;
	private ProductListPage productListPage;
	private ProductDetailPage productDetailPage;
	private GiftCertificate giftCertificate;
	private CartPage cartPage;
	private CheckOutPage checkOutPage;
	public String uemail;
	public String upassword;
	public String sitename;
	public String shop;
	String GCcode;
	
	private String CurrentURL;
	//Brandsdal (7013) - Collector, Card(no easypayment), Prepayment, Mobilepay
	//CPFI (7012) - Card, collector, Prepayment
	//CPSE (7011) - Collector, card (no easypayment), Masterpass, 
	//CPDK (7010)- Card, Dankort
	//Dl(7009) - Collector, Bankaxess, Card, Prepayment, , Mobilepay
	//BV(7008) - Collector, Bankaxess, Card, Prepayment,  Mobilepay // Masterpass Removed
	//CPDE(7008) - Kort, sofort, Masterpass, paypal
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		uemail = context.getCurrentXmlTest().getParameter("uEmail");
		upassword = context.getCurrentXmlTest().getParameter("uPassword");
		sitename = context.getCurrentXmlTest().getName();
		
		CurrentURL= driver.getCurrentUrl();
		 Pattern p = Pattern.compile("(^https?://[^/]+)");
		 java.util.regex.Matcher m = p.matcher(CurrentURL);
		 if (m.find())
        {
          // we're only looking for one group, so get it
           CurrentURL = m.group(1);
           //System.out.println("CurrentURL");
        }
	}
	@Test(priority=3,groups ={"visacard"}, description="Search and Buy Gift as Guest Card Visa Payment")	 
	public void guestCheckout() throws Exception {
		System.out.println("Guest Checkout with Visa Card");
		//Reporter.log("Registered user with Visa Card");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		if(sitename.equals("Brandsdal")||sitename.equals("Netthandelen")){
		productListPage.clickMenu();
		Thread.sleep(2000);
		//productListPage.clickFirstProductnew();
		//Thread.sleep(3000);
		}else{
			giftCertificate = homepage.giftCertificate();
			giftCertificate.buygiftcertificate(sitename);
		}
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		Thread.sleep(2000);
		checkOutPage.guestCheckout(uemail);
		Thread.sleep(1000);
		checkOutPage.cardPaymentVisa();
		checkOutPage.orderNumber();
		//COP.validUserCheckout();
	}
	@Test(priority=4,groups ={"gmastercard"}, description="Guest Checkout with Card Master Payment")	 
	public void mastercguest() throws Exception {
		System.out.println("Guest Checkout with Master Card");
		//Reporter.log("Registered user with Master Card");
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
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		Thread.sleep(2000);
		checkOutPage.guestCheckout(uemail);
		checkOutPage.cardPaymentMaster();
		checkOutPage.orderNumber();
		//COP.validUserCheckout();
	}
	@Test(priority=5, groups ={"mastercard"},description ="Registered user Apply Gift certificate and pay Master card")	 
	public void loggedinuserCheckout() throws Exception {
		System.out.println("Registered user checkout with Master card Payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Master Card");
		HomePage homepage = new HomePage(driver);
		homepage.Login(uemail,upassword);
		if(!(sitename.equals("Netthandelen")||sitename.equals("Brandsdal"))){
		giftCertificate = homepage.giftCertificate();
		GCcode = giftCertificate.readgiftcertificate();}
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
	//	productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		if(!(sitename.equals("Netthandelen")||sitename.equals("Brandsdal"))){
		giftCertificate.applygc(GCcode);}
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		checkOutPage.selectCity();
		//checkOutPage.registeredUserCheckout(uemail,upassword);
		
		//boolean paymentsection= driver.findElements(By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']")).size()!=0;
		boolean paymentsection =driver.findElement(By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']")).isDisplayed();
				//By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']");
		Reporter.log("Payment section-"+paymentsection);
		if(paymentsection){
		System.out.println("Now into master card payment");
		checkOutPage.cardPaymentMaster();
		checkOutPage.orderNumber();
		}else{
		checkOutPage.submitbtn();
		checkOutPage.orderNumber();	}
	}
	@Test(priority=6, groups ={"masterpass"},description ="Registered user checkout with Master pass Payment method")	 
	public void RegisterMasterPass() throws Exception {
		System.out.println("Registered user checkout with Master Pass Payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Master Pass");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
	//	productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
	//	checkOutPage.registeredUserCheckout(uemail,upassword);
		checkOutPage.Masterpass();
		Thread.sleep(2000);
		checkOutPage.orderNumber();	
	}
	@Test(priority=6, groups ={"sofort"},description ="Registered user checkout with Sofort Payment method")	 
	public void loggedinusersofort() throws Exception {
		
		System.out.println("Registered user checkout with Sofort  Payment method");
		//Reporter.log("Registered user with Sofort");
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
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		//checkOutPage.registeredUserCheckout(uemail,upassword);
		checkOutPage.Sofort();
		checkOutPage.orderNumber();
		
	}
	@Test(priority=7, groups ={"paypal"},description ="Registered user checkout with PayPal Payment method")	 
	public void loggedinuserpaypal() throws Exception {
		
		System.out.println("Registered user checkout with Paypal  Payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Sofort");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		//checkOutPage.registeredUserCheckout(uemail,upassword);
		checkOutPage.PayPal();
		checkOutPage.orderNumber();
		
	}
	@Test(priority=3,groups ={"easypayment"}, description="Registered user checkout with easypayment payment method" )	 
	public void loggedinuserEasyPayment() throws Exception {
		System.out.println("Registered user checkout with easypayment payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Easypayment");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		//Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		checkOutPage.registeredUserCheckout(uemail,upassword);
		//checkOutPage.easypaymentdetail();
		checkOutPage.easyPayment();
		checkOutPage.orderNumber();
		
	}
	@Test(priority=8, groups ={"collector"}, description = "Registered user with collector payment")	 
	public void loggedinuserCollector() throws Exception {
		//String curURL = context.getCurrentXmlTest().getParameter("appURL");
		//System.out.println(curURL);
		System.out.println("Registered user with collector payment");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Collector");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
		//productListPage.addmultipleProduct();
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		//Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		//checkOutPage.registeredUserCheckout(uemail,upassword );
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight/3);");
		checkOutPage.collector();
		Thread.sleep(3000);
		checkOutPage.orderNumber();
		
	}
	@Test(priority=10, groups ={"trustly"}, description = "Registered user with trustly payment")	 
	public void loggedinuserTrustly() throws Exception {
		//String curURL = context.getCurrentXmlTest().getParameter("appURL");
		//System.out.println(curURL);
		System.out.println("Registered user with Trustly payment");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Collector");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
		//productListPage.addmultipleProduct();
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		//Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		//checkOutPage.registeredUserCheckout(uemail,upassword );
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight/3);");
		checkOutPage.trustly();
		Thread.sleep(3000);
		checkOutPage.orderNumber();
		
	}
	@Test(priority=9, groups ={"dankort"}, description ="Registered user with Dankort Payment Method")	 
	public void loggedinuserDankort() throws Exception {
		//String curURL = context.getCurrentXmlTest().getParameter("appURL");
		//System.out.println(curURL);
		System.out.println("Registered user with Dankort Payment Method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Registered user with Dankort");
		HomePage homepage = new HomePage(driver);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
	//	productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		Thread.sleep(2000);
		//checkOutPage.addSpecialProduct();
		//checkOutPage.registeredUserCheckout(uemail,upassword);
		checkOutPage.selectCity();
		checkOutPage.dankort();
		checkOutPage.orderNumber();
		
	}
	@Test (priority=1,groups ={"prepayment"}, description ="Guest user with prepayment payment method")	 
	//public void guestPrepayment() throws Exception {
	public void guestPrepayment() throws Exception {
		//String curURL = context.getCurrentXmlTest().getParameter("appURL");
		//String website = curURL.replaceAll("[^0-9]","");
		//System.out.println(website);
		// uemail = context.getCurrentXmlTest().getParameter("uEmail");
		 //upassword = context.getCurrentXmlTest().getParameter("uPassword");
		
		System.out.println("Guest user with prepayment payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Guest user with prepayment");
		HomePage homepage = new HomePage(driver);
		Thread.sleep(2000);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		//checkOutPage.addSpecialProduct();
		Thread.sleep(4000);
		checkOutPage.guestCheckout(uemail);
		checkOutPage.prePayment();
		checkOutPage.orderNumber();
		
	}
	
	@Test (priority=2,groups ={"bankaxess"}, description ="Guest user with Bankaxess payment method")	 
	//public void guestPrepayment() throws Exception {
	public void guestBankaxess() throws Exception {
		//String curURL = context.getCurrentXmlTest().getParameter("appURL");
		//String website = curURL.replaceAll("[^0-9]","");
		//System.out.println(website);
		// uemail = context.getCurrentXmlTest().getParameter("uEmail");
		 //upassword = context.getCurrentXmlTest().getParameter("uPassword");
		
		System.out.println("Guest user with Bankaxess payment method");
		driver.navigate().to(CurrentURL);
		System.out.println(CurrentURL);
		//Reporter.log("Guest user with prepayment");
		HomePage homepage = new HomePage(driver);
		Thread.sleep(2000);
		productListPage = homepage.listMenu();
		productListPage.clickMenu();
		Thread.sleep(2000);
//		productListPage.clickFirstProductnew();
		productDetailPage = productListPage.clickproduct();
		cartPage = productDetailPage.addtoCart();
		//new changes 1703 cart value not displaying 
		//productListPage.cartvalue();
		cartPage.clickCheckout();
		checkOutPage = cartPage.gocheckout();
		//cartPage.addSpecialProduct();
		//checkOutPage.addSpecialProduct();
		Thread.sleep(4000);
		checkOutPage.guestCheckout(uemail);
		checkOutPage.Bankaxess();
		checkOutPage.orderNumber();
		
	}
	
}
