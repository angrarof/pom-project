package settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

abstract public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver,WaitingTimeSetup.getWaitForElement());
        actions = new Actions(this.driver);
    }

    public void waitElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getElementText(WebElement element){
        return element.getText().trim().toLowerCase();
    }

    public void enterText(WebElement element, String text){
        waitElementToAppear(element);
        element.sendKeys(text);
    }

    public void cleanText(WebElement element){
        waitElementToClickable(element);
        element.clear();
    }

    public void clickElement(WebElement element){
        waitElementToAppear(element);
        waitElementToClickable(element);
        element.click();
    }

    public void validateText(String expected, String obtained){
        Assert.assertEquals(expected.toLowerCase().trim(), obtained.toLowerCase().trim());
    }

    public void scrollToElement(WebElement element){
        waitElementToAppear(element);
        actions.moveToElement(element);
        actions.perform();
    }
	
	public void writeInInput(WebElement element, String message){
        waitElementToAppear(element);
        element.sendKeys(message);
    }

    public void hoverElement(WebElement element){
        assertTrue(true);
        this.waitElementToAppear(element);
        actions.moveToElement(element).perform();
    }

    //A list with the expected conditions that we can use for explicit waits
    //https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html

    public boolean isTextPresentOnElement(WebElement element, String text){
        this.waitElementToAppear(element);
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForElementsToAppear(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean elementAttributeIs(WebElement element, String attribute, String value){
        this.waitElementToAppear(element);
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    //HomeWork
    /*
    * Create a function that accepts like a parameter retry value and a WebElement
    * This function will retry to click the WebElement the integer assigned to value.
    *
    * Example -> retryClick(fancyButtonElement, 3)
    *
    * */

    public void retryClickElement(WebElement element, int times){
        boolean flag = false;
        for (int i=0; i<times; i++){
            try {
                waitElementToAppear(element);
                element.click();
                flag = true;
                break;
            }catch (Exception e){
                System.out.println("Element not found. Retrying click on element: "+element);
            }
        }
        Assert.assertTrue(flag,"Element '"+element+"' was never found.");
    }

}
