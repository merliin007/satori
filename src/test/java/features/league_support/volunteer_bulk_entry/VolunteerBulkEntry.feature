Feature: Volunteer bulk entry
  This feature exercises adding a position to a member

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal
    When I navigate to the following page option
      | League Support | Volunteer Bulk Entry |  |

  Scenario Outline: Searches for a member and adds him to a position
    When I search for <ALTA Number> ALTA number on Volunteer Bulk Entry page
    And I select the following positions for this user
      | <Position> | <Year> | <Comments> | <League> | <Season> |
    Then I get <Result> toastr

    Examples:
      | ALTA Number | Position              | Year | Comments                     | League                | Season | Result |
#      | 102-132-818 | 1st VP                | 2021 | 1st VP comment               |                       |        | error  |
#      | 102-521-618 | Board of Directors    | 2020 | Board of directors comments  |                       |        | warn   |
#      | 103-661-084 | Chairman of the Board | 2021 | Chairman comments            |                       |        | error  |
      | 102-671-737 | Overall Coordinator   | 2019 | Overall coordinator comments | Automated Test League | Fall   | error  |


