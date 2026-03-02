Feature: Login Test

Scenario: Valid login using DataTable

Given Launch the browser
When I open the login page
And I enter the following credentials
  | username      | password      |
  | standard_user | secret_sauce  |
And I click on login button
Then I should see the products page