package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import pages.HomePage;
import settings.BaseTest;
import components.NavBar;
import settings.ScreenshotMethods;
import java.util.List;

public class HomePageTests extends BaseTest {

    private NavBar navBar;
    private HomePage homePage;

    @BeforeTest
    public void setUp(){
        navBar = new NavBar(this.driver);
        homePage = new HomePage(driver);
    }

    @Test(enabled = true)
    public void openAllNavBarTabs() throws Exception {

        navBar.openYourStoreTab();
        ScreenshotMethods.takeScreenshot(driver);
        navBar.openGamesTab();
        ScreenshotMethods.takeScreenshot(driver);
    }

    @Test(enabled=true)
    public void testSuggestedGames() throws Exception {
        String game_name = "Portal";
        navBar.writeGameIntoSearchInput(game_name);
        List<String> games = navBar.getSuggestedGames();
        ScreenshotMethods.takeScreenshot(driver);

        Assert.assertTrue(games.size() == 5, "The number of suggested games is not correct");
        for(String game: games){
            Assert.assertTrue(game.indexOf("Portal") >= 0, "The game " + game + " is not correct. Not contains Search String " + game_name);
        }
    }

    @Test
    public void clickingOnFakeElement(){
        homePage.clickOnFakeButton();
    }

}

