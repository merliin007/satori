Feature: Login validation
  This feature exercises correct login into new site

  Background: Successfully loging into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"

  Scenario: User navigates for all rebuilt website without issues
    When I navigate to the following pages option
      | Website Support | Content editing           |
      | Website Support | Calendars                 |
      | Website Support | Events                    |
      | Website Support | Products                  |
      | Website Support | Rules/Admin               |
      | Website Support | Leagues                   |
      | League Support  | Tracking Sheet Scorecards |
      | League Support  | Assigned Leveling         |
    Then No error is shown

