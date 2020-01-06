package net.hanhaa.dev.steps;

import cucumber.api.java.en.Then;
import net.hanhaa.dev.pages.BasePage;
import net.hanhaa.dev.pages.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DistributorSteps {

    @Autowired
    BasePage basePage;
    @Autowired
    LoginPage loginPage;
    @Autowired
    DistributorListPage distributorListPage;
    @Autowired
    DistributorContractListPage distributorContractListPage;
    @Autowired
    UserListPage userListPage;
    @Autowired
    CustomerListPage customerListPage;
    @Autowired
    BillingListPage billingListPage;
    @Autowired
    CustomerOrderListPage customerOrderListPage;
    @Autowired
    AddressListPage addressListPage;
    @Autowired
    ShipmentListPage shipmentListPage;
    @Autowired
    TIDListPage tidListPage;
    @Autowired
    DeviceListPage deviceListPage;

    @Then("^Distributor verifies the first entry from customer list contains the following values:$")
    public void distributorVerifiesTheFirstEntryFromCustomerListContainsTheFollowingValues(List<String> values) {
        customerListPage.verifyFirstRowDetails(values);
    }

    @Then("^Distributor verifies the first entry from user list contains the following values second time:$")
    public void distributorVerifiesTheFirstEntryFromUserListContainsTheFollowingValuesSecondTime(List<String> values) {
        userListPage.verifyTheFirstRowDetailsSecondTime(values);
    }
}
