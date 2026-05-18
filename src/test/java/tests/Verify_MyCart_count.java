package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
- Add multiple product in cart and verify count 
- Delete a item and verify count should get deduct 
*/
 
public class Verify_MyCart_count extends BaseTest 
{
	HomePage homePage;
	JavascriptExecutor js;
	 
	@BeforeMethod
    public void start() throws InterruptedException, IOException
    {
    	startbrowser();
        homePage = new HomePage(driver); 
    }
	
	@Test
	public void Verify_Cart_count() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Cart count element
		WebElement cartCount = driver.findElement(By.xpath("(//li[@class='list-inline-item cart-btn']//*[@class='cart-value'])[1]"));

		// Get initial count
		int initialCount = Integer.parseInt(cartCount.getText());

		System.out.println("Initial Cart Count : " + initialCount);
		
		WebElement plusButton= driver.findElement(By.xpath("(//button[contains(text(),'+')])[1]"));
    	js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({block:'center'});",plusButton);
		Thread.sleep(1000);
	
		// Click PLUS button
		plusButton.click();
		driver.findElement(By.xpath("(//button[contains(text(),'+')])[3]")).click();
		driver.findElement(By.xpath("(//button[contains(text(),'+')])[4]")).click();
		driver.findElement(By.xpath("(//button[contains(text(),'+')])[5]")).click();

		// Wait until count increases
		wait.until(ExpectedConditions.textToBePresentInElement(cartCount,String.valueOf(initialCount + 4)));

		// Get updated count
		int increasedCount = Integer.parseInt(cartCount.getText());

		System.out.println("Updated Count After Plus : " + increasedCount);

		// Verify increase
		if (increasedCount == initialCount + 4) {
		    System.out.println("PASS - Cart count increased");
		} else {
		    System.out.println("FAIL - Cart count did not increase");
		}

		// Click MINUS button
		WebElement minusButton = driver.findElement(By.xpath("(//button[contains(text(),'-')])[1]"));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});",minusButton);
		Thread.sleep(1000);
		minusButton.click();
		driver.findElement(By.xpath("(//button[contains(text(),'-')])[3]")).click();
		driver.findElement(By.xpath("(//button[contains(text(),'-')])[4]")).click();
		driver.findElement(By.xpath("(//button[contains(text(),'-')])[5]")).click();
		
		
		// Wait until count decreases
		wait.until(ExpectedConditions.textToBePresentInElement(cartCount,String.valueOf(initialCount)));

		// Get decreased count
		int decreasedCount = Integer.parseInt(cartCount.getText());

		System.out.println("Updated Count After Minus : " + decreasedCount);

		// Verify decrease
		if (decreasedCount == initialCount) {
		    System.out.println("PASS - Cart count decreased");
		} else {
		    System.out.println("FAIL - Cart count did not decrease");
		}
	}
		
	@AfterTest
	public void Quit_application() 
	{
		driver.quit();
	} 
}


          // for variant product





