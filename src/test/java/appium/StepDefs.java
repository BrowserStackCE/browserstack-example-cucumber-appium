package appium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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

public class StepDefs{
    public AndroidDriver<AndroidElement> driver;
    DesiredCapabilities caps;

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";


    @Given("Open App")
    public void open_App() throws MalformedURLException, InterruptedException {

        caps = new DesiredCapabilities();
        caps.setCapability("device", "Samsung Galaxy S9");
        caps.setCapability("os_version", "8.0");
        caps.setCapability("name", "parallel_test");
        caps.setCapability("build", "Cucumber Parallel Execution");
        caps.setCapability("app", "BROWSERSTACK_APP_ID");

        driver = new AndroidDriver(new URL(URL), caps);

    }
    @When("{string} is searched")
    public void is_searched(String wikiSearch) throws InterruptedException {

        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        System.out.println(insertTextElement.getText());
        insertTextElement.sendKeys(wikiSearch);
        System.out.println(insertTextElement.getText());
        Thread.sleep(5000);
    }
    @Then("Display Products")
    public void display_Products() {
        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        Assert.assertTrue(allProductsName.size() > 0);

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        if(allProductsName.size() > 0) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Validated\"}}");
        }
        else{
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Not Validated\"}}");
        }
        driver.quit();
    }
}
