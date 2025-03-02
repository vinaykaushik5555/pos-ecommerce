Feature: User Login

  Scenario: Successful login with valid credentials
    Given the following user credentials:
      | email             | password |
      | vinay@example.com | 1234     |
    When I send a POST request to "/api/v1/users/login"
    Then the response status should be 200
