package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GoogleSearchCustom {
    @Given("Open the url {string} on {string}")
    public void openTheUrl(String url, String browser) {
        Keywords.openBrowser(browser);
        Keywords.gotToURL(url);
    }

    @Then("Search for {string}")
    public void searchFor(String text) {
        Keywords.type(text,"cssSelector", "input[name='q']");
        try {
            Keywords.wait("2000");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Keywords.click("cssSelector", "input[name='btnK']");
    }
}
