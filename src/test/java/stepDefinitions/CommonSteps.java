package stepDefinitions;

import io.cucumber.java.en.Given;

public class CommonSteps {
    @Given("Open the url {string} on {string}")
    public void openTheUrl(String url, String browser) {
        KeywordSteps.openBrowser(browser);
        KeywordSteps.gotToURL(url);
    }
}
