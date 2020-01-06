package net.hanhaa.dev.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.*;
import net.hanhaa.dev.pages.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class AdminSteps {

    @Autowired
    BasePage basePage;
    @Autowired
    StepsHelper stepsHelper;
    @Autowired
    DistributorListPage distributorListPage;
    @Autowired
    UserListPage userListPage;
    @Autowired
    DistributorContractListPage distributorContractListPage;
    @Autowired
    CustomerOrderListPage customerOrderListPage;
    @Autowired
    AddressListPage addressListPage;
    @Autowired
    DeviceListPage deviceListPage;
    @Autowired
    ShipmentListPage shipmentListPage;
    @Autowired
    BillingListPage billingListPage;
    @Autowired
    TIDListPage tidListPage;


    @And("^Admin adds a new distributor with the following data:$")
    public void adminAddsANewDistributorWithTheFollowingData(Map<String, String> companyData) {
        distributorListPage.addNewDistributorButtonClick();
        distributorListPage.fillInCompanyDetails(companyData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin adds a new user with the following data:$")
    public void adminAddsANewUserWithTheFollowingData(Map<String, String> userData) {
        userListPage.addNewUserButtonClick();
        userListPage.fillUserData(userData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin activates new user$")
    public void adminActivatesNewUser() {
        userListPage.activateUser();
    }

    @And("^Admin creates new leasing contract user with the following data:$")
    public void adminCreatesNewLeasingContract(Map<String, String> contractData) {
        distributorContractListPage.addNewDistributorContractButtonClick();
        distributorContractListPage.fillInDistributorContractDetails(contractData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @When("^Admin confirms new leasing contract$")
    public void adminConfirmsNewLeasingContractForDistributor() {
        distributorContractListPage.confirmDistributorContract();
    }

    @And("^Admin impersonates new user$")
    public void andAdminImpersonatesNewUser() {
        userListPage.impersonateNewUser();
        basePage.waitUntilNoLoader();
    }

    @And("^Admin creates new rental customer order with the following data:$")
    public void adminCreatesNewRentalCustomerOrderWithTheFollowingData(Map<String, String> rentalOrderData) {
        customerOrderListPage.addRentalCustomerOrderButtonClick();
        customerOrderListPage.fillRentalOrderData(rentalOrderData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin creates new leasing customer order with the following data:$")
    public void adminCreatesNewLeasingCustomerOrderWithTheFollowingData(Map<String, String> leasingOrderData) {
        customerOrderListPage.addLeasingCustomerOrderButtonClick();
        customerOrderListPage.fillLeasingOrderData(leasingOrderData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin adds a new address with the following data:$")
    public void adminAddsANewAddressWithTheFollowingData(Map<String, String> addressData) {
        addressListPage.addAddressButtonClick();
        addressListPage.fillAddressData(addressData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin adds contact for customer address with the following data:$")
    public void adminAddsContactForCustomerAddress(Map<String, String> contactData) {
        addressListPage.addContactButtonClick();
        addressListPage.fillContactData(contactData);
        addressListPage.addContactAddButtonClick();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin adds new shipment request with the following data:$")
    public void adminAddsNewShipmentRequestWithTheFollowingData(Map<String, String> shipmentData) {
        customerOrderListPage.addShipmentRequestButtonClick();
        customerOrderListPage.fillShipmentRequestData(shipmentData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @Given("^Admin change \"([^\"]*)\" device state to RETURNED$")
    public void adminChangeDeviceStateToRETURNED(String deviceSN) throws Throwable {
        basePage.selectTheFollowingButtonFromNavMenu("Device List");
        deviceListPage.filterBySerialNumber(deviceSN);
        deviceListPage.changeDeviceStateToReturned(deviceSN);
        deviceListPage.verifyDeviceReturnedStatus(deviceSN);
    }

    @And("^Admin imports device to shipment$")
    public void adminImportsDeviceToShipment() {
        shipmentListPage.importShipmentButtonClick();
        shipmentListPage.fillShippingDateAsToday();
        shipmentListPage.uploadExcelFileWithDevice();
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin adds production order with the following data:$")
    public void adminAddsProductionOrderWithTheFollowingData(Map<String, String> productionOrderData) {
        deviceListPage.addProductionOrderClick();
        deviceListPage.fillProductionOrderData(productionOrderData);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Admin creates device with excel file$")
    public void adminCreatesDeviceWithExcelFile() {
        deviceListPage.createDevicesClick();
        deviceListPage.uploadCreateDeviceExcelFile();
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @Then("^Admin verifies the first entry from distributor list contains the following values:$")
    public void adminVerifiesTheFirstEntryFromDistributorListContainsTheFollowingValues(List<String> values) {
        distributorListPage.verifyTheFirstRowDetails(values);
    }

    @Then("^Admin verifies the first entry from user list contains the following values:$")
    public void adminVerifiesTheFirstEntryFromUserListContainsTheFollowingValues(List<String> values) {
        userListPage.verifyTheFirstRowDetails(values);
    }

    @Then("^Admin verifies new leasing contract for \"([^\"]*)\" distributor was created$")
    public void adminVerifiesNewLeasingContractForDistributorWasCreated(String company) {
        distributorContractListPage.verifyNewLeasingContractForDistributorWasCreated(company);
    }

    @Then("^Admin verifies new billing event was created with the following data:$")
    public void adminVerifiesNewBillingEventWasCreatedWithTheFollowingData(List<String> values) {
        billingListPage.verifyNewBillingEventWasCreated(values);
    }

    @Then("^Admin verifies new rental customer order was created with the following data:$")
    public void adminVerifiesNewRentalCustomerOrderWasCreatedWithTheFollowingData(List<String> values) {
        customerOrderListPage.verifyNewRentalCustomerOrderWasCreated(values);
    }

    @Then("^Admin verifies new leasing customer order was created with the following data:$")
    public void adminVerifiesNewLeasingCustomerOrderWasCreatedWithTheFollowingData(List<String> values) {
        customerOrderListPage.verifyNewLeasingCustomerOrderWasCreated(values);
    }

    @Then("^Admin verifies new address for customer was created with the following data:$")
    public void adminVerifiesNewAddressForCustomerWasCreatedWithTheFollowingData(List<String> values) {
        addressListPage.verifyNewAddressForCustomerWasCreated(values);
    }

    @Then("^Admin verifies new contact for customer was created with the following data:$")
    public void adminVerifiesNewContactForCustomerWasCreatedWithTheFollowingData(List<String> values) {
        addressListPage.firstDetailButtonClick();
        addressListPage.verifyNewContactForCustomerWasCreated(values);
    }

    @Then("^Admin verifies new shipment with require state is created with the following data:$")
    public void adminVerifiesNewShipmentWithRequireStateIsCreatedWithTheFollowingData(List<String> values) {
        shipmentListPage.verifyNewShipmentWasCreated(values);
    }

    @Then("^Admin verifies shipment with the following data:$")
    public void adminVerifiesShipmentWithTheFollowingData(List<String> values) {
        shipmentListPage.verifyShipmentWithValues(values);
    }

    @Then("^Admin verifies journey with the following data:$")
    public void adminVerifiesJourneyWithTheFollowingData(List<String> values) {
        tidListPage.verifyJourneyWithValues(values);
    }

    @Then("^Admin verifies device with the following data:$")
    public void adminVerifiesDeviceWithTheFollowingData(List<String> values) {
        deviceListPage.filterBySerialNumber(values.get(0));
        deviceListPage.verifyDeviceWithValues(values);
    }
}
