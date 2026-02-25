Feature: Login Test

  Scenario: Valid login
    Given Launch the browser
    When I open the login page
    And I enter the valid username and password
    And I click on login button
    Then I should see the products page
