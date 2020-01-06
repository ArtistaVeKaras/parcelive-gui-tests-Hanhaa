package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class CustomerOrderListPage extends BasePage {

    private final String addRentalCustomerOrderButton = "//a[contains(@href,'customerOrderAddRental')]";
    private final String addLeasingCustomerOrderButton = "//a[contains(@href,'customerOrderAddLeasing')]";
    private final String addCustomerOrderModalWindow = "//div[@class='bjui-dialog bjui-dialog-container dialogShadow']";
    private final String orderModalChannelPartnerDropboxButton = "//*[@id='customerOrder']//button[@data-id='distributorId']";
    private final String openedDropbox = "//div[contains(@class, 'btn-group') and contains(@class, 'bootstrap-select') " +
            "and contains(@class, 'show-tick') and contains(@class, 'open')]/div[@class='dropdown-menu open']";
    private final String orderModalChannelPartnerDropboxInput = "//div[@class='btn-group bootstrap-select show-tick open']//input";
    private final String customerDropboxButton = "//*[@id='customerOrder']//button[@data-id='customerSelectId']";
    private final String customerDropboxInput = "//div[@class='btn-group bootstrap-select show-tick open']//input";
    private final String monthlyQuantityInput = "//input[@id='monthlyQuantity']";
    private final String firstCustomerNameField = "//div[@style='display: block;']//tbody/tr[1]/td[3]";
    private final String firstDistributorNameField = "//div[@style='display: block;']//tbody/tr[1]/td[5]";
    private final String firstOrderTypeField = "//div[@style='display: block;']//tbody/tr[1]/td[6]";
    private final String firstQuantityCommittedField = "//div[@style='display: block;']//tbody/tr[1]/td[8]";
    private final String contractDropboxButton = "//*[@id='customerOrder']/div[7]//button";
    private final String firstContractLink = openedDropbox + "//li[2]/a";
    private final String channelPartnerDropboxButton = "//div[@style='display: block;']//button[@data-id='distributorId']";
    private final String channelPartnerDropboxInput = "//div[contains(@class, 'btn-group') and contains(@class, 'bootstrap-select') " +
            "and contains(@class, 'show-tick') and contains(@class, 'open')]/div[@class='dropdown-menu open']//input";
    private final String searchButton = "//div[@style='display: block;']//button[@type='submit']";
    private final String firstAddShipmentRequestButton = "//*[@id='bjui-navtab']//div[@class='fixedtableScroller']//a[contains(@href,'shipmentAdd')]";
    private final String availableQuantityField = "//*[@id='quantityDisplay']";
    private final String quantityInput = "//*[@id='qty']";
    private final String shipmentAddressButton = "//*[@id='customerOrderShipmentAddVO']/div[10]//button";
    private final String shipmentContactTextField = "//button[@data-id='contactId' and not(@title='&amp;nbsp;')]";
    private final String orderNumberInput = "//*[@id='orderNumber']";
    private final String profileButton = "//*[@id='customerOrderShipmentAddVO']//button[@data-id='profileId']";
    private final String profileOpenedDropbox = "//div[@class='btn-group bootstrap-select open']//div[@class='dropdown-menu open']";
    private final String profileSearchFieldInput = "//div[@class='btn-group bootstrap-select open']//div[@class='dropdown-menu open']//input";

    public void addRentalCustomerOrderButtonClick() {
        $(addRentalCustomerOrderButton).click();
        waitForElementToBeVisible(By.xpath(addCustomerOrderModalWindow), 5);
        waitUntilNoLoader();
    }

    public void fillRentalOrderData(Map<String, String> rentalOrderData) {
        $(orderModalChannelPartnerDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(orderModalChannelPartnerDropboxInput).sendKeys(rentalOrderData.get("Channel Partner"), Keys.ENTER);
        $(customerDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(customerDropboxInput).sendKeys(rentalOrderData.get("Customer"), Keys.ENTER);
        $(monthlyQuantityInput).sendKeys(rentalOrderData.get("Monthly Quantity"));
    }

    public void verifyNewRentalCustomerOrderWasCreated(List<String> values) {
        Assert.assertThat($(firstCustomerNameField).getText(), equalTo(values.get(0)));
        Assert.assertThat($(firstDistributorNameField).getText(), equalTo(values.get(1)));
        Assert.assertThat($(firstOrderTypeField).getText(),
                equalTo(values.get(2)));
        Assert.assertThat($(firstQuantityCommittedField).getText(),
                equalTo(values.get(3)));
    }

    public void addLeasingCustomerOrderButtonClick() {
        $(addLeasingCustomerOrderButton).click();
        waitForElementToBeVisible(By.xpath(addCustomerOrderModalWindow), 5);
        waitUntilNoLoader();
    }

    public void fillLeasingOrderData(Map<String, String> leasingOrderData) {
        $(orderModalChannelPartnerDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(orderModalChannelPartnerDropboxInput).sendKeys(leasingOrderData.get("Channel Partner"), Keys.ENTER);
        $(customerDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(customerDropboxInput).sendKeys(leasingOrderData.get("Customer"), Keys.ENTER);
        $(contractDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(firstContractLink).click();
    }

    public void verifyNewLeasingCustomerOrderWasCreated(List<String> values) {
        Assert.assertThat($(firstCustomerNameField).getText(), equalTo(values.get(0)));
        Assert.assertThat($(firstDistributorNameField).getText(), equalTo(values.get(1)));
        Assert.assertThat($(firstOrderTypeField).getText(), equalTo(values.get(2)));
    }

    public void filterTableByChannelPartner(String channelPartner) {
        $(channelPartnerDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 5);
        $(channelPartnerDropboxInput).sendKeys(channelPartner, Keys.ENTER);
        $(searchButton).click();
        waitUntilNoLoader();
    }

    public void addShipmentRequestButtonClick() {
        $(firstAddShipmentRequestButton).click();
        waitUntilNoLoader();
    }

    public void fillShipmentRequestData(Map<String, String> shipmentData) {
        $(profileButton).click();
        waitForElementToBeVisible(By.xpath(profileOpenedDropbox), 2);
        $(profileSearchFieldInput).sendKeys(shipmentData.get("Profile"), Keys.ENTER);
        String shipmentAddressThatContains = "//a[contains(.,'" + shipmentData.get("Shipment Address") + "')]";
        $(quantityInput).sendKeys(shipmentData.get("Device Quantity"));
        $(shipmentAddressButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 2);
        $(shipmentAddressThatContains).click();
        waitForElementToBeVisible(By.xpath(shipmentContactTextField), 2);
    }

    public void filterTableByOrderNumber(String orderNumber) {
        $(orderNumberInput).sendKeys(orderNumber, Keys.ENTER);
        waitUntilNoLoader();
    }
}
