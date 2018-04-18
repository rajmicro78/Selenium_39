package src.com.pack.base;


//import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.File;
//import java.text.SimpleDateFormat;

import java.util.HashMap;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.logging.Level;

import org.apache.log4j.BasicConfigurator;

//import java.awt.event.KeyEvent;



//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
//import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;




public class TestBaseSetup  {

	private static  WebDriver driver;
	private static String downloadFilepath = "TestDownloads";
//	private Calendar cal;
	//private SimpleDateFormat sdf;
	public String appURL;
	public String nasenv;
	public String NASURL;
	public String PDAShop;
	public String pdaenv;
	public String liveurl;
	BufferedWriter writer = null;
	String currentURL = null;
	
	
	
	public static  WebDriver getDriver() {
		return driver;
		
	}
	private void setDriver(String browserType, String appURL) {
	//	cal = Calendar.getInstance();
      //  sdf = new SimpleDateFormat("HH:mm:ss");
		//BasicConfigurator.configure();
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "ie":
			driver = initinternetexplorer(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initinternetexplorer(String appURL) {
		//Reporter.log("Tested Firefox Browser"); 
		System.out.println("Launching Internet explorer browser..");
		String basePath = new File("").getAbsolutePath();
		     System.out.println("Basep"+basePath);
		String path = new File("Drivers/IEDriverServer.exe").getAbsolutePath();
		System.setProperty("webdriver.ie.driver",path);
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.navigate().to(appURL);
		Reporter.log("URL- "+appURL); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		boolean popup = driver.findElements( By.xpath("//div[@id='modal-newsletterpopup']/button") ).size() != 0;
		boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a") ).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(popup){
			driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();
		}
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a")).click();
		}
		//if (appURL.contains(":7011")){
			//driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();}
		
		
		/*
		if(!(appURL.contains(":7009")||appURL.contains(":7008"))){
		driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();	
		}
		if (appURL.contains(":7011")||appURL.contains(":7012")||appURL.contains(":7010")){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();}
		*/
		return driver;
	}
	
	
	
	
	private static WebDriver initChromeDriver(String appURL) {
		 
		String basePath = new File("").getAbsolutePath();
		 System.out.println("Basep"+basePath);
	     String path = new File("Drivers/chromedriver.exe").getAbsolutePath();
	     System.out.println("Absp"+path);
	  
	  System.setProperty("webdriver.chrome.driver", path);
		
		
	  /*WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.navigate().to(appURL);
		Reporter.log("URL- "+appURL); */
	  HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	  chromePrefs.put("profile.default_content_settings.popups", 0);
	  chromePrefs.put("download.default_directory", basePath+"\\"+downloadFilepath);
	  ChromeOptions options = new ChromeOptions();
	  options.setExperimentalOption("prefs", chromePrefs);
	  DesiredCapabilities cap = DesiredCapabilities.chrome();
	  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	  cap.setCapability(ChromeOptions.CAPABILITY, options);
	  @SuppressWarnings("deprecation")
	  WebDriver driver = new ChromeDriver(cap);
	  System.out.println(appURL);
	  driver.navigate().to(appURL);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean popup = driver.findElements( By.xpath("//div[@id='modal-newsletterpopup']/button") ).size() != 0;
		boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img") ).size() != 0;
		
		if(popup){
			driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();
		}
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();
		}
		
		
		/*
		if(!appURL.contains(":7009")){
			driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();	
			}
		if (appURL.contains(":7011")||appURL.contains(":7012")||appURL.contains(":7010")){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();}
		*/
		//driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();
		
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		//Reporter.log("Tested Firefox Browser"); 
		System.out.println("Launching Firefox browser..");
		String basePath = new File("").getAbsolutePath();
	    System.out.println("firefox bast path-"+basePath);
        String path = new File("Drivers/geckodriver.exe").getAbsolutePath();
	    System.out.println(path);
	  
	    System.setProperty("webdriver.gecko.driver", path);
		//WebDriver driver = new FirefoxDriver();
	   
	    FirefoxProfile firefoxProfile=new FirefoxProfile();
	   
	   // firefoxProfile.setPreference("browser.download.folderList",2);
	    //firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
	    //firefoxProfile.setPreference("browser.download.dir",basePath+"\\"+downloadFilepath);
	    //firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
	  
	     firefoxProfile.setPreference("http.response.timeout", 15);
	   // firefoxProfile.setPreference("dom.max_script_run_time", 15);
	    FirefoxOptions options=new FirefoxOptions();
	   
	   
	    options.setProfile(firefoxProfile);
	    
	    LoggingPreferences logs = new LoggingPreferences();
	    logs.enable(LogType.BROWSER, Level.OFF);
	    logs.enable(LogType.PERFORMANCE, Level.OFF);
	    logs.enable(LogType.SERVER, Level.OFF);
	    logs.enable(LogType.DRIVER, Level.OFF);
	    logs.enable(LogType.PROFILER, Level.OFF);
	    logs.enable(LogType.CLIENT, Level.OFF);
	  //System.out.println(logs.getEnabledLogTypes());
	    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
	    capabilities.setCapability("marionette", true);
	    
	    
	    
	    
	    
	    WebDriver driver=new FirefoxDriver(capabilities);
	    
	    driver.navigate().to(appURL);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		
		driver.manage().window().maximize();
		
		
		Reporter.log("URL- "+appURL); 
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean popup = driver.findElements( By.xpath("//div[@id='modal-newsletterpopup']/button") ).size() != 0;
		boolean cookie = driver.findElements(By.xpath("//div[@id='cookieInfoBannerControls']/p/a") ).size() != 0;
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(popup){
			driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();
		}
		if(cookie){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a")).click();
		}
		//if (appURL.contains(":7011")){
			//driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();}
		
		
		/*
		if(!(appURL.contains(":7009")||appURL.contains(":7008"))){
		driver.findElement(By.xpath("//div[@id='modal-newsletterpopup']/button")).click();	
		}
		if (appURL.contains(":7011")||appURL.contains(":7012")||appURL.contains(":7010")){
			driver.findElement(By.xpath("//div[@id='cookieInfoBannerControls']/p/a/img")).click();}
		*/
		return driver;
	}

	
	@Parameters({ "browserType", "sitename","env", "nasuser", "naspassword" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String sitename, String env, String nasuser, String naspassword) {
		try {
			if(env.equals("Live")){
				switch(sitename){
				case "NH" :
					appURL ="https://netthandelen.no";
					break;
				case "BV" :
					appURL ="https://blivakker.no";
					break;
				case "DL" :
					appURL= "InGarden.no";
					break;
				case "CPDK":
					appURL ="https://cocopanda.dk";
					break;
				case "CPSE" :
					appURL ="https://cocopanda.se";
					break;
				case "CPFI":
					appURL ="https://cocopanda.fi";
					break;
				case"BON" :
					appURL ="https://brandsdal.no";
					break;		
				case "CPDE" :
						appURL ="https://cocopanda.de";
					break;	
				case "CPAT" :
					appURL ="https://cocopanda.at";
					break;	
				}
			}else{
				if(env.equals("Releasetest")){
				env ="https://releasetest.netthandelen.no:";
				nasenv="http://"+nasuser+":"+naspassword+"@"+"releasetest.netthandelen.no:";
				pdaenv="http://releasetest.netthandelen.no:6101/Login.aspx?ReturnUrl=%2f";
			}else if(env.equals("Test") || (env.equals("Live") )){
				env = "https://test.netthandelen.no:";
				nasenv="http://"+nasuser+":"+naspassword+"@"+"test.netthandelen.no:";
				pdaenv="http://test.netthandelen.no:6101/Login.aspx?ReturnUrl=%2f";
			}else if(env.equals("Devbranch") ){
				env = "https://devbranch.netthandelen.no:";
			}
			
			switch(sitename){
			case "NH" :
				appURL = env+ "7001";
				NASURL= nasenv+ "6100/1/";
				PDAShop= "Netthandelen";
				liveurl ="https://netthandelen.no";
				break;
			case "BV" :
				appURL = env+ "7002";
				NASURL= nasenv+ "6100/2/";
				PDAShop= "Blivakker";
				liveurl ="https://blivakker.no";
				break;
			
			case "DL" :
				appURL = env+ "7005";
				NASURL= nasenv+ "6100/5/";
				PDAShop= "InGarden.no";
				break;
			case "CPDK":
				appURL = env+ "7004";
				NASURL= nasenv+ "6100/4/";
				PDAShop= "Cocopanda DK";
				liveurl ="https://cocopanda.dk";
				break;
			case "CPSE" :
				appURL = env+ "7007";
				NASURL= nasenv+ "6100/7/";
				PDAShop= "Cocopanda SE";
				liveurl ="https://cocopanda.se";
				break;
			case "CPFI":
				appURL = env+ "7008";
				NASURL=	nasenv+ "6100/8/";
				PDAShop= "Cocopanda FI";
				liveurl ="https://cocopanda.fi";
				break;
			case"BON" :
				appURL = env+ "7009";
				NASURL= nasenv+ "6100/9/";
				PDAShop= "Brandsdal Of Norway";
				liveurl ="https://brandsdal.no";
				break;		
			case "CPDE" :
				appURL = env+ "7010";
				NASURL= nasenv+ "6100/10/";
				PDAShop= "Cocopanda DE";
				liveurl ="https://cocopanda.de";
				break;	
			case "CPAT" :
				appURL = env+ "7011";
				NASURL= nasenv+ "6100/11/";
				PDAShop= "Cocopanda AT";
				liveurl ="https://cocopanda.at";
				break;	
			}
			}
			setDriver(browserType, appURL);
			System.out.println("appURL-"+appURL);
			
			
		} catch (Exception e) {
			System.out.println("Check Error....." + e.getStackTrace());
			
		}
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	
		//Reporter.log("End Time - " +sdf.format(cal.getTime()));
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		
	if (!result.isSuccess())
	//catchExceptions(result);
	// Quit environment.
	Thread.sleep(1000);
	//driver.quit();
	}
	
	
	/*
	
	public void catchExceptions(ITestResult result) {
		//boolean popup1 = driver.findElements( By.xpath("//div[@class='vex vex-theme-default']/div[2]/div[2]") ).size() != 0;
			//System.out.println("popup appear- " +popup1);
			//if(popup1){
			//driver.findElement(By.xpath("//div[@class='vex vex-theme-default']/div[2]/div[2]")).click();}
		
	String methodName = result.getName();
		
	
	System.setProperty("org.uncommons.reportng.escape-output", "false");
	
	if (!result.isSuccess()){
	try {
	String failureImageFileName = "sample1";
	String failureImageFileName1;
	
	currentURL = driver.getCurrentUrl();
	System.out.println("Failure URL-" +currentURL);
	Reporter.log("Failure URL-" +currentURL);
	
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
	
	final BufferedImage image = ImageIO.read(scrFile);
	Graphics g = image.getGraphics();
    g.setFont(g.getFont().deriveFont(20f));
    g.setColor(Color.RED);
    g.drawString(currentURL, 20, 200);
    g.drawString(methodName, 60, 500);
    g.dispose();
    Calendar cal = Calendar.getInstance();
	int currentminute = cal.get(Calendar.SECOND);
    String img = "rajerrortest"+currentminute +".png";
    System.out.println(img);
    Reporter.log("Failure Image-" +img);
    ImageIO.write(image, "png", new File(img));
	
	FileUtils.copyFile(scrFile, new File(failureImageFileName));
	String userDirector = System.getProperty("user.dir") + "\\";
	String s1 = "<table><tr><td><font style=\"text-decoration: underline;\" size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr> ";
	Reporter.log(s1);
	Reporter.log("<tr><td><a href=\""+ userDirector + failureImageFileName +"\"><img src=\"file:///" + userDirector+ failureImageFileName + "\" alt=\"\""+ "height='120' width='120'/></td></tr> ");
	String s2 = "<tr style=\"height:20px\"><td></td></tr>";
	Reporter.log(s2);
	String s3 = "<tr><td><font style=\"text-decoration: underline;\" size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Webpage</b></font></td></tr> ";
	Reporter.log(s3);
	failureImageFileName1 = userDirector + "sample2";
	scrFile = new File(failureImageFileName1);
	writer = new BufferedWriter(new FileWriter(scrFile));
	writer.write(driver.getPageSource());
	writer.close();
	Reporter.log("<tr><td><a href=\""+ scrFile +"\"><img src=\"file:///" + scrFile + "\" alt=\"\""+ "height='120' width='120'/></td></tr></table> "+"<br />");
	Reporter.setCurrentTestResult(null);
	} catch (IOException e1) {
	e1.printStackTrace();
	}
	}
	}*/
}
