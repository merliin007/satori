Feature: Events search
  This feature exercises searching an event and navigating to its child pages

  Background: Successfully loging into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User searches for an event and explores it
    When I navigate to the following page option
      | Website Support | Calendars & Events |  |
    And I search for "2019 Main" calendar or create a new if there is not any
      | year | calendarType | startDate  | endDate    |
      | 2019 | Main         | 01/03/2019 | 01/31/2019 |
    Then I create a new Event using
      | calendarList | eventName       | eventDescription               | dateOfEvent |
      | 2019 Main    | Automated Event | Automator creating a new event | 01/13/2019  |
#    Then I delete such event
