# Selenium Cucumber Framework
A framework to automate websites.

# How to run

### Clone the repository

    git clone git@github.com:hypertechnologies/ht-selenium-framework.git

### Install maven dependencies
Always run this if you have any updates to pom.xml

    mvn clean install -Dmaven.test.skip

### Run cucumber tests

    mvn test

# View HTML Report
HTML report will be generated under `src/test/resources/reports/htmlReport`

# Selenium configurations
You can change browser name, implicit and explicit wait time from `src/test/resources/selenium.properties` file

# Cucumber configurations
You can publish the HTML report to the cucumber cloud
by changing `cucumber.publish.enabled` to true in the `src/test/resources/cucumber.properties` file

## Keywords
You can use these keywords to perform different actions on the page.

* **GoToURL** - To navigate to a url (E.g., http://google.com)
    
    * **Required params**:
      
        test_data (E.g., http://google.com)


* **Type** - To enter text into an input field or textarea.

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)
      
      locator_value (E.g., #seachField)
      
      test_data (E.g., Restaurants near me)


* **Click** - To click on a button or link.

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)
      
      locator_value (E.g., #seachBtn)


* **AssertText** - To put assertion on a visible text (E.g., check header text says Contact Us).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #header)

      test_data (E.g., Contact Us)


* **AssertAttribute** - To put assertion on an attributes (E.g., check if id of an element is xyz).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #header)

      test_data (E.g., id==header)


* **AssertCssValue** - To put assertion on a css value (E.g., check if color of a button is #000).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #btn)

      test_data (E.g., color==#000)


* **AssertTitle** - To put assertion on a page title.

    * **Required params**:

      test_data (E.g., Welcome to Amazon!)


* **RefreshPage** - To refresh or reload webpage.

    * **Required params**:

      none


* **checkVisibility** - To check if an element is visible on the page.

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #btn)


* **CheckNotVisible** - To check if an element is NOT visible on the page.

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #btn)


* **SelectByVisibleText** - To select an item from dropdown by visible text (E.g., Select "New York" from the city dropdown)..

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #selectCity)

      test_data (E.g., New York)


* **SelectByIndex** - To select an item from dropdown by index number (E.g., Select first item from the city dropdown).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #selectCity)

      test_data (E.g., 0)


* **EnableCheckBox** - To enable/check a checkbox (if the checkbox is already enabled it will just make sure that it's enabled).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #termsCheckbox)


* **DisableCheckBox** - To disable/uncheck a checkbox (if the checkbox is already disabled it will just make sure that it's disabled).

    * **Required params**:

      locator_type (E.g., cssSelector, xpath, id etc.)

      locator_value (E.g., #termsCheckbox)


* **AssertURL** - To verify the url contains some text (E.g., Make sure url contains the login page path - /login.html).

    * **Required params**:

      test_data (E.g., /login.html)


* **SwitchToiFrame** - To switch to an iFrame on the page.

    * **Required params**:

      test_data (E.g., #iFrameId)


* **SwitchToDefaultFrame** - To switch to the default/main frame on the page.

    * **Required params**:

      none


* **SwitchTab** - To switch to a different tab on the browser by index number (For example, a link opens a page in new tab and you want to test something on that new window, you need to switch to that tab).

    * **Required params**:

      test_data (E.g., 1 for second tab, 0 to go back to original/first tab)


* **BrowserForward** - To click on the browser forward button.

    * **Required params**:

      none


* **BrowserBackward** - To click on the browser backward button.

    * **Required params**:

      none


* **DragAndDrop** - To drag one element into another element. It needs two comma separated locator_type and two comma separated locator_value for both elements.

    * **Required params**:

      locator_type (E.g., id,id)

      locator_value (E.g., #element1,#element2)


* **Wait** - To hard wait for certain milliseconds

    * **Required params**:

      test_data (E.g., 3000 to wait 3 seconds)

