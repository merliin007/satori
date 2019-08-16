@sanity
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
      | First Name | Last Name | Gender | DOB        | Wheel Chair | Home Phone | Mobile Phone | Work Phone | Email                        | Street | Apt | City | State | Zip   | County | Password | Rankings                               |
      | automated  | user      | Female | 12/19/1985 | no          |            |              |            | automatedtest@automation.com |        |     |      | TN    | 95621 | Coffee | test123  | Current Division I College Team Player |
