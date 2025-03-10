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
Feature: Purchase order from Ecommerce  website 
  I want to use this template for my feature file
#Background:             #this step is used to define prerequisits or common steps in cucumber 
#I landed on Ecommerce page 

@Regression
  Scenario Outline: Positive Test of submitting the order 
    Given  I landed on Ecommerce page 
    Given  logged in with username "<email>" and password "<password>" 
    When I add product "<productname>" to the cart
    And check out "<productname>" and submit the order
    Then "Thankyou for the order." message should get displyed

        Examples: 
      | email                       | password      | productname   |
      | soundaryapacharya@gmail.com | Test@123      | IPHONE 13 PRO |
      #Examples:
      #|productname|
      #|IPHONE 13 PRO |
