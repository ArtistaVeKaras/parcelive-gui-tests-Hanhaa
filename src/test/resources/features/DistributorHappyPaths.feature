#@DistributorHappyPaths
#Feature: Validate distributor basic actions works as intended
#
#  Background:
#    Given Access portal
#    Given Login with "TST@email.com"
#
#  Scenario: Create distributor, manager and customer test
#    Given Login with "oleksii.klimov@rinftech.com"
#    Given Delete "AutoTestDistributor" distributor
#    When Select the "Distributor List" from navigation menu
#    And Admin adds a new distributor with the following data:
#      | Company Name   | AutoTestDistributor |
#      | Company Email  | AutoTestDistributor@mail.com |
#      | Company Number | TSTContactNo |
#    And Filter the table by the following company name: "AutoTestDistributor"
#    Then Verify the first entry from distributor list contains the following values:
#      | AutoTestDistributor |
#      | AutoTestDistributor@mail.com |
#      | TSTContactNo |
#    When Select the "User List" from navigation menu
#    And Admin adds a new user with the following data:
#      | First Name    | TST_FN         |
#      | Last Name     | LN             |
#      | Contact Email | TST@email.com  |
#      | Company Name  | AutoTestDistributor |
#      | Role          | MANAGER        |
#    And Filter the table by the following user email: "TST@email.com"
#    Then Verify the first entry from user list contains the following values:
#      | TST_FN         |
#      | LN             |
#      | TST@email.com  |
#      | AutoTestDistributor |
#      | MANAGER        |
#    When Admin activates new user
#    And And Admin impersonates new user
#    And Select the "Customer List" from navigation menu
#    And Manager adds a new customer with the following data:
#      | Customer Name   | TSTCustomer    |
#      | Customer Email  | TST2@email.com |
#      | Customer Number | TSTContactNo   |
#    Then Verify the first entry from customer list contains the following values:
#      | TSTCustomer    |
#      | TST2@email.com |
#      | TSTContactNo   |
#      | CUSTOMER       |
#    When Select the "User List" from navigation menu
#    And Manager adds a new user with the following data:
#      | First Name    | TST_FN         |
#      | Last Name     | LN             |
#      | Contact Email | TST2@email.com  |
#      | Company Name  | AutoTestDistributor |
#      | Role          | MANAGER        |
#    When Filter the table by the following user email: "TST2@email.com"
#    Then Verify the first entry from user list contains the following values second time:
#      | TST_FN      |
#      | LN      |
#      | TST2@email.com  |
#      | AutoTestDistributor |
#      | MANAGER        |
#
#  Scenario: Create new address for customer
#    Given Customer "TSTCustomer" exists
#    When Select the "Address List" from navigation menu
#    And Distributor adds a new address with the following data:
#      | Street        | testStreet  |
#      | Street Number | 54321       |
#      | City          | testCity    |
#      | ZIP code      | 12345       |
#      | Country       | Afghanistan |
#      | Company       | TSTCustomer |
#    Then Verify new address for customer was created with the following data:
#      | testStreet  |
#      | 54321       |
#      | testCity    |
#      | AF          |
#      | TSTCustomer |
#  @test3
#  Scenario: Create new leasing as Admin contract and confirm it as Distributor
#  @test4
#  Scenario: Create new contact for customer's address
#    Given Address for "TSTCustomer" customer exists
#    When Select the "Address List" from navigation menu
#    And Filter address table by the following company name: "TSTCustomer"
#    And Distributor adds contact for customer address with the following data:
#      | First Name     | TContactCustomerFN         |
#      | Last Name      | TContactCustomerLN         |
#      | Contact Number | TContactNumber             |
#      | Email          | TContactCustomer@email.com |
#      | Language       | Abkhaz                     |
#    Then Verify new contact for customer was created with the following data:
#      | TSTCustomer                |
#      | TContactCustomerFN         |
#      | TContactCustomerLN         |
#      | TContactNumber             |
#      | TContactCustomer@email.com |
#      | Abkhaz                     |
#  @test5
#  Scenario: Create new rental customer order
#    Given Distributor "AutoTestDistributor" exists
#    Given Account Manager "TST@email.com" exists
#    Given Customer "TSTCustomer" exists
#    When Select the "Customer Order List" from navigation menu
#    And Distributor creates new rental customer order with the following data:
#      | Customer         | TSTCustomer         |
#      | Account Manager  | TST_FN              |
#      | Monthly Quantity | 1                   |
#    Then Verify new rental customer order was created with the following data:
#      | TSTCustomer         |
#      | AutoTestDistributor |
#      | Monthly Pre-Paid    |
#      | 1                   |
#  @test6
#  Scenario: Create new leasing customer order
#    Given Distributor "AutoTestDistributor" exists
#    Given Account Manager "TST@email.com" exists
#    Given Customer "TSTCustomer" exists
#    Given Confirmed leasing contract for "AutoTestDistributor" exists
#    When Select the "Customer Order List" from navigation menu
#    And Distributor creates new leasing customer order with the following data:
#      | Channel Partner  | AutoTestDistributor |
#      | Customer         | TSTCustomer         |
#      | Account Manager  | TST_FN              |
#    Then Verify new leasing customer order was created with the following data:
#      | TSTCustomer         |
#      | AutoTestDistributor |
#      | Leasing             |
#  @test7
#  Scenario: Create new shipment request
#    Given Customer order for "AutoTestDistributor" exists
#    Given Address for "TSTCustomer" customer exists
#    Given Contact for "TSTCustomer" customer exists
#    When Select the "Customer Order List" from navigation menu
#    And Filter the table by the following channel Partner: "AutoTestDistributor"
#    And Distributor adds new shipment request with the following data:
#      | Device Quantity  | 1          |
#      | Shipment Address | testStreet |
#    And Select the "Shipment List" from navigation menu
#    Then Verify new shipment with require state is created with the following data:
#      | AutoTestDistributor |
#      | TSTCustomer         |
#      | 1                   |
#      | REQUIRE             |