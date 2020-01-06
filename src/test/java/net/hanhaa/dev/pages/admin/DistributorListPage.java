package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class DistributorListPage extends BasePage {

    @Autowired
    StepsHelper stepsHelper;

    private final String addNewDistributorButtonLocator = "//a[contains(@href,'distributorAdd')]";
    private final String companyNameFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='companyName']";
    private final String companyEmailFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='email']";
    private final String companyNumberFieldLocator = "//div[contains(@class, 'bjui-dialog-wrap')]//input[@id='contactNumber']";
    private final String companyNameFilterLocator = "//input[@id='companyName']";
    private final String firstRowLocator = "//div[@class='fixedtableTbody']/table/tbody/tr[1]";

    public void addNewDistributorButtonClick() {
        $(addNewDistributorButtonLocator).click();
    }

    public void fillInCompanyDetails(Map<String, String> companyData) {
        $(companyNameFieldLocator).sendKeys(companyData.get("Company Name"));
        $(companyEmailFieldLocator).sendKeys(companyData.get("Company Email"));
        $(companyNumberFieldLocator).sendKeys(companyData.get("Company Number"));
    }

    public void filterTableByCompanyName(String companyName) {
        $(companyNameFilterLocator).sendKeys(companyName+ Keys.RETURN);
        waitUntilNoLoader();
    }

    public void verifyTheFirstRowDetails(List<String> values) {
        String[] valuesToCheck = $(firstRowLocator).getText().split("\\s+");
        Assert.assertThat(valuesToCheck[0], equalTo(values.get(0)));
        Assert.assertThat(valuesToCheck[1], equalTo(values.get(1)));
        Assert.assertThat(valuesToCheck[2], equalTo("0.0"));
        Assert.assertThat(valuesToCheck[3], equalTo(values.get(2)));
    }
}
