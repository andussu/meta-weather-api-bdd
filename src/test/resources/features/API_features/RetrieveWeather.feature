@important
Feature: Retrieve weather information
  # User story: As a MetaWeather API client, I want to retrieve “tomorrows” weather for “Nottingham”

# Boundaries:
  # a user cannot interact with UI to get previous years data (can only do it by manipulating URI)
  # future dates of forecast can only be 5-10 days (Defect - cannot test rugged intervals, need specific, from user perspective is as BUG)
  #

@positive
  Scenario: Retrieve weather of tomorrow's forecast for Nottingham
    Given the user is a MetaWeather client
    When the user looks up the weather for "Nottingham" and date of "tomorrow"
    Then the endpoint should return status 200
    And the payload data should reflect the date of "tomorrow"

@positive
  Scenario Outline: Retrieve weather data from <location> for the date of <date>
    Given the user is a MetaWeather client
    When the user looks up the weather for "<location>" and date of "<date>"
    Then the endpoint should return status 200
    And the content type should be "application/json"
    And the payload data should reflect the date of "<date>"
    And the payload "id" should not be null

    Examples:
      | location   | date       |
      | Nottingham | tomorrow   |
      | Nottingham | today      |
      | London     | yesterday  |
      | Bucharest  | 2021/01/01 |
      | Bucharest  | 2020/12/31 |
      | Leeds      | 2021/05/14 |
      | Leeds      | 2022/05/15 |
      | Leeds      | 2012/05/15 |
 @negative @bug
  Scenario Outline: (Negative) [ECP & BVA] Retrieve weather data from <location> for the date of <date>
    Given the user is a MetaWeather client
    When the user looks up the weather for "<location>" and date of "<date>"
    Then the endpoint should return status 404

    Examples:
      | location | date       |
      | Leeds    | 2022/05/17 |
      | Leeds    | 2022/05/16 |
      | Leeds    | 2012/05/17 |
      | Leeds    | 1000/01/01 |
      | Leeds    | 999/12/31  |
      | Leeds    | 100/01/01  |
@negative
  Scenario Outline: (Negative) Retrieve weather data from <location> for the date of <date> by URI manipulation
    Given the user is a MetaWeather client
    When the user looks up the weather for "<location>" and date of "<date>" by manipulating URI
    Then the endpoint should return status 404
    And the payload data should reflect that location was not found

    Examples: Negative examples of location where user would manipulate URI directly
      | location           | date      |
      | 0                  | today     |
      | 555555555          | yesterday |
      | 999999999999999999 | tomorrow  |

