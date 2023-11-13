
Feature: Title of your feature
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @LoginErrorValidation
  Scenario Outline: Login using wrong credentials and observe the result
    Given logged in with username <username> and password <password>
    Then "Incorrect email or password." message should be displayed
  	And close the browser
  	
  	 Examples: 
      | username  						 | password 			|
      | anubhavgupta@gmail.com | Anubhav@ 			|
