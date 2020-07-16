package pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagesTestPage extends TheInternetPage {

    //The container of the images
    @FindBy(css = ".example img")
    private List<WebElement> exampleImages;

    //Attribute for filter the kinds of images
    private List<WebElement> completeImages;
    private List<WebElement> failedImages;

    public ImagesTestPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Returns the images than loaded well
    public List<WebElement> getCompleteImages(){
        getImagesFromSrc();
        return completeImages;
    }

    /*
        Make the requests for get the images from the server
     */
    public void getImagesFromSrc(){
        //Instantiates lists for filtered images
        completeImages = new ArrayList<WebElement>();
        failedImages = new ArrayList<WebElement>();

        //Loop for make the request for each image
        for(WebElement img : exampleImages){
            HttpResponse response = null;
            try {
                //Waiting for the response from the server
                response = new
                        DefaultHttpClient().execute(new HttpGet(img.getAttribute("src")));

                //Filtering the images depending on her status
                if (response.getStatusLine().getStatusCode() != 200)
                    failedImages.add(img);
                else
                    completeImages.add(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Returns the images that its loading was not successful
    public List<WebElement> getFailedImages(){
        getImagesFromSrc();
        return failedImages;
    }





}
