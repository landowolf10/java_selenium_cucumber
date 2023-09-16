Feature: Invalid user login
  Scenario Outline: Invalid user
    Given I navigate to SauceLab demo page <browser>
    When type the username <user> with password <password>
    And press the login button
    Then verify user login was not successful

    Examples:
      | user            | password      | browser |
      | standard_use    | secret_sauce  | Chrome  |
      | standard_user    | secretsauce  | Chrome  |
      | standard_use   | secret_sauce   | Firefox |
      | standard_user   | secretsauce   | Firefox |

