package appium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefs{
    public AndroidDriver<AndroidElement> driver;
    DesiredCapabilities caps;

    public final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public final String APP_ID = System.getenv("BROWSERSTACK_APP_ID");
    public final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public JavascriptExecutor jse;

    @Before
    public void setup() throws Exception{
        caps = new DesiredCapabilities();
        caps.setCapability("device", "Samsung Galaxy S9");
        caps.setCapability("os_version", "8.0");
        caps.setCapability("name", "parallel_test");
        caps.setCapability("build", "browserstack-appium-java-cucumber-example");
        if(APP_ID!=null){
            System.out.println("Setting from Environment variable");
            caps.setCapability("app", APP_ID);
        }
        else
            caps.setCapability("app", "wiki_app_new");

        driver = new AndroidDriver(new URL(URL), caps);
        jse = (JavascriptExecutor)driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Given("Click on Search box")
    public void click_on_search_box() throws MalformedURLException, InterruptedException {
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
    }
    @When("{string} is searched")
    public void is_searched(String wikiSearch) throws InterruptedException {
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        System.out.println(insertTextElement.getText());
        insertTextElement.sendKeys(wikiSearch);
        System.out.println(insertTextElement.getText());
    }
    @Then("Display Products")
    public void display_Products() {
        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        Assert.assertTrue(allProductsName.size() > 0);
        if(allProductsName.size() > 0) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Validated\"}}");
        }
        else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Not Validated\"}}");
        }
    }

    @After
    public void teardown(Scenario scenario){
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+scenario.getName()+"\" }}");
        driver.quit();
    }
}
