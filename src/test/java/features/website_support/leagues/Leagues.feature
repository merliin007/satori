Feature: Leagues creation
  This feature exercises creating and editing a new league

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User creates a new league
    When I navigate to the following page option
      | Website Support | League Templates and Scheduled Dates |  |
    And I create a new league
    When I enter the following data in Description tab
      | LeagueType            | Year | Season | PlayDay | VPName      | RosterDocId | PacketDocId | AgeType              | MinAge | MaxAge | ScoreCardType | Lights | Tiebreaker |
      | Automated Test League | 2019 | Winter | Friday  | 100-573-938 | 21          | 441         | Before Add to Roster | 10     | 18     | Senior Mixed  | no     | no         |
    And I enter the following data in Dates tab
      | EndDate    | CaptMeeting | PlayWeek   |
      | 01/11/2019 | 01/18/2019  | 01/25/2019 |
    Then I save my new league without errors
#    And I search for such league and delete it