package tests;

import components.NavigationBar;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GamePage;
import settings.BaseTest;
import settings.ScreenshotMethods;

public class SteamTest extends BaseTest {

    private NavigationBar navigationBar;
    private String gameToSearch;
    private GamePage gamePage;

    @BeforeTest
    public void setUp(){
        navigationBar = new NavigationBar(driver);
        navigationBar.writeTextOnSearchInput("Testing nav bar before test");
        navigationBar.cleanTextOnSearchInput();
        gamePage = new GamePage(driver);
    }

    @Test(groups = "regression")
    public void validateSuggestedGames() throws Exception{
        gameToSearch = "slug";
        navigationBar.writeTextOnSearchInput(gameToSearch);
        int foundedGames = navigationBar.getSuggestedGames().size();
        Assert.assertTrue(foundedGames>=4,"There number of displayed games is less than 4");
        System.out.println("There are "+foundedGames+" displayed games");

        for (String game : navigationBar.getSuggestedGames()){
            Assert.assertTrue(game.toLowerCase().contains(gameToSearch),"Suggested games does not contains expected word");
            System.out.println(game+" contain the word "+gameToSearch);
        }

        ScreenshotMethods.takeScreenshot(driver);
    }

    @Test(groups = "smoke")
    public void searchGame() throws Exception{
        gameToSearch = "million arthur: arcana blood";
        navigationBar.writeTextOnSearchInput(gameToSearch);
        navigationBar.clickOnGame(gameToSearch);
        gamePage.validateGameTittleOnPage(gameToSearch);
        ScreenshotMethods.takeScreenshot(driver);
    }

    @Test(groups = "login")
    public void validateGameInformation() throws Exception{
        gameToSearch = "Age of Empires II: Definitive Edition";
        navigationBar.writeTextOnSearchInput(gameToSearch);
        navigationBar.clickOnGame(gameToSearch);
        gamePage.validateGameTittleOnPage(gameToSearch);
        String gameDetailsTemplate =
                "TITLE: Age of Empires II: Definitive Edition\n" +
                "GENRE: Strategy\n" +
                "DEVELOPER: Forgotten Empires, Tantalus Media, Wicked Witch\n" +
                "PUBLISHER: Xbox Game Studios\n" +
                "FRANCHISE: Age of Empires";
        gamePage.validateGameDetails(gameDetailsTemplate);
        ScreenshotMethods.takeScreenshot(driver);
    }

    @Test()
    public void baseTest(){
        System.out.println("Test 1");
    }

}
