package tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass 
{
	WebDriver driver;
	WebDriverWait wait;
	
	public void Handel_Notification() 
	{
		ChromeOptions options = new ChromeOptions();
		 Map<String, Object> prefs = new HashMap<>();
		 prefs.put("profile.default_content_setting_values.notifications", 2); // 2 = Block
		 options.setExperimentalOption("prefs", prefs);
		 
		 driver = new ChromeDriver(options);
	}
	
	public void Enetr_Url()
	{
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 
		 driver.get("https://demoweb-production-7099.up.railway.app/");
		 driver.manage().window().maximize();
	}
	
	public void User_SignUp() throws InterruptedException
	{
		 driver.findElement(By.xpath("//a[@title='Sign in']")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Login / SignUp')]")));
		 Thread.sleep(2000);
		 driver.findElement(By.id("mobile_code")).sendKeys("541234567");
		 driver.findElement(By.xpath("//button[@class='submit_btn otp_request']")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Verify & Continue']")));
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[1]")).sendKeys("1");
		 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[2]")).sendKeys("2");
		 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[3]")).sendKeys("3");
		 driver.findElement(By.xpath("(//div[@class='otp-input']/input)[4]")).sendKeys("4");
		 
		 driver.findElement(By.xpath("//button[text()='Verify & Continue']")).click();
	} 
	
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
	
	public void Quit_application() 
	{
		driver.quit();
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
