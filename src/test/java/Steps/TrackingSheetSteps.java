/*
 * Created by Miguel Angel Aguilar Cuevas
 * 10/01/2019 at 1:48 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.newPages.tss.TrackingSheetPage;
import pages.newPages.tss.TrackingSheetScorecardDetailPage;
import utility.Helpers;
import utility.Log;
import utility.tss.TrackingSheetScoreCard;
import utility.tss.TssWeekElements;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TrackingSheetSteps {
    private BaseUtil base;
    private Helpers I;
    private TrackingSheetPage tss;
    private TrackingSheetScorecardDetailPage tssDetailPage;

    public TrackingSheetSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @And("^I search using the criteria$")
    public void iSearchUsingTheCriteria(DataTable table) {
        tss = new TrackingSheetPage(base.driver);
        try {
            I.EnterTssSearchCriteria(table.raw().get(1), tss);
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @And("^Selecting tracking sheet at position \"([^\"]*)\"$")
    public void selectingTrackingSheetAtPosition(int index) {
        try {
            List<WebElement> tblResults = tss.getTblResults(0);
            I.Click(tblResults.get(index));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @And("^Edit tss changing$")
    public void editTssChanging(DataTable table) {
        try {
            List<TrackingSheetScoreCard> tssList = table.asList(TrackingSheetScoreCard.class);
            tssDetailPage = new TrackingSheetScorecardDetailPage(base.driver);
            I.waitUntilElementIsVisible(tssDetailPage.getBtnCancel());
            Log.info("Start editing tracking sheet");

            List<TssWeekElements> weekElements = tssDetailPage.getWeekElements();
            for (TrackingSheetScoreCard tss : tssList) {
                I.EditTSSFields(tss, weekElements.get(tss.getWeek() - 1));
                I.Write(tssDetailPage.getTxtHomeComments(), tss.getHomeComments());
                I.Write(tssDetailPage.getTxtAwayComments(), tss.getAwayComments());
            }

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^I got \"([^\"]*)\" message after save$")//TODO: VALIDATE CORRECT RESPONSE MESSAGE
    public void iGotMessageAfterSave(String resultMessage) {
        if (tssDetailPage == null || resultMessage.isEmpty()) return;
        try {
            Log.info("Validating final toastr message");
            I.Click(tssDetailPage.getBtnSave());
            I.waitUntilExistenceOfElement(tss.getToastContainerLocator());
            String msg = tss.getToastrMesage();
            Log.info("Gotten message: " + msg);
            assertTrue(msg.contains(resultMessage));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}

