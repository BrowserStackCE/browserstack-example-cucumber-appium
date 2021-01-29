Feature: First Appium Test
  @Feature1
  Scenario Outline: Wiki Search - <company>
    Given Open App
    When  '<company>' is searched
    Then  Display Products
    Examples:
      |company    |
      |Mastercard |
      |Uber       |
      |Apple      |
      |Samsung    |
      |Ola        |
      |Flipkart   |

  @Feature3
  Scenario: Wiki Search - Myntra
    Given Open App
    When  'Myntra' is searched
    Then  Display Products


