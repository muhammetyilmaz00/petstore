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
        return scenario.getName();
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

    @After
    public void tearDown(Scenario scenario) {
        LogUtils.logInfo("Finished Scenario: "+getScenarioName());
    }
}
