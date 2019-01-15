package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.home.MainPage;
import utility.Helpers;
import utility.Log;

import java.util.Collections;
import java.util.List;



import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static utility.Helpers.getErrorPages;
import static utility.Helpers.setAddErrorPage;

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
                    setAddErrorPage(row);
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: " + row.get(0) + " - " + row.get(1));
                }
                finally{
                    if(!row.get(2).isEmpty() && row.get(2).equals("true"))
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
        if(getErrorPages().size() > 0)
            Log.info("There was an error accessing: " + Collections.singleton(getErrorPages()));
        else
            Log.info("No errors were found accessing all pages");
        assertTrue(getErrorPages().isEmpty());
    }
}
