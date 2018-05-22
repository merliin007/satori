Feature: Payments with Credit Card
  This feature exercises Purchasing Membership via Office Credit from Office User for another member

  Scenario: As an Office user I need to purchase another's member membership using credit card
    Given I sign in as "OfficeUser" user in "test" environment
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    And Add the payment for the following member: "100-142-402"
    And After adding my credit card information I submit all information
    Then I get confirmation page Accepting my payment


