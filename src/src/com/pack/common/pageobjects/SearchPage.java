package src.com.pack.common.pageobjects;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class SearchPage {

	private WebDriver driver;
	private By mainMenu = By.xpath("//div[@class='category-level-1']/ul/li[2]/a");
	private By logoImage = By.xpath("//h2[@class='site-logo']/a");
	private By searchBox = By.className("input-text-search");
	private By searchButton = By.xpath("//button[contains(@class, 'button-search')]");
	private By navCategories = By.xpath("//ul[@class='nav-categories']/li");
	private By recommendedproduct = By.xpath("//div[@id='recommendedProductResults']/div/div/div[2]/div");
	private String noofrecord = "0 Result";
	
	private String alphabetical;
	private String mostavailable;
	private String mostsold;
	private String lowestprice;
	private String highestprice;
	private String newestproduct;
	private String highestrebatt;
	
	private By searchresult = By.xpath("//div[contains(@class,'search-results')]/ul/li");
	
	
	public SearchPage(WebDriver driver ) {
		this.driver=driver;	
		
		
		
	}
	
	public void productsearch() throws Exception{
		Thread.sleep(2000);
		driver.findElement(logoImage).click();
		Thread.sleep(1000);
		driver.findElement(searchBox).click();
		
		int noOfNavCat = driver.findElements(navCategories).size();
		//List<WebElement> menulist = driver.findElements(navCategories);
		for (int i=1;i<=noOfNavCat-2;i++){
			//String search = driver.findElement(mainMenu).getText();
		//int strlen = search.length();
		//System.out.println(strlen);
		String search=	driver.findElement(By.xpath("//ul[@class='nav-categories']/li["+i+"]/a")).getText();
			if(i<search.length()){
				search= search.substring(0,i);}else{search= search.substring(0,3);}
				//System.out.println(search);
		driver.findElement(searchBox).sendKeys(search);
		if(search.length()>2){
			Thread.sleep(3000);
			String autosugg = driver.findElement(searchresult).getAttribute("class");
			int productlist1 = driver.findElements(searchresult).size();
			System.out.println("productlist1-"+productlist1);
			if(productlist1<=4){
		
				if(autosugg.equals("result-item")){
					Thread.sleep(1000);
					int productlist = driver.findElements(searchresult).size();
					for(int j=1;j<=productlist;j++){
						String productn = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]/a/div[2]/span[1]")).getText();
						String productprice = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]/a/div[2]/span[2]")).getText();
						System.out.println("Product Name- " +productn+ "Product Price" +productprice);
						Thread.sleep(1000);
					}
				}else{
					Thread.sleep(2000);
					int productlist = driver.findElements(searchresult).size();
					for(int j=1;j<=productlist;j++){
						String message = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]")).getText();
						System.out.println(message);
					}
				
				}
			}else{
				System.out.println("Dropdown list is long");}
		}
		driver.findElement(searchButton).click();
			boolean searchresult = driver.findElements( By.xpath("//ul/li[2]/span[3]") ).size() != 0;
		if(searchresult){
		 noofrecord = driver.findElement(By.xpath("//ul/li[2]/span[3]")).getText();}
		else{
			noofrecord = driver.findElement(By.xpath("//div[@class='container']/h1")).getText();
			}
			Reporter.log("Search Term - "+search +"-Result-" +noofrecord);
			System.out.println("Search Term - "+search +"-Result-" +noofrecord);
		}
		
		
	}
	
	public void searchproductname() throws Exception{
		
		Thread.sleep(1000);
		int noofrecmprod = driver.findElements(recommendedproduct).size();
		
		for (int i=1;i<=noofrecmprod-5;i++){
			String search=driver.findElement(By.xpath("//div[@id='recommendedProductResults']/div/div/div[2]/div["+i+"]/div/div[2]/div/h3/a")).getText();
			
			search = search.substring(0,search.length()/2);
			
			driver.findElement(searchBox).sendKeys(search);
			
			
			Thread.sleep(3000);
			String autosugg = driver.findElement(searchresult).getAttribute("class");
			int productlist1 = driver.findElements(searchresult).size();
			System.out.println("productlist1-"+productlist1);
			if(productlist1<=4){
			
			if(autosugg.equals("result-item")){
				Thread.sleep(1000);
				int productlist = driver.findElements(searchresult).size();
					for(int j=1;j<=productlist;j++){
					String productn = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]/a/div[2]/span[1]")).getText();
					String productprice = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]/a/div[2]/span[2]")).getText();
					System.out.println("Product Name- " +productn+ "Product Price" +productprice);
					Thread.sleep(1000);
					}
				
			}else{
				Thread.sleep(2000);
				int productlist = driver.findElements(searchresult).size();
				for(int j=1;j<=productlist;j++){
					String message = driver.findElement(By.xpath("//div[@id='search-results']/ul/li["+j+"]")).getText();
					System.out.println(message);
				}
					
			}
			}else
			{
				System.out.println("Dropdown list is long");
			}
		
				
			
			driver.findElement(searchButton).click();
			Thread.sleep(1000);
			boolean searchresult = driver.findElements( By.xpath("//ul/li[2]/span[3]") ).size() != 0;
			if(searchresult){
			 noofrecord = driver.findElement(By.xpath("//ul/li[2]/span[3]")).getText();}
			else{
				noofrecord = driver.findElement(By.xpath("//div[@class='container']/h1")).getText();
			}
			Reporter.log("Search Term - "+search +"-Result-" +noofrecord);
			System.out.println("Search Term - "+search +"-Result-" +noofrecord);
			driver.findElement(logoImage).click();
			Thread.sleep(1000);
			driver.findElement(searchBox).click();
			Thread.sleep(1000);
		}
	}

	public void sortingterm(String sitename){
		//it has been moved to live test
		switch (sitename) {
		case "CP-Sweden":
			alphabetical="A till Ö";
			mostavailable="Tillgänglighet";
			mostsold="Bästsäljare";
			lowestprice ="Lägsta pris";
			highestprice="Högsta pris";
			newestproduct="Senast inkomna";
			highestrebatt= "Högsta rabatt";
			break;
		case "CP-Finland":
			alphabetical="A–Ö";
			mostavailable="Saatavuus";
			mostsold="Suositut";
			lowestprice ="Alin hinta";
			highestprice="Korkein hinta";
			newestproduct="Uusimmat";
			highestrebatt= "Suurin alennus";
			break;
		case "BliVakker":
			alphabetical="A til Å";
			mostavailable="Mest tilgjengelig";
			mostsold="Mest solgt";
			lowestprice ="Laveste pris";
			highestprice="Høyeste pris";
			newestproduct="Nyeste produkter";
			highestrebatt= "Høyest rabatt";
			
			break;
		case "CP-Austria":
			alphabetical="A bis Z";
			mostavailable="Verfügbarkeit";
			mostsold="BESTSELLER";
			lowestprice ="Niedrigster Preis";
			highestprice="Höchster Preis";
			newestproduct="Neueste Artikel";
			highestrebatt= "Höchster Rabatt";
			
			break;
		case "CP-Germany":
			alphabetical="A bis Z";
			mostavailable="Verfügbarkeit";
			mostsold="BESTSELLER";
			lowestprice ="Niedrigster Preis";
			highestprice="Höchster Preis";
			newestproduct="Neueste Artikel";
			highestrebatt= "Höchster Rabatt";
			
			break;
		case "CP-Denmark":
			alphabetical="A til Å";
			mostavailable="Mest tilgængelig";
			mostsold="Mest solgt";
			lowestprice ="Laveste pris";
			highestprice="Højeste pris";
			newestproduct="Nyeste produkter";
			highestrebatt= "Højeste rabat";
			
			break;
		}
	}
	
	
	public void searchwithterm(String searchterm, String sitename)throws Exception{
		//Thread.sleep(2000);
		//driver.findElement(logoImage).click();
		Thread.sleep(1000);
		driver.findElement(searchBox).click();
		driver.findElement(searchBox).sendKeys(searchterm);
		Thread.sleep(1000);
		Reporter.log("Search-"+searchterm);
		driver.findElement(searchButton).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='dropdown-arrow']")).click();
		
	//	sortingterm(sitename);
			
		/*   
		WebElement element  = driver.findElement(By.xpath("//div[@class='group-item product-list-item'][1]/div/a"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);*/
	}
	
	public void sortoption(String sorttype, String sitename ) throws Exception{
		Reporter.log("Sorting by "+sorttype);
	//	System.out.println(sorttype);
		int totalproduct = driver.findElements(By.xpath("//div[@id='product-list-container']/div")).size();
		
		WebElement countryUL= driver.findElement(By.xpath("//ul[@class='dropdown-list']"));
		List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
		
		for (WebElement li : countriesList) {
		//	System.out.println("lig-"+li.getText());
		if (li.getText().equals(sorttype)) {
		   
			li.click();
			Thread.sleep(2000);
			break;
		   }
		}
		
		/* This is also working you can use either of this
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-list']/li/label/a"));
		//Count the Values
		//System.out.println(options.size());
		
		System.out.println(sorttype);
		for(int j=0;j<8;j++){
			String optionName = options.get(j).getText();
			System.out.println(j+".-"+optionName);
		    if(optionName.equals(sorttype)){
		    	System.out.println("J"+j);
		       	options.get(j).click();
		       	Thread.sleep(2000);
		    }
		    }*/
		String firstproductprice =driver.findElement(By.xpath("//div[@id='product-list-container']/div[1]/div/div/a[2]/div/div[2]/strong[@class='price-final']")).getText();
		String lastproductprice ;
		if(totalproduct>28){
		 lastproductprice = driver.findElement(By.xpath("//div[@id='product-list-container']/div[28]/div/div/a[2]/div/div[2]/strong[@class='price-final']")).getText();
		}else{
		 lastproductprice = driver.findElement(By.xpath("//div[@id='product-list-container']/div["+totalproduct+"]/div/div/a[2]/div/div[2]/strong[@class='price-final']")).getText();}
		//System.out.println("-FP-"+firstproductprice+"LP-"+lastproductprice);
		
		Reporter.log("First item-"+firstproductprice+"Last item-"+lastproductprice);
		//Thread.sleep(4000);
		//driver.navigate().refresh();
	}
	
	
	
}
