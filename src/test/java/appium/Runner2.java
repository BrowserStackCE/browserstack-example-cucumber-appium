package appium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//This runner will feature file with Tag @Feature1
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/appium", glue = "appium", tags = "@Feature1")
public class Runner2 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
