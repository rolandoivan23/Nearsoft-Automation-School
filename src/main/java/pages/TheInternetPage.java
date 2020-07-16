package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

import java.util.List;

/*
The base class for the page for automation train examples
http://the-internet.herokuapp.com/
 */
public class TheInternetPage extends BasePage {

    //Extended Classes
    private ABTestPage abTestPage;
    private AddRemovePage addRemovePage;
    private ImagesTestPage imagesTestPage;

    //Elements for the links with tests types
    @FindBy(css = "#content ul li")
    private List<WebElement> testTypes;

    @FindBy(css = "#content > ul > li:nth-child(1) > a")
    private WebElement abTestLink;
    @FindBy(css = "#content > ul > li:nth-child(2) > a")
    private WebElement addRemoveLink;
    @FindBy(css = "#content > ul > li:nth-child(4) > a")
    private WebElement imagesTestLink;



    public TheInternetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Sends to the root page
    public void goHome(){
        driver.get("http://the-internet.herokuapp.com/");
    }

    //Sends to the link for a/b type test
    public void goToABTest(){
        goHome();
        this.click(abTestLink);
        abTestPage = new ABTestPage(driver);

    }

    //Sends to the link for add/remove type test
    public void goToAddRemovePage(){
        addRemovePage = new AddRemovePage(driver);
        goHome();
        click(addRemoveLink);
    }

    //Sends to the link for images type test
    public void goToImagesPage(){
        imagesTestPage = new ImagesTestPage(driver);
        goHome();
        click(imagesTestLink);
    }

    //Counts the number of links of kind of tests
    public int countTestTypes(){
        return this.testTypes.size();
    }

    //Returns instance for page of type A/B
    public ABTestPage getAbTestPage(){
        return abTestPage;
    }

    //Returns instance for page of AddRemove
    public AddRemovePage getAddRemovePage(){
        return addRemovePage;
    }

    //Returns instance for page of type A/B
    public ImagesTestPage getImagesTestPage(){
        return imagesTestPage;
    }
}
