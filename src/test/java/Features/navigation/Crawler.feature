Feature: Login validation
  This feature exercises correct login into new site

  Background: Successfully loging into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates for all rebuilt website without issues
    When I navigate to the following page option
      | Website Support | Calendars & Events             |
      | Website Support | Products                       |
      | Website Support | Rules & Admin                  |
      | Website Support | League Templates               |
      | Website Support | Content editing                |
      | League Support  | Tracking Sheet Scorecards      |
      | League Support  | Leagues Available for Leveling |
      | League Support  | Division Assignment Map        |
      | League Support  | Schedules                      |
      | League Support  | Jobs                           |
      | League Support  | Documents                      |
      | League Support  | Volunteer Bulk Entry           |
    Then No error is shown

