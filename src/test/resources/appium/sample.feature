Feature: First Appium Test
  @Feature2
  Scenario Outline: Wiki Search - <company>
    Given Open App
    When  '<company>' is searched
    Then  Display Products
    Examples:
      |company       |
      |BrowserStack  |
      |Worldline     |
      |Amazon        |
      |Vivo          |
      |iBall         |
