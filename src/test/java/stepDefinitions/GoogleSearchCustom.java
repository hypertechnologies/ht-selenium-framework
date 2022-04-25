package stepDefinitions;

import io.cucumber.java.en.Then;

// Define feature file specific steps here. Add more steps file for different features/
// You can use existing CommonSteps, KeywordSteps and/or write custom code.

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
