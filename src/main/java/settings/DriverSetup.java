package settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
    private WebDriver driver;

    public DriverSetup(String browser, boolean headless){
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(headless){
                chromeOptions.addArguments("--headless");
            }
            driver = new ChromeDriver(chromeOptions);
        }else if(browser.equalsIgnoreCase("firefox")){
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(headless){
                firefoxOptions.addArguments("--headless");
            }
            driver = new FirefoxDriver(firefoxOptions);
        }
        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WaitingTimeSetup.getWaitImplicitly(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(WaitingTimeSetup.getWaitForPageLoad(), TimeUnit.SECONDS);
    }

    public WebDriver getDriver(){
        return this.driver;
    }

}
