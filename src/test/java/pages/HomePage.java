package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    List<WebElement> products;

    // Constructor
    public HomePage(WebDriver driver) 
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Reusable Method
    public void addRandomProductFromCategory(String categoryName) throws InterruptedException
    {
    	Thread.sleep(2500);
    	
		WebElement Categories = driver.findElement(By.xpath("//h5[text()='Categories']"));
    	js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({block:'center'});",Categories);
		Thread.sleep(1000);
    	
	    // Open category
		System.out.println("category Name = " +categoryName);
	    WebElement category = wait.until(
	    		ExpectedConditions.elementToBeClickable(By.xpath("//h6[text()='" + categoryName + "']")));
	    category.click();

	    // Get all Products
	    getProducts(categoryName);
	    //List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));
	    //System.out.println("No. of Products in " + categoryName + " category = " + products.size());
	    
	    Thread.sleep(2000);
	    
	    // Random selection
	    Random random = new Random();
	    int randomIndex = random.nextInt(products.size());
	    System.out.println("randomIndex = " + randomIndex);
	    //WebElement selectedProduct = products.get(randomIndex);
	    int productIndex = randomIndex + 1;
	    System.out.println("product Index = " + productIndex);
	    
	    System.out.println("Adding random product from category: " + categoryName);
	
	    Thread.sleep(2000);

		// Re-fetch products again
		products = driver.findElements(By.xpath("//div[@class='product']"));
		WebElement selectedProduct = products.get(randomIndex);
		    
		String productName = driver.findElement(By.xpath("(//div[@class='product_name'])[" + productIndex + "]")).getText();
	    System.out.println("Selected Product: " + productName);
	    Thread.sleep(2000);
	    
	    // Scroll product at the center of the page
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});",selectedProduct);
	    Thread.sleep(2000);

	    //Handle Variant product
	    String productDetails = driver.findElement(By.xpath("(//div[@class='product'])[" + productIndex + "]")).getText();

	    System.out.println("Product Details = " + productDetails);
	    if(productDetails.contains("options"))
	    {
	    	selectedProduct.findElement(By.xpath("(//button[contains(text(),'+')])[" + productIndex + "]")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("(//button[@class='qty-btn varient-btn-plus'])[1]")).click();
	    	driver.findElement(By.xpath("//*[text()='Done']")).click();
	    }
	    else
	    {
	    	selectedProduct.findElement(By.xpath("(//button[contains(text(),'+')])[" + productIndex + "]")).click();
	    }
	    	
    }
    
    public void getProducts(String categoryName)
    {
    	products = driver.findElements(By.xpath("//div[@class='product']"));
	    System.out.println("No. of Products in " + categoryName + " category = " + products.size());
	    
	    // Validation
	    if (products.isEmpty()) 
	    {
	        throw new RuntimeException("No products available in category: " + categoryName);
	    }
    }
    
    public void Navigate_HomePage() throws InterruptedException
    {
    	//Home Button
    	js.executeScript("window.scrollTo(0, 0);");
    	Thread.sleep(1000);
	    driver.findElement(By.xpath("//img[contains(@class,'fluid desktop-logo')]")).click();
    }
    
    public void Click_on_Cart_Icon() throws InterruptedException
    {
    	//Click on cart Icon
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("(//li[contains(@class,'cart-btn')])[1]")).click();
    }     
    
    
    
    
    //------------------------------------------xx------------------------------------------------
    
    
    
    
    
    
    public void Verify_Signup_Status()
	{
		 String verify_login =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='Hi, Jivan'])[1]"))).getText();
		 
		 //String verify_login = driver.findElement(By.xpath("(//div[text()='Logout'])[1]")).getText();
		 System.out.println("Captured Text- " + verify_login);
		 
		 if(verify_login.equalsIgnoreCase("Hi, Jivan"))
		 {
			System.out.println("User Logged in Successfully");
		 }
		 else
		 {
			System.out.println("User Login Fail");
		 }
	}
    
    public void Logout() 
	{
		driver.findElement(By.xpath("(//div[text()='Logout'])[1]")).click();
		String verify_logout_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/button[text()='Logout']"))).getText();
		if(verify_logout_msg.equalsIgnoreCase("Logout"))
		 {
			driver.findElement(By.xpath("//div/button[text()='Logout']")).click();
			System.out.println("User Logged out Successfully");
		 }
		 else
		 {
			System.out.println("User Logout Fail");
		 }
	}
    
    
    public void Add_to_cart() throws InterruptedException
	{
		 driver.findElement(By.id("searchInput")).sendKeys("Fresh Cow Ghee");
		 driver.findElement(By.id("searchBtn")).click();
		 
		 boolean is_product_list_displaying = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("all_product_list"))).isDisplayed();
		 
		 if(is_product_list_displaying==true)
		 {
			 driver.findElement(By.xpath("(//button[contains(@class,'qty-btn-plus change-qty')])[1]")).click();
		 }
		 else
		 {
			 driver.findElement(By.id("searchInput")).sendKeys("Paneer");
			 driver.findElement(By.id("searchBtn")).click();
			 driver.findElement(By.xpath("(//button[contains(@class,'qty-btn-plus change-qty')])[1]")).click();
		 }

		 Thread.sleep(2000);
		 driver.findElement(By.xpath("(//li[contains(@class,'cart-btn')])[1]")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='order_detailbox']/div[contains(.,'Order Summary')]")));
		 Thread.sleep(2000);
		 String Order_Summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='order_detailbox']/div[contains(.,'Order Summary')]"))).getText();
		 		 
		 if (Order_Summary.contains("Order Summary"))
		 {
			System.out.println("Navigate successfully on Payment screen"); 
		 }
		 else
		 {
			 System.out.println("Failed to navigate on Payment screen");
		 }
	}
	
	public void Payment() throws InterruptedException
	{
	
		 //Date selection
		 driver.findElement(By.xpath("//div[@class='schedule_box']//div[@id='dayBox']")).click();
		 Thread.sleep(2000);
		 
		 //Choose Date 
		 List<WebElement> rdm_date = driver.findElements(By.xpath("//div[@class='schedule']"));

		 if (rdm_date.isEmpty()) {
		     throw new RuntimeException("No date elements found!");
		 }

		 Random rdm = new Random();
		 int date_index = rdm.nextInt(rdm_date.size());
		 WebElement selectedDate = rdm_date.get(date_index);
		 System.out.println("Selected date: " + selectedDate.getText());
		 selectedDate.click();
		 Thread.sleep(2000);
		 
		 //Choose Time
		 /*
		 List<WebElement> rdm_time = driver.findElements((By.xpath("//div[@id='timeList']")));
		
		 int time_index = rdm.nextInt(rdm_time.size());
		 rdm_date.get(time_index).click();
		 */
		 
		 driver.findElement(By.xpath("//button[@id='saveSelectedDateTime']")).click();
		 Thread.sleep(2000);
		 
		 // payment calculation
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	
		 WebElement ele = driver.findElement(By.xpath("//input[@id='daily_COD']"));
		 //js.executeScript("arguments[0].scrollIntoView();",ele);
		 js.executeScript("window.scrollBy(0,500);");
		 Thread.sleep(2000);
	
		 ele.click();
		 driver.findElement(By.xpath("//div[@id='dailyPlaceOrderBtn']")).click();
		 
		 
		 String Order_confirmed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Order Confirmed !']"))).getText();
		 System.out.println(Order_confirmed);
		 if (Order_confirmed.equalsIgnoreCase("Order Confirmed !"))
		 {
			System.out.println("Payment successfully done"); 
		 }
		 else
		 {
			 System.out.println("Payment Failed");
		 }
	}

    
    
    
}
