package net.hanhaa.dev.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.hanhaa.dev.pages.*;
import net.hanhaa.dev.pages.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasicSteps {

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

    @And("^Select the \"([^\"]*)\" from navigation menu$")
    public void selectTheFromNavigationMenu(String navigationMenuButton) throws Exception {
        basePage.selectTheFollowingButtonFromNavMenu(navigationMenuButton);
    }

    @And("^Filter the table by the following company name: \"([^\"]*)\"$")
    public void filterTheTableByTheFollowingCompanyName(String companyName) {
        distributorListPage.filterTableByCompanyName(companyName);
    }

    @And("^Select to add a new user$")
    public void selectToAddANewUser() {
        userListPage.addNewUserButtonClick();
    }

    @And("^Filter the table by the following user email: \"([^\"]*)\"$")
    public void filterTheTableByTheFollowingUserEmail(String email) {
        userListPage.filterByTheFollowingEmail(email);
    }

    @And("^Filter the table by the following channel Partner: \"([^\"]*)\"$")
    public void filterTheTableByTheFollowingChannelPartner(String channelPartner) throws Throwable {
        customerOrderListPage.filterTableByChannelPartner(channelPartner);
    }

    @And("^Filter address table by the following company name: \"([^\"]*)\"$")
    public void filterAddressTableByTheFollowingCompanyName(String companyName) throws Throwable {
        addressListPage.filterTableByCompany(companyName);
    }

    @And("^Filter TID list by \"([^\"]*)\" TID$")
    public void filterTIDListByTID(String sn) throws Throwable {
        tidListPage.filterTIDBySN(sn);
    }

    @And("^Filter the table by the following order number: \"([^\"]*)\"$")
    public void filterTheTableByTheFollowingOrderNumber(String orderNumber) throws Throwable {
        customerOrderListPage.filterTableByOrderNumber(orderNumber);
    }
}
