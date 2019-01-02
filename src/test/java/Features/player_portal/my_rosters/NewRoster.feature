Feature: Roster creation
  This feature exercises creating a new roster

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Member" portal

  Scenario: User creates a new Roster
    When I navigate to the following page option
      | My Rosters & New/Reinstate | My Rosters                |                 |
    And I create a new roster selecting "Wheelchair - Test League" league
    When I enter the following players on Players tab
      | ALTA_Number | First  | Last        | Captain | CoCaptain | Designee |
      | 100-392-308 | VICKI  | BACHMAN     | Yes     |           |          |
      | 101-223-223 | JESSIE | ABRAHAM     |         | Yes       |          |
      | 100-840-014 | RUBI   | BENNETT     |         |           | yes      |
      | 102-878-938 | SOFIA  | CENCIARELLI |         |           |          |
    When I select the following facility
      | Id | Name             |City|
      | 14 | ATHLETIC CLUB NE |    |
    And I select "C-3" Level flight
    Then I save my new roster without errors
