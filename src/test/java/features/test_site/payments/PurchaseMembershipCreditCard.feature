Feature: Payments with Credit Card
  This feature exercises Purchasing Membership via Credit Card from Office User for another member

  Scenario: As an Office user I need to purchase another's member membership using credit card
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    When Add the payment for the following member: "102-456-637"
    And After adding my credit card information I submit payment
    Then I get confirmation page Accepting my payment


