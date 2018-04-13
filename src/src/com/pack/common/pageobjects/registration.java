package src.com.pack.common.pageobjects;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class registration {
	private WebDriver driver;
	public String companyName;
	private By zipCodeTxtFld =By.id("txt-post-number");
	private By mobileTxtFld = By.id("txt-mobile-number");
	private By emailTxtFld = By.id("txt-email");
	private By fnameTxtFld = By.id("txt-first-name");
	
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	//private By minside = By.xpath("//li[2]/a/span");
	//new changes 1703
	private By minside = By.xpath("//div/a[contains(@href,'mypage')]");

	public registration(WebDriver driver) {
		this.driver = driver;
		companyName = driver.findElement(logoImage).getAttribute("title");
	}
	
	public void register(String url, String email, String password) throws Exception{
		String registrationurl =url+"/register";
		driver.navigate().to(registrationurl);
		//driver.get(url+"/register");
		Thread.sleep(2000);
		//companyName = driver.findElement(logoImage).getAttribute("title");
		System.out.println("into registration");
	    driver.findElement(emailTxtFld).clear();
	    System.out.println("into email");
	    System.out.println(email);
	    driver.findElement(emailTxtFld).sendKeys(email);
	    System.out.println("email entered");
	    driver.findElement(fnameTxtFld).click();
	    driver.findElement(fnameTxtFld).clear();
	    driver.findElement(By.id("txt-first-name")).sendKeys("Tøndevoldshagen");
	    driver.findElement(By.id("txt-last-name")).clear();
	    driver.findElement(By.id("txt-last-name")).sendKeys("Ertresvåg");
	    driver.findElement(By.id("txt-address")).clear();
	    driver.findElement(By.id("txt-address")).sendKeys("c/o S. Voll, Liakroken 14");
	    driver.findElement(zipCodeTxtFld).clear();
	    if(companyName.equals("Cocopanda.de")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("68159");
			
			Thread.sleep(2000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("01512345690");
			}
		else if(companyName.equals("Cocopanda.se")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("58002");
			
			Thread.sleep(2000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("0762552625");
			}
		else if(companyName.equals("Cocopanda.at")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("2320");
			
			Thread.sleep(2000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("066123456789");
			}
		else if (companyName.equals("BliVakker.no")||companyName.equals("Brandsdal.no")||companyName.equals("drLykke.no")||companyName.equals("InGarden.no")||companyName.equals("Netthandelen.no")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("5802");
			
			Thread.sleep(2000);
			Calendar cals = Calendar.getInstance();
			
			int minute = cals.get(Calendar.MINUTE);
			int date  = cals.get(Calendar.DATE);
			int year  = cals.get(Calendar.MONTH);
			int start =47;
			if(minute<10){
				minute=minute+10;
			}
			if(date<10){
				date=date+10;
			}
			if(year<10){
				year=year+10;
			}
			String mobile = String.valueOf(String.valueOf(start) + String.valueOf(minute)+ String.valueOf(date)+ String.valueOf(year));
			System.out.println(mobile);
			if(companyName.equals("Netthandelen.no")){
				driver.findElement(mobileTxtFld).sendKeys(mobile);
				Thread.sleep(2000);
				driver.findElement(By.id("btn-register")).click();
				
			}else{
			driver.findElement(mobileTxtFld).sendKeys("47544754");}}
		else if (companyName.equals("Cocopanda.fi")){
				driver.findElement(zipCodeTxtFld).clear();
				driver.findElement(zipCodeTxtFld).sendKeys("10120");
				
				Thread.sleep(2000);
				driver.findElement(mobileTxtFld).sendKeys("0453181878");
			}
		else if(companyName.equals("Cocopanda.dk")){
			driver.findElement(zipCodeTxtFld).clear();
			driver.findElement(zipCodeTxtFld).sendKeys("4000");
			
			Thread.sleep(2000);
			driver.findElement(mobileTxtFld).clear();
			driver.findElement(mobileTxtFld).sendKeys("47544754");
			}	   
	  //  JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollTo(500, 500);");
	    Thread.sleep(2000);
	    driver.findElement(By.id("txt-password")).click();
	    driver.findElement(By.id("txt-password")).clear();
	    driver.findElement(By.id("txt-password")).sendKeys(password);
	    Thread.sleep(2000);
	    driver.findElement(By.id("btn-register")).click();
	   
	    boolean newsletter = driver.findElements( By.id("radio-newsletter-on")).size() != 0;
		   System.out.println("Newsletter"+newsletter);
		    if(newsletter){
		    	Thread.sleep(1000);
		    	WebElement radioBtn1 = driver.findElement(By.id("radio-newsletter-on"));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn1);
		    	Reporter.log("Newsletter Signup-"+newsletter);
			}
		    
		boolean smssignup = driver.findElements( By.id("radio-sms-on")).size() != 0;
			   System.out.println("smssignup-"+smssignup);
			    if(smssignup){
			    	Thread.sleep(1000);
			    	WebElement radioBtn2 = driver.findElement(By.id("radio-sms-on"));
			    	((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn2);
			    	Reporter.log("SMS Signup -"+smssignup);
				}
		driver.findElement(By.id("btn-register")).click();
	    Reporter.log("Filled Registration Form submitted");
	    Thread.sleep(15000);
	}
	public void activationlink(String password) throws Exception{
		//companyName = driver.findElement(logoImage).getAttribute("title");
		 if(!(companyName.equals("Cocopanda.de")||companyName.equals("Netthandelen.no")||companyName.equals("Cocopanda.at"))){
			 Thread.sleep(2000);
			 driver.findElement(minside).click();
			 Thread.sleep(2000);
			 driver.findElement(By.id("btnCheckEmail")).click();
		 	Thread.sleep(2000);
		     driver.findElement(By.id("Password")).clear();
		    driver.findElement(By.id("Password")).sendKeys(password);
		    driver.findElement(By.id("LoginButton")).click();
		    Thread.sleep(2000);
		    //driver.findElement(By.xpath("//div[@id='account-content']/div/p/a")).click();
		    //new changes 1703
		    driver.findElement(By.xpath("//div[@id='orders-content']/div/p/a")).click();
		    
		    Thread.sleep(2000);
	}else{
		System.out.println("No Activation Link in CPDE & NH");
	}
}
	public void activation(String password) throws Exception{
		//companyName = driver.findElement(logoImage).getAttribute("title");
		 if(!(companyName.equals("Cocopanda.de")||companyName.equals("Netthandelen.no")||companyName.equals("Cocopanda.at"))){
			
			// driver.findElement(By.id("btnCheckEmail")).click();
		 	//Thread.sleep(1000);
		   //  driver.findElement(By.id("Password")).clear();
		   // driver.findElement(By.id("Password")).sendKeys(password);
		   // driver.findElement(By.id("LoginButton")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("button.button-large")).click();
		    Thread.sleep(1000);
	}else{
		System.out.println("CPDE, CPAT & NH customer Already Activated");
	}
}
}
