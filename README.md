![BrowserStack Logo](https://camo.githubusercontent.com/09765325129b9ca76d770b128dbe30665379b7f2915d9b60bf57fc44d9920305/68747470733a2f2f7777772e62726f77736572737461636b2e636f6d2f696d616765732f7374617469632f6865616465722d6c6f676f2e6a7067)


# BrowserStack Example - Appium Java Cucumber

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
      - Get app uploaded hash id and set environment variables-
        ````
        export BROWSERSTACK_ANDROID_APP_ID=<if uploaded android app> 
        export BROWSERSTACK_IOS_APP_ID=<bif uploaded iOS app>
        ````
      If you want to use a constant value to specify the application under test and don’t want to modify your test scripts after every app upload, you can define a [custom ID](https://www.browserstack.com/docs/app-automate/appium/upload-app-define-custom-id) for your app. 
* Tha Android app used in this repo can be found [here](https://github.com/BrowserStackCE/browserstack-example-cucumber-appium/blob/a47aa31e8bf000b26a38c2a0577d84d716995b5a/src/test/resources/app/browserstack-demoapp.apk).
* Tha iOS app used in this repo can be found [here](https://github.com/BrowserStackCE/browserstack-example-cucumber-appium/blob/a47aa31e8bf000b26a38c2a0577d84d716995b5a/src/test/resources/app/browserstack-demoapp.ipa).
* You can view your test results on the [BrowserStack App Automate dashboard](https://app-automate.browserstack.com/)
* To test on a different set of browsers, check out our [platform configurator](https://www.browserstack.com/docs/app-automate/appium/set-up-tests/select-devices).

## Steps to execute test
* Update src/test/resources/utils/readConfig paramters as per your choice (**platformName** #Android or #iOS) , Whether you want to execute on iOS or android platform.
* Configure the `src/test/resources/utils/single.testng.xml` to execute the Runner file of your choice. You can choose to run one or all of them together.
* Run test - `mvn test -P SingleRun`
    ### Parallel tests
    Configure the parallel testing capabilities in the `single.testng.xml` file by setting the values for `data-provider-thread-count` and `parallel`.

    ### Tags
    Use [Cucumber tags](https://cucumber.io/docs/cucumber/api/#tags) to run a subset of your tests. 
    Example:
    * ``@CucumberOptions(...tags = {"@tag1,@tag2"}...)`` - scenarios that have both the tags
    * ``@CucumberOptions(...tags = "@tag1 or @tag2"...)``-> scenarios that have either of these tags
    * ``@CucumberOptions(...tags = {"~@tag1"}...)`` -> excluding scenarios that have this tag
  
    When no tags are specified feature files are picked up in alphabetical order (ASCII)
    
