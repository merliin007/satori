Feature: Reports Roles Testing
  This feature exercises role base testing for reports sections

  Scenario Outline: This test case will impersonate different role types and visit its granted report categories
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal
    When I navigate to the following page option
      | Members and Rosters | Members Advanced Search |  |
    And I Search and Impersonate the following member <ALTA Number>
    Then I switch to "Switch to Manage ALTA"
    And I navigate to the following page option
      | Reports |  |  |
    Then I visit each of the reports granted for <Role>


#    And I open my excel file "president"
    Examples:
      | ALTA Number | Role        |
      | 100-243-909 | Coordinator |