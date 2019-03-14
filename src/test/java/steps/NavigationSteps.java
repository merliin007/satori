package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.home.MainPage;
import utility.Helpers;
import utility.Log;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static utility.Helpers.AddErrorPage;
import static utility.Helpers.getErrorPages;


public class NavigationSteps {
    private BaseUtil base;
    private MainPage mainPage;
    private Helpers I;

    public NavigationSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }


    @And("I navigate to the following page option")
    public void iNavigateToTheFollowingPageOption(DataTable links) {
        try {
            mainPage = new MainPage(base.driver);
            List<List<String>> data = links.raw();
            for (List<String> row : data) {
                Log.info("Navigating to " + row.get(1) + " page");
                I.Click(mainPage.getNavigator()); // Code for using Navigator Menu
                try {
                    I.Click(mainPage.getLinkOptionFromMenu(row.get(0), row.get(1)));
                    Log.info(String.valueOf(I.CompareExpectedPage(row.get(1))));
                } catch (Exception ee) {
                    AddErrorPage(row);
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: " + row.get(0) + " - " + row.get(1));
                } finally {
                    if (!row.get(2).isEmpty() && row.get(2).equals("true"))
                        I.GoBackToPreviousPage();
                }
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error during navigation to: ");
            fail();
        }
    }

    @Then("^No error is shown$")
    public void noErrorIsShown() {
        if (getErrorPages().isEmpty())
            Log.info("No errors were found accessing all pages");
        else
            Log.info("There was an error accessing: " + Collections.singleton(getErrorPages()));
        assertTrue(getErrorPages().isEmpty());
    }

    @Then("^I switch to \"([^\"]*)\"$")
    public void iSwitchTo(String menu) {
        try {
            if (mainPage == null) mainPage = new MainPage(base.driver);
            I.Click(mainPage.getSwitchTo());
            I.Click(mainPage.getSwitchToOptionDdl(menu));
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error switching to: " + menu);
            fail();
        }
    }
}
