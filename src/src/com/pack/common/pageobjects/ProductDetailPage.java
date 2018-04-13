package src.com.pack.common.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


public class ProductDetailPage {
	private By prdNumber = By.id("product-nr");
	@FindBy(id="description") 												WebElement pdpdescription;
	@FindBy(id="pdp-images") 												WebElement pdpImage;
	@FindBy(id="button-write-review") 										WebElement pdpreviewbtn;
	@FindBy(id="final-sales-price") 										WebElement pdpsaleprice;
	@FindBy(id="ddlQuantityOptions") 										WebElement pdpQtydrpdwn;
	@FindBy(id="recommendedProductResults") 								WebElement pdprecommended;
	@FindBy(id="addToCartButton") 											WebElement pdpaddtocart;
	
	private By prdName = By.id("product-title");
	private By prdFinalPrice = By.className("price-final");
	//private By addtocart = By.id("addToCartButton");
	private By qtydropdown =By.xpath("//select[@id='ddlQuantityOptions')]/option");
	private By wishlist = By.id("addToWishlistButton");
	private By ratings = By.xpath("//div[@id='product-ratings-summary']");
	private By attribute =By.xpath("//div[@id='attributesOptionsWrapper']/div[2]/div/div/label/div/ins");
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private WebDriver driver;
	public String companyName;
	
	public ProductDetailPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		companyName = driver.findElement(logoImage).getAttribute("title");
	}
	
	public String productnumber(){
		String productNumber = driver.findElement(prdNumber).getText();
		Reporter.log("Product Number-" +productNumber);
		System.out.println(productNumber);
		return productNumber;
	}
	
	public void productDescription(){
		 Assert.assertTrue(pdpdescription.isDisplayed());
		 Reporter.log("Description Displaying");
	}
	public void productQtydropdown(){
		 Assert.assertTrue(pdpQtydrpdwn.isDisplayed());
		 Reporter.log("Qty dropdown Displaying");
	}
	public void productrecommended(){
		Assert.assertTrue(pdprecommended.isDisplayed());
		 Reporter.log("Recommended section Displaying");
	}
	public void productaddtocartbtn(){
		Assert.assertTrue(pdpaddtocart.isDisplayed());
		 Reporter.log("Add to cart  button Displaying");
	}
	
	public void productReviewbtn(){
		 Assert.assertTrue(pdpreviewbtn.isDisplayed());
		 Reporter.log("Review button Displaying");
	}
	
	public void productSaleprice(){
		 Assert.assertTrue(pdpreviewbtn.isDisplayed());
		 Reporter.log("Sale Price Displaying");
		
	}
	
	public void productImage(){
		 Assert.assertTrue(pdpImage.isDisplayed());
		 Reporter.log("Product Image Displaying");
		
	}
	
	public String productName(){
		String ProductName = driver.findElement(prdName).getText();
		Reporter.log("Product Name-" +ProductName);
		System.out.println(ProductName);
		return ProductName;
	}
	
	public int finalPrice(){
		String Price = driver.findElement(prdFinalPrice).getText();
		int FinalPrice = Integer.parseInt(Price.replaceAll("[^0-9]", ""));
		Reporter.log("Final  Price-" +FinalPrice);
		System.out.println(FinalPrice);
		return FinalPrice;
	}
	
	public void qtyDropdown(){
		int quantity = driver.findElements(qtydropdown).size();
		Reporter.log("Qty Dropdown-"+quantity );
	}
	
	
	
	public void wishlistBtn(){
	 boolean wishbtn = driver.findElement(wishlist).isDisplayed();
	 if(wishbtn){
		 Reporter.log("Wishlist button present");
	 }else{
		 Reporter.log("Wishlist button not present");
	 }
	}
	public void productRating(){
		boolean ratin = driver.findElement(ratings).isDisplayed();
		if(ratin){
		String rating = driver.findElement(ratings).getAttribute("class");
		String finalrate= rating.replace(rating, "");
		Reporter.log("Final  Rating-" +finalrate);} 
	}
	
	public void productPrice(){
		
	}
	
	public CartPage addtoCart() throws Exception{
		Thread.sleep(2000);
		
		if (companyName.equals("Brandsdal.no")){
			//new 1706 changes for attribute on product detail page
			int attributava= driver.findElements(By.xpath("//div[@id='attributesOptionsWrapper']/div")).size();
			System.out.println("available attribute"+attributava);
			if(attributava>1){
				int totalsize = driver.findElements(By.xpath("//div[@id='attributesOptionsWrapper']/div[2]/div/div")).size();
				System.out.println("total second attribute" +totalsize);
				for(int i=1;i<=totalsize-1;i++){	
				String attrava = driver.findElement(By.xpath("//div[@id='attributesOptionsWrapper']/div[2]/div/div["+i+"]")).getAttribute("class");
				if(attrava.equals("radio-container")){
					driver.findElement(By.xpath("//div[@id='attributesOptionsWrapper']/div[2]/div/div["+i+"]/label/div/ins")).click();
				}
			}
			}
		}
		int qtydrop= driver.findElements(By.xpath("//select[@id='ddlQuantityOptions']/option")).size();
		System.out.println("Total value in dropdown-" +qtydrop);
		Reporter.log("Product Final Price- " +driver.findElement(By.id("final-sales-price")).getText());
		 boolean qtybtn = driver.findElement(By.id("ddlQuantityOptions")).isEnabled();//isdisplayed
		// System.out.println(qtybtn);
		//Select dropdown = new Select(driver.findElement(By.id("ddlQuantityOptions")));
		WebElement select = driver.findElement(By.id("ddlQuantityOptions"));
		//System.out.println("Dropdown-" +qtydrop+"-"+ dropdown);
		if (qtybtn){
		if(qtydrop>=5){
		//	System.out.println("Selected 2");
			//Thread.sleep(1000);
			System.out.println("selecte by index");
			Reporter.log("Product add to cart- 2"); 
			((JavascriptExecutor)driver).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", select, "2");
			
			//dropdown.selectByVisibleText("2");
			//dropdown.selectByValue("2");
			//Thread.sleep(1000);
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("window.scrollTo(0, 0);");
		}else{
			System.out.println("Selected 1");
			((JavascriptExecutor)driver).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", select, "1");
			Reporter.log("Product add to cart- 1");
			//dropdown.selectByValue("1");
			//Thread.sleep(1000);
		}
		}
		Thread.sleep(1000);
		pdpaddtocart.click();
		//driver.findElement(pdpaddtocart).click();
		//WebElement element =driver.findElement(addtocart);
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		return new CartPage(driver);
		
	}
}
