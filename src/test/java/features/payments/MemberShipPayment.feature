@sanity
Feature: Membership payment
  This feature exercises searching for a member paying his membership

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates to a member profile and edits it
    When I navigate to the following page option
      | Members and Rosters | Members Advanced Search |  |
    And I Search and Select the following member
      | Automated | User |
    And I pay his "current" membership using my credit card
    Then I get success payment confirmation



