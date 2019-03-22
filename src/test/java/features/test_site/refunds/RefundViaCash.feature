Feature: Refund membership with Cash
  This feature exercises Membership refunding payment via Cash from Office User for another member

  Scenario: As an Office user I need to refund a payment made via cash
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    When Upgrading membership for user "100-142-402"
    And After selecting "Cash" option I submit all information
    Then Refund is made for this payment