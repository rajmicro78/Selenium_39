package src.com.pack.common.pageobjects;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	@FindBy(id="trck_checkout_top_nav_logo") 										WebElement toplogo;
	@FindBy(xpath="//span[contains(@class,'layoutCartProductCount')]") 				WebElement cart;
	@FindBy(xpath="//a[contains(@onclick,'RemoveProductFromCart')]") 				WebElement decreaseqty;
	//@FindBy(xpath="//a[contains(@onclick,'RemoveAllProductFromCart')]") 			WebElement removeallproduct;
	private By removeallproduct = By.xpath("//a[contains(@onclick,'RemoveAllProductFromCart')]");
	//private By wishlistcancel =By.id("add-wishlist-cancel");
	@FindBy(id="add-wishlist-cancel") 												WebElement wishlistcancel;
	@FindBy(xpath="//a[contains(@onclick,'ConfirmProductRemoval')]") 				WebElement confirmproductremoval;
	//private By confirmproductremoval = By.xpath("//a[contains(@onclick,'ConfirmProductRemoval')]");
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
@FindBy(xpath="//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']")	List<WebElement> paymentMethod;
	
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
		/*boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a") ).size() != 0;
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a")).click();
		}*/
		Reporter.log("Verify fields in Checkout form");
		//Assert.assertTrue(verifyobjectpresent(chkfnameTxtFld), "First name field not visible");
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
		chkemailTxtFld.clear();
		chkemailTxtFld.sendKeys(uemail);
		guestchkoutBtn.click();
		
	}
	public void checkpaymentmethod() throws Exception {
		int paymenttype = paymentMethod.size();
		String[] paytype = new String[paymenttype];
			for(int i=0;i<paymenttype;i++){
				int j=i+1;
			String paymentt =  driver.findElement( By.xpath("//div[@data-ng-if='isPaymentGroupNotAddedToPageAlready(paymentMethod)']["+j+"]/label/span")).getText();
			paytype[i]=paymentt;
			System.out.println(paytype[i]);
			Reporter.log(paytype[i]);
			}
		}
	public void removeproduct() throws Exception {
		toplogo.click();
		Thread.sleep(1000);
		String cartvalue = cart.getText();
		cart.click();
		System.out.println("loop to run-"+Integer.parseInt(cartvalue));
		//for(int i=1;i<=Integer.parseInt(cartvalue);i++){
			
			 WebElement decqty =driver.findElement(removeallproduct);
			 JavascriptExecutor executor = (JavascriptExecutor)driver;
			 executor.executeScript("arguments[0].click();", decqty);
			//removeallproduct.click();
			 Thread.sleep(1000);
			 System.out.println("WL-"+wishlistcancel.isDisplayed());
			if(wishlistcancel.isDisplayed()){
				 wishlistcancel.click();
			}
			 Thread.sleep(1000);
			// boolean confi = driver.findElements(confirmproductremoval).size() != 0;
			 //System.out.println("confi"+confi);
			 System.out.println("CPR-"+confirmproductremoval.isDisplayed());
			 if(confirmproductremoval.isDisplayed()){
					confirmproductremoval.click();
				}
			//decreaseqty.click();
			//System.out.println(i);
			Thread.sleep(2000);
		//}
	
	}
	
	
	
	
	//public void readExcel(String filePath,String fileName,String sheetName) throws IOException{
		public static String[] readExcel() throws IOException{
	    //Create an object of File class to open xlsx file
		String filePath= "D:";
		String fileName ="excelread.xlsx";
		String sheetName="registration";
	    File file =    new File(filePath+"\\"+fileName);
	    System.out.println(file);
	    FileInputStream inputStream = new FileInputStream(file);

	    Workbook guru99Workbook = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    guru99Workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls")){
	        guru99Workbook = new HSSFWorkbook(inputStream);
	    }
	    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
	    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
	    int colCount=guru99Sheet.getRow(1).getLastCellNum();
	    System.out.println("RC-"+ colCount);
	    String str[] = new String[colCount];
	    for (int i = 1; i < rowCount+1; i++) {
	        Row row = guru99Sheet.getRow(i);
	      
	        System.out.println("row"+row.getLastCellNum());
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	
	         
	        	if(row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
	        	     str[j] = NumberToTextConverter.toText(row.getCell(j).getNumericCellValue());
	        	}else{
	        		 str[j]= row.getCell(j).getStringCellValue();
	        	}
	        	System.out.print(str[j]+"|| ");
	        }
	        System.out.println();
	    }
	    return str;
	}
	public boolean verifyobjectpresent(WebElement objname){
		boolean objpresent = objname.isDisplayed();
		return  objpresent;
	}
}
