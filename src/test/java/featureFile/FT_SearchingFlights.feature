Feature: Flight Search Validation

  Background:
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification

  Scenario: Validate wrong inputs then correct flow in same session
    Given user is on booking homepage
    When user runs complete flight validation flow
