package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/parallel"},
		glue = {"stepdefinitions", "parallel"},
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "html:target/html-reports/report.html", "junit:target/xml-reports/report.xml", "json:target/json-reports/report.json"},		//,"timeline:test-output-thread/","json:target/json-report/cucumber.json"
		monochrome = true,
		publish = true
		)
public class Runner  {

	
}
