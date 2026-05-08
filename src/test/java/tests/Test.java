package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Test {

public static void main(String[] args) throws InterruptedException 
{
	
	 ChromeOptions options = new ChromeOptions();
	 Map<String, Object> prefs = new HashMap<>();
	 prefs.put("profile.default_content_setting_values.notifications", 2); // 2 = Block

	 options.setExperimentalOption("prefs", prefs);

	 WebDriver driver = new ChromeDriver(options);
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 
	 driver.get("https://demoweb-production-7099.up.railway.app/");
	 driver.manage().window().maximize();
	 Thread.sleep(3000);
	 
	 driver.findElement(By.xpath("//a[@title='Sign in']")).click();
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Login / SignUp')]")));
	 
	 driver.findElement(By.id("mobile_code")).sendKeys("541234567");
	 driver.findElement(By.xpath("//button[@class='submit_btn otp_request']")).click();
	  
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Verify & Continue']")));
	 
	 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[1]")).sendKeys("1");
	 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[2]")).sendKeys("2");
	 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[3]")).sendKeys("3");
	 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[4]")).sendKeys("4");
	 
	 driver.findElement(By.xpath("//button[text()='Verify & Continue']")).click();
	 
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='Logout'])[1]"))); 
	 
	 String verify_login = driver.findElement(By.xpath("(//div[text()='Logout'])[1]")).getText();
	 System.out.println("Captured Text- " + verify_login);
	 
	 if(verify_login.equalsIgnoreCase("Logout"))
	 {
		 System.out.println("User Logged in Successfully");
	 }
	 else
	 {
		 System.out.println("User Login Fail");
	 }
	 
	 driver.findElement(By.xpath("(//div[text()='Logout'])[1]")).click();
	 driver.quit(); 
	

//--------------------------xx---------------------------
	 
	 
	 //search "Fresh Cow Ghee"
	 
	 driver.findElement(By.id("searchInput")).sendKeys("Fresh Cow Ghee");
		 driver.findElement(By.id("searchBtn")).click();
		 
		 boolean is_product_list_displaying = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("all_product_list"))).isDisplayed();
		 //System.out.println(is_product_list_displaying);
		 
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
/*		 	
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart_btn add_cart_btn change-qty']"))).click();
		// driver.findElement(By.xpath("//div[@class='cart_btn add_cart_btn change-qty']")).click();
		 System.out.println("1");
		 wait.until(ExpectedConditions.elementToBeClickable(By.className("cart_btnBox"))).click();
		 System.out.println("1");
*/
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("(//li[contains(@class,'cart-btn')])[1]")).click();
		 
		 
		 //Item added in cart
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='order_detailbox']/div[contains(.,'Order Summary')]")));
		 String Order_Summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='order_detailbox']/div[contains(.,'Order Summary')]"))).getText();
		 
		 //String Order_Summary = driver.findElement(By.xpath("//div[@class='order_detailbox']/div[contains(.,'Order Summary')]")).getText();
		 
		 if (Order_Summary.contains("Order Summary"))
		 {
			System.out.println("Navigate successfully on Payment screen"); 
		 }
		 else
		 {
			 System.out.println("Failed to navigate on Payment screen");
		 }
	 
		 
		 //Date selection
		 driver.findElement(By.xpath("//div[@class='schedule_box']//div[@id='dayBox']")).click();
		 Thread.sleep(2000);
		 
		 
		 
		 driver.findElement(By.xpath("//button[@id='saveSelectedDateTime']")).click();
		 Thread.sleep(2000);
		 
		 // payment calculation
		 
		 driver.findElement(By.xpath("//input[@id='daily_COD']")).click();
		 driver.findElement(By.xpath("//div[@id='dailyPlaceOrderBtn']")).click();
		 

		 


}
}
