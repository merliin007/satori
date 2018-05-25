Feature: Upgrade membership with Credit Card
  This feature exercises Upgrading Membership via Credit Card from Office User for another member

  Scenario: As an Office user I need to upgrade another's member membership using credit card
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    When Upgrading membership for user "100-142-402"
    And After adding my credit card information I submit payment
    Then Purchase detail page shows correct payment

