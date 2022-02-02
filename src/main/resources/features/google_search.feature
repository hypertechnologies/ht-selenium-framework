Feature: Just testing a feature

  Scenario: Search for a vehicle
    Given Open the "chrome"
    And Launch the url "https://www.google.com/"
    Then Search for "Awesome Sauce"
    And Wait for "3000" milliseconds
    And Close Browser
