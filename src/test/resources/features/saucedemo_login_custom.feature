Feature: Saucedemo login with custom steps

  Scenario: User should be able to login
    When I navigate to the url "https://www.saucedemo.com/"
    And I fill out the login form
    And I click on login button
    Then I should see a checkout icon