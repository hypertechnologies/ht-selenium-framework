Feature: Saucedemo login with custom steps

  Scenario: Search for a vehicle
    When I navigate to the url "https://www.saucedemo.com/"
    And I fill out the login form
    And I click on login button
    Then I should see a checkout icon