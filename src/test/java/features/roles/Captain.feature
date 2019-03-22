Feature: Captain access
  This feature exercises correct login into new site

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "101-467-605" and password as "password123"
#   Then I select "Member" portal

  Scenario: User navigates for all rebuilt website without issues
    When I navigate to the following page option
      | My Active Teams            | Schedule & Lineup         |                 |
    And I can create a lineup on week 7
    When I navigate to the following page option
      | My Active Teams            | Availability              |                 |
    And I can change availability to "No" for some members
    When I navigate to the following page option
      | My Active Teams | Calendar |  |
    And I can create a new event with the following info
      | Name       | Description        | Date       |
      | Test Event | Sample description | 01/05/2019 |
    When I navigate to the following page option
      | My Active Teams            | Leader Preferences        |                 |
    And I enter random information as food assignment
    Then No error is shown
