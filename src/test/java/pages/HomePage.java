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
	
	    // Get all Add buttons
	    List<WebElement> addButtons = wait.until(
	    		ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'+')]")));
	
	    // Validation
	    if (addButtons.isEmpty()) 
	    {
	        throw new RuntimeException("No products available in category: " + categoryName);
	    }
	
	    // Random selection
	    Random random = new Random();
	    int randomIndex = random.nextInt(addButtons.size());
	    WebElement selectedProduct = addButtons.get(randomIndex);
	    System.out.println("Adding random product from category: " + categoryName);
	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // Click Add
	    wait.until(ExpectedConditions.elementToBeClickable(selectedProduct)).click();
	    
	    //Handle Variant product
	    String Variant = driver.findElement(By.xpath("//*[text()='Done']")).getText();
	    if(Variant.equalsIgnoreCase("Done"));
	    {
	    	driver.findElement(By.xpath("(//button[@class='qty-btn varient-btn-plus'])[1]")).click();
	    	driver.findElement(By.xpath("//*[text()='Done']")).click();
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

