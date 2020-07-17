package tests;

import com.google.common.collect.ImmutableMap;
import components.NavBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import settings.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TheInternetTests extends BaseTest {
    private TheInternetPage theInternetPage;
    private NavBar navBar;

    private static final String ABTESTTEXT = "A/B Test Control\nAlso known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";
    private static final String ABTESTTEXTAFTER = "A/B Test Variation 1\nAlso known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";

    @BeforeTest
    public void setUp(){
        System.out.println("Execution of @Before Test");
        this.changeDocument("http://the-internet.herokuapp.com/");
        theInternetPage = new TheInternetPage(this.driver);
        navBar = new NavBar(driver);
    }

    @Test(priority = 1)
    public void allkindsOfTestPresent(){
        Assert.assertEquals(theInternetPage.countTestTypes(),44);
    }

    @Test(priority = 2)
    public void abTestPageDisplayed() throws InterruptedException {
        theInternetPage.goToABTest();
        String initial_text = this.theInternetPage.getAbTestPage().getExampleText();
        Assert.assertEquals(initial_text, ABTESTTEXT);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
/*        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //wait(10000);
        String variant_text = this.theInternetPage.getAbTestPage().getExampleText();
        Assert.assertEquals(variant_text, ABTESTTEXTAFTER);
    }

    @Test(priority = 3)
    public void addRemoveTest(){
        theInternetPage.goToAddRemovePage();
        AddRemovePage addRemovePage = theInternetPage.getAddRemovePage();
        addRemovePage.addFiveElements();
        addRemovePage.removeTwoElements();
        List<WebElement> visibleElements = addRemovePage.getVisibleElements();
        Assert.assertTrue(visibleElements.size() == 3);
    }

    @Test(priority = 4)
    public void imagesTest(){
        theInternetPage.goToImagesPage();
        ImagesTestPage imagesPage = theInternetPage.getImagesTestPage();
        Assert.assertEquals(imagesPage.getCompleteImages().size() ,1);
        Assert.assertEquals(imagesPage.getFailedImages().size(), 2);
    }

    @Test(priority = 5)
    public void ultimateDoomPage(){
        driver.get("https://store.steampowered.com/");
        navBar.writeGameIntoSearchInput("Ultimate Doom");
        String urlUltimateDoom = navBar.getUltimateDoomSuggestion().getAttribute("href");
        String urlWithoutQueryString = urlUltimateDoom.split("\\?")[0];
        navBar.goUltimateDoomPage();
        Assert.assertEquals(urlWithoutQueryString, driver.getCurrentUrl());
        UltimateDoomGamePage ultimateDoom = new UltimateDoomGamePage(driver);
        Assert.assertEquals(ultimateDoom.getGameTitle(), "Ultimate Doom");
    }

    @Test(priority = 6)
    public void ageOfEmpiresTwo(){
        driver.get("https://store.steampowered.com/");
        navBar.writeGameIntoSearchInput("Age of Empires II: Definitive Edition");
        navBar.goAgeOfEmpires();
        AgeOfEmpiresTwo ageOfEmpiresTwo = new AgeOfEmpiresTwo(driver);
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        //Scroll to div container with the game details
        WebElement detailsContainer = ageOfEmpiresTwo.getDetailsContainer();
        jse.executeScript("arguments[0].scrollIntoView(true)",detailsContainer);

        Assert.assertTrue(detailsContainer.isDisplayed());

        //Sleep to get time for see scroll movement
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        ImmutableMap<String, String> infoHash = ImmutableMap.of("TITLE",     "Age of Empires II: Definitive Edition",
                                                                "GENRE",     "Strategy",
                                                                "DEVELOPER", "Forgotten Empires, Tantalus Media, Wicked Witch",
                                                                "PUBLISHER", "Xbox Game Studios",
                                                                "FRANCHISE", "Age of Empires");
        for(String key : infoHash.keySet() ){
            String actualValue = ageOfEmpiresTwo.getInfoByKey(key);
            Assert.assertEquals(actualValue.trim(), infoHash.get(key).trim());
        }

    }

}
