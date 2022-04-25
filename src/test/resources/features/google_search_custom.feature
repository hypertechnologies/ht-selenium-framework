Feature: Testing a feature with custom keywords

  Scenario: Search for a vehicle
    Given Navigate to the url "https://www.google.com/"
    # Custom
    When Search for "Awesome Sauce"
    And Wait for "3000" milliseconds
    Then Check visibility of an element with selector type "id" and selector value "result-stats"
