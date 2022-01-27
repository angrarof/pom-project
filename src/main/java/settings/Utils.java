package settings;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
    public static void highlightElement(WebDriver driver, WebElement element, boolean scroll){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(scroll){
            js.executeScript("arguments[0].scrollIntoView();",element);
        }
        js.executeScript("arguments[0].style.border='3px solid yellow'",element);

    }
}
