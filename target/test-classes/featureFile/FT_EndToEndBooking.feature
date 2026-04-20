Feature: Flight Booking Flow

  Background:
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification

  Scenario: User books flight with multiple departure and destination
    Given user launches booking homepage for flights
    When user opens flights section
    And user chooses one way trip option
    And user selects multiple departure locations
    And user selects multiple destination locations
    And user picks travel date
    And user configures travellers and children ages
    And user triggers flight search
    Then user views available flight results
    And user selects first flight and proceeds
    Then user lands on traveller details section
    When user fills traveller personal information
    And user fills contact information and proceeds
    And user selects ticket type and proceeds
    And user skips seat selection
 
