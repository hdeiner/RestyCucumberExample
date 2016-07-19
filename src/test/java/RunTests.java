package test.java;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
//      dryRun   = false,
//      strict = true,
//      tags     = "",
        monochrome = false,
        features = { "src/test/resources/features/" },
        glue     = {"test.java"},
        plugin   = { "pretty", "html:target/cucumber-reports/cucumber-html-report", "json:target/cucumber-reports/cucumber-json-report.json" }
)

public class RunTests {

}