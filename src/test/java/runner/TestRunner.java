package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/features"},
        format = {"json:target/cucumber.json", "html:target/site/satori-report"},
        glue = {"common", "steps"},
        tags = {"@sanity"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
