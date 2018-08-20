/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 10:36 PM
 */
package steps;

import base.BaseUtil;
import base.CustomExceptions;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.newPages.documents.RosterInstructions_SelectionPage;
import pages.newPages.leagues.LeagueListPage;
import pages.newPages.leagues.LeagueTemplateSelectionPage;
import pages.newPages.leagues.LeagueNewEditPage;
import pages.newPages.leagues.LeagueVP_SelectionPage;
import utility.Helpers;
import utility.Log;
import utility.league.*;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertFalse;

public class LeagueSteps {
    private BaseUtil base;
    private Helpers I;
    private LeagueListPage leagueListPage;
    private LeagueTemplateSelectionPage leageTemplateSelectionPage;
    private LeagueNewEditPage leagueNewEditPage;
    private LeagueVP_SelectionPage leagueVPSelectionPage;
    private RosterInstructions_SelectionPage rosterInstructionsPage;

    private List<LeagueComponents.LeagueDescription> leagueDescriptionList;
    private List<LeagueComponents.LeagueDates> leagueDatesList;


    public LeagueSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
        I = new Helpers(base);
    }

    @And("^I create a new league$")
    public void iCreateANewLeague() {
        try {
            leagueListPage = new LeagueListPage(base.driver);
            I.Click(leagueListPage.getBtnNewLeague());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @When("^I enter the following data in Description tab$")
    public void iEnterTheFollowingDataInDescriptionTab(DataTable table) {
        try {
            Log.info("[League][New League] - Filling out Description fields");
            leageTemplateSelectionPage = new LeagueTemplateSelectionPage(base.driver);
            I.waitUntilElementIsVisible(leageTemplateSelectionPage.getTblLeagueTemplaes());
            leagueDescriptionList = table.asList(LeagueComponents.LeagueDescription.class);

            List<WebElement> tblResults = leageTemplateSelectionPage.getTblResults(1);
            int i = I.searchForElementInTheLeagueTemplateList(leagueDescriptionList.get(0), tblResults);

            /*Note: As there are two tables with same elements,
                button column is fixed and overlaps the button I actually need to click on,
                so the temporary solution is to get first table elements,
                otherwise click on button using javascript would do the trick
             */
            I.ClickOnRowButtonAtPosition(leageTemplateSelectionPage.getTblResults(0).get(i), 0);
            //I.ClickOnRowButtonAtPosition(tblResults.get(i),0);

            leagueNewEditPage = new LeagueNewEditPage(base.driver);
            I.waitUntilElementIsClickable(leagueNewEditPage.getDdlYear());
            for (LeagueComponents.LeagueDescription ld : leagueDescriptionList) {
                I.SelectValue(leagueNewEditPage.getDdlYear(), ld.getYear());
                I.SelectValue(leagueNewEditPage.getDdlSeason(), ld.getSeason());
                I.SelectValue(leagueNewEditPage.getDdlPlayDay(), ld.getPlayday());
                I.SelectValue(leagueNewEditPage.getDdlMinAgeType(), ld.getAgeType());
                I.Write(leagueNewEditPage.getTxtMinAge(), ld.getMinAge());
                I.Write(leagueNewEditPage.getTxtMaxAge(), ld.getMaxAge());
                I.SelectValue(leagueNewEditPage.getDdlScoreCard(), ld.getScoreCardType());

                I.Click(leagueNewEditPage.getBtnVPSelect());
                iSearchForTheFollowingAltaNumberInVPLeague(ld.getVPName());

                I.Click(leagueNewEditPage.getBtnRosterInstructions());
                iSearchForTheFollowingDocumentID(ld.getRosterDocId());

                I.Click(leagueNewEditPage.getBtnPacketDocument());
                iSearchForTheFollowingDocumentID(ld.getPacketDocId());
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I enter the following data in Details tab$")
    public void iEnterTheFollowingDataInDetailsTab(DataTable table) throws Throwable {
        throw new PendingException();
    }

    @And("^I enter the following data in Dates tab$")
    public void iEnterTheFollowingDataInDatesTab(DataTable table) {
        try {
            Log.info("[League][New League] - Filling out Dates fields");
            I.Click(leagueNewEditPage.getTabDates());
            I.waitUntilElementIsVisible(leagueNewEditPage.getTxtRegEndDate());
            leagueDatesList = table.asList(LeagueComponents.LeagueDates.class);

            for (LeagueComponents.LeagueDates dates : leagueDatesList) {
                I.Write(leagueNewEditPage.getTxtRegEndDate(), dates.getEndDate());
                I.Write(leagueNewEditPage.getTxtCaptMeetingDate(), dates.getCaptMeeting());
                I.Write(leagueNewEditPage.getTxtFirstWeekPlay(), dates.getPlayWeek());
            }
            I.Click(leagueNewEditPage.getBtnCalculateDates());
            I.waitUntilElementIsVisible(leagueNewEditPage.getPostSeasonDeletionLocator());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I save my new league without errors$")
    public void iSaveMyNewLeagueWithoutErrors() {
        try {
            I.Click(leagueNewEditPage.getBtnSave());
            Log.info("[League][New League] - Saving new League");
            I.waitUntilElementIsVisible(leagueNewEditPage.getTabErrors());
            I.Click(leagueNewEditPage.getBtnSave());
            assertFalse(checkForErrors());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    public void iSearchForTheFollowingAltaNumberInVPLeague(String altaNumber) {
        try {
            Log.info("Searching for: " + altaNumber + " alta number");
            leagueVPSelectionPage = new LeagueVP_SelectionPage(base.driver);
            I.waitUntilElementIsClickable(leagueVPSelectionPage.getTxtALTANumber());
            I.Write(leagueVPSelectionPage.getTxtALTANumber(), altaNumber);
            I.Click(leagueVPSelectionPage.getBtnSearch());

            List<WebElement> tblResults = leagueVPSelectionPage.getTblResults(1);
            int i = I.searchForElementInTheListAtPos(altaNumber, tblResults, 1);

            I.ClickOnRowButtonAtPosition(leagueVPSelectionPage.getTblResults(0).get(i), 0);

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    public void iSearchForTheFollowingDocumentID(String documentId) {
        try {
            Log.info("Searching for: " + documentId + " alta number");
            rosterInstructionsPage = new RosterInstructions_SelectionPage(base.driver);
            List<WebElement> tblResults = rosterInstructionsPage.getTblResults(1);
            int i = I.searchForElementInTheListAtPos(documentId, tblResults, 1);
            I.ClickOnRowButtonAtPosition(rosterInstructionsPage.getTblResults(0).get(i), 0);

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    public boolean checkForErrors(){
        WebElement errors = leagueNewEditPage.getTblErrors();
        Log.info("Checking for errors");
        try {
            String lblErrors = errors.findElement(By.className("errorandwarningscontainer")).getText();
            return !lblErrors.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}