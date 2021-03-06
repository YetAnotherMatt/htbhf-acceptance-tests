Feature: Address
  In order to apply for the Apply for Healthy Start programme
  As a potential claimant
  I want to enter my address manually

  Background:
    Given I have entered my details and wish to manually enter my address

  Scenario Outline: Enter a valid address with all fields entered
    When I enter an address with postcode <postcode>
    Then I am shown the phone number page

    Examples:
      | postcode  |
      | EC11BB    |
      | M11AE     |
      | DN55 1PT  |
      | dn55 1pt  |
      | dn55  1pt |

  Scenario: Enter a valid address without an optional second address line
    When I do not enter the second line of an address
    Then I am shown the phone number page

  Scenario: Enter a valid address without an optional county line
    When I do not enter the 'county' of an address
    Then I am shown the phone number page

  Scenario Outline: Enter an address with an invalid postcode
    When I enter an address with postcode <postcode>
    Then I am informed that the postcode is in the wrong format on the manual address page

    Examples:
      | postcode |
      | AA1122BB |
      | A        |
      | 11AA21   |

  Scenario Outline: Entering a postcode from the Channel Islands or Isle of Man shows an error
    When I enter an address with postcode <postcode>
    Then I am informed that you can only apply if you live in England, Wales or Northern Ireland on the manual address page

    Examples:
      | postcode |
      | GY1 1WR  |
      | JE3 1FU  |
      | IM1 3LY  |


  Scenario: Do not enter in any address fields
    When I do not enter in any address fields
    Then I am informed that I need to enter an address on the 'address line 1', 'town or city' and 'postcode' fields

  Scenario: Do not enter in the first line of the address
    When I do not enter the first line of an address
    Then I am informed that I need to enter an address on the 'address line 1' field

  Scenario: Do not enter in the town or city
    When I do not enter the 'town or city' of an address
    Then I am informed that I need to enter an address on the 'town or city' field

  Scenario: Enter in an address where the first line is too long
    When I enter in an address where the first line is too long
    Then I am informed that the first line of the address is too long

  Scenario: Enter in an address where the second line is too long
    When I enter in an address where the second line is too long
    Then I am informed that the second line of the address is too long

  Scenario: Enter in an address where the 'town or city' is too long
    When I enter in an address where the 'town or city' is too long
    Then I am informed that the 'town or city' of the address is too long

  Scenario: Enter in an address where the 'county' is too long
    When I enter in an address where the 'county' is too long
    Then I am informed that the 'county' of the address is too long
