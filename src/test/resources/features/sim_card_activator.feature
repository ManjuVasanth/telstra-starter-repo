Feature: SIM Card Activation

  Scenario: Successfully activate a SIM card
    Given I submit a SIM activation request with ICCID "1255789453849037777" and email "david.jones@gmail.com"
    When I query the SIM activation record with ID 1
    Then the response should indicate activation was "true"

  Scenario: Fail to activate a SIM card
    Given I submit a SIM activation request with ICCID "8944500102198304826" and email "faj.james@gmail.com"
    When I query the SIM activation record with ID 2
    Then the response should indicate activation was "false"

