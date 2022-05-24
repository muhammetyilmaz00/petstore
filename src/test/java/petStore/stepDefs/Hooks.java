package petStore.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.PropertyConfigurator;
import petStore.utils.LogUtils;

import java.util.Collection;

public class Hooks {

    private static Scenario scenario;
    public static Scenario getScenario(){
        return scenario;
    }
    public static String getScenarioName(){
        return getScenario().getName();
    }
    public static Collection<String> getScenarioTags(){
        return scenario.getSourceTagNames();
    }

    @Before
    public void init(Scenario scenario) {
        Hooks.scenario = scenario;
    }

    @Before
    public void initLog4j() {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        LogUtils.logInfo("Started Scenario: "+getScenarioName());
    }

    @Before
    public void setUp(Scenario scenario) {
//        isAPI = scenario.getSourceTagNames().contains("@api");
//        if(!isAPI) Driver.setUp();
    }

    @After
    public void tearDown(Scenario scenario) {
//        if (!isAPI && scenario.isFailed()) {
//            final byte[] screenshot = (
//                    (TakesScreenshot) Driver.driver
//            ).getScreenshotAs(OutputType.BYTES);
//
//            scenario.attach(screenshot, "image/png", "screenshot");
//        }
//        Driver.closeDriver();
        LogUtils.logInfo("Finished Scenario: "+getScenarioName());
    }
}
