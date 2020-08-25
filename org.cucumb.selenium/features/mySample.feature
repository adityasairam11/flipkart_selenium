#Author: adisai.11@gmail.com


Feature: Flipkart application automation
  I want to use this template for my feature file

  
  Scenario Outline: Shop for a product in Flipkart
    Given Login to flipkart application with valid credentials
    When I enter valid "<username>" and "<passwd>"
    Then I validate the outcome wherein user logs in successfully
    Then I search for a "<product>" and collect the data
    Then I want to sort the results and choose the product with lowest price
    Then I want to search the chosen product and add to cart

    Examples: 
      | username   | passwd    | product    |
      | 9790905946 | Gabby@23  | smartphone |
      #| 8939345822 | blahblah  |
