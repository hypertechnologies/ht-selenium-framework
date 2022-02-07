package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:src/main/resources/htmlReport/cucumber-html-report.html", "json:src/main/resources/htmlReport/cucumber-json-report.json" },
        features= "src/main/resources/features",
        glue={"stepDefinitions"}
)

public class run { }