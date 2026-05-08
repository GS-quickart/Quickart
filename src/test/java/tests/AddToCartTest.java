package tests;

import baseclass.BaseTest;
import pages.HomePage;

import org.testng.annotations.*;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void start() throws InterruptedException 
    {
    	setup();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void addProductsFromCategories() throws InterruptedException 
    {
        homePage.addRandomProductFromCategory("Fruits");
        homePage.addRandomProductFromCategory("Vegetables");
       // homePage.addRandomProductFromCategory("Snacks");  
        
        homePage.Click_on_Cart_Icon();
    }

}

