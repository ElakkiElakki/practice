Feature: Flight Selection Page

Background:
  Given user opens booking homepage for registration
  When user dismisses popup if visible
  And user selects register option
  And user provides email and proceeds
  Then user completes CAPTCHA and OTP verification

  Given user is on booking homepage after login
  When user opens flight + hotel tab
  And user enters departure location from excel row 1
  And user enters destination location from excel row 1
  And user clicks search button
  Then user should see hotel results

  Given user is on hotel listing page  
  And user selects first hotel
  Then user should see room details page
  When user selects room and clicks continue
  Then flight selection page should be displayed

Scenario: User selects flight and continues
  When user selects flight and clicks continue
  Then traveller details page should be displayed