Feature: Login using Database Data

Scenario: Login with credentials from DB

Given Launch the browser
And I connect to database
When I fetch login data from database
And I open the login page
And I login using database credentials
Then I should see the products page