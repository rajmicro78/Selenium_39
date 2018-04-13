package src.com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.NASPage;

public class NASUserRight extends TestBaseSetup {
	private WebDriver driver;
	
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
	}
	
	@Test(priority=1,groups ={"User Right"}, description="Setting User Right")
	public void UserRight() throws Exception {
		NASPage naspage = new NASPage(driver);
		naspage.nasuserright(NASURL);
	}
}
