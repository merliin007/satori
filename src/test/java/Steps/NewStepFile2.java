package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import pages.newPages.TransactionManagerPage;
import utility.Log;

import static org.testng.Assert.fail;

public class NewStepFile2 {
    private BaseUtil base;
    private TransactionManagerPage transactionManagerPage;

    public NewStepFile2(BaseUtil base) {
        this.base = base;
        transactionManagerPage = new TransactionManagerPage(base.driver);
    }

    @And("^I type \"([^\"]*)\" as reason$")
    public void iTypeAsReason(String arg0) {
        try {
            transactionManagerPage.getTxtReason().sendKeys(arg0);
        } catch (Exception e) {
            Log.error(e.getMessage());
            fail();
        }
    }
}
