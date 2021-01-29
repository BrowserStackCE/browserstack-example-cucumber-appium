package appium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//This runner will feature file with Tag @Feature3
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/appium", glue = "appium", tags = "@Feature3")
public class Runner3 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
