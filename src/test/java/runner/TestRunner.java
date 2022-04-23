package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:src/test/resources/htmlReport/cucumber-html-report.html",
                "json:src/test/resources/htmlReport/cucumber-json-report.json"
//                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"
        },
        features= "src/test/resources/features",
        glue={"stepDefinitions"}
)

public class TestRunner { }