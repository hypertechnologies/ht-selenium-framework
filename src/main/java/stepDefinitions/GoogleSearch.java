package stepDefinitions;

import io.cucumber.java.en.Then;

public class GoogleSearch {
    @Then("Search for {string}")
    public void searchFor(String text) {
        Keywords.type("cssSelector", "input[name='q']", text);
        Keywords.click("cssSelector", "input[name='btnK']");
    }
}
