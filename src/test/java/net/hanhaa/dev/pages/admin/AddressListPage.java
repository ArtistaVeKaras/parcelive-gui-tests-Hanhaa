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
public class AddressListPage extends BasePage {

    private final String addAddressButton = "//a[contains(@href,'companyAddressAdd')]";
    private final String streetInput = "//*[@id='street']";
    private final String streetNumberInput = "//*[@id='streetNumber']";
    private final String cityInput = "//*[@id='city']";
    private final String zipCodesInput = "//*[@id='zip']";
    private final String countryDropboxButton = "//*[@id='companyAddressVO']/div/div[5]//button";
    private final String openedCountryDropbox = "//body/div[10]/div";
    private final String openedCountryDropboxInput = "//body/div[10]//div[@class='bs-searchbox']/input";
    private final String addAddressModalCompanyDropboxButton = "//*[@id='companyAddressVO']/div/div[8]//button";
    private final String openedCompanyDropbox = "//body/div[11]/div";
    private final String addAddressModalOpenedCompanyDropboxInput = "//body/div[11]//div[@class='bs-searchbox']/input";
    private final String firstCompanyNameField = "//div[@style='display: block;']//tbody/tr[1]/td[1]";
    private final String firstAddressField = "//div[@style='display: block;']//tbody/tr[1]/td[2]";
    private final String companyDropboxButton = "//*[@id='pagerForm']//button[@data-id='companyId']";
    private final String openedDropbox = "//div[@class='btn-group bootstrap-select show-tick open']//div[@class='dropdown-menu open']";
    private final String openedCompanyDropboxInput = "//body/div[10]/div/div/input";
    private final String searchCompanyButton = "//div[@style='display: block;']//button[@type='submit']";
    private final String addContactButton = "//a[contains(@href,'companyContactAdd')]";
    private final String addContactFirstNameInput = "//*[@id='firstName']";
    private final String addContactLastNameInput = "//*[@id='lastName']";
    private final String addContactNumberInput = "//*[@id='contactNumber']";
    private final String addContactEmailInput = "//*[@id='email']";
    private final String addContacLanguageButton = "//*[@id='companyContactVO']/div[5]//button";
    private final String addContacLanguageDropboxInput = "//div[@class='btn-group bootstrap-select show-tick open']//div[@class='dropdown-menu open']//input";
    private final String addContactAddButton = "//*[@id='companyContactSaveBtn']";
    private final String firstDetailButton = "//a[contains(@href,'companyAddress/detail')]";
    private final String detailCompanyField = "//*[@id='bjui-navtab']//fieldset[1]/div[9]/label[2]";
    private final String detailFirstNameField = "//*[@id='bjui-navtab']//fieldset[2]/div[1]/label[2]";
    private final String detailLastNameField = "//*[@id='bjui-navtab']//fieldset[2]/div[2]/label[2]";
    private final String detailContactNumberField = "//*[@id='bjui-navtab']//fieldset[2]/div[3]/label[2]";
    private final String detailEmailField = "//*[@id='bjui-navtab']//fieldset[2]/div[4]/label[2]";
    private final String detailLanguageField = "//*[@id='bjui-navtab']//fieldset[2]/div[5]/label[2]";

    public void addAddressButtonClick() {
        $(addAddressButton).click();
        waitUntilNoLoader();
    }

    public void fillAddressData(Map<String, String> addressData) {
        $(streetInput).sendKeys(addressData.get("Street"));
        $(streetNumberInput).sendKeys(addressData.get("Street Number"));
        $(cityInput).sendKeys(addressData.get("City"));
        $(zipCodesInput).sendKeys(addressData.get("ZIP code"));
        $(countryDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedCountryDropbox), 2);
        $(openedCountryDropboxInput).sendKeys(addressData.get("Country"), Keys.ENTER);
        $(addAddressModalCompanyDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedCompanyDropbox), 2);
        $(addAddressModalOpenedCompanyDropboxInput).sendKeys(addressData.get("Company"), Keys.ENTER);
    }

    public void verifyNewAddressForCustomerWasCreated(List<String> values) {
        Assert.assertThat($(firstCompanyNameField).getText(), equalTo(values.get(4)));
        Assert.assertThat($(firstAddressField).getText(), equalTo(
                values.get(0) + ", " + values.get(1) + ", " + values.get(2) + ", " + values.get(3)));
    }

    public void filterTableByCompany(String companyName) {
        $(companyDropboxButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 2);
        $(openedCompanyDropboxInput).sendKeys(companyName, Keys.ENTER);
        $(searchCompanyButton).click();
        waitUntilNoLoader();
    }

    public void addContactButtonClick() {
        $(addContactButton).click();
        waitUntilNoLoader();
    }

    public void fillContactData(Map<String, String> contactData) {
        $(addContactFirstNameInput).sendKeys(contactData.get("First Name"));
        $(addContactLastNameInput).sendKeys(contactData.get("Last Name"));
        $(addContactNumberInput).sendKeys(contactData.get("Contact Number"));
        $(addContactEmailInput).sendKeys(contactData.get("Email"));
        $(addContacLanguageButton).click();
        waitForElementToBeVisible(By.xpath(openedDropbox), 2);
        $(addContacLanguageDropboxInput).sendKeys(contactData.get("Language"), Keys.ENTER);
    }

    public void addContactAddButtonClick() {
        $(addContactAddButton).click();
    }

    public void firstDetailButtonClick() {
        $(firstDetailButton).click();
        waitUntilNoLoader();
    }

    public void verifyNewContactForCustomerWasCreated(List<String> values) {
        Assert.assertThat($(detailCompanyField).getText(), equalTo(values.get(0)));
        Assert.assertThat($(detailFirstNameField).getText(), equalTo(values.get(1)));
        Assert.assertThat($(detailLastNameField).getText(), equalTo(values.get(2)));
        Assert.assertThat($(detailContactNumberField).getText(), equalTo(values.get(3)));
        Assert.assertThat($(detailEmailField).getText(), equalTo(values.get(4)));
        Assert.assertThat($(detailLanguageField).getText(), equalTo(values.get(5)));
    }
}