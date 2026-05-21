package tests;

import baseclass.BaseTest;
import pages.HomePage;

import java.io.IOException;

import org.testng.annotations.*;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void start() throws InterruptedException, IOException 
    {
    	startbrowser();
        homePage = new HomePage(driver);
    }
    
    @Test
    public void addProductsFromCategories() throws InterruptedException 
    {
        homePage.addRandomProductFromCategory("Fruits");
        homePage.Navigate_HomePage();
        System.out.println("--------------------------------");
        homePage.addRandomProductFromCategory("Vegetables");
        homePage.Navigate_HomePage();
        System.out.println("--------------------------------");
        homePage.addRandomProductFromCategory("Test Honey");  
        homePage.Navigate_HomePage();
        
        homePage.Click_on_Cart_Icon();
    }

}

