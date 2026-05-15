package tests;
/*
- Click and search a product.
- Verify all the suggested product
- close the suggestion popup and again search another product
*/

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
	HomePage homePage;

    @BeforeMethod
    public void start() throws InterruptedException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void t1() throws InterruptedException
    {
    	Thread.sleep(5000);
    	searchProduct("tomato");
    	System.out.println("--------------------------");
    	
    	WebElement searchField = driver.findElement(By.xpath("//input[@id='searchInput']"));
    	searchField.clear();
    	
    	searchProduct("potato");
    	searchField.sendKeys(Keys.ENTER);
    	
    	
    }

    public void searchProduct(String searchitem) throws InterruptedException
    {
    	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

    	WebElement searchField = wait.until(
    			ExpectedConditions.elementToBeClickable((By.xpath("//input[@id='searchInput']"))));
    	
    	searchField.sendKeys(searchitem);
    	System.out.println("Searched product is - " +searchitem );
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
    	
    @AfterTest
	public void Quit_application() 
	{
		driver.quit();
	} 
    
    
    
    					//verify all the products are tomato
    
}
