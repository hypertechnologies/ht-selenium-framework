Feature: Just testing a feature with plain keywords

  Scenario: Search for a vehicle
    Given Open the "chrome"
    When Navigate to the url "https://www.google.com/"
    And Type "Awesome Sauce" into an element with selector type "cssSelector" and selector value "input[name='q']"
    And Wait for "2000" milliseconds
    And Click on an element with selector type "cssSelector" and selector value "input[name='btnK']"
    Then Check visibility of an element with selector type "id" and selector value "result-stats"
    And Close browser
