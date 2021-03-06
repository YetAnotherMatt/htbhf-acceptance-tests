Feature: Complete application journey
  In order to apply for the Apply for Healthy Start programme
  As a potential claimant
  I want to enter my details and complete the application journey

  Scenario: Valid application details can be checked
    Given I am on the first page of the application
    When I complete the application with valid details for a pregnant woman
    Then I am shown the check answers page with correct page content
    And I accept the terms and conditions and submit my application
    And I am shown a successful decision page
    And my entitlement is £9.30 per week with a first payment of £37.20