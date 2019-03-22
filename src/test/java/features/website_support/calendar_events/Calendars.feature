Feature: Calendars creation
  This feature exercises creating and editing a new calendar

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User creates a new calendar then edits it
    When I navigate to the following page option
      | Website Support | Calendars & Events |  |
    And I create a new calendar using
      | year | calendarType | startDate  | endDate    |
      | 2019 | Ladders      | 07/03/2019 | 07/31/2019 |
    And I edit recently created calendar without issues
      | year | calendarType | startDate  | endDate    |
      | 2019 | Main         | 07/03/2019 | 08/30/2019 |
#    Then I delete such calendar