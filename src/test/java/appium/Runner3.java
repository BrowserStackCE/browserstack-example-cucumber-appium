package appium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//This runner will feature file with Tag @list2
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/features", glue = "appium", tags = "@list2")
public class Runner3 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
