package settings;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void suitSetup() {
        driver = new DriverSetup("chrome",false).getDriver();
        driver.get("https://store.steampowered.com/");

    }

    @BeforeMethod
    public void beforeEachMethod() throws Exception{
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
