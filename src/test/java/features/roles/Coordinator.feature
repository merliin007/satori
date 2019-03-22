Feature: Coordinator access
  This feature exercises Coordinator access to portal

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "101-443-070" and password as "password123"
    Then I select "Manage" portal

  Scenario: User navigates across the following pages without issues
    When I navigate to the following page option
#     | Section             | Child page                | Browser go back |
      | Members and Rosters | Rosters Advanced Search   |                 |
      | League Support      | Tracking Sheet Scorecards |                 |
      | Reports             |                           |                 |
    Then No error is shown