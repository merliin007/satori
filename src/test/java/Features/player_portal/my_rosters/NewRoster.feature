Feature: Roster creation
  This feature exercises creating users and use them to create a couple of rosters

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Member" portal

  Scenario: User creates a new Roster
    When I navigate to the following page option
      | My Rosters & New/Reinstate | My Rosters |  |
    And I create a new roster selecting a league matching
      | Year | Season | League Type   |
      | 2019 | Winter | Automated Test League |
#      | 2019 | Winter | Mixed doubles |
    When I enter the following players on Players tab
      | ALTA_Number | First  | Last        | Captain | CoCaptain | Designee |
      | 100-392-308 | VICKI  | BACHMAN     | Yes     |           |          |
      | 101-223-223 | JESSIE | ABRAHAM     |         | Yes       |          |
      | 100-840-014 | RUBI   | BENNETT     |         |           | yes      |
      | 102-878-938 | SOFIA  | CENCIARELLI |         |           |          |
    And I select the captains
    Then I select the following facility
      | Id | Name             | City |
      | 14 | ATHLETIC CLUB NE |      |
    And I select designees
    And I select "C-3" Level flight
    Then I save my new roster without errors
