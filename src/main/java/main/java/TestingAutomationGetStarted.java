package main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class TestingAutomationGetStarted {
    public static void main(String args[]){
        System.out.println("Hello world of automation testing. :)");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/");

        int no_test_examples = driver.findElements(By.cssSelector("#content ul li")).size();
        Assert.assertEquals(no_test_examples, 44);

        // Assertion fails, there are 44 list elements
        // Assert.assertEquals(no_test_examples, 47);

        // Selector for A/B Testing
        driver.findElement( By.cssSelector("#content > ul > li:nth-child(1) > a")).click();
        Assert.assertTrue( driver.findElement(By.className("example")).isDisplayed() );

        driver.get("http://the-internet.herokuapp.com/");

        // Selector for Add/Remove Elements Test
        driver.findElement( By.cssSelector("#content > ul > li:nth-child(2) > a")).click();
        WebElement btn_add = driver.findElement( By.cssSelector("button[onclick='addElement()']") );
        for(int i = 1; i <= 5; i++){
            btn_add.click();
        }
        List<WebElement> delete_btns = driver.findElements(By.cssSelector("#elements .added-manually"));
        Assert.assertEquals(delete_btns.size(), 5);
        for(WebElement tmp_btn : delete_btns){
            tmp_btn.click();
        }
        delete_btns = driver.findElements(By.cssSelector("#elements .added-manually"));
        Assert.assertEquals(delete_btns.size(), 0);

        //Broken image testing
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement( By.cssSelector("#content > ul > li:nth-child(4) > a")).click();
        List<WebElement> images = driver.findElements(By.cssSelector(".example img"));
        int broken_images_counter = 0;
        int success_images_counter = 0;
        for(WebElement img : images){
            HttpResponse response = null;
            try {
                response = new
                        DefaultHttpClient().execute(new HttpGet(img.getAttribute("src")));

                if (response.getStatusLine().getStatusCode() != 200)
                    broken_images_counter++;
                else
                    success_images_counter++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(broken_images_counter, 2);
        Assert.assertEquals(success_images_counter, 1);

        driver.close();
    }
}
