Feature: Public website navigation
  This feature exercises visiting all of the public website pages

  Scenario: As a non logged in user I want to visit all of the public pages
    Given User navigates to ALTA website without signing in
    When I navigate to the following public page option
      |                        | Players & Leagues       |
      | Players & Leagues      | Find Players and Teams  |
      | Players & Leagues      | Adult Leagues           |
      | Players & Leagues      | Men                     |
      | Players & Leagues      | Mixed Doubles           |
      | Players & Leagues      | Run 'n Roll             |
      | Players & Leagues      | Senior Day Men          |
      | Players & Leagues      | Senior Day Women        |
      | Players & Leagues      | Senior Men              |
      | Players & Leagues      | Senior Mixed            |
      | Players & Leagues      | Senior Women            |
      | Players & Leagues      | Sunday Women            |
      | Players & Leagues      | Thursday Women          |
      | Players & Leagues      | Wheelchair              |
      | Players & Leagues      | Junior Leagues          |
      | Players & Leagues      | Junior Boys             |
      | Players & Leagues      | Junior Girls            |
      |                        | Standings & Postseason  |
      | Standings & Postseason | Division Standings      |
      | Standings & Postseason | Playoffs                |
      | Standings & Postseason | City Finals             |
      |                        | Facilities              |
      | Facilities             | Facility Search         |
      | Facilities             | Request New Facility    |
      |                        | Ladders & Mixers        |
      | Ladders & Mixers       | Junior Challenge Ladder |
      | Ladders & Mixers       | Mixers                  |
      |                        | Resources & Calendars   |
      | Resources & Calendars  | ALTA Documents          |
      | Resources & Calendars  | Calendar                |
      | Resources & Calendars  | FAQs                    |
      |                        | ALTA Foundation         |
      |                        | About ALTA              |
      | About ALTA             | ALTA Membership         |
      | About ALTA             | Volunteering            |
      | About ALTA             | ALTA History            |
    Then No error is shown