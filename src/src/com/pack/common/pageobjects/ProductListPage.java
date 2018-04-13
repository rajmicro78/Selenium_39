package src.com.pack.common.pageobjects;
import java.util.Calendar;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;



public class ProductListPage {
	private WebDriver driver;
	private By mainMenu = By.xpath("//div[@class='category-level-1']/ul/li[2]/a");
	private By submainMenu = By.xpath("//div[@class='category-level-2']/div[2]/div[2]/ul/li[1]/a");
	//private By searchBox = By.id("input-text-search");
	//new changes 1703
	//private By searchBox = By.className("input-text-search");
	//new changes 1801
	private By searchBox = By.id("txt-search");
	private By firstProduct = By.xpath("//div[@class='group-item']/div/div/div[@class='product-image']/a");
	private String menutype;
	private By addtocart = By.id("addToCartButton");
	private By navCategories = By.xpath("//ul[@class='nav-categories']/li");
	private By continuebrow = By.id("continueBrowsing");
	private By cartvalue = By.id("layoutCartValue");
	
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private By prdCount =By.id("layoutCartProductCount");
	public String companyName;
	public ProductListPage(WebDriver driver) {
		this.driver=driver;
		companyName = driver.findElement(logoImage).getAttribute("title");
	}
	public void clickMenu() throws Exception{
		/*
		Actions builder = new Actions(driver);
		WebElement moveonmenu = driver.findElement(mainMenu);
		builder.moveToElement(moveonmenu).click().perform();
		WebElement menuoption = driver.findElement(submainMenu);
		menuoption.click();
		*//*
		if (companyName.equals("BliVakker.no")){
		Thread.sleep(8000);
		
		boolean pop1703 = driver.findElements( By.xpath("//div[@class='pushcrew-button-wrapper']/button") ).size() != 0;
		if(pop1703){
			driver.findElement( By.xpath("//div[@class='pushcrew-button-wrapper']/button")).click();
		}
		//driver.findElement(logoImage).click();
		Thread.sleep(2000);
		}*/
		Reporter.log("Navigate to HomePage");
		// New changes 1708 
		menuselect();
		/*
		driver.findElement(mainMenu).click();
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='select2-SortValue-container']")));
		//driver.findElement(submainMenu).click();
		driver.findElement(searchBox).click();*/
	}
	public void verifySubMenu() throws Exception{
		//Thread.sleep(2000);
		clickMenu() ;
		driver.findElement(logoImage).click();
		//Thread.sleep(2000);
		int noOfNavCat = driver.findElements(navCategories).size();
	 	for (int j=1;j<=noOfNavCat-1;j++){
			menutype = driver.findElement(By.xpath("//ul[@class='nav-categories']/li["+j+"]")).getAttribute("class");
			Reporter.log("-Menu -"+driver.findElement(By.xpath("//ul[@class='nav-categories']/li["+j+"]/a")).getText());
			//System.out.println(driver.findElement(By.xpath("//ul[@class='nav-categories']/li["+j+"]/a")).getText());
			driver.findElement(By.xpath("//ul[@class='nav-categories']/li["+j+"]/a")).click();	
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='select2-SortValue-container']")));
			driver.findElement(searchBox).click();
			Thread.sleep(1000);
			verifySubSubMenu();
			veriftySortingOption();
			verifySubMenuAttribute();
			}
	}
	public void verifySubSubMenu(){
		Reporter.log("---Check Sub Menu Name---");  
		if(menutype.equals("cat-nav-item is-parent expandMenuItem")){
			int noofSubSubItem =driver.findElements(By.xpath("//nav[@role='navigation']/ul/li[@class='is-active']/ul/li")).size();
			//Reporter.log("Current Cart Value-" +cartAmount);
			//System.out.println(noofSubSubItem);
			for(int l=1;l<=noofSubSubItem;l++){
				String submenuname= driver.findElement(By.xpath("//nav[@role='navigation']/ul/li[@class='is-active']/ul/li["+l+"]/a")).getText();
				Reporter.log(+l+"-" +submenuname);
			}
		}else{
			Reporter.log("No sub menu presen" );}
			//System.out.println("No sub menu present");}
	}
	public void verifySubMenuAttribute(){
		Reporter.log("---Check Menu Attribute---"); 
		//System.out.println("Check Menu Attribute");
		int noofAttribute = driver.findElements(By.xpath("//div[@id='facets-container']/div")).size();
		for(int m=1;m<=noofAttribute;m++){
			String mAttribute = driver.findElement(By.xpath("//div[@id='facets-container']/div["+m+"]/h3/a")).getText();
			Reporter.log(+m+"-" +mAttribute); 
		}
	}
	public void veriftySortingOption(){

		 
		int noofSorting = driver.findElements(By.xpath("//select[@id='SortValue']/option")).size();
		//System.out.println(noofSorting);
		Reporter.log("Total Sorting option - " + noofSorting);
		/*for (int n=1;n>=noofSorting;n++){
			Reporter.log(driver.findElement(By.xpath("//select[@id='SortValue']/option["+n+"]")).getAttribute("value"));
			System.out.println(driver.findElement(By.xpath("//select[@id='SortValue']/option["+n+"]")).getAttribute("value"));
		}*/
	}
	public ProductDetailPage clickproduct() throws Exception {
		return new ProductDetailPage(driver);
	}
	
	public void menuselect() throws Exception{
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(By.xpath("//li[12]/a/span")).click();
			Reporter.log("Click on fixed price link");
			Thread.sleep(2000);
		}
		
		int noOfNavCat = driver.findElements(navCategories).size();
		noOfNavCat=noOfNavCat-1;
		System.out.println("Navigation Category-" +noOfNavCat );
		List<WebElement> menulist = driver.findElements(navCategories);
		Calendar cals = Calendar.getInstance();
		int currentminutes = cals.get(Calendar.MINUTE);
		System.out.println("current minute-"+currentminutes);
		if(currentminutes>1&&currentminutes<noOfNavCat){
		currentminutes=currentminutes/2;System.out.println("Divide by 2");}
		if(currentminutes>10&&currentminutes<30){
		currentminutes=currentminutes/4;System.out.println("Divide by 4");}
		if(currentminutes>30&&currentminutes<50){
		currentminutes=currentminutes/5;System.out.println("Divide by 5");}
		if(currentminutes>50&&currentminutes<59){
		currentminutes=currentminutes/6;System.out.println("Divide by 6");}
		System.out.println("After calculation-"+currentminutes);
		if(currentminutes>=noOfNavCat){
		noOfNavCat=noOfNavCat/2;	
		Reporter.log("Clicked on Menu-"+noOfNavCat+"-"+menulist.get(noOfNavCat).getText());
		menulist.get(noOfNavCat).click();
			
		System.out.println("click on menu-"+noOfNavCat);
		}
		else{
			Reporter.log("Clicked on Menu-"+currentminutes+"-"+menulist.get(currentminutes).getText());
			menulist.get(currentminutes).click();
			
			System.out.println("click on menu-"+currentminutes);
		}
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='product-list-container']/div")));
		Thread.sleep(1000);
		if(companyName.equals("Netthandelen.no")){
			driver.findElement(By.id("input-text-search")).click();
		}else{
		driver.findElement(searchBox).click();}
		
		if(companyName.equals("Netthandelen.no")){
			clickFirstProduct();
		}else{
			clickFirstProductnew();}
	}
	public void clickFirstProduct() throws Exception{
		Thread.sleep(2000);
		System.out.println("Now click product");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='product-list-container']/div")));
		// - New change on product selection
		int tprodlist = driver.findElements(By.xpath("//div[@id='product-list-container']/div")).size();
		System.out.println("Number of product -" +tprodlist);
		Calendar cal = Calendar.getInstance();
		int currentminute = cal.get(Calendar.SECOND);
		if(currentminute<=2){
			System.out.println("second less than 2 -" +currentminute);
			currentminute =currentminute+3;
		}
		currentminute =currentminute/2;
		System.out.println("Current second" +currentminute);	
		//WebDriverWait wait = new WebDriverWait(driver, 15);
		if(currentminute>tprodlist){
			int prodlist =tprodlist/2;
			System.out.println("click product-" +prodlist );
			String buybtn;
			if(companyName.equals("Netthandelen.no")){
				buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[4]/a")).getAttribute("class");	
			}else{
			 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[5]/a")).getAttribute("class");}
				System.out.println(buybtn);
			//button sold-out-button grey-outline
			//button standard-outline
			while(!buybtn.equals("button")){
				prodlist=prodlist+1;
				if(companyName.equals("Netthandelen.no")){
					buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[4]/a")).getAttribute("class");	
				}else{
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[3]/div/div[2]/a")).getAttribute("class");}
				System.out.println("currently out of stock picking next one-"+ prodlist+"-button text-" +buybtn);
				Thread.sleep(1000);
			} 
			driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div/div[@class='product-image']/a")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(addtocart));
			Thread.sleep(2000);
			}else{	
			System.out.println("click on product-" +currentminute );
			String buybtn="";
			if(companyName.equals("Netthandelen.no")){
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[4]/a")).getAttribute("class");	
			}else{
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[5]/a")).getAttribute("class");}
			while(!buybtn.equals("button")){
				currentminute=currentminute+1;
				if(companyName.equals("Netthandelen.no")){
					 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[4]/a")).getAttribute("class");	
				}else{
					 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[5]/a")).getAttribute("class");}
				System.out.println("currently out of stock picking next one-"+ currentminute+"-button text-" +buybtn);
				Thread.sleep(1000);
			} 
			/*
			if(buybtn.contains("sold-out-button")){
				System.out.println("currently out of stock picking next one");
				currentminute=currentminute+1;
			}*/
			System.out.println("click on product image "+currentminute);
			//driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div/div/a")).click();
			driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div/div[@class='product-image']/a")).click();
		//driver.findElement(firstProduct).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(addtocart));}
	}
	
	public void clickFirstProductnew() throws Exception{
		//changes as per 1801
		Thread.sleep(3000);
		System.out.println("Now click product");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='product-list-container']/div")));
		// - New change on product selection
		int tprodlist = driver.findElements(By.xpath("//div[@id='product-list-container']/div")).size();
		System.out.println("Number of product -" +tprodlist);
		Calendar cal = Calendar.getInstance();
		int currentminute = cal.get(Calendar.SECOND);
		if(currentminute<=2){
			System.out.println("second less than 2 -" +currentminute);
			currentminute =currentminute+3;
		}
		currentminute =currentminute/2;
		System.out.println("Current second" +currentminute);	
		//WebDriverWait wait = new WebDriverWait(driver, 15);
		//15-2-18-newly added if total product is less than 2
		if(tprodlist<=2){
			Reporter.log("Product Location-" +tprodlist+"- Product Name -"+driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+tprodlist+"]/div/a")).getAttribute("title"));
			WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+tprodlist+"]/div/a"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			//driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+tprodlist+"]/div/a")).click();
		}else{
		if(currentminute>tprodlist){
			int prodlist =tprodlist/2;
			System.out.println("click product-" +prodlist );
			String buybtn;
			if(companyName.equals("Netthandelen.no")){
				buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[4]/a")).getAttribute("class");	
			}else{
			 buybtn= driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+prodlist+"]/div/div[2]/div/div[2]/a")).getAttribute("class");}
				System.out.println(buybtn);
			//button sold-out-button grey-outline
			//button standard-outline
			while(!buybtn.equals("button standard-outline")){
				prodlist=prodlist+1;
				if(prodlist==29){
					System.out.println("Trying to pick 29th product subtracting 10");
					Reporter.log("Trying to pick 29th product subtracting 10");
					prodlist=prodlist-15;
				}
				if(companyName.equals("Netthandelen.no")){
					buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+prodlist+"]/div/div[2]/div[4]/a")).getAttribute("class");	
				}else{
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+prodlist+"]/div/div[2]/div/div[2]/a")).getAttribute("class");}
				System.out.println("currently out of stock picking next one-"+ prodlist+"-button text-" +buybtn);
				Thread.sleep(1000);
			} 
			Reporter.log("Product Location-" +prodlist+"- Product Name -"+driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+prodlist+"]/div/a")).getAttribute("title"));
			
			WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+prodlist+"]/div/a"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			//above is the changes for below click on add to product
	//		driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+prodlist+"]/div/a")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(addtocart));
			Thread.sleep(2000);
			}else{	
			System.out.println("click on product-" +currentminute );
			String buybtn="";
			if(companyName.equals("Netthandelen.no")){
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[4]/a")).getAttribute("class");	
			}else{
				 buybtn= driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/div[2]/div/div[2]/a")).getAttribute("class");
			System.out.println(buybtn);	 
			}
			while(!buybtn.equals("button standard-outline")){
				currentminute=currentminute+1;
				if(currentminute==29){
					System.out.println("Min Trying to pick 29th product subtracting 10");
					Reporter.log("Min Trying to pick 29th product subtracting 10");
					currentminute=currentminute-15;
				}
				if(companyName.equals("Netthandelen.no")){
					 buybtn= driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div[2]/div[4]/a")).getAttribute("class");	
				}else{
					 buybtn= driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/div[2]/div/div[2]/a")).getAttribute("class");}
				System.out.println("currently out of stock picking next one-"+ currentminute+"-button text-" +buybtn);
				Thread.sleep(1000);
			} 
			/*
			if(buybtn.contains("sold-out-button")){
				System.out.println("currently out of stock picking next one");
				currentminute=currentminute+1;
			}*/
			System.out.println("click on product images "+currentminute);
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/a")));
			//Thread.sleep(2000);
			//driver.findElement(By.xpath("//div[@class='group-item']["+currentminute+"]/div/div/div/a")).click();
			Thread.sleep(1000);
			Reporter.log("Product Location-" +currentminute+"- Product Name -"+driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/a")).getAttribute("title"));	
			WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/a"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			}
			//driver.findElement(By.xpath("//div[@class='group-item product-list-item']["+currentminute+"]/div/a")).click();
			
			
			
		//driver.findElement(firstProduct).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(addtocart));
			}
	}
	public void addmultipleProduct() throws Exception{
		for(int i =1;i<=2;){
			//Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			driver.findElement(By.xpath("//div[@class='group-item']["+i+"]/div/div/div[@class='product-image']/a")).click();
			//Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(addtocart));
			driver.findElement(addtocart).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(continuebrow));
			//Thread.sleep(2000);
			driver.findElement(continuebrow).click();
			i++;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='group-item']["+i+"]/div/div/div[@class='product-image']/a")));
			//Thread.sleep(2000);
			System.out.println("Successfully Added" +i+"Product in cart");
		}
//		driver.findElement(continuebrow).click();
		//Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		driver.findElement(searchBox).click();
		clickFirstProduct();
	}
	public void cartvalue() throws Exception{
		//driver.findElement(prdCount).click();
		String cartamt = driver.findElement(cartvalue).getText();
		System.out.println("cart value- "+cartamt);
		int cartamount = Integer.parseInt(cartamt.replaceAll("[^0-9]",""));
		System.out.println("cart value- "+cartamount);
	}
	
	public void checkimage() throws Exception{
		int totalproduct = driver.findElements(By.xpath("//div[@id='product-list-container']/div")).size();
		for(int i=1;i<=totalproduct;i++){
			String imgurl= driver.findElement(By.xpath("//div[@id='product-list-container']/div["+i+"]/div/a/div/div/img")).getAttribute("src");
			Assert.assertTrue(imgurl!=null);
			System.out.println(imgurl);
			System.out.println(companyName);
			Assert.assertTrue(imgurl.contains(companyName.toLowerCase()));
			Reporter.log("All image is in domain "+companyName);
		}
	}
	
		
}
