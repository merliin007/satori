Feature: 301 Redirects
  This feature validates redirects from old site url to newest

  Scenario: As an authenticated user I want to verify all URLs redirect correctly to new site
#    Given User navigates to ALTA website without signing in
#    When I navigate to the following public page option

    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    And I start navigating URLs from XML file being redirected to new site correctly
#    And I create a file with redirected urls
#    Then No error is shown
Then I print results


