#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Purchase the Products from Ecommerce site.

	Background:
	Given I landed on Ecommerce Page

  @PlaceOrder
  Scenario Outline: Placing the order in Ecommerce Site
    Given logged in with username <username> and password <password>
    When I add products <product_list> to cart
    And checkout <product_list> and place the order
    Then "THANKYOU FOR THE ORDER." message displayed on confirmation page
		And close the browser
    Examples: 
      | username  						 | password 			| product_list  							|
      | anubhavgupta@gmail.com |     Anubhav@11 | ADIDAS ORIGINAL,ZARA COAT 3 |
 
 	 @VerifyOrderHistory
   Scenario Outline: Cheking order History page
   Given logged in with username <username> and password <password>
   And I moved to the Order Page
   Then <product_list> should be displayed on the page
   And close the browser
   
    Examples: 
      | username  						 | password 			| product_list  							|
      | anubhavgupta@gmail.com |     Anubhav@11 | ADIDAS ORIGINAL,ZARA COAT 3 |
   