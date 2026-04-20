Feature: Flight Filters and Sorting

  Background:
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification

  Scenario: Validate sorting, details, fare options and filters
    Given user launches booking homepage for flights
    When user opens flights section
    And user chooses one way trip option
    And user selects multiple departure locations
    And user selects multiple destination locations
    And user picks travel date
    And user configures travellers and children ages
    And user triggers flight search
    Then User validates cheapest sorting
    And User validates flight details
    And User validates fare options
    And User validates airline filter
    And User validates time filter
