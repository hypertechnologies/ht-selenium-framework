package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:src/test/resources/reports/htmlReport/cucumber-html-report.html",
                "json:src/test/resources/reports/htmlReport/cucumber-json-report.json"
        },
        features= "src/test/resources/features",
        glue={"stepDefinitions"}
)

public class TestRunner { }