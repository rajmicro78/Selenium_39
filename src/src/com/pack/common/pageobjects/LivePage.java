package src.com.pack.common.pageobjects;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import src.com.pack.base.TestBaseSetup;
public class LivePage extends TestBaseSetup {
	private WebDriver driver;
	String PDAuser = "3019";
	String PDApass ="9666";
	String Shopname;
	public String companyName;
	@FindBy(id="txt-post-number") 													WebElement zipCodeTxtFld;
	@FindBy(id="zipCode") 															WebElement chkzipCodeTxtFld;
	@FindBy(id="txt-post-number-error") 											WebElement txtzipCodeTxtFld;
	@FindBy(id="txt-mobile-number") 												WebElement mobileTxtFld;
	@FindBy(id="input-mobile-phone") 												WebElement chkmobileTxtFld;
	@FindBy(id="txt-mobile-number-error") 											WebElement txtmobileTxtFld;
	@FindBy(id="txt-email") 														WebElement emailTxtFld;
	@FindBy(id="input-email") 														WebElement chkemailTxtFld;
	@FindBy(xpath="//button[contains(@data-ng-click,'guestLogin')]")				WebElement guestchkoutBtn;
	@FindBy(id="txt-email-error") 													WebElement txtemailTxtFld;
	@FindBy(id="txt-first-name") 													WebElement fnameTxtFld;
	@FindBy(id="input-name") 														WebElement chkfnameTxtFld;
	@FindBy(id="txt-first-name-error") 												WebElement txtfnameTxtFld;
	@FindBy(id="txt-last-name") 													WebElement lnameTxtFld;
	@FindBy(id="input-lname") 														WebElement chklnameTxtFld;
	@FindBy(id="txt-last-name-error") 												WebElement txtlnameTxtFld;
	@FindBy(id="txt-address") 														WebElement addressTxtFld;
	@FindBy(id="input-address") 													WebElement chkaddressTxtFld;
	@FindBy(id="txt-address-error") 												WebElement txtaddressTxtFld;
	@FindBy(id="btn-register") 														WebElement registerbtn;
	@FindBy(id="txt-password") 														WebElement passwordTxtfld;
	@FindBy(id="txt-password-error") 												WebElement txtpasswordTxtfld;
	@FindBy(id="radio-newsletter-on") 												WebElement chkradionewsletter;
	@FindBy(id="radio-sms-on") 														WebElement chkradiosms;
	@FindBy(id="check-newsletter") 													WebElement chkchknewsletter;
	@FindBy(id="check-sms") 														WebElement chkchksms;
	@FindBy(id="Newsletter-error") 													WebElement txtnewsletter;
	@FindBy(id="SmsMarketing-error") 												WebElement txtsms;
	@FindBy(id="submit-order") 														WebElement submitordBtn;
	@FindBy(xpath="//h2[@class='site-logo']/a")										WebElement logoImage;
	public LivePage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		companyName = logoImage.getAttribute("title");
		
	}
	public void Livesite(String appURL) throws Exception{
		driver.navigate().to(appURL);
		Thread.sleep(2000);
	}
	public void verifyregistrationpage() throws Exception{
		boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a") ).size() != 0;
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a")).click();
		}
		Reporter.log("Verify fields in registration form");
		Assert.assertTrue(verifyobjectpresent(emailTxtFld));
		Reporter.log("Email Field -"+verifyobjectpresent(emailTxtFld));
		Assert.assertTrue(verifyobjectpresent(fnameTxtFld));
		Reporter.log("First name Field -"+verifyobjectpresent(fnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(lnameTxtFld));
		Reporter.log("Last name Field -"+verifyobjectpresent(lnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(addressTxtFld));
		Reporter.log("Address name Field -"+verifyobjectpresent(addressTxtFld));
		Assert.assertTrue(verifyobjectpresent(zipCodeTxtFld));
		Reporter.log("Zipcode  Field -"+verifyobjectpresent(zipCodeTxtFld));
		Assert.assertTrue(verifyobjectpresent(mobileTxtFld));
		Reporter.log("Mobile  Field -"+verifyobjectpresent(mobileTxtFld));
		Assert.assertTrue(verifyobjectpresent(passwordTxtfld));
		Reporter.log("Password  Field -"+verifyobjectpresent(passwordTxtfld));
		Assert.assertTrue(verifyobjectpresent(registerbtn));
		Reporter.log("Password  Field -"+verifyobjectpresent(registerbtn));
		registerbtn.click();
		Reporter.log("Mandatory field check in registration form");
		Assert.assertTrue(verifyobjectpresent(txtemailTxtFld));
		Reporter.log("Mandatory email  message -"+verifyobjectpresent(txtemailTxtFld));
		Assert.assertTrue(verifyobjectpresent(txtfnameTxtFld));
		Reporter.log("Mandatory first name  message -"+verifyobjectpresent(txtfnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(txtlnameTxtFld));
		Reporter.log("Mandatory last name  message -"+verifyobjectpresent(txtlnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(txtaddressTxtFld));
		Reporter.log("Mandatory Address  message -"+verifyobjectpresent(txtaddressTxtFld));
		Assert.assertTrue(verifyobjectpresent(txtzipCodeTxtFld));
		Reporter.log("Mandatory Zipcode  message -"+verifyobjectpresent(txtzipCodeTxtFld));
		if(!(companyName.equals("Cocopanda.de")||companyName.equals("Cocopanda.at"))){
			Assert.assertTrue(verifyobjectpresent(txtmobileTxtFld));
			Reporter.log("Mandatory mobile  message -"+verifyobjectpresent(txtmobileTxtFld));
			
		}
		Assert.assertTrue(verifyobjectpresent(txtnewsletter));
		Reporter.log("Mandatory Zipcode  message -"+verifyobjectpresent(txtnewsletter));
		Assert.assertTrue(verifyobjectpresent(txtsms));
		Reporter.log("Mandatory SMS  message -"+verifyobjectpresent(txtsms));
	}

	
	public void verifycheckoutpage() throws Exception{
		boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a") ).size() != 0;
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a")).click();
		}
		Reporter.log("Verify fields in Checkout form");
		
		Assert.assertTrue(verifyobjectpresent(chkfnameTxtFld));
		Reporter.log("First Name Field -"+verifyobjectpresent(chkfnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(chklnameTxtFld));
		Reporter.log("Last Name Field -"+verifyobjectpresent(chklnameTxtFld));
		Assert.assertTrue(verifyobjectpresent(chkaddressTxtFld));
		Reporter.log("Address  Field -"+verifyobjectpresent(chkaddressTxtFld));
		Assert.assertTrue(verifyobjectpresent(chkzipCodeTxtFld));
		Reporter.log("Zipcode  Field -"+verifyobjectpresent(chkzipCodeTxtFld));
		Assert.assertTrue(verifyobjectpresent(chkmobileTxtFld));
		Reporter.log("Mobile  Field -"+verifyobjectpresent(chkmobileTxtFld));
		Reporter.log("Mandatory field check in registration form");
	}
	
	public void guestuserData(String uemail) throws Exception {
		
		Assert.assertTrue(verifyobjectpresent(chkemailTxtFld));
		Reporter.log("Email Field -"+verifyobjectpresent(chkemailTxtFld));
		chkemailTxtFld.sendKeys(uemail);
		guestchkoutBtn.click();
		
	}
	public boolean verifyobjectpresent(WebElement objname){
		boolean objpresent = objname.isDisplayed();
		return  objpresent;
	}
}
