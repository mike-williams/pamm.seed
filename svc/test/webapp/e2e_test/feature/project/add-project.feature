Feature: List Projects
  As a user
  I want to see my projects
  In order to manage them

  Scenario: No Project Message
    Given I am on the project page
    Given I am not a member of any projects
    Then I should see a no projects message

