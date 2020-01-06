package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;

@Component
public class UserListPage extends BasePage {

    @Autowired
    StepsHelper stepsHelper;

    private final String addNewUserButtonLocator = "//a[contains(@href,'userAdd')]";
    private final String userFirstNameLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='firstName']";
    private final String userLastNameLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='lastName']";
    private final String userEmailLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='email']";
    private final String companyFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//button[@data-toggle='dropdown']";
    private final String roleDropdownButtonLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//button[@data-id='role']";
    private final String managerRoleEntryLocator = "//div[@class='dropdown-menu open']/ul/li//span[contains(.,'MANAGER')]";
    private final String tableEmailFieldLocator = "//input[@id='email']";
    private final String tableHeadLocator = "//div[@class='navtabPage unitBox' and contains(@style,'display: block;')]//table[@class='table table-bordered']";
    private final String firstRowLocator = "//div[@class='navtab-panel tabsPageContent']/div[3]//div[@class='fixedtableTbody']//table/tbody/tr[1]";
    private final String firstRowLocatorSecondTime = "//div[@class='navtab-panel tabsPageContent']/div[2]//div[@class='fixedtableTbody']//table/tbody/tr[1]";
    private final String activateButton = "//div[@style='display: block;']//tbody/tr[1]//a[@data-confirm-msg='Are you sure you want to activate user?']";
    private final String distributorListDropbox = "//body/div[11]/div[@class='dropdown-menu open']";
    private final String impersonateFirstUser = "//div[@style='display: block;']//tbody/tr[1]//a[@class='btn btn-orange']";


    public void addNewUserButtonClick() {
        $(addNewUserButtonLocator).click();
    }

    public void fillUserData(Map<String, String> userData) {
        $(userFirstNameLocator).sendKeys(userData.get("First Name"));
        $(userLastNameLocator).sendKeys(userData.get("Last Name"));
        $(userEmailLocator).sendKeys(userData.get("Contact Email"));
        $(companyFieldLocator).click();
        String companyEntryLocator = "//div[@class='dropdown-menu open']/ul[@style]/li/a/span[contains(text(),'" + userData.get("Company Name") + "')]";
        $(companyEntryLocator).click();
    }


    public void filterByTheFollowingEmail(String userEmail) {
        $(tableEmailFieldLocator).sendKeys(userEmail + Keys.ENTER);
        waitUntilNoLoader();
    }

    public void verifyTheFirstRowDetails(List<String> values) {
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[0]
                .concat($(firstRowLocator).getText().split("\\s+")[1]), equalTo(values.get(0).concat(values.get(1))));
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[2], equalTo(values.get(2)));
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[4], equalTo(values.get(3)));
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[3], equalTo(values.get(4)));
    }

    public void activateUser() {
        $(activateButton).click();
        waitForElementToBeVisible(By.xpath(portalAlert), 5);
        $(confirmOkAlertButton).click();
        waitUntilNoLoader();
        waitForElementToBeVisible(By.xpath(portalAlert), 5);
        $(successAlertOkButton).click();
    }

    public void verifyTheFirstRowDetailsSecondTime(List<String> values) {
        Assert.assertThat($(firstRowLocatorSecondTime).getText().split("\\s+")[0]
                .concat($(firstRowLocatorSecondTime).getText().split("\\s+")[1]), equalTo(values.get(0).concat(values.get(1))));
        Assert.assertThat($(firstRowLocatorSecondTime).getText().split("\\s+")[2], equalTo(values.get(2)));
        Assert.assertThat($(firstRowLocatorSecondTime).getText().split("\\s+")[4], equalTo(values.get(3)));
        Assert.assertThat($(firstRowLocatorSecondTime).getText().split("\\s+")[3], equalTo(values.get(4)));
    }

    public void impersonateNewUser() {
        $(impersonateFirstUser).click();
    }
}
