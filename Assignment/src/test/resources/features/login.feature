Feature: Login functionality

  Background:
    Given user is on login page

  Scenario: Successful login
    When user enters username "admin" and password "admin123"
    And user clicks on login button
    Then login should be "successful"

  Scenario: Unsuccessful login
    When user enters username "user" and password "wrong123"
    And user clicks on login button
    Then login should be "unsuccessful"

  Scenario: Login using data table
    When user logs in with following credentials
      | username | password  |
      | admin    | admin123  |
      | user     | wrong123  |