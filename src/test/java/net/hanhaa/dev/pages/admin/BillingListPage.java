package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class BillingListPage extends BasePage {

    private final String firstChannelPartnerFieldLocator = "//div[@style='display: block;']//tbody/tr[1]/td[4]";
    private final String firstCPAmountDueFieldLocator = "//div[@style='display: block;']//tbody/tr[1]/td[9]";

    public void verifyNewBillingEventWasCreated(List<String> values) {
        String firstChannelPartnerFieldValue = $(firstCPAmountDueFieldLocator).getText();
        Assert.assertThat($(firstChannelPartnerFieldLocator).getText(), equalTo(values.get(0)));
        Assert.assertThat(firstChannelPartnerFieldValue.substring(0, firstChannelPartnerFieldValue.length() - 4),
                equalTo(values.get(1).concat(".0")));
    }
}
