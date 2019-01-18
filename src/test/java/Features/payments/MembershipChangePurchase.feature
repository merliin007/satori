Feature: Membership payment
  This feature exercises searching for a member paying his membership and changing Purchase type

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

  Scenario: User changes purchase detail after paying his membership
    When I edit payment changing the following information
      | Purchase Type | Year | Ladder                  | Tournament | Membership | Upgrade | Purchase Amount | Comments                   |
      | Ladder        | 2019 | Boys 2019 Autumn Ladder |            |            |         |                 | Changing only product type |
    Then I get "success" message