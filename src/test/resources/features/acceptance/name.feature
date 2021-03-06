Feature: Enter name
  In order to apply for the Apply for Healthy Start programme
  As a potential claimant
  I want to enter my name

  Background:
    Given I have entered my details up to the enter name page

  Scenario: Fill “First name” textbox with text which exceeds maximum length
    When I enter a first name which is too long
    Then I am informed that the first name is too long
    Then I see the invalid first name I entered in the textbox

  Scenario: Fill “Last name” textbox with text which exceeds maximum length
    When I enter a last name which is too long
    Then I am informed that the last name is too long
    Then I see the last name I entered in the textbox

  Scenario: Fail to fill "First name" textbox shows error
    When I enter last name only
    Then I am informed that a first name is required

  Scenario: Fail to fill "Last name" textbox shows error
    When I enter first name only
    Then I am informed that a last name is required

  Scenario Outline: Valid first name and last name form submission
    When I enter <firstName> and <lastName> values
    Then I am shown the enter national insurance number page

    Examples:
      | firstName                                              | lastName                   |
      | <script>window.location.href=“www.google.com”</script> | ‘;exec xp_cmdshell ‘dir’;– |
      | Henrietta                                              | Fulsome-Blues              |
      | Henrietta                                              | Młynarczyk                 |
      | Maria                                                  | Nowak                      |
