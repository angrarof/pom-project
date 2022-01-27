package steps;

import com.itextpdf.text.DocumentException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.plugin.event.Result;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import settings.BaseTest;
import settings.ScreenshotMethods;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Steps {

    @Given("This is my step")
    public void thisIsMyStep() throws DocumentException {
        System.out.println("My step");
        ScreenshotMethods.addLogger("My Log");
    }

    @And("This fails")
    public void thisFails(){
        int a=1;
        Assert.assertEquals(a, 0, "The result is not the expected.");
    }

}
