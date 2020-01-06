package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class DistributorContractListPage extends BasePage {

    @Autowired
    StepsHelper stepsHelper;

    private final String addNewDistributorContractButtonLocator = "//a[contains(@href,'companyContractAdd')]";
    private final String companyDropdownLocator = "//form[@id='companyContractLeasingConfigurationVO']//button[@data-id='companyId']";
    private final String companyDropdownSearchLocator = "/html//div[11]//div[@class='bs-searchbox']/input";
    private final String initialPaymentInboxFieldLocator = "//div[@id='lpd-GBP']/div[1]/input";
    private final String firstRecordDistributorNameLocator = "//div[@style='display: block;']//tbody/tr[1]/td[1]";
    private final String firstRecordStateLocator = "//div[@style='display: block;']//tbody/tr[1]/td[6]";
    private final String confirmDistributorContractButton = "//tbody/tr[1]/td/a[contains(@href,'confirm')]";
    private final String distributorListDropbox = "//body/div[11]/div[@class='dropdown-menu open']";

    public void addNewDistributorContractButtonClick() {
        $(addNewDistributorContractButtonLocator).click();
        waitUntilNoLoader();
    }

    public void fillInDistributorContractDetails(Map<String, String> contractData) {
        $(companyDropdownLocator).click();
        waitForElementToBeVisible(By.xpath(distributorListDropbox),5);
        $(companyDropdownSearchLocator).sendKeys(contractData.get("Company"), Keys.ENTER);
        $(initialPaymentInboxFieldLocator).clear();
        $(initialPaymentInboxFieldLocator).sendKeys(contractData.get("Initial Payment"));
    }

    public void verifyNewLeasingContractForDistributorWasCreated(String company) {
        waitForElementToBeVisible(By.xpath(confirmDistributorContractButton), 5);
        Assert.assertThat($(firstRecordDistributorNameLocator).getText(), equalTo(company));
        Assert.assertThat($(firstRecordStateLocator).getText(), equalTo("OPEN"));
    }

    public void confirmDistributorContract() {
        $(confirmDistributorContractButton).click();
        waitForElementToBeVisible(By.xpath(portalAlert), 5);
        $(confirmOkAlertButton).click();
        waitUntilNoLoader();
        waitForElementToBeVisible(By.xpath(portalAlert), 5);
        $(successAlertOkButton).click();
    }
}
