@AdminHappyPaths
Feature: Validate admin basic actions works as intended

  Background:
    Given Access portal
    Given Login with admin
@test1
  Scenario: Create distributor, manager and customer test
    Given Delete "AutoTestDistributor" distributor
    When Select the "Distributor List" from navigation menu
    And Admin adds a new distributor with the following data:
      | Company Name   | AutoTestDistributor |
      | Company Email  | AutoTestDistributor@mail.com |
      | Company Number | TSTContactNo |
    And Filter the table by the following company name: "AutoTestDistributor"
    Then Admin verifies the first entry from distributor list contains the following values:
      | AutoTestDistributor |
      | AutoTestDistributor@mail.com |
      | TSTContactNo |
    When Select the "User List" from navigation menu
    And Admin adds a new user with the following data:
      | First Name    | TST_CP_M_FN         |
      | Last Name     | LN             |
      | Contact Email | AutoTestCPManager@email.com  |
      | Company Name  | AutoTestDistributor |
      | Role          | MANAGER        |
    And Filter the table by the following user email: "AutoTestCPManager@email.com"
    Then Admin verifies the first entry from user list contains the following values:
      | TST_CP_M_FN         |
      | LN             |
      | AutoTestCPManager@email.com  |
      | AutoTestDistributor |
      | MANAGER        |
    When Admin activates new user
    And Admin impersonates new user
    And Select the "Customer List" from navigation menu
    And Distributor adds a new customer with the following data:
      | Customer Name   | TSTCustomer    |
      | Customer Email  | AutoTestCust@email.com |
      | Customer Number | TSTContactNo   |
    Then Distributor verifies the first entry from customer list contains the following values:
      | TSTCustomer    |
      | AutoTestCust@email.com |
      | TSTContactNo   |
      | CUSTOMER       |
    When Select the "User List" from navigation menu
    And Distributor adds a new user with the following data:
      | First Name    | TST_CP_C_FN    |
      | Last Name     | LN             |
      | Contact Email | AutoTestCPCust@email.com  |
      | Company Name  | TSTCustomer |
      | Role          | MANAGER        |
    When Filter the table by the following user email: "AutoTestCPCust@email.com"
    Then Distributor verifies the first entry from user list contains the following values second time:
      | TST_CP_C_FN      |
      | LN      |
      | AutoTestCPCust@email.com  |
      | TSTCustomer |
      | MANAGER     |
@test2
  Scenario: Create new leasing contract
    Given Distributor "AutoTestDistributor" exists
    When Select the "Distributor Contract List" from navigation menu
    And Admin creates new leasing contract user with the following data:
      | Company         | AutoTestDistributor |
      | Initial Payment | 111                 |
    Then Admin verifies new leasing contract for "AutoTestDistributor" distributor was created
    When Admin confirms new leasing contract
    And Select the "Billing List" from navigation menu
    Then Admin verifies new billing event was created with the following data:
      | AutoTestDistributor |
      | 111                 |
@test3
  Scenario: Create new rental customer order
    Given Distributor "AutoTestDistributor" exists
    Given Account Manager "AutoTestCPManager@email.com" exists
    Given Customer "TSTCustomer" exists
    When Select the "Customer Order List" from navigation menu
    And Admin creates new rental customer order with the following data:
      | Channel Partner  | AutoTestDistributor |
      | Customer         | TSTCustomer         |
      | Account Manager  | TST_FN              |
      | Monthly Quantity | 1                   |
    Then Admin verifies new rental customer order was created with the following data:
      | TSTCustomer         |
      | AutoTestDistributor |
      | Monthly Pre-Paid    |
      | 1                   |
@test4
  Scenario: Create new leasing customer order
    Given Distributor "AutoTestDistributor" exists
    Given Account Manager "AutoTestCPManager@email.com" exists
    Given Customer "TSTCustomer" exists
    Given Confirmed leasing contract for "AutoTestDistributor" exists
    When Select the "Customer Order List" from navigation menu
    And Admin creates new leasing customer order with the following data:
      | Channel Partner  | AutoTestDistributor |
      | Customer         | TSTCustomer         |
      | Account Manager  | TST_FN              |
    Then Admin verifies new leasing customer order was created with the following data:
      | TSTCustomer         |
      | AutoTestDistributor |
      | Leasing             |
@test5
  Scenario: Create new address for customer
    Given Customer "TSTCustomer" exists
    When Select the "Address List" from navigation menu
    And Admin adds a new address with the following data:
      | Street        | testStreet  |
      | Street Number | 54321       |
      | City          | testCity    |
      | ZIP code      | 12345       |
      | Country       | Afghanistan |
      | Company       | TSTCustomer |
    Then Admin verifies new address for customer was created with the following data:
      | testStreet  |
      | 54321       |
      | testCity    |
      | AF          |
      | TSTCustomer |
@test6
  Scenario: Create new contact for customer's address
    Given Address for "TSTCustomer" customer exists
    When Select the "Address List" from navigation menu
    And Filter address table by the following company name: "TSTCustomer"
    And Admin adds contact for customer address with the following data:
      | First Name     | TContactCustomerFN         |
      | Last Name      | TContactCustomerLN         |
      | Contact Number | TContactNumber             |
      | Email          | TContactCustomer@email.com |
      | Language       | Abkhaz                     |
    Then Admin verifies new contact for customer was created with the following data:
      | TSTCustomer                |
      | TContactCustomerFN         |
      | TContactCustomerLN         |
      | TContactNumber             |
      | TContactCustomer@email.com |
      | Abkhaz                     |
@test7
  Scenario: Create new shipment request
    Given Customer order for "AutoTestDistributor" exists
    Given Address for "TSTCustomer" customer exists
    Given Contact for "TSTCustomer" customer exists
    When Select the "Customer Order List" from navigation menu
    And Filter the table by the following channel Partner: "AutoTestDistributor"
    And Admin adds new shipment request with the following data:
      | Device Quantity  | 1          |
      | Shipment Address | testStreet |
    And Select the "Shipment List" from navigation menu
    And Filter the table by the following channel Partner: "AutoTestDistributor"
    Then Admin verifies new shipment with require state is created with the following data:
      | AutoTestDistributor |
      | TSTCustomer         |
      | 1                   |
      | REQUIRE             |
@test8
  Scenario: Create new device
    Given Delete "CPGATAZMR555" device
    When Select the "Device List" from navigation menu
    And Admin adds production order with the following data:
      | Quantity     | 1              |
      | Device Name  | Device Test    |
      | Model Number | TS-555001-01   |
      | Factory      | Quick Circuits |
    And Admin creates device with excel file
    Then Admin verifies device with the following data:
      | CPGATAZMR555    |
      | TS-555001-01    |
      | Device Test     |
      | 555555555555555 |
      | AWAIT TO BUILD  |
      | Quick Circuits  |
@test9
  Scenario: Confirm shipment
    Given Shipment for "TSTCustomer" exists with require state
    Given Admin change "CPGATAZMR555" device state to RETURNED
    When Select the "Shipment List" from navigation menu
    And Filter the table by the following channel Partner: "AutoTestDistributor"
    And Admin imports device to shipment
    Then Admin verifies shipment with the following data:
      | AutoTestDistributor |
      | TSTCustomer         |
      | SHIPPING            |
    When Select the "Device List" from navigation menu
    Then Admin verifies device with the following data:
      | CPGATAZMR555    |
      | TS-555001-01    |
      | Device Test     |
      | 555555555555555 |
      | SHIPPING OUT    |
      | TSTCustomer     |
    When Select the "TID List" from navigation menu
    And Filter TID list by "CPGATAZMR555" TID
    Then Admin verifies journey with the following data:
      | CPGATAZMR555        |
      | TSTCustomer         |
      | AutoTestDistributor |
      | NOT STARTED         |
@test10
  Scenario: Change journey statuses
  When POST request with the following params:
    | SN       | CPGATAZMR555    |
    | Tracking | 1               |
  And Select the "TID List" from navigation menu
  And Filter TID list by "CPGATAZMR555" TID
  Then Admin verifies journey with the following data:
    | CPGATAZMR555        |
    | TSTCustomer         |
    | AutoTestDistributor |
    | TRACKING            |
  When POST request with the following params:
    | SN       | CPGATAZMR555    |
    | Tracking | 2               |
  And Select the "TID List" from navigation menu
  And Filter TID list by "CPGATAZMR555" TID
  Then Admin verifies journey with the following data:
    | CPGATAZMR555        |
    | TSTCustomer         |
    | AutoTestDistributor |
    | RETURNING           |
  When Select the "Device List" from navigation menu
  And Admin change "CPGATAZMR555" device state to RETURNED
  And Select the "TID List" from navigation menu
  And Filter the table by the following channel Partner: "AutoTestDistributor"
  Then Admin verifies journey with the following data:
    | CPGATAZMR555        |
    | TSTCustomer         |
    | AutoTestDistributor |
    | COMPLETED           |
  And Filter TID list by "CPGATAZMR555" TID
  Then Admin verifies journey with the following data:
    | CPGATAZMR555            |
    | ID Commerce + Logistics |
    |                         |
    | NOT STARTED             |