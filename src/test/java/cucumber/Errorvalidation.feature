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
@tag
Feature: VALIDATE Error message when logged in with wrong credentials 
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Negative scenario
    Given  I landed on the Ecommerce page 
    Given  logged in with the username "<email>" and password "<password>"
    Then "Incorrect email or password" message is displayed
    Examples: 
      |email                       | password      | 
      | soundaryapacharya@gmail.com | Test@1234    | 
     
