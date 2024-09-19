package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/java/features",
        glue = {"stepDefinitions", "utility"},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:cucumber.json", "junit:target/cucumber-junit-report.xml"}
)
//@Test
public class TestRunner {
}
