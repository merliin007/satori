package steps;

import base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.ui.Select;
import pages.newPages.TransactionManagerPage;
import utility.Helpers;
import utility.Log;
import static org.junit.Assert.fail;

public class NewStepFile{
    private TransactionManagerPage transactionManagerPage;
    private Helpers I;
    private BaseUtil base;

    public NewStepFile(BaseUtil base) {
        this.base = base;
        transactionManagerPage = new TransactionManagerPage(base.driver);
        I = new Helpers(base);
    }

    @And("^I search for \"([^\"]*)\" in the dropdown$")
    public void iSearchForInTheDropdown(String searchString) {
        try {
            transactionManagerPage = new TransactionManagerPage(base.driver);
            transactionManagerPage.getTxtLocation().sendKeys(searchString);
            I.waitUntilElementWithTextIsInvisible(transactionManagerPage.getByLoadingResults(), "Searching...");
            I.waitUntilExistenceOfElement(transactionManagerPage.byHighlightedResults());
            transactionManagerPage.HighlightedResults().click();
            Log.info("Correct selection of: " + searchString + " result!!!");
        } catch (Exception e) {
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I Select \"([^\"]*)\" option for Category$")
    public void iSelectOptionForCategory(String opt) {
        try {
            Select select = new Select(transactionManagerPage.getDrpCategory());
            select.selectByVisibleText(opt);
            Log.info("Correct selection of: " + opt);
        } catch (Exception e) {
            Log.error(e.getMessage());
            fail();
        }
    }
}
