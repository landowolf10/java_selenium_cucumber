Feature: Successful login
  @Login
  Scenario Outline: Validate successful login to Sauce Lab demo app
    Given I navigate to SauceLab demo page <browser>
    When type the username standard_user with password secret_sauce
    And press the login button
    Then verify that the user successfully logged in

    Examples:
      | browser |
      | Chrome  |