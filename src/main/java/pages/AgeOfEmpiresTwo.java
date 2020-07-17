package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

import java.util.HashMap;
import java.util.List;

public class AgeOfEmpiresTwo extends BasePage {

    private HashMap<String, String> dataHash;

    @FindBy(className = "game_details")
    private WebElement detailsContainer;

    //El div con los datos del test case
    @FindBy(css = ".game_details  .details_block:nth-child(1) ")
    private WebElement attributesContainer;

    public AgeOfEmpiresTwo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getDetailsContainer(){
        setDataStructureForTestData();
        //System.out.println(attributesContainer.getText());
        // ---->
        /*
            TITLE: Age of Empires II: Definitive Edition
            GENRE: Strategy
            DEVELOPER: Forgotten Empires, Tantalus Media, Wicked Witch
            PUBLISHER: Xbox Game Studios
            FRANCHISE: Age of Empires
            RELEASE DATE: 14 Nov, 2019
         */
        /*
            Al imprimir el texto del contenedor de detalles se pudo deducir
            que se puede crear una estructura para hacer la prueba
            de una manera mas sencilla
        */
        return detailsContainer;
    }

    private void setDataStructureForTestData(){
        String initial_text = attributesContainer.getText();
        String[] splitedLines = initial_text.split("\\\n");
        String[] splitedData;
        String elementValue;
        dataHash = new HashMap();
        for(String line : splitedLines){
            splitedData = line.split("\\:");

            //El primer elemento después del elemento de la llave
            elementValue = splitedData[1];

          /*  Se concatenan todos los elementos poniendo al inicio ':' ya que
            es el caractér por el cual se hace el split entre la llave y
            el valor */
            for(int i = 2; i < splitedData.length; i++){
                elementValue += ":" + splitedData[i];
            }

            dataHash.put(splitedData[0], elementValue);
        }
    }

    public String getInfoByKey(String key){
        return dataHash.get(key);
    }
}
