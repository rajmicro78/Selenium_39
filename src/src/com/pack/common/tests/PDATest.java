package src.com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.NASPage;
import src.com.pack.common.pageobjects.PDAPage;

public class PDATest extends TestBaseSetup  {
	private WebDriver driver;
	public String nasuser;
	public String naspass;
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		driver.manage().deleteAllCookies();
	}
	@Test(priority=1,groups ={"Pickpack"}, description="Pick & Pack")
	public void PDAChecking() throws Exception  {
		NASPage naspage = new NASPage(driver);
		naspage.bundling(NASURL);
		boolean pickbundle = naspage.pausedbundle(NASURL);
		System.out.println(pickbundle);
		if(pickbundle){
		PDAPage pdapage = new PDAPage(driver);
		pdapage.PDALogin(pdaenv);
		pdapage.PDASelectshop(PDAShop);
		pdapage.PDApicking(NASURL);}
		naspage.packing(NASURL);
	}
}
