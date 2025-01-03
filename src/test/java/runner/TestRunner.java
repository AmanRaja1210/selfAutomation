package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "org.example.stepdefs"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html", // Unique HTML report path
                "json:target/cucumber-reports/report.json", // Unique JSON report path
                "timeline:target/cucumber-reports/timeline" // Unique timeline report path
        },
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @org.testng.annotations.DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
