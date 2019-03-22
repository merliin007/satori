Feature: Login validation
  This feature exercises correct login into new site

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates for all rebuilt website without issues
    When I navigate to the following page option
#     | Section             | Child page                         | Browser go back |
      | Website Support     | Calendars & Events                   |      |
      | Website Support     | Products                             |      |
      | Website Support     | Rules & Admin                        |      |
      | Website Support     | League Templates and Scheduled Dates |      |
      | Website Support     | Content Editing                      |      |
      | League Support      | Tracking Sheet Scorecards            |      |
      | League Support      | Assigned Leveling                    |      |
      | League Support      | Division Assignment Map              |      |
      | League Support      | Schedule Override                    |      |
      | League Support      | Jobs                                 |      |
      | League Support      | League Documents                     |      |
      | League Support      | Volunteer Bulk Entry                 |      |
      | Members and Rosters | Member Search                        |      |
      | Members and Rosters | Members Advanced Search              |      |
      | Members and Rosters | Rosters Advanced Search              |      |
      | Members and Rosters | Rosters Default out                  | true |
      | Members and Rosters | Post-Season Deletion Request Purge   | true |
      | Members and Rosters | Roster Player swap                   | true |
      | Ladders and Mixers  | Ladders                              | true |
      | Ladders and Mixers  | Ladder Entries                       | true |
      | Ladders and Mixers  | Ladder Participant Bulk Entry        | true |
      | Ladders and Mixers  | Mixers                               | true |
      | Ladders and Mixers  | Mixers Entries                       | true |
      | Facilities          | Search                               | true |
      | Facilities          | Request New Facility                 | true |
      | Facilities          | Inspection Requests                  | true |
      | Facilities          | Inspection Reports                   | true |
      | Facilities          | Unused Facilities                    | true |
      | Service Recognition | Awards                               | true |
      | Service Recognition | Award Gift Assignment                | true |
      | Service Recognition | Gift Management                      | true |
      | Service Recognition | Honorariums                          | true |
      | Service Recognition | Honorarium Admin                     | true |
      | Logs                | UI Activity Log                      | true |
      | Logs                | Data Log                             | true |

    Then No error is shown

