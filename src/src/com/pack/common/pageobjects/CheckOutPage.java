package src.com.pack.common.pageobjects;

//import static org.junit.Assert.assertEquals;

//import java.awt.Robot;
//import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.ITestContext;
import org.testng.Reporter;



//import com.sun.jna.IntegerType;

public class CheckOutPage {
	private WebDriver driver;
	private By topLogo = By.xpath("//h2/a[contains(@id,'top_nav_logo')]");
	//private By searchBox = By.id("input-text-search");
	//new changes 1703
	private By searchBox = By.className("input-text-search");
	private By searchBoxn = By.id("input-text-search");
	private By checkoutBtn = By.xpath("//a[contains(@onclick,'cart.Checkout()')]"); 
	private By emailTxtFld = By.id("input-email");
	private By guestchkoutBtn = By.xpath("//button[contains(@data-ng-click,'guestLogin')]");
	private By loginLink = By.xpath("//a[contains(@data-ng-click,'showPasswordSection')]");
	private By loginSubmitBtn = By.xpath("//button[contains(@data-ng-click,'login(passwordForm)')]");
	private By passwordTxt = By.id("input-password");
	//private By nameTxtFld = By.id("input-name|input-fname"); 
	private By nameTxtFld = By.name("firstName");
	private By lstNameTxtFld = By.name("lastName");
	private By addresTxtFld = By.id("input-address");
	private By zipCodeTxtFld = By.name("zipCode");
	//private By selectCity = By.id("select-order-number");
	//new changes 1703
	private By selectCity = By.name("pups");
	private By mobileTxtFld = By.name("mobileNumber");
	//private By submitBtn = By.xpath("//div[contains(@class,'checkout-action')]/a");
	//new changes 1703
	private By submitBtn = By.xpath("//a[contains(@data-ng-click,'submitOrder')]");
	private By nxtBtn = By.id("nextButton");
	private By masterCartChk = By.id("MasterCard");
	private By cartNumTxt = By.id("cardNumber");
	private By collectorday = By.id("select-day-birth");
	private By collectormonth = By.id("select-month-birth");
	private By collectoryear = By.id("select-year-birth");
	private By monthTxt = By.id("month");
	private By yearTxt = By.id("year");
	private By securityTxt = By.id("securityCode");
	private By okBtn = By.id("okButton");
	private By orderNum = By.xpath("//h2/a/strong");
	private By paymentMethod = By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']");
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private By collectorTxt = By.id("input-social-number1");
	public String companyName;
	static String VisacardNumber = "4925000000000004";
	static String MastercartNumber = "5413000000000000";
	static String Dankort = "5019994001300153";
	static String Dankortmonth = "05";
	static String Dankortyear ="2021";
	static String Dankortcvv="603";
	static String Expiry = "2020";
	static String CVV = "786";
	//public String email ="rajeev@netthandelen.no";
	static String fName = "Tøndevoldshagen";
	static String lName ="Ola Magne";
	//public String bvPassword = "BV12345";
	//public String cpPassword ="CP12345";
	//public String bnPassword ="BN12345";
	//public String dlPassword ="DL12345";
	static String cfName = "Tester";
	static String clName = 	"Person";
	static String caddress = "Startveien 56";
	static String cZip = "9300";
	static String cNumber = "06073910828";
	static String sofort = "88888888";
	static String soforttan = "85475";
	static String paypal ="service-buyer@cocopanda.de";
	static String paypalpass ="@2$BlHWl83bb";
	//CPDE collector Detail MMC-1710
	
	static String cpdecollfname = "Emro";
	static String cpdecolllname = "Djordjev";
	static String cpdecolladd = "Dörperfeld 7";
	static String cpdecollzip = "42555";
	static String cpdecollday = "10";
	static String cpdecollmonth = "02";
	static String cpdecollyear = "1970";
	static String cpatcollfname = "Alexander";
	static String cpatcolllname = "Kende";
	static String cpatcolladd = "Ungargasse 20/1/157";
	static String cpatcollzip = "1030";
	static String cpatcollday = "09";
	static String cpatcollmonth = "08";
	static String cpatcollyear = "1974";
	private WebDriverWait wait;

	public CheckOutPage(WebDriver driver ) {
		this.driver=driver;
		WebDriverWait wait = new WebDriverWait(driver, 25);
		companyName = driver.findElement(logoImage).getAttribute("title");
		
	}
	
	
	public TicketCreation CreatTicket(){
		return new TicketCreation(driver);
		
	}
	
	public void guestCheckout(String uemail) throws Exception{
		
		//driver.findElement(checkoutBtn).click();
		//Thread.sleep(1000);
		System.out.println("Guest Login");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'island-list')]/div")));
		//companyName = driver.findElement(logoImage).getText();
		//new changes 1703
		//companyName = driver.findElement(logoImage).getAttribute("title");
		//Thread.sleep(1000);
		
		//Thread.sleep(2000);
		String temp = driver.findElement(By.xpath("//div[contains(@class,'island-list')]/div")).getAttribute("class");		
		System.out.println(temp);
		switch (temp){
		case "island-content ng-scope ng-dirty ng-valid-email ng-valid-pattern ng-invalid ng-invalid-required":
		case "island-content ng-pristine ng-scope ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":
		case "island-content login ng-pristine ng-scope ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":
		case "island-content login ng-pristine ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":	  
			//Thread.sleep(2000);
			guestuserData(uemail);
			break;
		case "island-content":
		case "island-content split-blocks":
			//System.out.println("Email Id already Entered");
			//driver.findElement(By.xpath("//div[contains(@class,'island-list')]/div[@class='island-content']/p/a")).click();
			//new changes 1703
			driver.findElement(By.xpath("//div[@class='island-list']/div[contains(@class,'island-content')]/div/div/p[2]/a")).click();
			Thread.sleep(2000);
			guestuserData(uemail);
			break;
		case "island-content ng-hide":
			
			System.out.println("Email Id already Entered");
			//driver.findElement(By.xpath("//div[@class='island-list']/div[@class='island-content']/p/a")).click();
			//new changes 1703
			driver.findElement(By.xpath("//div[contains(@class,'island-list')]/div[@class='island-content']/div/p/a")).click();

			//Thread.sleep(2000);
			guestuserData(uemail);
			break;
		}
		/*
		if(!temp.equals("island-content")){
			guestuserData();
		}else{
		System.out.println("Email Id already Entered");
		driver.findElement(By.xpath("//div[@class='island-list']/div[@class='island-content']/p/a")).click();
		Thread.sleep(2000);
		guestuserData();
		
		}*/
		
	
	}
	
    public void forceSendKeys(WebElement element, String value)
{
    element.clear();

    for (int i = 0; i < value.length(); i++){
        char c = value.charAt(i);
        String s = new StringBuilder().append(c).toString();
        element.sendKeys(s);
    }       
}

	public void guestuserData(String uemail) throws Exception {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(emailTxtFld));
		//WebDriverWait wait = new WebDriverWait(driver, 15);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(emailTxtFld));
		driver.findElement(emailTxtFld).sendKeys(uemail);
		Reporter.log("Email Enterered- "+uemail);
		
		driver.findElement(guestchkoutBtn).click();
		Reporter.log("Click on Guest Chekout Button");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//companyName = driver.findElement(logoImage).getText();
		//new changes 1703
		//companyName = driver.findElement(logoImage).getAttribute("title");
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameTxtFld));
		//Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(nameTxtFld));
		//Thread.sleep(2000);
		driver.findElement(nameTxtFld).clear();
		
		if(companyName.equals("Cocopanda.de")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("68159");
			Reporter.log("Enter Zip Code -68159 ");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
			Thread.sleep(1000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("01512345690");
			Reporter.log("Enter Mobile Number -01512345690 ");
			}
		else if(companyName.equals("Cocopanda.at")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("2320");
			Reporter.log("Enter Zip Code -2320 ");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
			Thread.sleep(1000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("066123456789");
			Reporter.log("Enter Mobile Number -066123456789 ");
			}
		else if(companyName.equals("Cocopanda.se")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("10028");
			Reporter.log("Enter Zip Code -10028 ");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
			Thread.sleep(1000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("0762552625");
			Reporter.log("Enter Mobile Number -0762552625 ");
			}
		else if (companyName.equals("BliVakker.no")||companyName.equals("Brandsdal.no")||companyName.equals("drLykke.no")
				||companyName.equals("InGarden.no")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("9006");
			Reporter.log("Enter Zip Code -9006 ");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
			Thread.sleep(1000);
			driver.findElement(mobileTxtFld).sendKeys("47544754");Reporter.log("Enter Mobile Number -47544754 ");}
		else if (companyName.equals("Cocopanda.fi")){
				driver.findElement(zipCodeTxtFld).clear();
				driver.findElement(zipCodeTxtFld).sendKeys("10120");
				Reporter.log("Enter Zip Code -10120 ");
				//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
				Thread.sleep(1000);
				driver.findElement(mobileTxtFld).sendKeys("0453181878");
				Reporter.log("Enter Mobile Number -0453181878 ");
			}
		else if(companyName.equals("Cocopanda.dk")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("4000");
			Reporter.log("Enter Zip Code -4000 ");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTxtFld));
			Thread.sleep(5000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("47544754");
			Reporter.log("Enter Mobile Number -47544754 ");
			
			}
		driver.findElement(submitBtn).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameTxtFld));
		forceSendKeys(driver.findElement(nameTxtFld),"Tøndevoldshagen");
		//driver.findElement(nameTxtFld).sendKeys("Tøndevoldshagen");
		//Thread.sleep(2000);
		Reporter.log("Enter First Name- Tøndevoldshagen");
		//driver.findElement(lstNameTxtFld).clear();
		forceSendKeys(driver.findElement(lstNameTxtFld),"Ola Magne");
		Reporter.log("Enter Last Name- Ola Magne");
		//driver.findElement(lstNameTxtFld).sendKeys("Ola Magne");
		
		
		//driver.findElement(addresTxtFld).clear();
		forceSendKeys(driver.findElement(addresTxtFld),"co/ Torget 2 AS, Pb. 2010. 3255 Larvik");
		Reporter.log("Enter Address- co/ Torget 2 AS, Pb. 2010. 3255 Larvik");
		//driver.findElement(addresTxtFld).sendKeys("co/ Torget 2 AS, Pb. 2010. 3255 Larvik");
		Thread.sleep(1000);
		selectCity();
	}
	public void registeredUserCheckout(String uemail, String upassword) throws Exception{
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='island-list']/div")));
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, 25);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='island-list']/div")));
		//driver.findElement(checkoutBtn).click();
		//String temp = driver.findElement(By.xpath("//div[contains(@class,'island-list')]/div")).getAttribute("class");
		//1703 new changes 1703
		String temp = driver.findElement(By.xpath("//div[contains(@class,'island-list')]/div/div/div[@class='address-login']")).getAttribute("data-ng-show");
		System.out.println(temp);
		switch (temp){
		case "island-content ng-scope ng-dirty ng-valid-email ng-valid-pattern ng-invalid ng-invalid-required":
		case "island-content ng-pristine ng-scope ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":
		case "island-content login ng-pristine ng-scope ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":
		case "island-content login ng-pristine ng-valid-email ng-invalid ng-invalid-required ng-valid-pattern":
			driver.findElement(emailTxtFld).sendKeys(uemail);
			Reporter.log("Entered Email Id " +uemail);
			driver.findElement(guestchkoutBtn).click();
			Reporter.log("Guest Checkout Button Clicked " );
			Thread.sleep(2000);
			userLogin(upassword);
			break;
		case "island-content":
		case "data.isAuthenticated && data.isAuthenticationAndGuest":
			userLogin(upassword);
			break;
		case "island-content ng-hide":
		case "data.isAuthenticated && !data.isAuthenticationAndGuest":
			selectCity();
			break;
			
		}
		/*
		if(!temp.equals("island-content")){
			driver.findElement(emailTxtFld).sendKeys(email);
			driver.findElement(guestchkoutBtn).click();
			userLogin();
		}else{
			userLogin();
		}*/
		
	}
	public void userLogin(String upassword) throws Exception{
		//companyName = driver.findElement(logoImage).getAttribute("title");
		System.out.println(companyName);
		driver.findElement(loginLink).click();
		Thread.sleep(1000);
		driver.findElement(passwordTxt).sendKeys(upassword);
		Reporter.log("Entered Password Id -  " +upassword);
		/*switch (companyName){
		case  "BliVakker.no":
			driver.findElement(passwordTxt).sendKeys(bvPassword);
			break;
		case  "Cocopanda.de":
			driver.findElement(passwordTxt).sendKeys(cpPassword);
			break;
		case "Cocopanda.se":
			driver.findElement(passwordTxt).sendKeys(cpPassword);
			break;
		case "Cocopanda.fi":
			driver.findElement(passwordTxt).sendKeys(cpPassword);
			break;
		case "Brandsdal.no":
			driver.findElement(passwordTxt).sendKeys(bnPassword);
			break;
		case "Cocopanda.dk":
			driver.findElement(passwordTxt).sendKeys(upassword);
			break;
		case "drLykke.no":
			driver.findElement(passwordTxt).sendKeys(dlPassword);
			break;
		}*/
		driver.findElement(loginSubmitBtn).click();
		Thread.sleep(1000);
	
		selectCity();
	}
	public void selectCity()throws Exception {
		if(companyName.equals("Cocopanda.dk")){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/3);");
		//companyName = driver.findElement(logoImage).getAttribute("title");
		if(!(companyName.equals("Brandsdal.no")||companyName.equals("drLykke.no")||companyName.equals("InGarden.no"))){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		/*String custdetail = driver.findElement(By.xpath("//div[@id='main']/div/div/div[2]/div[3]/div[3]")).getAttribute("class");
		//System.out.println("city section -"+custdetail);
		if(custdetail.equals("island-list is-closed")){
			driver.findElement(By.xpath("//div[@id='main']/div/div/div[2]/div[3]/div[3]/h3/a")).click();
			Thread.sleep(2000);
		}*/
		boolean city = driver.findElements( selectCity).size() != 0;
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if(city){
			
			
			
			new Select(driver.findElement( selectCity)).selectByIndex(2);
			Thread.sleep(7000);
			//driver.findElement(By.xpath("//div[@id='main']/div/div/div[2]/div[3]/div[3]/h3/a")).click();
			
		}else {
			Thread.sleep(1000);
		}
		}else
		{
			Thread.sleep(1000);
		}
		
		/*
		
		companyName = driver.findElement(logoImage).getText();
		switch (companyName){
		case "Cocopanda.se":
			break;
		case "BliVakker.no":
			break;
		case "drLykke.no":
			break;
		case "Brandsdal.no":
			break;
		case "Cocopanda.dk":
			new Select(driver.findElement(selectCity)).selectByIndex(2);
			Thread.sleep(2000);
			
		}*/}
	}
	public void cardPaymentVisa() throws Exception {	
		paymentMethodSelection("Kort");
		
		//String cartvalue = driver.findElement(By.xpath("//li[@class='item-total']/span[2]")).getText();
		// new changes 1703
		
		String cartvalue = driver.findElement(By.xpath("//div[contains(@class,'group-total')]/span[2]")).getText();
		System.out.println(cartvalue);
		Reporter.log("Cart Value-" +cartvalue);
		int CV = Integer.parseInt(cartvalue.replaceAll("[^0-9]",""));
		System.out.println(CV);
		if(CV==0){
			submitbtn();
		}else{
		submitbtn();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(nxtBtn));
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated( By.id("Visa")));
		boolean visachk = driver.findElements( By.id("Visa") ).size() != 0;
		System.out.println("Visa button visible" +visachk);
		
		if(visachk){
			Thread.sleep(4000);
			driver.findElement(By.id("Visa")).click();
			Reporter.log("Visa Option Clicked");
		}
		
		//driver.findElement(By.id("Visa")).click();
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean nxtbtn = driver.findElements( nxtBtn ).size() != 0;
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if(nxtbtn){
			driver.findElement(nxtBtn).click();
			Reporter.log("Next Button Clicked");
		}
		 // driver.findElement(By.linkText("Bekreft bestilling")).click();
		 //driver.findElement(nxtBtn).click();	    
		 driver.findElement(cartNumTxt).clear();
		 driver.findElement(cartNumTxt).sendKeys(VisacardNumber);
		 Reporter.log("Visa Card Number-"+VisacardNumber);
		 new Select(driver.findElement(yearTxt)).selectByVisibleText(Expiry);
		 Reporter.log("Visa Card Expiry-"+Expiry);
		 driver.findElement(securityTxt).clear();
		 driver.findElement(securityTxt).sendKeys(CVV);
		 Reporter.log("Visa CVV-"+CVV);
		 driver.findElement(okBtn).click();
		 Reporter.log("Visa Ok Button Clicked");
		}
	}
	public void Sofort() throws Exception {	
		paymentMethodSelection("SOFORT");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		submitbtn();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(nxtBtn));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MultipaysSessionSenderBankCode")));
		//1709 change
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BankCodeSearch")));
		//Thread.sleep(2000);
		driver.findElement(By.id("BankCodeSearch")).clear();
	    driver.findElement(By.id("BankCodeSearch")).sendKeys(sofort);
	    Reporter.log("Sofort Bank Code-"+sofort);
	    driver.findElement(By.xpath("//div[@class='text-center']/button")).click();
	   // Thread.sleep(2000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BackendFormLOGINNAMEUSERID")));
	    driver.findElement(By.id("BackendFormLOGINNAMEUSERID")).clear();
	    driver.findElement(By.id("BackendFormLOGINNAMEUSERID")).sendKeys(sofort);
	    Reporter.log("Sofort User Id-"+sofort);
	    driver.findElement(By.id("BackendFormUSERPIN")).clear();
	    driver.findElement(By.id("BackendFormUSERPIN")).sendKeys(cZip);
	    Reporter.log("Sofort Pin-"+cZip);
	    driver.findElement(By.xpath("//div[@class='text-center']/button")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='account-selector']/label[3]/p[2]")));
	    //Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='account-selector']/label[3]/p[2]")).click();
	    driver.findElement(By.xpath("//div[@class='text-center']/button")).click();
	    //Thread.sleep(2000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BackendFormTan")));
	    driver.findElement(By.id("BackendFormTan")).clear();
	    driver.findElement(By.id("BackendFormTan")).sendKeys(soforttan);
	    Reporter.log("Sofort Transaction Number-"+soforttan);
	    driver.findElement(By.xpath("//div[@class='text-center']/button")).click();
	    Thread.sleep(4000);
		
	}
	
	public void PayPal() throws Exception {	
		paymentMethodSelection("PayPal");
		
		submitbtn();
		Thread.sleep(10000);
		System.out.println("in Paypal");
		Reporter.log("Paypal payment Method");
		// WebDriverWait wait = new WebDriverWait(driver,30);
		
		 
		 boolean paypaliframe = driver.findElements( By.name("login_email") ).size() != 0;
		// boolean paypalpas = driver.findElements( By.name("login_password") ).size() != 0;
		/* if(paypaliframe && paypalpas){
			 Thread.sleep(2000);
				driver.findElement(By.name("login_email")).clear();
			    driver.findElement(By.name("login_email")).sendKeys(paypal);
			    Reporter.log("Paypal Email- "+paypal );
			   // driver.findElement(By.id("btnNext")).click();
			    Thread.sleep(15000);
			    driver.findElement(By.name("login_password")).clear();
			    driver.findElement(By.name("login_password")).sendKeys(paypalpass);
			    Reporter.log("Paypal Password- "+paypalpass );
			    driver.findElement(By.id("btnLogin")).click();
			    Reporter.log("Paypal Submit Button");
			  
			    
			    Thread.sleep(25000);
				System.out.println("Waiting 15000");
				
				//boolean paypacn = driver.findElements(By.id("confirmButtonTop") ).size() != 0;
				//System.out.println("confirm" +paypacn);
				//if(paypacn){
				
			    System.out.println("into another");
			    //driver.switchTo().frame(0);
			   // WebDriverWait wait = new WebDriverWait(driver,30);
			  //  WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButtonTop")));
			    WebElement element1 =driver.findElement(By.id("confirmButtonTop"));
			    //element1.click();
			    JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element1);
				 Reporter.log("Paypal Confirm Button");
			    
		 } else */if(paypaliframe ){
			Thread.sleep(2000);
			driver.findElement(By.name("login_email")).clear();
		    driver.findElement(By.name("login_email")).sendKeys(paypal);
		    Reporter.log("Paypal Email- "+paypal );
		    driver.findElement(By.id("btnNext")).click();
		    Thread.sleep(15000);
		    driver.findElement(By.name("login_password")).clear();
		    driver.findElement(By.name("login_password")).sendKeys(paypalpass);
		    Reporter.log("Paypal Password- "+paypalpass );
		    driver.findElement(By.id("btnLogin")).click();
		    Reporter.log("Paypal Submit Button");
		  
		    
		    Thread.sleep(25000);
			System.out.println("Waiting 15000");
			
			//boolean paypacn = driver.findElements(By.id("confirmButtonTop") ).size() != 0;
			//System.out.println("confirm" +paypacn);
			//if(paypacn){
			
		    System.out.println("into another");
		    //driver.switchTo().frame(0);
		   // WebDriverWait wait = new WebDriverWait(driver,30);
		  //  WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButtonTop")));
		    WebElement element1 =driver.findElement(By.id("confirmButtonTop"));
		    //element1.click();
		    JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element1);
			 Reporter.log("Paypal Confirm Button");
		    
		    
		    // Thread.sleep(25000);
		   
		    
		    /*
		    
		    WebDriverWait wait = new WebDriverWait(driver,30);
		    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue_abovefold")));
		    element.click();
		    // driver.findElement(By.id("confirmButtonTop")).click();
		   // driver.findElement(By.id("continue_abovefold")).click();
		    Thread.sleep(10000);*/
		}else{
		
			
		driver.switchTo().frame("injectedUl");
		System.out.println("switched to frame");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean paypalf = driver.findElements(By.xpath("//div[@id='login_emaildiv']/div/input") ).size() != 0;
		System.out.println(paypalf);
		if(paypalf){
		//Thread.sleep(2000);
		System.out.println("into loop");
		driver.findElement(By.xpath("//div[@id='login_emaildiv']/div/input")).clear();
	    driver.findElement(By.xpath("//div[@id='login_emaildiv']/div/input")).sendKeys(paypal);
	    System.out.println("Enter Email");
	    Reporter.log("Paypal Email- "+paypal );
	    driver.findElement(By.xpath("//div[@id='login_passworddiv']/div/input")).clear();
	    driver.findElement(By.xpath("//div[@id='login_passworddiv']/div/input")).sendKeys(paypalpass);
	    System.out.println("Enter Password");
	    Reporter.log("Paypal Password- "+paypalpass );
	    driver.findElement(By.id("btnLogin")).click();
	    System.out.println("Clicked Login Button");
	    Reporter.log("Paypal Submit Button");
		
		Thread.sleep(15000);
		System.out.println("Waiting 15000");
		
		//boolean paypacn = driver.findElements(By.id("confirmButtonTop") ).size() != 0;
		//System.out.println("confirm" +paypacn);
		//if(paypacn){
		
	    System.out.println("into another");
	    //driver.switchTo().frame(0);
	   // WebDriverWait wait = new WebDriverWait(driver,30);
	  //  WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButtonTop")));
	    WebElement element1 =driver.findElement(By.id("confirmButtonTop"));
	    //element1.click();
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element1);
		 Reporter.log("Paypal Confirm Button");
		}
	    //driver.findElement(By.id("confirmButtonTop")).click();}
	    Thread.sleep(2000);}
		//}
	}
	
	public void submitbtn() throws Exception{
		Thread.sleep(2000);
		boolean submitbtn = driver.findElements(submitBtn ).size() != 0;
		if(submitbtn){
		driver.findElement(submitBtn).click(); }
	}

	public void Masterpass() throws Exception {	
		paymentMethodSelection("MasterPass");
		
		submitbtn();
		
		Thread.sleep(45000);
		
		driver.findElement(By.id("localeArrow")).click();
		
		Thread.sleep(1000);
		
	    driver.findElement(By.xpath("//div[@id='localeDropdown']/div/li[97]/div")).click();
	   
	    Thread.sleep(4000);
	   
	    driver.findElement(By.xpath("//div[@id='featured-wallets-logos']/div/div/button")).click();
	    
	    Thread.sleep(10000);
	   
	    System.out.println("Wait for email button");
	    driver.switchTo().frame("MasterPass_wallet_frame");
	   
	    
	    driver.findElement(By.id("email")).click();
	    boolean cookie = driver.findElements(By.id("email")).size() != 0;
	    System.out.println(cookie);
		
		if(cookie){
			 System.out.println(cookie);
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("saisree@yahoo.no");
		}
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("123456a");
	   // driver.findElement(By.id("remember")).click();
		
	    driver.findElement(By.id("signInButton")).click();
	    Thread.sleep(12000);
	    
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   // driver.switchTo().frame("MasterPass_wallet_frame");
	   // driver.findElement(By.cssSelector("button.button.command")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    Thread.sleep(6000);
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	
	
	public void cardPaymentMaster() throws Exception {
		
		paymentMethodSelection("Kort");
		
		submitbtn();
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated( masterCartChk));
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//boolean mastchk = driver.findElements( masterCartChk ).size() != 0;
		
		
		//if(mastchk){
			driver.findElement(masterCartChk).click();
			Reporter.log("Card Master Payment ");
		//}
		   // driver.findElement(By.linkText("Bekreft bestilling")).click();
		    //driver.findElement(masterCartChk).click();
		    driver.findElement(nxtBtn).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(cartNumTxt));
		    driver.findElement(cartNumTxt).clear();
		    driver.findElement(cartNumTxt).sendKeys(MastercartNumber);
		    Reporter.log("Master Card Number- "+MastercartNumber);
		    new Select(driver.findElement(yearTxt)).selectByVisibleText(Expiry);
		    Reporter.log("Master Card Expiry- "+Expiry);
		    driver.findElement(securityTxt).clear();
		    driver.findElement(securityTxt).sendKeys(CVV);
		    Reporter.log("Master Card CVV- "+CVV);
		    driver.findElement(okBtn).click();
		    Reporter.log("Master Ok Button");
		    
	}	
	public void prePayment() throws Exception{
		
		paymentMethodSelection("Forskuddsbetaling");
		submitbtn();
		Thread.sleep(1000);
		
	}
	public void Bankaxess() throws Exception{
		

		paymentMethodSelection("Bankaxess");
		submitbtn();
		Thread.sleep(25000);
		
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@class='wrp']/input")).sendKeys("22128011111");
		Reporter.log("Bankaxess Number- 22128011111");
		driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='wrp']/input")).sendKeys("otp");
		Reporter.log("Bankaxess OTP Number- otp");
		driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[2]/button")).click();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//li")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='wrp']/input")).sendKeys("qwer1234");
		Reporter.log("Bankaxess code- qwer1234");
		//driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Reporter.log("Bankaxess Final Button");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		//div[@class='wrp']/input 
		
	}
	public void easyPayment() throws Exception{
		easypaymentdetail();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		paymentMethodSelection("EasyPayment");
		submitbtn();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));
		//Thread.sleep(1000);
		driver.findElement(securityTxt).clear();
	    driver.findElement(securityTxt).sendKeys(CVV);
	    driver.findElement(okBtn).click();
	}
	public void easypaymentdetail() throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(topLogo).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/a/span")));
		driver.findElement(By.xpath("//li[3]/a/span")).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '#account')]")));
	    driver.findElement(By.xpath("//a[contains(@href, '#account')]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector("p > a.button.button-link > span.label")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("btn-new-card")).click();
	    driver.findElement(cartNumTxt).clear();
	    driver.findElement(cartNumTxt).sendKeys(MastercartNumber);
	    new Select(driver.findElement(yearTxt)).selectByVisibleText(Expiry);
	    driver.findElement(securityTxt).clear();
	    driver.findElement(securityTxt).sendKeys(CVV);
	    driver.findElement(okBtn).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layoutCartProductCount")));
	    //Thread.sleep(1000);
	    ////////////////////////////////////////
	   
		///////////////////////////
	    driver.findElement(By.id("layoutCartProductCount")).click();
	    Thread.sleep(2000);
	    //wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
	    
	   ////////////////////////////////////
	    int offercount=0;
		String Offer = driver.findElement(By.id("layoutQualifiedPlusNotifiedProductOfferCount")).getText();
		if(Offer.matches(".*\\d.*")){
			offercount = Integer.parseInt(Offer.replaceAll("[^0-9]",""));
			//Reporter.log("Offer available -"+offercount);
			System.out.println(offercount);}
				if(offercount >= 1){
					String offercheck = driver.findElement(By.xpath("//div[@class='offer-footer']/a/i")).getAttribute("class");
					System.out.println(offercheck);
					if(!offercheck.equals("icon icon-lock")){
						System.out.println(offercheck);
						driver.findElement(checkoutBtn).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[@class='action notEmptyCart']/a")).click();
				
					}else{
						driver.findElement(checkoutBtn).click();
			
					}
		}else{
		driver.findElement(checkoutBtn).click();}
	    
	    
	    ///////////////////////////////////
	   // Thread.sleep(1000);
	   // driver.findElement(checkoutBtn).click();
	    Thread.sleep(1000);
	    selectCity();
	}
	public void dankort() throws Exception{
		paymentMethodSelection("Dankort");
		submitbtn(); 
		Thread.sleep(2000);
		new Select(driver.findElement(monthTxt)).selectByVisibleText(Dankortmonth);
		driver.findElement(cartNumTxt).clear();
		driver.findElement(cartNumTxt).sendKeys(Dankort);
		Reporter.log("Dankort Number - "+Dankort);
		new Select(driver.findElement(yearTxt)).selectByVisibleText(Dankortyear);
		Reporter.log("Dankort Year - "+Dankortyear);
		driver.findElement(securityTxt).clear();
		driver.findElement(securityTxt).sendKeys(Dankortcvv);
		Reporter.log("Dankort CVV - "+Dankortcvv);
		driver.findElement(okBtn).click();
		Reporter.log("Dankort Ok Button");
	}
	public void collector() throws Exception{
		System.out.println("inside collector");
		Thread.sleep(1000);
		paymentMethodSelection("Collector");
		Thread.sleep(1000);
		collectorpay();
		Thread.sleep(2000);
		/*
		if(!companyName.equals("Cocopanda.se")){
		driver.findElement(By.id("input-social-number")).clear();
	    driver.findElement(By.id("input-social-number")).sendKeys(cNumber);
	    driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
	    Thread.sleep(1000);
	    driver.findElement(nameTxtFld).clear();
		driver.findElement(nameTxtFld).sendKeys(cfName);
		driver.findElement(lstNameTxtFld).clear();
		driver.findElement(lstNameTxtFld).sendKeys(clName);
		driver.findElement(addresTxtFld).clear();
		driver.findElement(addresTxtFld).sendKeys(caddress);
		driver.findElement(zipCodeTxtFld).clear();
		driver.findElement(zipCodeTxtFld).sendKeys(cZip);
		Thread.sleep(2000);
		driver.findElement(submitBtn).click(); 
		Thread.sleep(1000);
		}else{ 
			driver.findElement(By.id("input-social-number")).clear();
		    driver.findElement(By.id("input-social-number")).sendKeys("1602079954");
		    driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
		    Thread.sleep(1000);
		    driver.findElement(nameTxtFld).clear();
			driver.findElement(nameTxtFld).sendKeys("FÖRNAMNAKT211");
			driver.findElement(lstNameTxtFld).clear();
			driver.findElement(lstNameTxtFld).sendKeys("EFTERNAMNAKT211");
			driver.findElement(addresTxtFld).clear();
			driver.findElement(addresTxtFld).sendKeys("GATUADRESSAKT211");
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("90737");
			Thread.sleep(2000);
			driver.findElement(submitBtn).click(); 
			Thread.sleep(1000);
		}*/
	}
	
	public void trustly() throws Exception{
		System.out.println("inside Trustly");
		Thread.sleep(1000);
		paymentMethodSelection("Trustly");
		Thread.sleep(2000);
		submitbtn(); 
		Thread.sleep(10000);
		trustlypay();
		
		
	}
	public void trustlypay()throws Exception{
		System.out.println("Inside trust pay");
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		driver.switchTo().frame(0);
		System.out.println("Switched to frame 0");
		//System.out.println(driver.findElement(By.xpath("//p")).getText());
		Thread.sleep(1000);
		driver.switchTo().frame( driver.findElement(By.xpath("//div[@class='trustly']/iframe")));
		//driver.switchTo().frame(1);
		Thread.sleep(1000);
		System.out.println("Frame-"+driver.findElement(By.xpath("//form[@id='core_order_holder']/div/div[contains(@class,'core_SelectMethod_Country')]/a[2]/span/div")).getText());
		driver.findElement(By.xpath("//form[@id='core_order_holder']/div/div[contains(@class,'core_SelectMethod_Country')]/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='action_buttons']/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("loginid")).sendKeys("1111111111");
		driver.findElement(By.xpath("//div[@class='action_buttons']/a")).click();
		Thread.sleep(1000);
		String code1= driver.findElement(By.xpath("//div[contains(@class,'order_holder')]/div[2]/span[2]")).getText();
		driver.findElement(By.name("challenge_response")).sendKeys(code1);
		driver.findElement(By.xpath("//div[@class='action_buttons']/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='action_buttons']/a")).click();
		Thread.sleep(1000);
		String code2= driver.findElement(By.xpath("//div[contains(@class,'order_holder')]/div[2]/span[2]")).getText();
		driver.findElement(By.name("challenge_response")).sendKeys(code2);
		driver.findElement(By.xpath("//div[@class='action_buttons']/a")).click();
		Thread.sleep(1000);
	}
	
	public void orderNumber() throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.visibilityOfElementLocated(orderNum));
		//Thread.sleep(10000);
		boolean ordervisible = driver.findElement(orderNum).isDisplayed();
		//boolean ordervisible = driver.findElements(orderNum).size()!=0;
		if(ordervisible){
		String OrderNumber = driver.findElement(orderNum).getText();
		
		System.out.println(OrderNumber);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(topLogo));
		//driver.findElement(topLogo).click();
		//new changes 1703
		driver.findElement(logoImage).click();
		if(companyName.equals("Netthandelen.no")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxn));
		}else{
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));}
		//Thread.sleep(3000);
		Reporter.log("Final Order Number- "+OrderNumber);}
	else{
		System.out.println("order not created");
		Reporter.log("order not created");
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.history.go(-1)");
		//driver.navigate().back();
		Thread.sleep(2000);
		System.out.println("Waiting for popup");
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		System.out.println("pop visible-" +pop1703);
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
			driver.findElement(topLogo).click();
			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
			}
	}
	public void paymentMethodSelection(String paytype) throws Exception{
	int paymenttype = driver.findElements(paymentMethod).size();
	switch (paytype){
	case "Trustly":
		for(int i=1;i<=paymenttype;i++){
		String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			//Sofort Payment Option
		System.out.println(paymentt);	
		if(paymentt.equalsIgnoreCase("Verkkopankkimaksu – Trustly")||paymentt.equalsIgnoreCase("Bankbetalning - Trustly")){
			driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
			Thread.sleep(1000);
			
			/*	
			int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
				System.out.println(subpaymenttype);
				for (int j = 1;j<=subpaymenttype;j++){
				String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
					System.out.println(subpayment);
					if(subpayment.equals("Dankort")){
						System.out.println("click Kort checkbox");
						driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
						Thread.sleep(2000);
						break;
					}
				}
			break;*/
			}			
		}
		Reporter.log("Trustly Payment Selected");
		break;
	case "PayPal":
		for(int i=1;i<=paymenttype;i++){
		String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			//Sofort Payment Option
			if(paymentt.equalsIgnoreCase("PayPal")){
			driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
			Thread.sleep(1000);
			
			/*	
			int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
				System.out.println(subpaymenttype);
				for (int j = 1;j<=subpaymenttype;j++){
				String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
					System.out.println(subpayment);
					if(subpayment.equals("Dankort")){
						System.out.println("click Kort checkbox");
						driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
						Thread.sleep(2000);
						break;
					}
				}
			break;*/
			}			
		}
		Reporter.log("Paypal Payment Selected");
		break;
	
	case "SOFORT":
		for(int i=1;i<=paymenttype;i++){
		String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			//Sofort Payment Option
			if(paymentt.equalsIgnoreCase("SOFORT Überweisung")){
			driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
			Thread.sleep(1000);
			
			/*	
			int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
				System.out.println(subpaymenttype);
				for (int j = 1;j<=subpaymenttype;j++){
				String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
					System.out.println(subpayment);
					if(subpayment.equals("Dankort")){
						System.out.println("click Kort checkbox");
						driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
						Thread.sleep(2000);
						break;
					}
				}
			break;*/
			}			
		}
		Reporter.log("Sofort Payment Selected");
		break;
	case "MasterPass":
		for(int i=1;i<=paymenttype;i++){
		String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			//Sofort Payment Option
			if(paymentt.equalsIgnoreCase("MasterPass")){
			driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
			Thread.sleep(1000);
			
			/*	
			int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
				System.out.println(subpaymenttype);
				for (int j = 1;j<=subpaymenttype;j++){
				String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
					System.out.println(subpayment);
					if(subpayment.equals("Dankort")){
						System.out.println("click Kort checkbox");
						driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
						Thread.sleep(2000);
						break;
					}
				}
			break;*/
			}			
		}
		Reporter.log("MasterPass Payment Selected");
		break;
	case "EasyPayment":
			for(int i=1;i<=paymenttype;i++){
			String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
				//Kort Payment Option
				if(paymentt.equalsIgnoreCase("Kort")||paymentt.equalsIgnoreCase("Kortti")){
				driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
				Thread.sleep(1000);
					int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
					System.out.println(subpaymenttype);
					for (int j = 1;j<=subpaymenttype;j++){
					String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
						System.out.println(subpayment);
						if(subpayment.equals("EasyPayment")){
							System.out.println("click Kort checkbox");
							driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
							Thread.sleep(1000);
							
							break;
						}
					}
				break;
				}			
			}
			Reporter.log("Easypayment Payment Selected");
			break;
		case "Dankort":
			for(int i=1;i<=paymenttype;i++){
			String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
				//Dankort Payment Option
				if(paymentt.equalsIgnoreCase("Kort")){
				driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
				Thread.sleep(1000);
					int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
					System.out.println(subpaymenttype);
					for (int j = 1;j<=subpaymenttype;j++){
					String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
						System.out.println(subpayment);
						if(subpayment.equalsIgnoreCase("Dankort")){
							System.out.println("click Kort checkbox");
							driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
							Thread.sleep(1000);
							
							break;
						}
					}
				break;
				}			
			}
			Reporter.log("Dankort Payment Selected");
			break;
		case "Kort":
		case "Kortti":
		case "Kreditkarte":
		case "Karte":
			
			for(int i=1;i<=paymenttype;i++){
			String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
				//Kort Payment Option
				if(paymentt.equalsIgnoreCase("Kort") ||paymentt.equalsIgnoreCase("Kortti")||paymentt.equalsIgnoreCase("Kreditkarte")||paymentt.equalsIgnoreCase("Karte")){
				driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
				Thread.sleep(1000);
					int subpaymenttype = driver.findElements(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div")).size();
					System.out.println(subpaymenttype);
					//new changes 1703 changes to 1 from 0 
					if(subpaymenttype>1){
					System.out.println(subpaymenttype);
					
					for (int j = 1;j<=subpaymenttype;j++){
					String subpayment=	driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label")).getText();
						System.out.println(subpayment);
						// new changes 1703
						// added new if condition as kort checkbox is selected by default
						
							
						if(subpayment.equalsIgnoreCase("Kort")||subpayment.equalsIgnoreCase("Kortti")||subpayment.equalsIgnoreCase("Kreditkarte")||subpayment.equalsIgnoreCase("Karte")){
							System.out.println("click Kort checkbox");
							//driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div")).click();
							//new changes 1703
							//driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div/div")).click();
							//new changes 1705
							driver.findElement(By.xpath("//div[@data-ng-if='paymentGroup == data.selectedPaymentGroup']/div[2]/div["+j+"]/label/div/ins")).click();
							Thread.sleep(1000);
							break;
						}
						
					}
					}
				break;
				}			
			}
			Reporter.log("Card Payment Selected");
			break;
		case "Forskuddsbetaling":
		case "PrePayment":
			for(int i=1;i<=paymenttype;i++){
			String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			System.out.println(paymentt);	
			//Prepayment Payment Option
				if(paymentt.equalsIgnoreCase("Forskuddsbetaling")||paymentt.equalsIgnoreCase("PrePayment")){
					driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
					Thread.sleep(1000);
					Reporter.log("Prepayment Payment Selected");
					break;
				}
			}
			break;
		case "Collector":
		case "Lasku":
		case "Rechnung":
		
			Thread.sleep(1000);
			for(int i=1;i<=paymenttype;i++){
			//Collector Payment Option
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			boolean collector = driver.findElements( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span") ).size() != 0;
			//boolean cookie = driver.findElements(By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span") ).size() != 0;
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(collector){
			String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
			if(paymentt.equalsIgnoreCase("Faktura")||paymentt.equalsIgnoreCase("Lasku")||paymentt.equalsIgnoreCase("Rechnung")){
					driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
					Thread.sleep(2000);
					break;
				}}
			}
			Reporter.log("Collector Payment Selected");
			break;
		case "Bankaxess":
			for(int i=1;i<=paymenttype;i++){
				String paymentt = driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).getText();
				System.out.println(paymentt);	
				//Prepayment Payment Option
					if(paymentt.equalsIgnoreCase("Bankaxess")){
						driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+i+"]/label/span")).click();
						Thread.sleep(1000);
						break;
					}
				}
			Reporter.log("Bankaxess Payment Selected");
			break;
		}
	}
	public void collectorpay() throws Exception{
		System.out.println("inside collector1");
		companyName = driver.findElement(By.xpath("//h2[@class='site-logo']/a")).getAttribute("title");
		System.out.println("Collector -"+companyName);
		if(companyName.equals("Cocopanda.at")){
			//driver.findElement(collectorday).clear();
			driver.findElement(collectorday).sendKeys(cpatcollday);
			Reporter.log("Collector Day- "+cpatcollday);
			//driver.findElement(collectormonth).clear();
			Thread.sleep(1000);
			driver.findElement(collectormonth).sendKeys(cpatcollmonth);
			Reporter.log("Collector Month- "+cpatcollmonth);
			//driver.findElement(collectoryear).clear();
			Thread.sleep(1000);
			driver.findElement(collectoryear).sendKeys(cpatcollyear);
			Reporter.log("Collector Year- "+cpatcollyear);
			Thread.sleep(4000);
		   // driver.findElement(By.xpath("//div[2]/div/label/div[@class='icheckbox']/ins")).click();
		    Thread.sleep(1000);
		    driver.findElement(nameTxtFld).clear();
			driver.findElement(nameTxtFld).sendKeys(cpatcollfname);
			Reporter.log("Collector First Name- "+cpatcollfname);
			driver.findElement(lstNameTxtFld).clear();
			driver.findElement(lstNameTxtFld).sendKeys(cpatcolllname);
			Reporter.log("Collector Last Name- "+cpatcolllname);
			driver.findElement(addresTxtFld).clear();
			driver.findElement(addresTxtFld).sendKeys(cpatcolladd);
			Reporter.log("Collector Address- "+cpatcolladd);
			driver.findElement(zipCodeTxtFld).clear();
			Thread.sleep(2000);
			driver.findElement(zipCodeTxtFld).sendKeys(cpatcollzip);
			Reporter.log("Collector Zipcode- "+cpatcollzip);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
			Thread.sleep(2000);
			selectCity();
			submitbtn();
			Thread.sleep(1000);
			}else if(companyName.equals("Cocopanda.de")){
			//driver.findElement(collectorday).clear();
			driver.findElement(collectorday).sendKeys(cpdecollday);
			Reporter.log("Collector Day- "+cpdecollday);
			//driver.findElement(collectormonth).clear();
			Thread.sleep(1000);
			driver.findElement(collectormonth).sendKeys(cpdecollmonth);
			Reporter.log("Collector Month- "+cpdecollmonth);
			//driver.findElement(collectoryear).clear();
			Thread.sleep(1000);
			driver.findElement(collectoryear).sendKeys(cpdecollyear);
			Reporter.log("Collector Year- "+cpdecollyear);
			Thread.sleep(4000);
		   // driver.findElement(By.xpath("//div[2]/div/label/div[@class='icheckbox']/ins")).click();
		    Thread.sleep(1000);
		    driver.findElement(nameTxtFld).clear();
			driver.findElement(nameTxtFld).sendKeys(cpdecollfname);
			Reporter.log("Collector First Name- "+cpdecollfname);
			driver.findElement(lstNameTxtFld).clear();
			driver.findElement(lstNameTxtFld).sendKeys(cpdecolllname);
			Reporter.log("Collector Last Name- "+cpdecolllname);
			driver.findElement(addresTxtFld).clear();
			driver.findElement(addresTxtFld).sendKeys(cpdecolladd);
			Reporter.log("Collector Address- "+cpdecolladd);
			driver.findElement(zipCodeTxtFld).clear();
			Thread.sleep(2000);
			driver.findElement(zipCodeTxtFld).sendKeys(cpdecollzip);
			Reporter.log("Collector Zipcode- "+cpdecollzip);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
			Thread.sleep(2000);
			selectCity();
			submitbtn();
			Thread.sleep(1000);
			} else if(companyName.equals("Cocopanda.se")){
			driver.findElement(collectorTxt).clear();
		    driver.findElement(collectorTxt).sendKeys("1602079954");
		    Thread.sleep(1000);
		    Reporter.log("Collector Number- 1602079954");
		    driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
		    Thread.sleep(1000);
		    driver.findElement(nameTxtFld).clear();
			driver.findElement(nameTxtFld).sendKeys("FÖRNAMNAKT211");
			Reporter.log("Collector First Name- FÖRNAMNAKT211");
			driver.findElement(lstNameTxtFld).clear();
			driver.findElement(lstNameTxtFld).sendKeys("EFTERNAMNAKT211");
			Reporter.log("Collector Last Name- EFTERNAMNAKT211");
			driver.findElement(addresTxtFld).clear();
			driver.findElement(addresTxtFld).sendKeys("GATUADRESSAKT211");
			Reporter.log("Collector Address- GATUADRESSAKT211");
			driver.findElement(zipCodeTxtFld).clear();
			Thread.sleep(2000);
			driver.findElement(zipCodeTxtFld).sendKeys("90737");
			Reporter.log("Collector Zipcode- 90737");
			Thread.sleep(2000);
			selectCity();
			submitbtn();
			Thread.sleep(1000);
			} else if(companyName.equals("Cocopanda.fi")){
				System.out.println("Now pay collector for CPFI");
				driver.findElement(collectorTxt).clear();
			    driver.findElement(collectorTxt).sendKeys("071259999M");
			    Thread.sleep(1000);
			    Reporter.log("Collector Number- 071259999M");
			    driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
			    Thread.sleep(1000);
			    driver.findElement(nameTxtFld).clear();
				driver.findElement(nameTxtFld).sendKeys("Dmitri Jonatan");
				Reporter.log("Collector First Name- Dmitri Jonatan");
				driver.findElement(lstNameTxtFld).clear();
				driver.findElement(lstNameTxtFld).sendKeys("Casimirsson");
				Reporter.log("Collector Last Name- Casimirsson");
				driver.findElement(addresTxtFld).clear();
				driver.findElement(addresTxtFld).sendKeys("Sepänkatu 11 A 1");
				Reporter.log("Collector Address-Sepänkatu 11 A 1");
				driver.findElement(zipCodeTxtFld).clear();
				Thread.sleep(2000);
				driver.findElement(zipCodeTxtFld).sendKeys("70100");
				Reporter.log("Collector Zipcode- 70100");
				Thread.sleep(2000);
				selectCity();
				submitbtn(); 
				Thread.sleep(1000);
			}else if(companyName.equals("Netthandelen.no")||companyName.equals("BliVakker.no")||companyName.equals("drLykke.no")||companyName.equals("InGarden.no")||companyName.equals("Brandsdal.no")){
				driver.findElement(collectorTxt).clear();
			    driver.findElement(collectorTxt).sendKeys(cNumber);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//div[2]/div[2]/div/label/div/ins")).click();
			    Thread.sleep(1000);
			    driver.findElement(nameTxtFld).clear();
				driver.findElement(nameTxtFld).sendKeys(cfName);
				Reporter.log("Collector First Name-"+cfName);
				driver.findElement(lstNameTxtFld).clear();
				driver.findElement(lstNameTxtFld).sendKeys(clName);
				Reporter.log("Collector Last Name- "+clName);
				driver.findElement(addresTxtFld).clear();
				driver.findElement(addresTxtFld).sendKeys(caddress);
				Reporter.log("Collector Address-"+caddress);
				driver.findElement(zipCodeTxtFld).clear();
				Thread.sleep(2000);
				driver.findElement(zipCodeTxtFld).sendKeys(cZip);
				Reporter.log("Collector Zipcode- "+cZip);
				Thread.sleep(2000);
				selectCity();
				submitbtn(); 
				Thread.sleep(1000);
			}
		}
}