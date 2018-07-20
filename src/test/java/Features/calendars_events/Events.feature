Feature: Events search
  This feature exercises searching an event and navigating to its child pages

  Background: Successfully loging into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"

  Scenario: User searchs for an event and explores it
    When I navigate to the following pages option
      | Website Support | Events |
    And I search for "2018 Main" calendar or create a new if there is not any
      | year | calendarType | startDate  | endDate    |
      | 2018 | Main         | 07/03/2018 | 07/31/2018 |
    Then I create a new Event using
      | calendarList | eventName       | eventDescription               | dateOfEvent |
      | 2018 Main    | Automated Event | Automator creating a new event | 11/13/2018  |
    Then I delete such event
    And I navigate to the following pages option
      | Website Support | Events |
    Then I delete such calendar
