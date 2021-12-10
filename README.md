## BrowserStack Appium Java Cucumber Sample

This repo demonstrates a simple way of running your Appium Java Cucumber tests on BrowserStack. This repo uses Testng as the runner.

## Pre-requisites

* Set BrowserStack credentials
    * Identify your BrowserStack username and access key from the [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) and export them as environment variables using the below commands.
        - For *nix based and Mac machines:
            ```
            export BROWSERSTACK_USERNAME=<browserstack-username> &&
            export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
            ```
        - For Windows:
            ```
            set BROWSERSTACK_USERNAME=<browserstack-username>
            set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
            ```
* Upload your app on BrowserStack
  Upload your Android app (.apk or .aab file) or iOS app (.ipa file) to BrowserStack servers using our [REST API](https://www.browserstack.com/docs/app-automate/appium/upload-app-from-filesystem). Here is an example cURL request :
  ```
  curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
  -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
  -F "file=@/path/to/apk/file"
  ```
  If you want to use a constant value to specify the application under test and don’t want to modify your test scripts after every app upload, you can define a [custom ID](https://www.browserstack.com/docs/app-automate/appium/upload-app-define-custom-id) for your app. 
* Tha app used in this repo can be found [here](/Users/nithyamani/Desktop/BrowserStackCE repo migration/cucumber-appium-demo/src/test/resources/app/WikipediaSample.apk).
* You can view your test results on the [BrowserStack App Automate dashboard](https://app-automate.browserstack.com/)
* To test on a different set of browsers, check out our [platform configurator](https://www.browserstack.com/docs/app-automate/appium/set-up-tests/select-devices).

## Steps to execute test
* Run test - `mvn test -P single`
* Configure the `config/wiki-search.testng.xml` to execute the Runner file of your choice. You can choose to run one or all of them together.
    ### Parallel tests
    Configure the parallel testing capabilities in the `wiki-search.testng.xml` file by setting the values for `data-provider-thread-count` and `parallel`.

    ### Tags
    Use [Cucumber tags](https://cucumber.io/docs/cucumber/api/#tags) to run a subset of your tests. 
    Example:
    * ``@CucumberOptions(...tags = {"@tag1,@tag2"}...)`` - scenarios that have both the tags
    * ``@CucumberOptions(...tags = "@tag1 or @tag2"...)``-> scenarios that have either of these tags
    * ``@CucumberOptions(...tags = {"~@tag1"}...)`` -> excluding scenarios that have this tag
  
    When no tags are specified feature files are picked up in alphabetical order (ASCII)
    
