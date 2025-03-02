package com.ps.userservice;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("test")  // Optional: Use a specific profile for testing if you have one
public class CucumberSpringConfiguration {
}
