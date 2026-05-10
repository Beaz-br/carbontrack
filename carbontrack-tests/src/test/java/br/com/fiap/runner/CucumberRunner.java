package br.com.fiap.runner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.plugin", value =
        "pretty," +
        "html:target/cucumber-reports/report.html," +
        "json:target/cucumber-reports/report.json," +
        "junit:target/cucumber-reports/report.xml")
@ConfigurationParameter(key = "cucumber.glue", value = "br.com.fiap.steps")
@ConfigurationParameter(key = "cucumber.publish.quiet", value = "true")
public class CucumberRunner {
}
