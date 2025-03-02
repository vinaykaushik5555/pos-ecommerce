package com.ps.userservice.stepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONException;
import org.json.JSONObject;

import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceStepDefs {
    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;

    private Map<String, String> requestBody;
    private String userId;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) {
        String url1 = url+"/"+userId;
        response = restTemplate.getForEntity("http://localhost:8080" + url1, String.class);
    }
    @Then("the response should contain the email {string}")
    public void the_response_should_contain_the_email(String expectedEmail) {
        String responseBody = response.getBody();
        assertTrue(responseBody.contains(expectedEmail));
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @When("I send a POST request to {string} with body {string}")
    public void i_send_a_post_request_to_with_body(String url, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        response = restTemplate.postForEntity("http://localhost:8080" + url, request, String.class);
    }



    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String expectedBody) {
        assertTrue(response.getBody().contains(expectedBody));
    }

    @When("I send a POST request to {string} with the following JSON body:")
    public void i_send_a_post_request_to_with_json_body(String url, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        response = restTemplate.postForEntity("http://localhost:8080" + url, request, String.class);
    }

    @Given("the following user credentials:")
    public void the_following_user_credentials(io.cucumber.datatable.DataTable table) {
        requestBody = new HashMap<>();
        requestBody.put("email", table.cell(1, 0));
        requestBody.put("password", table.cell(1, 1));
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        response = restTemplate.exchange("http://localhost:8080" + url, HttpMethod.POST, request, String.class);
    }

    @Given("a user exists with email {string}")
    public void a_user_exists_with_email(String id) {
        this.userId = id;
    }

    @When("I send a POST request to {string} with dynamic JSON body")
    public void i_send_a_post_request_with_dynamic_json_body(String path) throws JSONException {
        // Prepend localhost:8080 to the API path
        String url = "http://localhost:8080" + path;

        // Generate a random email
        String randomEmail = "vinay@example.com";

        // Create dynamic JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", randomEmail);
        requestBody.put("password", "1234");
        requestBody.put("firstName", "vinay");
        requestBody.put("lastName", "kumar");
        requestBody.put("mobileNumber", 999994321);

        // Create HTTP headers and request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

        // Send POST request
        response = restTemplate.postForEntity(url, request, String.class);
    }


}
