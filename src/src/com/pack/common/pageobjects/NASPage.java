package src.com.pack.common.pageobjects;

import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import src.com.pack.base.TestBaseSetup;

public class NASPage extends TestBaseSetup  {
	private WebDriver driver;
	String PDAuser = "3019";
	
	public NASPage (WebDriver driver) {
		this.driver=driver;
	}
	
	public void nasuserright(String NASURL)throws Exception{
		String Nasuserpage= 	"/Administration/UserDetail/"+PDAuser;
		Nasuserpage= NASURL+Nasuserpage;
		driver.get(Nasuserpage);
		int groupoptin = driver.findElements(By.xpath("//div[@id='ctl13_pnlShowGroup']/fieldset/ul/li")).size();
		if(groupoptin>1){
			System.out.println("Already in multiple group");
			Reporter.log("Already in Multiple Group");
		}else{
			driver.findElement(By.id("ctl13_btnEdit")).click();
		
		final String[] textOptions = {"PDA Packing", "PDA Picking"};
		final WebElement element = driver.findElement(By.id("ctl13_lbAvailableGroups_left"));
		final Select dropdown = new Select(element);
		final List<WebElement> options = dropdown.getOptions();
		final Actions builder = new Actions(driver);
		final boolean isMultiple = dropdown.isMultiple();
		if (isMultiple) {
		    dropdown.deselectAll();
		}
		builder.keyDown(Keys.CONTROL);
		for (String textOption : textOptions) {
		    for (WebElement option : options) {
		        final String optionText = option.getText().trim();
		        if (optionText.equalsIgnoreCase(textOption)) {
		            if (isMultiple) {
		                if (!option.isSelected()) {
		                    builder.click(option);
		                }
		            } else {
		                option.click();
		            }
		            break;
		        }
		    }
		}
		builder.keyUp(Keys.CONTROL).build().perform();
		driver.findElement(By.className("csadd")).click();
		driver.findElement(By.id("btnSave")).click();
		Reporter.log("Added to PDA Packing,Picking group");
		
		}	
	}
	
	public void bundling(String NASURL ) throws Exception{
		String Nasbundling = NASURL + "Warehouse/PickingInfo/";
		//System.out.println(Nasbundling);
		driver.get(Nasbundling);
		for (int i=0;i<=5;i++){
			driver.findElement(By.xpath("//input[contains(@id,'btnGenerateBundle')]")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
		}
		Reporter.log("NAS Bundling done");
		
	}
	
	public void packing(String NASURL ) throws Exception{
		String Naspacking = NASURL + "Warehouse/BundleInfo/";
		//System.out.println(Naspacking);
		driver.get(Naspacking);
		driver.findElement(By.xpath("//input[contains(@id,'btnReportReadyToBePacked')]")).click();
		String oddeven = driver.findElement(By.xpath("//tr[contains(@id,'trReportReadyToBePacked')]/td/table/tbody/tr")).getAttribute("class");
		if(oddeven.contains("odd")){
		Thread.sleep(500);
		int[] array = null;
		int size = driver.findElements(By.xpath("//tr[contains(@id,'trReportReadyToBePacked')]/td/table/tbody/tr")).size();
		System.out.println(size);
		//if(size>1){
			array = new int[size];
			int j=1;
			for(int k = 0 ; k < size ; k++){
			
			array[k] = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@id,'trReportReadyToBePacked')]/td/table/tbody/tr["+j+"]/td[1]")).getText());
			j++;
			}
			String packingurl=NASURL+"Warehouse/Packing/";
			System.out.println("NAS-"+packingurl);
			driver.get(packingurl);
			for(int l=0;l<size;l++){
				driver.findElement(By.id("ctl13_txtBarCode")).sendKeys("BUN "+array[l]);
				driver.findElement(By.id("ctl13_btnReadCode")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("ctl13_txtBarCode")).sendKeys("BRU "+PDAuser);
				driver.findElement(By.id("ctl13_btnReadCode")).click();
				Thread.sleep(1000);
				Reporter.log(l+". "+"Finished packing-"+array[l]);
			}
		}else{Reporter.log("Nothing to pack");}
	}
	
	public boolean pausedbundle(String NASURL ) throws Exception{
		String Nasbundling = NASURL + "Warehouse/BundleInfo/";
		//System.out.println(Nasbundling);
		driver.get(Nasbundling);
		driver.findElement(By.xpath("//input[contains(@id,'BundleInfo_btnReportPicked')]")).click();
		Thread.sleep(1000);
		String oddeven = driver.findElement(By.xpath("//tr[contains(@id,'trReportPicked')]/td/table/tbody/tr")).getAttribute("class");
		if(oddeven.contains("odd")){
		driver.findElement(By.xpath("//input[contains(@id,'BundleInfo_lvReportPicked_btnPauseBundle')]")).click();
		driver.switchTo().alert().accept();
		}
		driver.findElement(By.xpath("//input[contains(@id,'BundleInfo_btnReportToPick')]")).click();
		Thread.sleep(1000);
		String bunpick = driver.findElement(By.xpath("//tr[contains(@id,'trReportToPick')]/td/table/tbody/tr")).getAttribute("class");
		System.out.println(bunpick);
		if(bunpick.contains("row")){
			return false;
			
		}else{
			return true;
		}
			
	}
}
