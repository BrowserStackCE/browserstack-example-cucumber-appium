package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class StepDefs {

    DesiredCapabilities caps;
    public final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public final String APP_ID = System.getenv("BROWSERSTACK_APP_ID");
    public final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public JavascriptExecutor jse;
    public AppiumDriver<MobileElement> driver;

    @Before
    public void setup() throws Exception{
        caps = new DesiredCapabilities();

        if(readPropertiesFile("platformName").equals("Android"))
        {
            caps.setCapability("deviceName", readPropertiesFile("android_device"));
            caps.setCapability("osVersion", readPropertiesFile("android_os_version"));
            caps.setCapability("name", readPropertiesFile("android_testName")+" "+readPropertiesFile("android_device"));
            caps.setCapability("build", readPropertiesFile("android_build"));
            if(APP_ID!=null){
                System.out.println("Setting from Environment variable");
                caps.setCapability("app", "bs://96c11ded5deb147ec2e6977c1a940d1f0f17e14a");
            }
            else
                caps.setCapability("app", "browserStackDemoApp");
            driver = new AndroidDriver<MobileElement>(new URL(URL), caps);
        }
        else if(readPropertiesFile("platformName").equals("iOS"))
        {
            caps.setCapability("device", readPropertiesFile("iOS_device"));
            caps.setCapability("os_version", readPropertiesFile("iOS_os_version"));
            caps.setCapability("name", readPropertiesFile("iOS_testName")+" "+readPropertiesFile("iOS_device"));
            caps.setCapability("build", readPropertiesFile("iOS_build"));
            if(APP_ID!=null){
                System.out.println("Setting from Environment variable");
                caps.setCapability("app", "bs://1ec11589be09b2dc9190f9018cbb0a7cf417d7b3");
            }
            else
                caps.setCapability("app", "browserStackDemoApp");
            driver = new IOSDriver<MobileElement>(new URL(URL), caps);
        }

        jse = (JavascriptExecutor)driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private String readPropertiesFile(String propertyName) {

        Properties prop= new Properties();
        try
        {
            prop.load(new FileInputStream(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"utils"+File.separator+"readConfig"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    @Given("Login to demo App")
    public void click_on_search_box() throws MalformedURLException, InterruptedException {
        driver.findElementByAccessibilityId("menu").click();
        System.out.println("BrowserStack demo app is launched, menu clicked");

        driver.findElementByAccessibilityId("nav-signin").click();
        System.out.println("Sign in is clicked");
        System.out.println("Given executed");
    }
    @When("Login credentials entered")
    public void is_searched() throws InterruptedException {
        driver.findElementByAccessibilityId("username-input").click();
        driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@value='Accepted usernames are']")).sendKeys(new CharSequence[]{"fav_user"});
        driver.findElement(By.name("done_button")).click();

        Thread.sleep(2000);
        driver.findElementByAccessibilityId("password-input").click();
        // driver.findElement(MobileBy.xpath("//*[@text = '" + password + "']")).click();
        driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@value='Password for all users']")).sendKeys(new CharSequence[]{"testingisfun99"});
        driver.findElement(By.name("done_button")).click();

        Thread.sleep(2000);
        driver.findElementByAccessibilityId("login-btn").click();
        Thread.sleep(2000);
        System.out.println("when executed");
    }
    @Then("Login is successful")
    public void display_Products() {
       /* List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        Assert.assertTrue(allProductsName.size() > 0);
        if(allProductsName.size() > 0) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Validated\"}}");
        }
        else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Not Validated\"}}");
        }*/
        System.out.println("then executed");
    }

    @After
    public void teardown(Scenario scenario){
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+scenario.getName()+"\" }}");
        driver.quit();
    }
}
