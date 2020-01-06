@Profile
Feature: Validate profile works as intended

  Background:
    Given Access portal
    Given Login with admin

  Scenario: Create shipment and journey with device with profile
    Given Customer order for "Internal Trial Limited" exists
    Given Address for "BML" customer exists
    Given Contact for "BML" customer exists
    Given Order with "000933" number exists
    Given Admin change "CPGATAZMR222" device state to RETURNED
    When Select the "Customer Order List" from navigation menu
    And Filter the table by the following channel Partner: "Internal Trial Limited"
    And Filter the table by the following order number: "000933"
    And Admin adds new shipment request with the following data:
      | Profile          | BMLTestProfileLabel |
      | Device Quantity  | 1                   |
      | Shipment Address | testStreet          |
    And Select the "Shipment List" from navigation menu
    And Filter the table by the following channel Partner: "Internal Trial Limited"
    Then Admin verifies new shipment with require state is created with the following data:
      | Internal Trial Limited |
      | BML                    |
      | BMLTestProfileLabel    |
      | 1                      |
      | REQUIRE                |
    And Admin imports device to shipment
    Then Admin verifies shipment with the following data:
      | Internal Trial Limited |
      | BML                    |
      | SHIPPING               |
    When Select the "Device List" from navigation menu
    Then Admin verifies device with the following data:
      | CPGATAZMR222    |
      | SHIPPING OUT    |
      | BML             |
    When Select the "TID List" from navigation menu
    And Filter TID list by "CPGATAZMR222" TID
    Then Admin verifies journey with the following data:
      | CPGATAZMR222           |
      | BML                    |
      | Internal Trial Limited |
      | NOT STARTED            |