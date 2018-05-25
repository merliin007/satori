Feature: Refund membership with Credit Card
  This feature exercises Membership refunding payment via Credit Card from Office User for another member

  Scenario: As an Office user I need to refund a payment made via credit card
    Given I sign in as member user
      | username   | password |
      | OfficeUser | Abc!123  |
    Then I Should be logged in successfully
    And I navigate to My Profile menu
    And Click on MemberShip & Payments
    And Click on Add Member Payment
    When Upgrading membership for user "100-142-402"
    And After adding my credit card information I submit payment
    Then Refund is made for this payment