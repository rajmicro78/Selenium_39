package src.com.pack.common.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
public class CartPage {
	private WebDriver driver;
	public String companyName;
	//private By prdCount =By.id("layoutCartProductCount");
	//new changes 1703
	private By prdCount =By.xpath("//span[contains(@class,'layoutCartProductCount')]");
	private By freightMessage = By.className("freeFreightMessage");
	private By noofProduct = By.xpath("//table[@id='cartProducts']/tbody/tr");
	private By nooffreightrow = By.xpath("//table[@id='cartProducts']/tfoot/tr");
	private By freightCharge = By.xpath("//table[@id='cartProducts']/tfoot/tr[2]/td[2]");
	private By freightCharge1 = By.xpath("//table[@id='cartProducts']/tfoot/tr[1]/td[2]");
	private By totalSum = By.xpath("//table[@id='cartProducts']/tfoot/tr[3]/td[2]");
	private By qtyIncrease = By.xpath("//a[contains(@onclick,'AddProductToCart')]");
	private By qtyDecrease = By.xpath("//table[@id='cartProducts']/tbody/tr/td[4]/div/a/i");
	private By specialOffrproduct = By.xpath("//div[@id='specialOffersWrapper']/table/tbody/tr");
	private By checkoutBtn = By.xpath("//a[contains(@onclick,'cart.Checkout()')]");
	//private By Offercount = By.id("layoutQualifiedPlusNotifiedProductOfferCount");
	//new changes 1703 
	private By Offercount =By.xpath("//span[contains(@class,'layoutQualifiedPlusNotifiedProductOfferCount')]");
	//private By Offercheck = By.xpath("//div[@class='offer-footer']/a/i");
	// new changes 1703 
	private By Offercheck = By.xpath("//div[contains(@class,'offersCollection')]/div/div/div/div/span");
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private int productCount; 
	private String freightValue;
	private String freighmess;
	private int frieght;
	private String total;
	private int carttotal;
	private int freightcharge;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		companyName = driver.findElement(logoImage).getAttribute("title");
	}
	public void cartPageDetail() throws Exception{
	
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(By.xpath("//span[contains(@id,'layoutCartProductCount')]")).click();
		}else{
		driver.findElement(prdCount).click();}
		Thread.sleep(2000);
		//driver.findElement(checkoutBtn).click();
		//Thread.sleep(2000);
		/*
		//productCount = driver.findElements(noofProduct).size();
		int freightline = driver.findElements(nooffreightrow).size();
		if (freightline==3){
		freighmess = driver.findElement(freightMessage).getText();
		freightValue = driver.findElement(freightCharge).getText();
		total = driver.findElement(totalSum).getText();
		frieght = Integer.parseInt(freighmess.replaceAll("[^0-9]", ""));
		freightcharge = Integer.parseInt(freightValue.replaceAll("[^0-9]", ""));
		carttotal = Integer.parseInt(total.replaceAll("[^0-9]", ""));
		}else if (freightline==2){
			freightValue = driver.findElement(By.xpath("//table[@id='cartProducts']/tfoot/tr[1]/td[2]")).getText();
			total = driver.findElement(By.xpath("//table[@id='cartProducts']/tfoot/tr[2]/td[2]")).getText();
			System.out.println(total);
			if(freightValue.contains("[0-9]"))
				{freightcharge = Integer.parseInt(freightValue.replaceAll("[^0-9]",""));}
			carttotal = Integer.parseInt(total.replaceAll("[^0-9]",""));
		}
		System.out.println("Cart Total -"+carttotal);
		Reporter.log("Cart Total -"+carttotal);
		System.out.println("Freight Charge -"+freightcharge);
		Reporter.log("Freight Charge -"+freightcharge);*/
	}
	public void increaseqty()throws Exception{
		//if(productCount>=1){
		System.out.println("Qty Increase");
		
		 WebElement incqty =driver.findElement(qtyIncrease);
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click();", incqty);
		//Thread.sleep(5000);
		//driver.findElement(qtyIncrease).click();
		//}
		//else{System.out.println("Blank Cart");}
	}
	public void decreaseqty(){
		if(productCount>=1){
		System.out.println("Qty Decreased");
		driver.findElement(qtyDecrease).click();}else{System.out.println("Blank Cart");}
	}
	public void cartcalculation ()throws Exception{
		System.out.println(frieght);
		System.out.println(freightcharge);
		System.out.println(carttotal);
	}
	public void clickCheckout() throws Exception{
		cartPageDetail(); 
		//offerCheck();
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(By.id("openCheckoutPopupButton")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[contains(@href,'checkout')]")).click();
			Thread.sleep(4000);
		}else{
		offerChecknew();}
		//cartcalculation();		
		//System.out.println(amountexcludingfreight);
			//return new CheckOutPage(driver);
		}
	
	public CheckOutPage gocheckout(){
		return new CheckOutPage(driver);
	}
	
	public void offerChecknew() throws Exception{
		boolean checkoutpage=false;
		String qtyincart;
		String Carttotal;
		
		if(companyName.equals("Netthandelen.no")){
			 qtyincart=driver.findElement(By.xpath("//span[contains(@id,'layoutCartProductCount')]")).getText();
			 Carttotal=driver.findElement(By.xpath("//span[contains(@id,'layoutCartValue')]")).getText();
		}else{
			System.out.println("Get Qty");
			 qtyincart=driver.findElement(By.xpath("//span[contains(@class,'layoutCartProductCount')]")).getText();
			 System.out.println("total Qty"+qtyincart);
			 Carttotal=driver.findElement(By.xpath("//td[contains(@class,'cart-total')]")).getText();
			 System.out.println("total valu"+Carttotal);
		}
		Reporter.log("Total Qty-"+qtyincart+" ,Amount-"+Carttotal);
		System.out.println("Total Qty-"+qtyincart+" ,Amount-"+Carttotal);
		driver.findElement(checkoutBtn).click();
		Reporter.log("Checkout Button Clicked");
		Thread.sleep(2000);
		
		
		boolean productofferpage = driver.findElements( By.xpath("//div[@id='unredeemedOffersWrapper']/div/div/div/div/div/div[2]/a/span") ).size() != 0;
		System.out.println("offer page-" +productofferpage);
		if(!productofferpage){
			checkoutpage = driver.findElements( By.xpath("//h2/a/span") ).size() != 0;
			Reporter.log("Offer Available");
			System.out.println("checkout page-" +checkoutpage);}
		if(productofferpage){
			driver.findElement(By.xpath("//div[@id='unredeemedOffersWrapper']/div/div/div/div/div/div[2]/a/span")).click();
			Thread.sleep(2000);
			System.out.println("Going to checkout page");
			Reporter.log("Offer Available");
			Reporter.log("Click Checkout button from Offer page");
		}else if(checkoutpage){System.out.println("Now on checkout page");}
		
		
	}
	//old one added a new function offerChecknew
	public void offerCheck() throws Exception{
		
	int offercount=0;
	  

	String Offer = driver.findElement(Offercount).getText();
	
	
	boolean chkbtn = driver.findElements( checkoutBtn ).size() != 0;
	System.out.println("Checkout button -" +chkbtn);
	if(Offer.matches(".*\\d.*")){
		offercount = Integer.parseInt(Offer.replaceAll("[^0-9]",""));
		//Reporter.log("Offer available -"+offercount);
		System.out.println(offercount);}
	if(offercount >= 1){
		//1706 changes
		String offercheck="";
		boolean offeravaile=false;
		for(int i=1;i<=offercount;i++){
			offercheck = driver.findElement(By.xpath("//div[contains(@class,'offersCollection')]/div/div["+i+"]/div/div/span")).getAttribute("class");
			System.out.println("Total Offer-"+offercheck);
			if((offercheck.equals("icon icon-nh-unlocked"))){
				System.out.println("setting value to true");
				 offeravaile=true;
			}
		}
		
		//String offercheck = driver.findElement(Offercheck).getAttribute("class");
		//driver.findElement((By.xpath("//div[contains(@class,'offersCollection')]/div/div[2]/div/div/span")).getAttribute("class");
		
		
		//if((offercheck.equals("offer-list  is-redeemable"))){
		//new changes 1706	
			if(offeravaile){
		//if(!(offercheck.equals("icon icon-lock")||offercheck.equals("no-hover-offer is-available"))){
			System.out.println(offercheck);
			
			
			
			System.out.println("Ready to click button");
			driver.findElement(checkoutBtn).click();
			System.out.println("Button Clicked");
			Thread.sleep(2000);
			//new changes 1703
			driver.findElement(By.xpath("//div[@id='unredeemedOffersWrapper']/div/div/div/div/div/div[2]/a/span")).click();
			Thread.sleep(2000);
			
		}else{
			if(chkbtn){
			driver.findElement(checkoutBtn).click();
			Thread.sleep(2000);}
		
		}
	}else{
		if(chkbtn){
	driver.findElement(checkoutBtn).click();
	Thread.sleep(2000);}
	}

	}
	public void addSpecialProduct(){
		System.out.println("Add Special Offer product");
		int noofSpecialProduct = driver.findElements(specialOffrproduct).size();
		//int amountexcludingfreight = carttotal - freightcharge;
		for (int i =noofSpecialProduct-1;i<=noofSpecialProduct;i++){
		String butattribute = driver.findElement(By.xpath("//div[@id='specialOffersWrapper']/table/tbody/tr["+i+"]/td[4]/a")).getAttribute("href");
		System.out.println(butattribute);	
		if(butattribute.contains("#")){
			driver.findElement(By.xpath("//div[@id='specialOffersWrapper']/table/tbody/tr["+i+"]/td[4]/a")).click();}
		
		}
	}
}
	
	


