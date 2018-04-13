package src.com.pack.common.pageobjects;


//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.KeyEvent;
//import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TicketCreation {
	private WebDriver driver;
	public String companyName;
	
	private static By KundeLink = By.xpath("//a[contains(@href, '#support')]");
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private static By btnCreatTick = By.xpath("//a[contains(@href,'createticket')]/span");
	private String steps;
	private int i=1;
	private int m=1;
	private int o=1;
	private int t=1;
	private String Topiclist;
	private int  wizards;
	
	
	public TicketCreation(WebDriver driver) {
		this.driver=driver;
		companyName = driver.findElement(logoImage).getAttribute("title");
	}
	public void start() throws Exception{
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(By.xpath("//li[2]/a/span")).click();
			WebDriverWait wait = new WebDriverWait(driver, 15);
			Thread.sleep(1000);
			driver.findElement(KundeLink).click();
			Thread.sleep(8000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(btnCreatTick));
			driver.findElement(By.xpath("//div[3]/div/div[4]/a/span")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ticket-wizard-container']/div")));
			//wizards = driver.findElements(By.xpath("//div[@class='ticket-wizard-container']/div")).size();
			wizards = driver.findElements(By.xpath("//ul[@id='topicList']/li")).size();
			wizard();
		}else{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(KundeLink).click();
		//Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnCreatTick));
		driver.findElement(btnCreatTick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ticket-wizard-container']/div")));
		//wizards = driver.findElements(By.xpath("//div[@class='ticket-wizard-container']/div")).size();
		wizards = driver.findElements(By.xpath("//ul[@id='topicList']/li")).size();
		wizard();}
	}
	
	public void wizard() throws Exception{
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(KundeLink).click();
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(btnCreatTick));
			driver.findElement(By.xpath("//div[3]/div/div[4]/a/span")).click();
		}else{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(KundeLink).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnCreatTick));
		driver.findElement(btnCreatTick).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ticket-wizard-container']/div")));}
		//wizards = driver.findElements(By.xpath("//div[@class='ticket-wizard-container']/div")).size();
		do{
			System.out.println("Wizard-" +i+"of-"+wizards);
			step();
			//System.out.println("Wizard-" +i+"of-"+wizards);
		} while(i<wizards);
		//for(;i<=wizards;){
			
		//	step();
		//	}
		/*
		int  wizards = driver.findElements(By.xpath("//div[@class='ticket-wizard-container']/div")).size();
		for(int i=1;i<=wizards;i++){
		String styletype =driver.findElement(By.xpath("//div[@class='ticket-wizard-container']/div["+i+"]")).getAttribute("style");
			if(styletype.equals("")){
			String wiz =	driver.findElement(By.xpath("//div[@class='ticket-wizard-container']/div["+i+"]")).getAttribute("id");
			System.out.println(+i+"-"+wiz);
			System.out.println(driver.findElement(By.xpath("//ul[@id='topicList']/li[1]/a")).getText());
			//driver.findElement(By.xpath("//ul[@id='topicList']/li[1]/a")).click();
			//System.out.println(driver.findElement(By.xpath("//div[@id='categoryStep']/div/nav/ul/li[1]/a")).getText());
			//driver.findElement(By.xpath("//div[@id='categoryStep']/div/nav/ul/li[1]/a")).click();
			break;
			}else{
				System.out.println("not none");
			}
		}*/
	}
	public void step() throws Exception{
		int  wizard = driver.findElements(By.xpath("//div[@class='ticket-wizard-container']/div")).size();
		for(int j=1;j<=wizard;j++){
			
			String styletype =driver.findElement(By.xpath("//div[@class='ticket-wizard-container']/div["+j+"]")).getAttribute("style");
			if(styletype.equals("")){
			 steps =	driver.findElement(By.xpath("//div[@class='ticket-wizard-container']/div["+j+"]")).getAttribute("id");
			 System.out.println(steps);
			 break;
			}
			
		}
			switch (steps){
			case"topicStep":
				topicStep();
				break;
			case"categoryStep":
				categoryStep();
				
				break;
			case"damageStep":
				damageStep();
				
				break;
			case"orderStep":
				orderStep();
				
				break;
			case"productStep":
				productStep();
				
				break;
			case"trackingStep":
				trackingStep();
				
				break;
			case"uploadStep":
				uploadStep();
				
				break;
			case"descriptionStep":
				descriptionStep();
				
				break;
			}
	}
	
	public void topicStep() throws Exception{
		//System.out.println("topicStep");
		int topiclist = driver.findElements(By.xpath("//ul[@id='topicList']/li")).size();
		System.out.println(topiclist);
		//for (;m<=topiclist;){
		if(i<=topiclist){	
		Topiclist = driver.findElement(By.xpath("//ul[@id='topicList']/li["+m+"]/a")).getText();
			System.out.println(Topiclist);
			
		//	break;
		//}
		driver.findElement(By.xpath("//ul[@id='topicList']/li["+m+"]/a")).click();
		m++;
		step();}
	}
	public void categoryStep() throws Exception{
		//System.out.println("categoryStep");
		int  wizards = driver.findElements(By.xpath("//div[@id='categoryStep']/div/nav/ul")).size();
		for(int n=1;n<=wizards;n++){
			String styletype =driver.findElement(By.xpath("//div[@id='categoryStep']/div/nav/ul["+n+"]")).getAttribute("style");
			if(styletype.equals("")){
			System.out.println( driver.findElement(By.xpath("//div[@id='categoryStep']/div/nav/ul["+n+"]/li/a")).getText());
			 driver.findElement(By.xpath("//div[@id='categoryStep']/div/nav/ul["+n+"]/li/a")).click();
			 break;
			}
		}
		step();
	}
	public void damageStep() throws Exception{
		//System.out.println("damageStep");
		driver.findElement(By.xpath("//ul[@id='damageList']/li[1]/a")).click();
		step();
	}
	public void orderStep() throws Exception{
		//System.out.println("orderStep");
		Thread.sleep(1000);
		boolean noorder = driver.findElements( By.xpath("//ul[@id='orderList']/li") ).size() != 0;
		//int noorder =driver.findElements(By.xpath("//ul[@id='orderSkip']/li")).size();
		if(!noorder){
			driver.findElement(By.xpath("//ul[@id='orderSkip']/li[1]/a")).click();
			Thread.sleep(1000);
			step();
		}else{
		int orderlist =driver.findElements(By.xpath("//ul[@id='orderList']/li")).size();
		System.out.println(orderlist);
		for(;t<=orderlist;){		
			driver.findElement(By.xpath("//ul[@id='orderList']/li["+t+"]/a")).click();
			Thread.sleep(1000);
			step();
			break;
		}
		}
		/*
		Calendar now = Calendar.getInstance();
		int millisStart = now.get(Calendar.SECOND);
		System.out.println("ms"+millisStart);
		
		if(millisStart==0){
			millisStart =millisStart+i;
			driver.findElement(By.xpath("//ul[@id='orderList']/li["+millisStart+"]/a")).click();
			step();
			//break;
		}else if (millisStart> 60){
			millisStart =millisStart-30;
			//orderlist = orderlist-5;
			driver.findElement(By.xpath("//ul[@id='orderList']/li["+millisStart+"]/a")).click();
			step();
			//break;
		}else if(millisStart>15){
			millisStart =millisStart-15;
			driver.findElement(By.xpath("//ul[@id='orderList']/li["+millisStart+"]/a")).click();
			step();
		}*/
	}
	public void productStep() throws Exception{
		System.out.println("productStep");
	//1	int numberproduct = driver.findElements(By.xpath("//tbody[@id='productList']/tr")).size();
		
		//1	for (int p=1;p<=numberproduct;){
			//	System.out.println(driver.findElement(By.xpath("//tbody[@id='productList']/tr["+p+"]/td[3]")).getText());
			//1	int qty = Integer.parseInt(driver.findElement(By.xpath("//tbody[@id='productList']/tr["+p+"]/td[3]")).getText());
				int qty = Integer.parseInt(driver.findElement(By.xpath("//tbody[@id='productList']/tr[1]/td[3]")).getText());
				if(qty>0){
					
					driver.findElement(By.xpath("//table/tbody[@id='productList']/tr[1]/td[5]")).click();
					Thread.sleep(1000);
					
					Select dropdown = new Select(driver.findElement(By.xpath("//tbody[@id='productList']/tr/td[6]/div/div/select")));
					//System.out.println(dropdown);
					dropdown.selectByValue("1");
					//dropdown.selectByVisibleText("1");
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);");
					driver.findElement(By.id("productContinue")).click();
					step();}
				else{
					driver.findElement(By.xpath("//li[@id='orderMenu']/a")).click();
					t++;
					o++;
					step();
				//	p++;
				}
				
				
			}
			
			
	//}
		
	public void trackingStep() throws Exception{
		//System.out.println("trackingStep");
		driver.findElement(By.xpath("//ul[@id='trackingList']/li/a")).click();
		step();
	}
	public void uploadStep() throws Exception{
		System.out.println("uploadStep");
		
		driver.findElement(By.id("uploadContinue")).click();
        step();
		/*driver.findElement(By.xpath("//div[@id='uploadStep']/div[2]/nav/ul/li[2]/a")).click();
		uploadFile ("C:\\111.jpg");*/
		//driver.findElement(By.id("uploadContinue")).click();
	    //driver.findElement(By.xpath("//div[@id='uploadStep']/div[2]/nav/ul/li[2]/a")).click();
	    //driver.findElement(By.id("fileUploadAttach")).click();
	    //driver.findElement(By.id("fileUploadAttach")).sendKeys("C:\\Desert.jpg");
		//driver.findElement(By.id("uploadContinue")).click();
		
	}
	/*
	public  void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		
		
		StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public  void uploadFile(String image) throws Exception {
       
        	//Setting clipboard with file location
		
            setClipboardData(image);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            driver.findElement(By.id("uploadContinue")).click();
            step();
          }*/
	
	public void descriptionStep() throws Exception{
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		//System.out.println("descriptionStep");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Description")));
		//Reporter.log("Ticket Type -" +Topiclist);
		driver.findElement(By.id("Description")).sendKeys(Topiclist);
		driver.findElement(By.id("buttonFinish")).click();
		String Ticketno= driver.findElement(By.xpath("//form[@id='frm-create-ticket']/div/div/div[2]/h1/strong")).getText();
		Reporter.log(+i+"."+Topiclist+"-" +Ticketno);
		//Reporter.log("\n");
		System.out.println(Ticketno);
		driver.findElement(By.xpath("//nav/a/span")).click();
		//driver.findElement(By.)
		i++;
		wizard();
		
		
	}
}
