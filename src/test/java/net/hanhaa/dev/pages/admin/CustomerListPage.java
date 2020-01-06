package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class CustomerListPage extends BasePage {

    private final String addNewCustomerButtonLocator = "//a[contains(@href,'customerAdd')]";
    private final String customerCompanyNameFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='companyName']";
    private final String customerCompanyCodeFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='companyCode']";
    private final String customerCompanyEmailFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='email']";
    private final String customerCompanyContactNumberFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='contactNumber']";
    private final String firstRowLocator = "//*[@id='bjui-navtab']/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr";

    public void clickAddNewCustomer() {
        $(addNewCustomerButtonLocator).click();
    }

    public void fillInCustomerDetails(Map<String, String> values) {
        $(customerCompanyNameFieldLocator).sendKeys(values.get("Customer Name"));
        $(customerCompanyEmailFieldLocator).sendKeys(values.get("Customer Email"));
        $(customerCompanyContactNumberFieldLocator).sendKeys(values.get("Customer Number"));
    }

    public void verifyFirstRowDetails(List<String> values) {
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[0], equalTo(values.get(0)));
        for (int i = 2; i < 3; i++) {
            Assert.assertThat($(firstRowLocator).getText().split("\\s+")[i], equalTo(values.get(i-1)));
        }
        Assert.assertThat($(firstRowLocator).getText().split("\\s+")[3], equalTo(values.get(3)));
    }
}
