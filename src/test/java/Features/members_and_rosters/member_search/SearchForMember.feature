Feature: Member search
  This feature exercises searching for a member and editing it

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates to a member profile and edits it
    When I navigate to the following page option
      | Members and Rosters | Members Advanced Search |  |
    Then I Search and Select the following ALTA number "103-675-630"
    And I change his address with random information
    And I change his preferences toggling Sharing Contact Information
    And I grant him a "10 years" award on "2017"




