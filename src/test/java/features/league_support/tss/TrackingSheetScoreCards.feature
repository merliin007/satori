Feature: TSS edition
  This feature exercises searching and editing a Scorecard from Manage ALTA

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates to TSS, searches and edits one TSS
    When I navigate to the following page option
      | League Support | Tracking Sheet Scorecards |  |
    And I search using the criteria
      | Status   | Year | Season | League        | Age Group | Original Scorecard | Level-Flight | Division | Week | Roster ID |
      | Approved | 2019 | Winter | Mixed Doubles |           |                    |              |          |      |           |
    And Selecting tracking sheet at position "1"
    And Edit tss changing
      | Week | Home Result | Home Player1 | Home Player2 | Home Set1 | Home Set2 | Home Set3 | Played Date | Away Set1 | Away Set2 | Away Set3 | Away Result | Away Player1 | Away Player2 | Home Comments   | Away Comments  |
      | 5    |             |              |              | 6         | 6         |           |             | 1         |           |           |             |              |              | Automated (66-) | Testing  (00-) |
    Then I got "updated correctly" message after save

