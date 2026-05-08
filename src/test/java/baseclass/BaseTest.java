package baseclass;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest
{
    public WebDriver driver;
    WebDriverWait wait;
    
    public void setup() throws InterruptedException
    {
    	//Handle Notification
    	ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2); // 2 = Block
		options.setExperimentalOption("prefs", prefs);
		
		//Chrome Driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoweb-production-7099.up.railway.app/");
        
        User_SignUp();
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
}
	