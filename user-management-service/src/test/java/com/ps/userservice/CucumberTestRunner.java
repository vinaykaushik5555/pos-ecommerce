package com.ps.userservice;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.ps.userservice.stepdefs",
        plugin = {"pretty", "json:target/cucumber-report.json","html:target/cucumber-html-report.html"}
)
@SpringBootTest
public class CucumberTestRunner {
}