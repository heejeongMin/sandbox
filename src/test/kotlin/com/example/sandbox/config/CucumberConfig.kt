package com.example.sandbox.config

import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.SelectClasspathResource

@SelectClasspathResource("cucumber")
@CucumberContextConfiguration
class CucumberConfig {
}