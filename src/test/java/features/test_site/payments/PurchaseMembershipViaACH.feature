Feature: Payments with Bank Account (ACH)
  This feature exercises Purchasing Membership Bank Account from Office User for another member

  Scenario: As an Office user I need to purchase another's member membership using Bank Account
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    And Add the payment for the following member: "102-456-637"
    And After adding my Bank Account information I submit all information
    Then I get confirmation page Accepting my payment


