package src.com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.com.pack.base.TestBaseSetup;
import src.com.pack.common.pageobjects.HomePage;
import src.com.pack.common.pageobjects.registration;

public class RegistrationTest extends TestBaseSetup {
	private WebDriver driver;
	public String env;
	public String URL;
	public String sitename;
	public String site;
	
	public String uemail;
	public String upassword;
	public String database;
	public registration Registration;

	@BeforeClass
	public void setUp(ITestContext context) {
		driver=getDriver();
		env = context.getCurrentXmlTest().getParameter("env");
		sitename = context.getCurrentXmlTest().getParameter("sitename");
		if(env.equals("Releasetest")){
			env ="https://releasetest.netthandelen.no:";
		}else if(env.equals("Test") ){
			env = "https://test.netthandelen.no:";
		}else if(env.equals("Devbranch") ){
			env = "https://devbranch.netthandelen.no:";
		}
		switch(sitename){
		case "NH" :
			URL = env+ "7001";
			site ="Netthandelen.no";
			break;
		case "BV" :
			URL = env+ "7002";
			site ="BliVakker.no";
			break;
		case "DL" :
			URL = env+ "7005";
			site ="InGarden.no";
			break;
		case "CPDK":
			URL = env+ "7004";
			site ="Cocopanda.dk";
			break;
		case "CPSE" :
			URL = env+ "7007";
			site ="Cocopanda.se";
			break;
		case "CPFI":
			URL = env+ "7008";
			site ="Cocopanda.fi";
			break;
		case"BON" :
			URL = env+ "7009";
			site ="Brandsdal.no";
			break;		
		case "CPDE" :
			URL = env+ "7010";
			site ="Cocopanda.de";
			break;
		case "CPAT" :
			URL = env+ "7011";
			site ="Cocopanda.at";
			break;
		}
		//URL =env+URL;
		System.out.println(URL);
		uemail = context.getCurrentXmlTest().getParameter("uEmail");
		upassword = context.getCurrentXmlTest().getParameter("uPassword");
		database = context.getCurrentXmlTest().getParameter("database");
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
	}
	@Test (groups ={"registration"},description="Registered as new customer and activation")
	public void register() throws Exception {
		System.out.println("Registration Process");	
		HomePage homepage = new HomePage(driver);
		Registration = homepage.newregister();
		Registration.register(URL,uemail,upassword);
	
		Registration.activationlink(upassword);
		Thread.sleep(2000);
		homepage.databasev(env, URL,site,uemail);
		Thread.sleep(2000);
		Registration.activation(upassword);
		Reporter.log("Registered as a new User");
	}


}
