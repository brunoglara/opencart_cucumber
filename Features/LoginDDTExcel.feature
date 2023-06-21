Feature: Login Data Driven Test with Excel

  @sanity
  Scenario Outline: Login Data Driven Test with Excel
    Given User Launch a browser
    And opens the main URL of Open Cart
    When User navigate to Login form
    And User enters Email and Password with Excel row "<row_index>"
    And submit to validate the credentials
    Then Check user navigates to MyAccount Page with Excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
