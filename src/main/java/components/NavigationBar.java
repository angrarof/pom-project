package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

import java.util.ArrayList;
import java.util.List;

public class NavigationBar extends BasePage {

    @FindBy(id = "store_nav_search_term")
    private WebElement searchInput;

    @FindBy(css = ".match_name")
    private List<WebElement> suggestedGames;

    public void writeTextOnSearchInput(String text){
        enterText(searchInput,text);
    }

    public void cleanTextOnSearchInput(){
        cleanText(searchInput);
    }

    public List<String> getSuggestedGames(){
        List<String> newList = new ArrayList<>();
        for (WebElement game : suggestedGames){
            newList.add(game.getText());
        }
        return newList;
    }

    public void clickOnGame(String gameName){
        for(WebElement game : suggestedGames){
            if (game.getText().equalsIgnoreCase(gameName)){
                clickElement(game);
                break;
            }
        }
    }

    public NavigationBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
