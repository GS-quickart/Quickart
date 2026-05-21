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
}
