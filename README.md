# Selenium keyword Driven Framework
A framework to automate website using different action based keywords.

## Keywords
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

