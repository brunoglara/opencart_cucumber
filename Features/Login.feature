Feature: Login with Valid Credentials

@sanity @regression
	Scenario: Successful Login with Valid Credentials
		Given User Launch a browser
		And opens the main URL of Open Cart
		When User navigate to Login form
		And User enters a valid credentials
		And submit to validate the credentials
		Then User must be logged in
 			
 @regression
	Scenario: Unsuccessful Login with invalid Credentials
		Given User Launch a browser
		And opens the main URL of Open Cart
		When User navigate to Login form
		And User enters a invalid credentials
		And submit to validate the credentials
		Then User must not be logged in			