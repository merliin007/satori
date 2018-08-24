Feature: Payments with Credit Card and wrong information
  This feature exercises entering incorrect value for credit card

  Scenario: As an Office user I need to purchase another's member membership using invalid credit card information
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    And Add the payment for the following member: "102-456-637"
    When Manually adding my credit card information I submit all information
      | ccNumber         | ccHolder           | ccMonth | ccYear | ccCVV |
      | ABCDEFGHIJKLMNOP | Automation Testing | 10      | 2020   | 255   |
      | 1234567890123456 | Automation Testing | 10      | 2020   | 255   |
      | 37828224631000_  | Automation Testing | 10      | 2020   | 255   |
      | 3625960xx00004   | Automation Testing | 10      | 2020   | 255   |
    Then No payment was registered