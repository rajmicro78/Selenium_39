package src.com.pack.common.pageobjects;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import src.com.pack.base.TestBaseSetup;
public class PDAPage extends TestBaseSetup {
	private WebDriver driver;
	String PDAuser = "3019";
	String PDApass ="9666";
	String Shopname;
	public PDAPage (WebDriver driver) {
		this.driver=driver;
	}
	public void PDALogin(String pdaenv) throws Exception{
		driver.navigate().to(pdaenv);
		driver.findElement(By.xpath("//input[contains(@class,'loginFieldInputField')]")).sendKeys(PDAuser);
		Thread.sleep(500);
		driver.findElement(By.id("txtPassword")).sendKeys(String.valueOf(PDApass));
		driver.findElement(By.id("cphMain_btnLogin")).click();
	}
	public void PDASelectshop(String PDAShop) throws Exception{
		System.out.println("Select shop -"+PDAShop);
		new Select(driver.findElement(By.id("cphMain_ddShop"))).selectByVisibleText(PDAShop);
		Thread.sleep(1000);
		driver.findElement(By.id("cphMain_btnSetShopId")).click();
		//Thread.sleep(2500);
	}
	public void PDApicking(String NASURL) throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='contentMain']/table/tbody/tr[1]/td[1]/a")));
		driver.findElement(By.xpath("//div[@id='contentMain']/table/tbody/tr[1]/td[1]/a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='contentMain']/table/tbody/tr[1]/td[2]/a")));
		driver.findElement(By.xpath("//div[@id='contentMain']/table/tbody/tr[1]/td[2]/a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("cphMain_lblBundlesReadyForPicking")));
		String bundle = driver.findElement(By.id("cphMain_lblBundlesReadyForPicking")).getText();
		String pattern ="\\d+";
		 Pattern r = Pattern.compile(pattern);
		 Matcher m = r.matcher(bundle);
		 if (m.find( )) {
	         //System.out.println("Found value: " + m.group(0) );
	         bundle =m.group(0);
	         //System.out.println("Found value1: " + m.group(1) );
	      }else {
	         System.out.println("NO MATCH");
	      }
		if(Integer.parseInt(bundle)>0){
			System.out.println("No of bundle -"+bundle);
			for(int x=1;x <=Integer.parseInt(bundle);x++){
				driver.findElement(By.id("cphMain_btnStartNewBundle")).click();
				driver.findElement(By.id("cphMain_btnStartNewBundleConfirmed")).click();
				driver.findElement(By.id("cphMain_btn2")).click();
				int noofproduct = driver.findElements(By.xpath("//table/tbody/tr[@class='bundlePickingDataStatusId2']")).size();
				driver.findElement(By.id("cphMain_btn1")).click();
				System.out.println("No of product -"+noofproduct);
				for(int y=1;y<=noofproduct;y++){
					driver.findElement(By.id("cphMain_btnConfirmPicking")).click();
					Thread.sleep(1200);
					//wait.until(ExpectedConditions.elementToBeClickable(By.id("cphMain_btnConfirmPicking")));
					
				}
				String bundlenumber = driver.findElement(By.id("cphMain_lblBundleInfo")).getText();
				String pattern1 ="[0-9]*\\Z";
				 Pattern r1 = Pattern.compile(pattern1);
				 Matcher m1 = r1.matcher(bundlenumber);
				 if (m1.find( )) {
			         bundlenumber =m1.group(0);
			      }else {
			         System.out.println("NO MATCH");
			      }
				driver.findElement(By.id("cphMain_btnFinishBundle")).click();
				String pickingurl = driver.getCurrentUrl();
				System.out.println("Bundle Number-" +bundlenumber);
				Reporter.log(x+". "+"Finished Picking-"+bundlenumber+"-Total Product-"+noofproduct);
			}
		}else {System.out.println("Nothing to Bundle");Reporter.log("Nothing to Bundle");}
			
		}
		
	
	

}
