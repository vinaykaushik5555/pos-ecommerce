Feature: User Registration API

  Scenario: Register a new user
    When I send a POST request to "/api/v1/users/register" with dynamic JSON body
    Then the response status should be 201
