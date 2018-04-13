package src.com.pack.common.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class GiftCertificate {
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private By searchBox = By.className("input-text-search");
	private By searchButton = By.xpath("//button[contains(@class, 'button-search')]");
	private static By giftlink = By.xpath("//a[contains(@href, '#giftcards')]");
	private WebDriver driver;
	public String GCcode=null;
	private String Giftsearchterm;
	private String lowestprice;
	public GiftCertificate(WebDriver driver ) {
		this.driver=driver;	
	}
	public void buygiftcertificate(String sitename)throws Exception{
		//Thread.sleep(2000);
		//driver.findElement(logoImage).click();
		
		Thread.sleep(1000);
		driver.findElement(searchBox).click();
		switch (sitename) {
		case "CP-Sweden":
			Giftsearchterm= "Presentkort";
			lowestprice ="Lägsta pris";
			break;
		case "CP-Finland":
			Giftsearchterm= "Lahjakortit";
			lowestprice ="Alin hinta";
			break;
		case "BliVakker":
			Giftsearchterm= "GAVEKORT";
			lowestprice ="Laveste pris";
			break;
		case "CP-Austria":
			Giftsearchterm= "Geschenkgutscheine";
			lowestprice ="Niedrigster Preis";
			break;
		case "CP-Germany":
			Giftsearchterm= "Geschenkgutscheine";
			lowestprice ="Niedrigster Preis";
			break;
		case "CP-Denmark":
			Giftsearchterm= "GAVEKORT";
			lowestprice ="Laveste pris";
			break;
		}
		driver.findElement(searchBox).sendKeys(Giftsearchterm);
		Thread.sleep(1000);
		Reporter.log("Search-"+Giftsearchterm);
		driver.findElement(searchButton).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='dropdown-arrow']")).click();
		 List<WebElement> options = driver.findElements(By.xpath("//div[@id='mCSB_1_container']/ul[@class='dropdown-list']/li/label/a"));
		    //Count the Values
		    System.out.println(options.size());
			
		    for(int i=0;i<8;i++){
		   
		    String optionName = options.get(i).getText();
		    if(optionName.equals(lowestprice)){
		       
		    options.get(i).click();
		    Thread.sleep(1000);
		    }
		    }
		    
		   /* 
		    int tprodlist = driver.findElements(By.xpath("//div[@id='product-list-container']/div")).size();
		    for(int m=1;m<=tprodlist;m++){
		    	String productnam= driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+m+"]/div/a")).getAttribute("title");
		    	if(productnam.toLowerCase().contains(Giftsearchterm.toLowerCase())){
		    			WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+m+"]/div/a"));
		    			JavascriptExecutor executor = (JavascriptExecutor)driver;
		    			executor.executeScript("arguments[0].click();", element);
		    			Thread.sleep(1000);
		    			break;
		    			}
		    }*/
		    WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item'][1]/div/a"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
	}
	
	public String readgiftcertificate()throws Exception{
		driver.findElement(giftlink).click();
		int noofgiftcertificate= driver.findElements(By.xpath("//table[contains(@class,'tablesorter')]/tbody/tr")).size();
		System.out.println(noofgiftcertificate);
		GCcode = driver.findElement(By.xpath("//table[contains(@class,'tablesorter')]/tbody/tr[1]/td[1]/span")).getText();
		System.out.println(GCcode);
		//Reporter.log("GC Code- "+GCcode);
		return GCcode;
	}
	public void applygc(String GCcode) throws Exception{
		System.out.println("Apply GC");
		//System.out.println("Value-"+usegiftcertificate());
		//GCcode =usegiftcertificate();
		driver.findElement(By.id("goToCart")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("showGiftCard")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-giftcard-number")).sendKeys(GCcode);
		Thread.sleep(2000);
		Reporter.log("Gift Certificate applied-"+GCcode);
		driver.findElement(By.id("btnAddCoupon")).click();
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, -250);");
		
	}
}
