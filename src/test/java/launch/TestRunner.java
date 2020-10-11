package test.java.launch;


import io.cucumber.java8.En;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import test.java.utils.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report.html","json:target/json/report.json"},
        features = "src/test/resources/features",
        glue= {"test.java.stepDef","test.java.utils"},
        monochrome = true //,
        //publish = true
)
public class TestRunner extends Hooks implements En {



}