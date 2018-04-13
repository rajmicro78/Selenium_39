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
	@FindBy(id="txt-post-number-error") 											WebElement txtzipCodeTxtFld;
	@FindBy(id="txt-mobile-number") 												WebElement mobileTxtFld;
	@FindBy(id="txt-mobile-number-error") 											WebElement txtmobileTxtFld;
	@FindBy(id="txt-email") 														WebElement emailTxtFld;
	@FindBy(id="txt-email-error") 													WebElement txtemailTxtFld;
	@FindBy(id="txt-first-name") 													WebElement fnameTxtFld;
	@FindBy(id="txt-first-name-error") 												WebElement txtfnameTxtFld;
	@FindBy(id="txt-last-name") 													WebElement lnameTxtFld;
	@FindBy(id="txt-last-name-error") 												WebElement txtlnameTxtFld;
	@FindBy(id="txt-address") 														WebElement addressTxtFld;
	@FindBy(id="txt-address-error") 												WebElement txtaddressTxtFld;
	@FindBy(id="btn-register") 														WebElement registerbtn;
	@FindBy(id="txt-password") 														WebElement passwordTxtfld;
	@FindBy(id="txt-password-error") 												WebElement txtpasswordTxtfld;
	@FindBy(id="Newsletter-error") 													WebElement txtnewsletter;
	@FindBy(id="SmsMarketing-error") 												WebElement txtsms;
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
		Assert.assertTrue(emailTxtFld.isDisplayed());
		Assert.assertTrue(fnameTxtFld.isDisplayed());
		Assert.assertTrue(lnameTxtFld.isDisplayed());
		Assert.assertTrue(addressTxtFld.isDisplayed());
		Assert.assertTrue(zipCodeTxtFld.isDisplayed());
		Assert.assertTrue(mobileTxtFld.isDisplayed());
		Assert.assertTrue(passwordTxtfld.isDisplayed());
		Assert.assertTrue(registerbtn.isDisplayed());
		registerbtn.click();
		Reporter.log("Mandatory field check in registration form");
		Assert.assertTrue(txtemailTxtFld.isDisplayed());
		Assert.assertTrue(txtfnameTxtFld.isDisplayed());
		Assert.assertTrue(txtlnameTxtFld.isDisplayed());
		Assert.assertTrue(txtaddressTxtFld.isDisplayed());
		Assert.assertTrue(txtzipCodeTxtFld.isDisplayed());
		
		if(!(companyName.equals("Cocopanda.de")||companyName.equals("Cocopanda.at"))){
		Assert.assertTrue(txtmobileTxtFld.isDisplayed());}
		Assert.assertTrue(txtnewsletter.isDisplayed());
		Assert.assertTrue(txtsms.isDisplayed());
	}


}
