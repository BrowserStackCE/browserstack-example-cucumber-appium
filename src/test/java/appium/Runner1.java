package appium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//This runner will run both the feature files (all tags)
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources/features/bsDemo.feature", glue = "appium")
public class Runner1 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

/*
Tags:
@CucumberOptions:
tags = {"@tag1,@tag2"} -> tags = "@tag1 and @tag2" -> scenarios that have both the tags
                          tags = "@tag1 or @tag2" -> scenarios that have either of these tags

tags = {"~@tag1"} -> tags = "not @tag1" -> excluding scenarios that have this tag

When no tags are specified feature files are picked up in alphabetical order (ASCII)
*/