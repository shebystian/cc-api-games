package cl.kranio.cc;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/"},
				plugin = {"json:target/cucumber-report.json",
          					"pretty:target/cucumber-pretty.txt"},
				glue = {"cl.kranio.cc.stepdefinitions"},
				monochrome = true, 
				strict = true)

public class RunTest {

}
