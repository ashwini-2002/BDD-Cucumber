package testrunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/Login.feature",
    glue = "stepdefinations",
    plugin = {"pretty"},
    monochrome = true
)
public class TestRunner {
}