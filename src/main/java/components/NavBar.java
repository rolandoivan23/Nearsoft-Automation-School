package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import settings.BasePage;

import java.util.ArrayList;
import java.util.List;

public class NavBar extends BasePage {

    @FindBy(id = "foryou_tab")
    private WebElement yourStoreTab;

    @FindBy(id = "genre_tab")
    private WebElement yourGameTab;

    @FindBy(id = "store_nav_search_term")
    private WebElement searchTheStoreInput;

    @FindBy(css = "#search_suggestion_contents > a > .match_name")
    private List<WebElement> suggestedGames;

    @FindBy(css = "#search_suggestion_contents > a")
    private WebElement ultimateDoomSuggestion;

    @FindBy(css = "#search_suggestion_contents > a[data-ds-itemkey='App_813780']")
    private WebElement ageOfEmpiresTwo;

    public NavBar(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openYourStoreTab(){
        this.openNavBarTab(yourStoreTab);
    }

    public void openGamesTab(){
        this.openNavBarTab(yourGameTab);
    }

    public ArrayList<String> getSuggestedGames(){

        ArrayList<String> suggested_games_names = new ArrayList<String>();

        for (WebElement suggestedGame : suggestedGames)
        {
            suggested_games_names.add(suggestedGame.getText());
        }

        return suggested_games_names;
    }

    public WebElement getUltimateDoomSuggestion(){
        return ultimateDoomSuggestion;
    }

    public void goUltimateDoomPage(){
        click(this.ultimateDoomSuggestion);
    }

    public void goAgeOfEmpires(){
        click(this.ageOfEmpiresTwo);
    }

    public void writeGameIntoSearchInput(String game_name){

        this.writeInInput(searchTheStoreInput, game_name);
    }

    private void openNavBarTab(WebElement tab){
        this.hoverElement(tab);
        String is_active = tab.getAttribute("class");
        Assert.assertTrue(is_active.indexOf("focus") > 0, "The Menu did not open");
    }

}
