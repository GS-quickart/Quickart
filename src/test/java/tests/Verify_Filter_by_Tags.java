package tests;

import java.time.Duration;
import java.util.List;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.BaseTest;
import pages.HomePage;

public class Verify_Filter_by_Tags extends BaseTest 
{
	HomePage homePage;
	
	@BeforeMethod
    public void start() throws InterruptedException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
	
	@Test
	public void verifyFiltersAndProductCount()throws InterruptedException
	{
		 Thread.sleep(10000);
	    // Get all filters
		
	    List<WebElement> filters = driver.findElements(By.xpath("//a[contains(@href,'featured-categories-product-list')]"));

	    // Verify total filters
	    System.out.println("Total Filters Available: " + filters.size());

	    // Loop through all filters
	    for (int i = 1; i <= filters.size(); i++)
	    {
	        // Re-fetch filter every iteration
	    	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

	    	WebElement filter = wait.until(
	    			ExpectedConditions.elementToBeClickable((By.xpath("(//div[@class='featured_cate_list'])[" + i + "]"))));

	        // Get filter name
	    	String filterName = driver.findElement(By.xpath("(//div[@class='featured_cate_list'])[" + i + "]")).getText();
	        //String filterName = filter.getText();

	        System.out.println("Clicking Filter: " + filterName);

	        // Scroll filter into view
	        JavascriptExecutor js =(JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});",filter);
	        Thread.sleep(1000);

	        // Click filter
	        filter.click();

	        Thread.sleep(2000);

	        // Get products under selected filter
	        List<WebElement> products = driver.findElements(By.xpath("//a[contains(@href,'product-details')]"));

	        // Print product count
	        System.out.println("Products under "+ filterName+ " = "+ products.size());

	        // Optional validation
	        if (products.isEmpty())
	        {
	            System.out.println("No products found under "+ filterName);
	        }
	        
	        driver.navigate().back();
	        wait.until(ExpectedConditions.urlToBe("https://demoweb-production-7099.up.railway.app/"));
            Thread.sleep(1000);
	    }
	}	
	
		@AfterTest
		public void Quit_application() 
		{
			driver.quit();
		}
}
