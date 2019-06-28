@sanity
Feature: Login validation
  This feature exercises correct login into new site

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "AUTOMATED1557848014391" and password as "test123"
#    Then I select "Member" portal

  Scenario: User navigates for all rebuilt website without issues
    When I navigate to the following page option
#     | Section                    | Child page                | Browser go back |
      | My Active Teams            | Dashboard                 |                 |
      | My Active Teams            | Roster                    |                 |
      | My Active Teams            | Schedule & Lineup         |                 |
      | My Active Teams            | Team Tracking Sheet       |                 |
      | My Active Teams            | Availability              |                 |
      | My Active Teams            | Communicate               |                 |
      | My Active Teams            | Division Standings        |                 |
      | My Active Teams            | Scorecards                |                 |
      | My Active Teams            | Playoffs                  |                 |
      | My Active Teams            | Performance               |                 |
      | My Active Teams            | Calendar                  |                 |
      | My Active Teams            | Leader Preferences        |                 |
      | My Rosters & New/Reinstate | My Rosters                |                 |
      | My Rosters & New/Reinstate | New/Reinstate Rosters     |                 |
      | My Rosters & New/Reinstate | Post Season deletion      |                 |
      | My Ladders & My Mixers     | My Ladders                |                 |
      | My Ladders & My Mixers     | My Mixers                 |                 |
#     | My Performance             | My Statistics             |                 |<-- currently bug AR-4032
      | My Performance             | My Scorecards             |                 |
      | My Account                 | My Profile                |                 |
      | My Account                 | My Password               |                 |
      | My Account                 | My Preferences            |                 |
      | My Account                 | My Membership Renewal(s)  |                 |
      | My Account                 | My Memberships & Payments |                 |
      | My Account                 | My Positions              |                 |
      | My Account                 | My Honorariums            |                 |
      | My Account                 | My Awards                 |                 |
      | My Account                 | Individual Suspension     |                 |
      | My Resources               | Find Players and Teams    | true            |
      | My Resources               | ALTA Documents            |                 |
      | Members & Memberships      | Member Search             |                 |
      | Members & Memberships      | New Memberships           | true            |
      | Members & Memberships      | Renew Membership(s)       |                 |
    Then No error is shown
