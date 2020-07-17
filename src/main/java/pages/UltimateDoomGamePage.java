package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

public class UltimateDoomGamePage extends BasePage {

    @FindBy(className = "apphub_AppName")
    private WebElement gameTitle;

    public UltimateDoomGamePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getGameTitle(){
        return gameTitle.getText();
    }
}
