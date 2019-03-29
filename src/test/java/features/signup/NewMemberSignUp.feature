@signUp
Feature: Member SignUp
  This feature exercises creating a new member SignUp

  Scenario Outline: New User sings up
    Given User navigates to ALTA website
    When User clicks on Join Now link
    Then User enters the following information for signing up
      | <First Name> | <Last Name> | <Gender> | <DOB> | <Wheel Chair> | <Home Phone> | <Mobile Phone> | <Work Phone> | <Email> | <Street> | <Apt> | <City> | <State> | <Zip> | <County> | <Password> |
    And User selects the following rankings <Rankings>
    Then After saving new account is created

    Examples:
      | First Name | Last Name | Gender | DOB        | Wheel Chair | Home Phone | Mobile Phone | Work Phone | Email                        | Street             | Apt | City      | State | Zip   | County | Password | Rankings                                                                          |
      | Automated  | User      | Male   | 10/23/2005 | yes         |            |              |            | automatedtest@automation.com |                    |     |           | IA    | 95621 | Bacon  | test123  | Tennis Professional, USTA Junior Ranking                                          |
      | Automated  | User      | Female | 12/19/1994 | no          |            |              |            | automatedtest@automation.com |                    |     |           | TN    | 95621 | Coffee | test123  | Current Division I College Team Player                                            |
      | Automated  | User      | Female | 11/13/1980 | no          | 6622113388 | 5551237896   |            | automatedtest@automation.com | 101 under a bridge | 50  | Some city | DE    | 74532 | Butts  | test123  | Current High School Team Player, USTA Junior Ranking, Current College Team Player |
