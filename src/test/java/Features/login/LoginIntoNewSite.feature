@loginTestCases
Feature: Login validation
  This feature exercises correct login into new site

  Scenario Outline: Login functionality for ALTA members with incorrect values
    Given User navigates to ALTA website
    When I enter username as "<username>" and password as "<password>"
    Then The login should be "<valid>"
    Examples:
      | username                             | password | valid |
      | xx                                   | 123      | false |
      | username                             |          | false |
      |                                      | password | false |
      |                                      |          | false |
      | two words                            | password | false |
      | <script><alert>hola</alert></script> | test     | false |
      | mrsSatori                            | password | true  |
      | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | false |



