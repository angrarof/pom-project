package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GamePage extends BasePage {
    @FindBy(css = ".apphub_AppName")
    private WebElement gameTittle;

    @FindBy(xpath = "//div[@class='details_block'][1]")
    private WebElement gameDetailsBox;

    public void validateGameTittleOnPage(String gameSearch){
        waitElementToAppear(gameTittle);
        validateText(getElementText(gameTittle), gameSearch);
        System.out.println("You are on page of game: "+gameSearch.toUpperCase());
    }

    public void validateGameDetails(String expectedDetails){
        scrollToElement(gameDetailsBox);
        String templateNoRelease = getElementText(gameDetailsBox).substring(0,getElementText(gameDetailsBox).length()-26);

        /*String[] arrayDetails = templateNoRelease.replace("\n",":").split(":");
        List<String> listOfDetails = new ArrayList<>();
        for (String detail : arrayDetails){
            listOfDetails.add(detail);
        }
        System.out.println(listOfDetails);*/

        System.out.println("You are expecting following Game Details:\n"+expectedDetails);
        System.out.println("You obtained following Game Details:\n"+templateNoRelease);
        validateText(expectedDetails,templateNoRelease);
    }

    public GamePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
