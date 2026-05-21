package tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.HomePage;

public class Verify_Complete_Payment_Flow extends BaseTest
{
    HomePage homePage;

    @BeforeTest
    public void start() throws InterruptedException, IOException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void complete_payment() throws InterruptedException
    { 	
    	Thread.sleep(5000);
    	homePage.Add_to_cart();
    	homePage.Payment();	
    }
    
    @AfterTest
    public void Quit_application() 
	{
		driver.quit();
	}
}



