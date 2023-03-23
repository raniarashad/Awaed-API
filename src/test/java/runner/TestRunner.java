package runner;

import io.cucumber.testng.CucumberOptions;
import steps.APITests;

@CucumberOptions(features="src/test/java/features",
        glue= {"steps"},
        plugin= {"pretty","html:target/cucumber-html-report"})
public class TestRunner extends APITests {

}
