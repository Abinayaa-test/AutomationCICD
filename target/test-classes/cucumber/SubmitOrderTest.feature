@purchase
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product "<productName>" to cart
    And I checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
    | name 					 | password 		| productName		 |
	|rajini123@gmail.com	 |	Rajini@123		|	ADIDAS ORIGINAL	 |