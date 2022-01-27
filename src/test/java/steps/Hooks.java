package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import org.apache.commons.lang3.reflect.FieldUtils;
import settings.ScreenshotMethods;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Hooks {
    @Before
    public void beforeEachClass(Scenario scenario) throws Exception{
        System.out.println("running before");
        ScreenshotMethods.createTestFolder(scenario.getName());
        ScreenshotMethods.screenshotSetup();
    }

    @After
    public void afterScenario(Scenario scenario) throws ClassNotFoundException {
        ScreenshotMethods.screenshotTearDown();
        if (scenario.isFailed())
            System.out.println("Failed Scenario!");
            logError(scenario);
    }

    private static void logError(Scenario scenario) throws ClassNotFoundException {
        try {
            //Class clasz = ClassUtils.getClass("cucumber.runtime.java.JavaHookDefinition$ScenarioAdaptor");
            Field fieldScenario = FieldUtils.getField(Scenario.class, "scenario", true);
            fieldScenario.setAccessible(true);
            Object objectScenario =  fieldScenario.get(scenario);

            Field fieldStepResults = objectScenario.getClass().getDeclaredField("stepResults");
            fieldStepResults.setAccessible(true);

            ArrayList<Result> results = (ArrayList<Result>) fieldStepResults.get(objectScenario);
            for (Result result : results) {
                if (result.getError() != null) {
                    ScreenshotMethods.addLogger(result.getError().toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error while logging error "+e);
        }
    }
}
