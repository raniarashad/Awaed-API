Feature: Add new user and get the created one

  Scenario: Add a new user and retrieve by id
    Given I have a user with first name "Rania" and last name "Rashad" and email "rania.rashad@gmail.com"
    When I create a user
    Then the status code should be 201
    And I retrieve the created user by id
    Then the first name should be "Rania"