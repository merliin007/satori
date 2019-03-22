/*
 * Created by Miguel Angel Aguilar Cuevas
 * 14/01/2019 at 11:44 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import pages.home.LandingPage;
import utility.Helpers;
import utility.Log;
import java.util.List;
import static org.testng.Assert.fail;
import static utility.Helpers.AddErrorPage;

public class LandingSteps {

    private BaseUtil base;
    private Helpers I;
    private LandingPage landingPage;

    public LandingSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @When("^I navigate to the following public page option$")
    public void iNavigateToTheFollowingPublicPageOption(DataTable table) {
        try {
            landingPage = new LandingPage(base.driver);

            List<List<String>> data = table.raw();
            for (List<String> row : data) {
                Log.info("Navigating to " + row.get(1) + " page");
                I.Click(landingPage.getBtnNavigator()); // Code for using Navigator Menu
                Log.info("Current page: " + landingPage.getBreadCrumbChildText());
                try {
                    WebElement pageLink = landingPage.getLinkOptionFromMenu(row.get(1));
                    if (pageLink != null)
                        I.Click(pageLink);
                    else
                        AddErrorPage(row);
                } catch (Exception e) {
                    AddErrorPage(row);
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: " + row.get(0) + " - " + row.get(1));
                    Log.error(e.getMessage());
                }
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error during navigation to: ");
            fail();
        }
    }
}
