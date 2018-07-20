Feature: Calendars creation
  This feature exercises creating and editing a new calendar

  Background: Successfully loging into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"

  Scenario: User creates a new calendar then edits it
    When I navigate to the following pages option
      | Website Support | Calendars |
    And I create a new calendar using
      | year | calendarType | startDate  | endDate    |
      | 2018 | Main         | 07/03/2018 | 07/31/2018 |
    And I edit recently created calendar without issues
      | year | calendarType | startDate  | endDate    |
      | 2018 | Main         | 07/03/2018 | 08/30/2018 |
    Then I delete such calendar