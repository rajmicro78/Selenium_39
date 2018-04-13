package src.com.pack.common.tests;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.HomePage;

import src.com.pack.common.pageobjects.TicketCreation;

public class TicketCreationTest extends TestBaseSetup {
	private WebDriver driver;
	private TicketCreation ticketcreate;
	public String uemail;
	public String upassword;
	public String image;
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		uemail = context.getCurrentXmlTest().getParameter("uEmail");
		upassword = context.getCurrentXmlTest().getParameter("uPassword");
		image = context.getCurrentXmlTest().getParameter("imagepath");
		System.out.println(uemail);
		System.out.println(upassword);
		System.out.println("ticket creation");
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
		
	}
	@Test(priority=11,groups ={"ticket"},description="All type of Ticket creation Support & Return")
	public void SupportTicket() throws Exception{
		HomePage homepage = new HomePage(driver);
		homepage.Login(uemail,upassword);
		ticketcreate = homepage.goticket();
		ticketcreate.start();
		
		//registeredUserCheckout(uemail,upassword);
	}

}
