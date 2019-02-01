Feature: League Template creation
  This feature exercises creating a League Template

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User creates a league template successfully
    When I navigate to the following page option
      | Website Support | League Templates and Scheduled Dates |  |
    Then I click on "League Templates" tab
    And I click on New Template button
    And I enter the following data as New Template description
      | League Name           | Gender | Min Age Type       | Min Age | Max Age | Score Card Type      | Number of seasons per year | Active |
      | Automated Test League | Mixed  | During League Year | 18      | 99      | Adult Doubles 5 Line | 1                          | yes   |
    And I enter the following data as New Template details
      | Min Team Members | Min Ret Team Members | Min Match Players | Max Addons | Min Females | Min Ret Females | Min Males | Min Ret Males | Min Team Members Needed | Min Females Needed | Min Males Needed |
      | 4                | 4                    | 4                 | 6          | 2           | 2               | 2         | 2             | 4                       | 2                  | 2                |
    And I choose the following options on New Template seasons
      | Season |
      | Winter |
      | Fall   |
    And I choose the following options on new Template Exclusions
      | Excluded League |
      | Men             |
      | Wheelchair      |
    Then I save my new league templates without errors



