package net.hanhaa.dev.steps;

import cucumber.api.java.en.And;
import net.hanhaa.dev.helpers.RestConnector;
import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.BasePage;
import net.hanhaa.dev.pages.admin.CustomerListPage;
import net.hanhaa.dev.pages.admin.UserListPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ManagerSteps {

    @Autowired
    BasePage basePage;
    @Autowired
    StepsHelper stepsHelper;
    @Autowired
    CustomerListPage customerListPage;
    @Autowired
    UserListPage userListPage;


    @And("^Distributor adds a new customer with the following data:$")
    public void distributorAddsANewCustomerWithTheFollowingData(Map<String, String> values) {
        customerListPage.clickAddNewCustomer();
        customerListPage.fillInCustomerDetails(values);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }

    @And("^Distributor adds a new user with the following data:$")
    public void distributorAddsANewUserWithTheFollowingData(Map<String, String> values) {
        userListPage.addNewUserButtonClick();
        userListPage.fillUserData(values);
        stepsHelper.clickSaveButton();
        basePage.clickOkButtonAtSuccessAlert();
    }
}
