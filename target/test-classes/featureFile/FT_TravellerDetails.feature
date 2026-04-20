Feature: Traveller Details Validation (FT_TravellerDetails)

  Background:
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification

  Scenario: Validate traveller and contact details
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
  # 🔴 ADULT INVALID → FIX
    When user enters invalid adult details
    And user clicks done
    Then user should see traveller validation error
    When user corrects adult details
  # 🔴 CHILD INVALID → FIX
    And user enters invalid child details
    And user clicks done
    Then user should see traveller validation error
    When user corrects child details
  # 🔴 EMAIL INVALID → FIX
    And user enters invalid email
    And user clicks next
    Then user should see email error
    When user corrects email and proceeds

