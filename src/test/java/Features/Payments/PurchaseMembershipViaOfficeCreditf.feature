Feature: Payments with Office Credit
  This feature exercises Purchasing Membership via Office Credit

  Scenario: As an Office user I need to purchase another's member membership using Office Credit
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    And Add the payment for the following member: "101-575-919"
    And After selecting "Office Credit" option I submit all information
    Then I get confirmation page Accepting my payment

