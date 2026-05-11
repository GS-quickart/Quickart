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

import com.google.errorprone.annotations.Var;

public class HomePage 
{

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) 
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Reusable Method
    public void addRandomProductFromCategory(String categoryName) throws InterruptedException
    {
    	Thread.sleep(5000);
		
		WebElement Categories = driver.findElement(By.xpath("//h5[text()='Categories']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", Categories);
    	Thread.sleep(2000);
    	js.executeScript("window.scrollBy(0,-300);");
		Thread.sleep(1000);
    	
	    // Open category
	    WebElement category = wait.until(
	    		ExpectedConditions.elementToBeClickable(By.xpath("//h6[text()='" + categoryName + "']")));
	    category.click();
	    
	    // Get all Products
	    List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));
	    System.out.println("No. of Products- " + products.size());

	    // Validation
	    if (products.isEmpty()) 
	    {
	        throw new RuntimeException("No products available in category: " + categoryName);
	    }
	    Thread.sleep(2000);
	    
	    // Random selection
	    Random random = new Random();
	    int randomIndex = random.nextInt(products.size());
	    //WebElement selectedProduct = products.get(randomIndex);
	    System.out.println(randomIndex);
	    System.out.println("Adding random product from category: " + categoryName);
	
	    Thread.sleep(2000);

		// Re-fetch products again (IMPORTANT)
		products = driver.findElements(By.xpath("//div[@class='product']"));
		WebElement selectedProduct = products.get(randomIndex);
		    
		String productName = driver.findElement(By.xpath("(//div[@class='product_name'])[" + randomIndex + "]")).getText();
	    System.out.println("Selected Product: " + productName);
	    Thread.sleep(2000);
	    
	    //Get Coordinate of selected product
	    int Product_position = selectedProduct.getLocation().getY();
	    System.out.println("Y Coordinate of selected product : " + Product_position);
	    js.executeScript("window.scrollBy(0, arguments[0]);", Product_position);
	    Thread.sleep(2000);
	    js.executeScript("window.scrollBy(0,-400)");
	    Thread.sleep(2000);
  
	    // Click on Add(+) button of selected product
	    selectedProduct.findElement(By.xpath("(//button[contains(text(),'+')])[" + randomIndex + "]")).click();
	    //System.out.println("selectedProduct- " + selectedProduct);
	   
	    //Handle Variant product
	    String Variant = driver.findElement(By.xpath("(//div[@class='product']//span[contains(.,'options')])[" + randomIndex + "]")).getText();
	    System.out.println(Variant);
	    if(Variant.equalsIgnoreCase("Done"))
	    {
	    	driver.findElement(By.xpath("(//button[@class='qty-btn varient-btn-plus'])[1]")).click();
	    	driver.findElement(By.xpath("//*[text()='Done']")).click();
	    }
	    else
	    {
	    	
	    }
	    	
	    Navigate_HomePage();
	      
    }
    
    public void Navigate_HomePage() throws InterruptedException
    {
    	//Home Button
	    driver.findElement(By.xpath("//img[contains(@class,'fluid desktop-logo')]")).click();
	    Thread.sleep(2000);
    }
    
    public void Click_on_Cart_Icon() throws InterruptedException
    {
    	//Click on cart Icon
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("(//li[contains(@class,'cart-btn')])[1]")).click();
    }
        
}

/*
-----------------------------------------------------------------------




// Get all Add buttons
List<WebElement> addButtons = wait.until(
		ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'+')]")));


// Random selection
Random random = new Random();
int randomIndex = random.nextInt(addButtons.size());
WebElement selectedProduct = addButtons.get(randomIndex);
System.out.println("Adding random product from category: " + categoryName);




int Product_position = selectedProduct.getLocation().getY();

System.out.println("Y Coordinate: " + Product_position);

js.executeScript("window.scrollBy(0, arguments[0]);", Product_position);
Thread.sleep(2000);
js.executeScript("window.scrollBy(0,-450)");
Thread.sleep(2000);
selectedProduct.click();
System.out.println("selectedProduct- " + selectedProduct);








// Click Add
//wait.until(ExpectedConditions.elementToBeClickable(selectedProduct)).click();

//Handle Variant product
String Variant = driver.findElement(By.xpath("//*[text()='Done']")).getText();
System.out.println(Variant);
if(Variant.equalsIgnoreCase("Done"))
{
	driver.findElement(By.xpath("(//button[@class='qty-btn varient-btn-plus'])[1]")).click();
	driver.findElement(By.xpath("//*[text()='Done']")).click();
}
else
{
	
}


/*
 * 
 * if that perticular product contains options then 
 * 
     if(Variant.equalsIgnoreCase("Done"))
    {
    	driver.findElement(By.xpath("(//button[@class='qty-btn varient-btn-plus'])[1]")).click();
    	driver.findElement(By.xpath("//*[text()='Done']")).click();
    }
    else
    {
    	
    }
 * 
 * 
 * 
 */


