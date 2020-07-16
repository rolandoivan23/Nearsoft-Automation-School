package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRemovePage extends TheInternetPage {

    //Buttons Elements
    @FindBy(css = "button[onclick='addElement()']")
    private WebElement addBtn;
    @FindBy(css = "#elements .added-manually")
    private List<WebElement> removeBtns;

    public AddRemovePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /*
        Adding 5 elements (remove buttons) to
        the interface by doing click in the
        button 'Add'
     */
    public void addFiveElements(){
        for(int i = 0; i < 5; i++){
            addElement();
        }
        PageFactory.initElements(driver, this);
    }

    /*
        Removing 2 'remove buttons' of the interface
        by doing clink themselves. The click are on
        the first 2 buttons
     */
    public void removeTwoElements(){
        for(int i = 0; i < 2; i++){
            removeElement( removeBtns.get(i) );
        }
    }

    /*
        Returns a list with the remove buttons
        than are visible in the interface
     */
    public List<WebElement> getVisibleElements(){
        PageFactory.initElements(driver, this);
        return removeBtns;
    }

    /*
        Simulates a human interaction with the interface.
        It does click in the add button
     */
    private void addElement(){
        click(addBtn);
    }

    /*
        Simulates a human interaction with the interface.
        It does click in an specific remove button
     */
    private void removeElement(WebElement removeBtn){
        click(removeBtn);
    }


}
