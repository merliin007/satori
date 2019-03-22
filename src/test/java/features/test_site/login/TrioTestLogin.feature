@Trio
Feature: Login validation
  This feature exercises correct login into Trio site

  Scenario: Login functionality for Trio admin with correct values
    Given User navigates to Trio website
    And I click on any element after spinner goes off
    Then I Select "Voids & Testing" option for Category
    And I type "testing" as reason
