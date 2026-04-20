Feature: Flight + Hotel Search Flow

  Background:
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification

  Scenario: Run all Flight + Hotel test cases from Excel
    Given user is on booking homepage after login
    When user performs search for all test cases