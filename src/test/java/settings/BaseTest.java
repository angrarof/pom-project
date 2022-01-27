package settings;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void suitSetup() {
        driver = new DriverSetup("chrome",false).getDriver();
        driver.get("https://store.steampowered.com/");

    }

    @BeforeClass
    public void beforeEachClass() throws Exception{
        ScreenshotMethods.createTestFolder("Scenario Name");
    }

    @BeforeMethod
    public void beforeEachMethod() throws Exception {
        ScreenshotMethods.screenshotSetup();
    }

    @AfterMethod
    public void afterEachMethod()throws Exception{
        ScreenshotMethods.screenshotTearDown();
    }

    @AfterSuite
    public void suiteTearDown(){
        driver.close();
        driver.quit();
    }
}
