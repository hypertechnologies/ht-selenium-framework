package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

// Define feature file specific steps here. Add more steps file for different features/
// You can use existing CommonSteps, KeywordSteps and/or write custom code.

public class SauceDemoCustom {
    @And("I fill out the login form")
    public void iFillOutTheLoginForm() {
        KeywordSteps.type("standard_user", "cssSelector", "#user-name");
        KeywordSteps.type("secret_sauce", "cssSelector", "#password");
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        KeywordSteps.click("cssSelector", "#login-button");
    }

    @Then("I should see a checkout icon")
    public void iShouldSeeACheckoutIcon() {
        KeywordSteps.checkVisibility("cssSelector", ".shopping_cart_link");
    }
}
