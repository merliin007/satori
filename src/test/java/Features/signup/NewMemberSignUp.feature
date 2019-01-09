Feature: Member SignUp
  This feature exercises creating a new member SignUp

  Scenario: New User sings up
    Given User navigates to ALTA website
    When User clicks on Join Now link
    Then User enters the following information for signing up
      | First Name | Last Name | Gender | DOB        | Wheel Chair | Home Phone | Mobile Phone | Work Phone | Email                        | Street             | Apt | City      | State | Zip   | County | Username  | Password |
      | Automated  | User      | Female | 11/13/1980 | no          | 6622113388 | 5551237896   |            | automatedtest@automation.com | 101 under a bridge | 50  | Some city | DE    | 74532 | Butts  | automated | test123  |
    And User selects the following rankings
      | Tennis Professional |
      | USTA Junior Ranking |
    Then After saving new account is created

