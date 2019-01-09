/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 2:43 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.newPages.volunteer.VolunteerBulkEntryPage;
import utility.Helpers;
import utility.Log;
import utility.volunteer.Position;

import java.util.List;

import static org.testng.Assert.fail;

public class VolunteerBulkEntrySteps {
    private BaseUtil baseUtil;
    private Helpers I;
    private VolunteerBulkEntryPage bulkEntry;

    public VolunteerBulkEntrySteps(BaseUtil base) {
        baseUtil = base;
        I = new Helpers(base.driver);
    }

    @When("^I search for ([^\"]*) ALTA number on Volunteer Bulk Entry page$")
    public void iSearchForALTANumberOnVolunteerBulkEntryPage(String altaNumber) {
        try {
            bulkEntry = new VolunteerBulkEntryPage(baseUtil.driver);
            I.Write(bulkEntry.getTxtAltaNumber(), altaNumber);
            I.Click(bulkEntry.getBtnSearch());
            I.WaitUntilVisualizationOfElementLocatedBy(bulkEntry.getModalLocator());
        } catch (Exception e) {
            Log.error(e.getMessage());
            baseUtil.GrabScreenShot();
            fail();
        }
    }

    @And("^I select the following positions for this user$")
    public void iSelectTheFollowingPositionsForThisUser(DataTable table) {
        try {
            List<List<String>> positionList = table.raw();
            for (List<String> pos : positionList) {
                Position position = new Position(pos.get(0), pos.get(1), pos.get(2), pos.get(3), pos.get(4));
                I.SelectValue(bulkEntry.getPositionModal(), position.getPosition());
                I.WaitUntilPresenceOfElement(bulkEntry.getYearLocator());
                I.SelectValue(bulkEntry.getYearModal(), position.getYear());
                if (!position.getLeague().isEmpty() && !position.getSeason().isEmpty()) {
                    I.WaitUntilPresenceOfElement(bulkEntry.getLeagueLocator());
                    I.SelectValueLike(bulkEntry.getLeagueModal(), position.getLeague());
                    I.SelectValue(bulkEntry.getSeasonModal(), position.getSeason());
                }
                I.Write(bulkEntry.getCommentsModal(), position.getComments());
                I.Click(bulkEntry.getSaveModal());
                I.waitUntilInvisibilityOf(bulkEntry.getModal());

            }
            I.Click(bulkEntry.getBtnClear());
        } catch (Exception e) {
            Log.error(e.getMessage());
            baseUtil.GrabScreenShot();
            fail();
        }
    }

    @Then("^I get ([^\"]*) toastr$")
    public void iGetMessageToastr(String result)  {
        try {
            I.WaitUntilPresenceOfElement(bulkEntry.getToastMessage(result));
            //Log.info(bulkEntry.getToast(bulkEntry.getToastMessage(result)).getText());
        } catch (Exception e) {
            Log.error(e.getMessage());
            baseUtil.GrabScreenShot();
            fail();

        }
    }
}
