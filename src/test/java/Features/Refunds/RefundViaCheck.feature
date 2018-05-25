Feature: Refund membership with Check
  This feature exercises Membership refunding payment via Check from Office User for another member

  Scenario: As an Office user I need to refund a payment made via check
    Given I sign in as Default user
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    When Upgrading membership for user "100-142-402"
    And After adding my check information I submit payment
    Then Refund is made for this payment


