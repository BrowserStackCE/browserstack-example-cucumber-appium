Feature: First Appium Test
  @list1
  Scenario Outline: Wiki Search - <company>
    Given Click on Search box
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

  @list2
  Scenario: Wiki Search - Myntra
    Given Click on Search box
    When  'Myntra' is searched
    Then  Display Products


