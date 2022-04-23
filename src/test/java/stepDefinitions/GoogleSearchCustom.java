package stepDefinitions;

import io.cucumber.java.en.Then;

public class GoogleSearchCustom {
    @Then("Search for {string}")
    public void searchFor(String text) {
        KeywordSteps.type(text,"cssSelector", "input[name='q']");
        try {
            KeywordSteps.wait("2000");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeywordSteps.click("cssSelector", "input[name='btnK']");
    }
}
