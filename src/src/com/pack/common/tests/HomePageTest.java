package src.com.pack.common.tests;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.HomePage;


public class HomePageTest extends TestBaseSetup {
	WebDriver driver;
    HomePage objhomePage;
   
    
    @BeforeClass
	public void setUp() {
		driver=getDriver();
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
	}
	
	@Test(groups ={"home"}, description="Open home page list all the top menu")	
	public void verifyHomePage() throws Exception {
		
		//HomePage homePage = new HomePage(driver);
		objhomePage = new HomePage(driver);
		objhomePage.listMenuName();
		//Reporter.log("Company Name - "+homePage.verifyCompanyLogo());
		//Reporter.log("Search Box Present - " +homePage.verifySearchBoxPresent());
		//Reporter.log("Search Box Submit Button - " +homePage.verifySearchBoxbutton());
		//Reporter.log("Cart Value - " +homePage.verifyCartValue());
		//homePage.navcategory();
		//homePage.databasev();
	//	homePage.listMenuName();
			//homePage.nas();
	}
	
}
