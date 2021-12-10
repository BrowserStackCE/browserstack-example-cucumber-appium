Feature: First Appium Test
  @list3
  Scenario Outline: Wiki Search - <company>
    Given Click on Search box
    When  '<company>' is searched
    Then  Display Products
    Examples:
      |company       |
      |BrowserStack  |
      |Worldline     |
      |Amazon        |
      |Vivo          |
      |iBall         |
