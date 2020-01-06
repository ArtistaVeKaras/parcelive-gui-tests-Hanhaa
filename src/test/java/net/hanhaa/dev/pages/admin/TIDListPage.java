package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class TIDListPage extends BasePage {

    private final String firstTIDTableRow = "//div[@style='display: block;']//tbody//td[1]";
    private final String firstCustomerTableRow = "//div[@style='display: block;']//tbody//td[2]";
    private final String firstDistributorTableRow = "//div[@style='display: block;']//tbody//td[3]";
    private final String firstStateTableRow = "//div[@style='display: block;']//tbody//td[4]";
    private final String firstRequestedDeliveryTableRow = "//div[@style='display: block;']//tbody//td[5]";
    private final String tidFilterInput = "//div[@style='display: block;']//*[@name='tid']";


    public void verifyJourneyWithValues(List<String> values) {
        Assert.assertThat($(firstTIDTableRow).getText(), containsString(values.get(0)));
        Assert.assertThat($(firstCustomerTableRow).getText(), equalTo(values.get(1)));
        Assert.assertThat($(firstDistributorTableRow).getText(), equalTo(values.get(2)));
        Assert.assertThat($(firstStateTableRow).getText(), equalTo(values.get(3)));
        Assert.assertThat($(firstRequestedDeliveryTableRow).getText(), equalTo(getCurrentDate()));
    }

    public void filterTIDBySN(String SN) {
        $(tidFilterInput).sendKeys(SN, Keys.ENTER);
        waitUntilNoLoader();
    }


}
