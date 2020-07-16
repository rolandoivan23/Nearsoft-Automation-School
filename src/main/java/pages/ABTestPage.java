package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ABTestPage extends TheInternetPage {

    //container div for text of the test
    @FindBy(className = "example")
    private WebElement exampleText;

    public ABTestPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //Returns the text of the main container
    public String getExampleText() {
        this.waitForElementToAppear(exampleText);
        return exampleText.getText();
    }
}
