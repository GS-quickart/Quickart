package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class test1 {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		/*WebDriver driver = new ChromeDriver();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 
		 Properties propertiesobj = new Properties();
		 FileInputStream file =new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		 propertiesobj.load(file);
		 
		 String Quickaet_url1 = propertiesobj.getProperty("Quickaet_url");
		 System.out.println(Quickaet_url1);
		 driver.get(Quickaet_url1);
		 driver.manage().window().maximize();
		 Thread.sleep(3000);
*/
		
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2); // 2 = Block
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver;
		WebDriverWait wait;
		//Chrome Driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoweb-production-7099.up.railway.app/");
        
        
    
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
	 
		Thread.sleep(5000);
		
		WebElement Categories = driver.findElement(By.xpath("//h5[text()='Categories']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", Categories);
    	Thread.sleep(2000);
    	js.executeScript("window.scrollBy(0,-300);");
		Thread.sleep(2000);
		
		
		
		WebElement category = wait.until(
	    		ExpectedConditions.elementToBeClickable(By.xpath("//h6[text()='Fruits']")));
	    category.click();
	    
	    Thread.sleep(5000);
	  
	    WebElement abc = driver.findElement(By.xpath("(//button[contains(text(),'+')])[5]"));
	    /*  
	   	js.executeScript("arguments[0].scrollIntoView(true);", abc);
    	Thread.sleep(2000);
    	js.executeScript("window.scrollBy(0,-400);");
	   */
	    
	    while (!abc.isDisplayed()) {

	        js.executeScript("window.scrollBy(0,100)");

	        Thread.sleep(2000);
	    }
	    js.executeScript("window.scrollBy(0,100)");
	    Thread.sleep(2000);
	    
	    abc.click();
	    
	    
	}
	

}
