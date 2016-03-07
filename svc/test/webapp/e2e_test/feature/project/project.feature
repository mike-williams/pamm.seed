Feature: List Projects
  As a user
  I want to see my projects
  In order to manage them

  Scenario: No Projects
    Given I am on the project page
    Given I am not a member of any projects
    Then I should see a no projects message

  Scenario: Have existing projects
    Given I am on the project page
    Given I have at least one project
    Then I should see a a project list

  Scenario: Add a new project
    Given I am on the add project form
    Then I should be able add a new project