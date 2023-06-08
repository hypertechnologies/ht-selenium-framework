package stepDefinitions;

import io.cucumber.java.en.Given;

// Define commonly used steps here.
// Use existing KeywordSteps and/or write custom code.

public class CommonSteps {
    @Given("Open the url {string} on {string}")
    public void openTheUrl(String url, String browser) {
        KeywordSteps.gotToURL(url);
    }
}
