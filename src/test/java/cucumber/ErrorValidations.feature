@error
Feature: Error Validation

@ErrorValidation
Scenario Outline: Negative scenario
	Given I landed on Ecommerce Page
	When Logged in with username <name> and password <password>
	Then "Incorrect email or password." error message is displayed
	
	  Examples:
    | name 					 | password 		| 
	|rajini4123@gmail.com	 |	Rajini@123		|	