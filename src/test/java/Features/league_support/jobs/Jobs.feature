Feature: Jobs execution
  This feature exercises executing a job and verifies it is shown in the Job Queue

  Background: Successfully login into alta website
    Given User navigates to ALTA website
    When I enter username as "OfficeUser" and password as "Abc!123"
    Then I select "Manage" portal

  Scenario: User navigates through Jobs page and queues some jobs and verifies all jobs are listed on job queue
    When I navigate to the following page option
      | League Support | Jobs |
    And I visit each of the following jobs and queue them
      | JobName            | Year | Season | League         | Age | LevelFlight |
      | Calculate Awards   | 2017 |        |                |     |             |
      | Drop Duplicates    | 2018 | Fall   | Wheelchair     |     |             |
      | Assign to Division | 1971 | Spring | Senior Day Men |     |             |
      | Schedule League    | 1972 | Winter | Mixed Doubles  |     |             |
      | Playoff Draw       | 2017 | Spring | Junior Girls   | 18  | C-1         |
    Then I can verify all jobs are listed on the job queue page