package tests;

import java.io.IOException;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.HomePage;

//Add a product in a cart and complete a payment process
// verify that product is displaying in repeat section

public class Verify_Repeat_order_functionality extends BaseTest
{
	HomePage homePage;
	
	
    @BeforeMethod
    public void start() throws InterruptedException, IOException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void t123() throws InterruptedException
    {
    	Verify_Searched_suggestions_displayed vssd = new Verify_Searched_suggestions_displayed();
    	vssd.searchProduct("Frozen Green Peas");
    }
    
    
    
    
    
}
