package tests;
/*
- Click and search a product.
- Verify all the suggested product
- close the suggestion popup and again search another product
*/

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.HomePage;

public class Verify_Searched_suggestions_displayed extends BaseTest 
{	
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	HomePage homePage;

    @BeforeMethod
    public void start() throws InterruptedException, IOException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void Verify_Searched_suggestions_displayed() throws InterruptedException
    {
    	Thread.sleep(5000);
    	searchProduct("paneer");
    	print_all_suggested_products();
    	System.out.println("--------------------------");
    	
    	WebElement searchField = driver.findElement(By.xpath("//input[@id='searchInput']"));
    	searchField.clear();
    	
    	Thread.sleep(1000);
    	searchProduct("milk");
    	print_all_suggested_products();
    	searchField.sendKeys(Keys.ENTER);
    	
    	Thread.sleep(2000);
    	Verify_serached_products();

    }

    public void searchProduct(String searchitem) throws InterruptedException
    {
    	
    	WebElement searchField = wait.until(
    			ExpectedConditions.elementToBeClickable((By.xpath("//input[@id='searchInput']"))));
    	
    	searchField.sendKeys(searchitem);
    	System.out.println("Searched product is - " +searchitem );
    	
    }
    
    public void print_all_suggested_products()
    {
    	//print all the suggested products
		List<WebElement> suggestedProducts = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='suggestion-item']")));
		System.out.println("Number of suggested product displayed - " + suggestedProducts.size());
		System.out.println("SuggestedProduct are - ");
		
    	for (WebElement suggestedProduct : suggestedProducts) 
    	{
			System.out.println(suggestedProduct.getText().toString());
		}
    }
    	
    public void Verify_serached_products()
    {
    	List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));
    	System.out.println("No. of Products = " + products.size());
 
    	List<WebElement> productName=driver.findElements(By.xpath("//div[@class='product_name']"));

    	for (WebElement product : productName)
    	{
    		String nameText = product.getText();
    		if (nameText.toLowerCase().contains("milk")){
    			System.out.println("PASS - Product contains milk");
    	    } 
    		else {
    	        System.out.println("FAIL - Product does not contain milk");
    	    }
		}    	
    }
    
    @AfterTest
	public void Quit_application() 
	{
		driver.quit();
	}     
}
