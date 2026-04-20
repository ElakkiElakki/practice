Feature: Complete Booking Flow

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
  When user selects flight and clicks continue
  Then traveller details page should be displayed

Scenario: Enter traveller details and proceed to payment

 When user enters complete traveller details
| type       | firstName | lastName | gender | day | month | year | nationality | docNumber | issueCountry | issueDay | issueMonth | issueYear | expDay | expMonth | expYear | email          | phone     | address    | houseNo | pincode | city    |
| contact    | dhana     | lakshmi  |        |     |       |      |             |           |              |          |            |           |        |          |         | maha@gmail.com | 9884974556| Anna Nagar | 12A     | 600040  | Chennai |
| traveller1 | Maha      | Karpagam | Ms     | 26  | March | 1998 | India       | P1234567  | India        | 10       | January    | 2020      | 10     | January  | 2030    |                |           |            |         |         |         |
  And user accepts policy and clicks next
  Then payment page should be displayed