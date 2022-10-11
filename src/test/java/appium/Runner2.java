package appium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//This runner will feature file with Tag @list1
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/features/bsDemo.feature", glue = "appium")
public class Runner2 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
