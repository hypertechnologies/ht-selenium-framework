Feature: Saucedemo login with plain keywords

  Scenario: User should be able to login
    When I navigate to the url "https://www.saucedemo.com/"
    And I type "standard_user" into an element with selector type "cssSelector" and selector value "#user-name"
    And I type "secret_sauce" into an element with selector type "cssSelector" and selector value "#password"
    And I click on an element with selector type "cssSelector" and selector value "#login-button"
    Then I check visibility of an element with selector type "cssSelector" and selector value ".shopping_cart_link"