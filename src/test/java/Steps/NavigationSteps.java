package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.newPages.nMainPage;
import utility.Helpers;
import utility.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class NavigationSteps {
    private BaseUtil base;
    private nMainPage mainPage;
    private Helpers I;

    public NavigationSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }

    private Map<String, String> errorPages = new HashMap<String, String>();

    @And("I navigate to the following page option")
    public void iNavigateToTheFollowingPageOption(DataTable links) {
        try {
            mainPage = new nMainPage(base.driver);
            List<List<String>> data = links.raw();
            for (List<String> row : data) {
                Log.info("Navigating to " + row.get(1) + " page");
                I.Click(mainPage.getNavigator()); // Code for using Navigator Menu
                try {
                    I.Click(mainPage.getOptionFromMenu(row.get(0), row.get(1)));
                } catch (Exception ee) {
                    errorPages.put(row.get(0), row.get(1));
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: ");
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
        Log.info("There as an error accessing: " + Arrays.asList(errorPages));
        assertTrue(errorPages.isEmpty());
    }
}
