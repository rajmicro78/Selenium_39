package src.com.pack.common.pageobjects;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import src.com.pack.database.database;
public class HomePage {
	WebDriver driver;
	String companyName;
	String appURL;
	
	@FindBy(xpath="//h2[@class='site-logo']/a")								WebElement logoImage;
	@FindBy(id="txt-search") 												WebElement searchBox;
	@FindBy(xpath="//div[contains(@class,'is-search')]/button") 			WebElement searchButton;
	@FindBy(xpath="//div[contains(@class,'item-mypage')]")					WebElement mypageicon;
	@FindBy(xpath="//div[contains(@class,'item-info')]")					WebElement infocentericon;
	@FindBy(xpath="//div[contains(@class,'item-offers')]")					WebElement offericon;
	@FindBy(xpath="//div[contains(@class,'item-save')]")					WebElement wishlisticon;
	@FindBy(xpath="//div[contains(@class,'item-cart')]")					WebElement carticon;
	@FindBy(id="layoutCartValue") 											WebElement cartValue;
	@FindBy(xpath="//ul[@class='nav-categories']/li")						List<WebElement> navCategories;
	@FindBy(xpath="//div/section[contains(@class,'usp-bar')][1]")			WebElement topuspbar;
	@FindBy(id="front-page-banners")										WebElement banner;
	@FindBy(id="recommendedProductResults")									WebElement recommendedproduct;
	@FindBy(css=".block.promo-banner")										WebElement promobanner;
	@FindBy(xpath="//div[contains(@class,'instagram-feed')]")				WebElement instagramfeed;
	@FindBy(className="block")												WebElement trustpilot;
	//@FindBy(cssSelector = "//div[@class='container']/span")						WebElement newslettersign;
	@FindBy(xpath="//div[@class='content-wrapper']/section[contains(@class,'usp-bar')]")	
																			WebElement bottomuspbar;
	@FindBy(className="section-footer")										WebElement footer;
	@FindBy(xpath="//a[contains(@href, 'mypage')]")							WebElement minorderlink;
	@FindBy(id="Email") 													WebElement emailfield;
	@FindBy(id="btnCheckEmail")												WebElement chkemail;
	@FindBy(id="Password")													WebElement passwordfield;
	@FindBy(id="LoginButton")												WebElement loginBtn;
	@FindBy(xpath="//a[contains(@id,'top_nav_logo')]")						WebElement topLogo;
	@FindBy(xpath="//a[contains(@href, '#loginPopup-modal')]")				WebElement NHLoginPopUP; 
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		companyName = logoImage.getAttribute("title");
		System.out.println(companyName);
	}
	public void Login(String uemail, String upassword) throws Exception{
		Thread.sleep(1000);
		topLogo.click();
		Thread.sleep(3000);
		if(companyName.equals("Netthandelen.no")){
			NHLoginPopUP.click();
	//	Thread.sleep(1000);
			emailfield.clear();
			emailfield.sendKeys(uemail);
		//	Thread.sleep(1000);
			passwordfield.sendKeys(upassword);
			loginBtn.click();
		Thread.sleep(4000);	
		}else{
		minorderlink.click();
	//	Thread.sleep(1000);
		emailfield.clear();
	//	Thread.sleep(1000);
		emailfield.sendKeys(uemail);
		chkemail.click();
	//	Thread.sleep(1000);
		passwordfield.sendKeys(upassword);
		loginBtn.click();
		Thread.sleep(1000);	
		}
	}
	public String verifyCompanyLogo(){
		
		//Reporter.log("Company Name-" +companyName);
		return companyName;		
	}
	public boolean verifySearchBoxPresent(){
		boolean searchtextBox = searchBox.isDisplayed();
		//Reporter.log("Search Box Present");
		return  searchtextBox;
	}
	public boolean verifySearchBoxbutton(){
		boolean searchButtonExist = searchButton.isDisplayed();
		return  searchButtonExist;
	}
	public boolean verifymypageicon(){
		boolean mypageIcon = mypageicon.isDisplayed();
		return  mypageIcon;
	}
	public boolean verifyinfoicon(){
		boolean infocenterIcon = infocentericon.isDisplayed();
		return  infocenterIcon;
	}
	public boolean verifyoffericon(){
		boolean offerIcon = offericon.isDisplayed();
		return  offerIcon;
	}
	public boolean verifywishlisticon(){
		boolean wishlistIcon = wishlisticon.isDisplayed();
		return  wishlistIcon;
	}
	public boolean verifycarticon(){
		boolean cartIcon = carticon.isDisplayed();
		return  cartIcon;
	}
	public boolean topuspbar(){
		boolean topusp = topuspbar.isDisplayed();
		return  topusp;
	}
	public boolean banner(){
		boolean banners = banner.isDisplayed();
		return  banners;
	}
	public boolean recommendedproduct(){
		boolean recommendedproducts = recommendedproduct.isDisplayed();
		return  recommendedproducts;
	}
	public boolean promobanner(){
		boolean promobanners = promobanner.isDisplayed();
		return  promobanners;
	}
	public boolean instagramfeed(){
		boolean instagramfeeds = instagramfeed.isDisplayed();
		return  instagramfeeds;
	}
	public boolean trustpilot(){
		boolean trustpilots = trustpilot.isDisplayed();
		return  trustpilots;
	}
	public boolean newsletter(){
		boolean newsletters = driver.findElement(By.id("newsletter-signup")).isDisplayed();
		return  newsletters;
	}
	public boolean bottomuspbar(){
		boolean bottomuspbars = bottomuspbar.isDisplayed();
		return  bottomuspbars;
	}
	public boolean footer(){
		boolean footers = footer.isDisplayed();
		return  footers;
	}
	public String verifyCartValue(){
		String cartAmount = cartValue.getText();
		Reporter.log("Current Cart Value-" +cartAmount);
		return cartAmount;		
	}
	public void listMenuName(){
		int noOfNavCat = navCategories.size();
		//List<WebElement> menulist = driver.findElements(navCategories);
		for (int i=0;i<=noOfNavCat-1;i++){
			//menulist.get(1).getText();
			Reporter.log("Menu Item-" +i+ "-"+navCategories.get(i).getText());
			System.out.println(navCategories.get(i).getText());}
	}
	
	public void clickmenu2(){
		int noOfNavCat = navCategories.size();
		//List<WebElement> menulist = driver.findElements(navCategories);
		//for (int i=0;i<=noOfNavCat-2;i++){
			//menulist.get(1).getText();
			navCategories.get(2).click();
			String totalproduct = driver.findElement(By.className("pager-top-max")).getText();
			Reporter.log("Menu Item-" +2+ "-"+navCategories.get(2).getText()+"-Total Product-"+totalproduct);
		//}
	}
	public void databasev(String env, String URL,String site,String uemail) throws ClassNotFoundException, SQLException{
		System.out.println("Into database");
		database db1 = new database(driver);
		if(!companyName.equals("Netthandelen.no")){
		db1.customeremail(env, URL,site,uemail);}else{
			db1.smsmessage(env, URL);
		}
		//System.out.println(db1.customeremail());
	}
	public TicketCreation goticket(){
		return new TicketCreation(driver);
	}
	
	public void getdatafromexcel() throws Exception{
		System.out.println("Into read data from excel");
		String[] val = LivePage.readExcel();
		System.out.println(val.length);
		for(int j=0;j<=val.length-1;j++){
			System.out.println("Into Home Page Read -"+val[j]);
		}
		
	}
	public ProductListPage listMenu(){
		return new ProductListPage(driver);
	}
	public registration newregister(){
		return new registration(driver);
	}
	public SearchPage search(){
		return new SearchPage(driver);
	}
	public GiftCertificate giftCertificate(){
		return new GiftCertificate(driver);
	}
}
