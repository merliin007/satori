package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.newPages.nMainPage;
import utility.Log;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.fail;

public class NavigationSteps {
    private BaseUtil base;
    private nMainPage mainPage;

    public NavigationSteps(BaseUtil base) {
        this.base = base;
    }

    @And("I navigate to the following pages option")
    public void iNavigateToTheFollowingPagesOption(DataTable links) {
        try {
            mainPage = new nMainPage(base.driver);
            List<List<String>> data = links.raw();
            for (List<String> row : data) {
                Log.info(row.get(1));
                mainPage.getNavigator().click(); // Code for using Navigator Menu
                try {
                    mainPage.getNavigatorLink(row.get(1)).click();
                }
                catch(Exception ee)
                {
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: " );
                }
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error during navigation to: " );
            fail();
        }
    }

    @Then("^No error is shown$")
    public void noErrorIsShown() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
