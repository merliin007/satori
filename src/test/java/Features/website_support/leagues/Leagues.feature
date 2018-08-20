Feature: Leagues creation
  This feature exercises creating and editing a new league

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User creates a new league then edits it and deletes it
    When I navigate to the following page option
      | Website Support | League Templates |
    And I create a new league
    When I enter the following data in Description tab
      | LeagueType  | Year | Season | PlayDay | VPName      | RosterDocId | PacketDocId | AgeType              | MinAge | MaxAge | ScoreCardType | Lights | Tiebreaker |
      | Junior Boys | 2019 | Fall   | Friday  | 100-573-938 | 21          | 441         | Before Add to Roster | 10     | 18     | Senior Mixed  | no     | no         |
    And I enter the following data in Dates tab
      | EndDate    | CaptMeeting | PlayWeek   |
      | 09/10/2019 | 09/17/2019  | 09/24/2019 |
    Then I save my new league without errors