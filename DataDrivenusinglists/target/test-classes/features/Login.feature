Feature: Login Test using List DataTable

Scenario: Valid login using List

Given Launch the browser
When I open the login page
And I enter the credentials
  | standard_user |
  | secret_sauce  |
And I click on login button
Then I should see the products page