Feature: Membership payment
  This feature exercises searching for a member paying his membership and deleting payment

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal
    When I navigate to the following page option
      | Members and Rosters | Members Advanced Search |  |
    And I Search and Select the following member
      | Automated | User |
    And I pay his "current" membership using my credit card
    Then I get success payment confirmation

  Scenario: User gets refund for his membership after successfully paid for it
    When I delete purchase
    Then I get "success" message
