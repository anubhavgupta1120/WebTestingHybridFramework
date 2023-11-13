package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/Cucumber", glue = "StepDefinitions",
monochrome = true, plugin = {"html:CucumberReports/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests{
	
}
