Feature: Login Data Driven Test

  @sanity
  Scenario Outline: Login Data Driven
    Given User Launch a browser
    And opens the main URL of Open Cart
    When User navigate to Login form
    And User enters Email as "<email>" and Password as "<password>"
    And submit to validate the credentials
    Then User must be "<status>" in

    Examples: 
      | email              | password  | status     |
      | teste@gmail.com    | test@123  | logged     |
      | teste2@gmail.com   | qwertyuio | logged     |
      | asdfasdf@gmail.com | 4565sdfas | not logged |
