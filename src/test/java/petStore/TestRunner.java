package petStore;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json"
        },
        features = {"src/test/resources/features"},
        glue = {"petStore/stepDefs"},
        tags = "@petStore"
)

// this class runs features which have specified the "tags"
public class TestRunner {}