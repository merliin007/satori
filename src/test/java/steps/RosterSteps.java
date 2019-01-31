/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/08/2018 at 11:48 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.memberPortal.payments.PendingPaymentsPage;
import pages.myRosters.MyRostersPage;
import pages.myRosters.NewRosterPage;
import pages.myRosters.NewRosterWizardPage;
import utility.Helpers;
import utility.Log;
import utility.league.LeagueComponents;
import utility.newRoster.Facility;
import utility.newRoster.PlayerRoster;
import utility.payment.CreditCardInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.fail;

public class RosterSteps {

    private MyRostersPage myRostersPage;
    private NewRosterPage newRosterPage;
    private NewRosterWizardPage newRosterWizardPage;
    private Helpers I;
    private BaseUtil base;

    private List<PlayerRoster> playerRosterList;

    public RosterSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @And("^I create a new roster selecting a league matching$")
    public void iCreateANewRosterSelectingLeague(DataTable table) {
        try {
            Log.info("Entering to New Roster Page");
            myRostersPage = new MyRostersPage(base.driver);
            I.Click(myRostersPage.getBtnCreateRoster());
            Log.info("Creating New Roster Wizard");
            newRosterPage = new NewRosterPage(base.driver);
            List<WebElement> tblResults = newRosterPage.getTblResults(1);
            List<LeagueComponents.LeagueDescription> leagueTemplate = table.asList(LeagueComponents.LeagueDescription.class);
            int index = I.searchForSuitableLeagueTemplate(leagueTemplate.get(0), tblResults);
            //WebElement row = newRosterPage.getTblResults(0).get(idx);
            I.selectOptionFromCell(index, "select", newRosterPage);
            I.waitUntilElementIsVisible(newRosterPage.getNewRosterModal());
            I.Click(newRosterPage.modalCommands("New"));

            newRosterWizardPage = new NewRosterWizardPage(base.driver);
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsClickable(newRosterWizardPage.getChkAcknowledgment());

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }

    }

    @When("^I enter the following players on Players tab$")
    public void iEnterTheFollowingPlayersOnPlayersTab(DataTable table) throws Exception {
//        Thread.sleep(2000L);
        try {
            if (newRosterWizardPage == null)
                newRosterWizardPage = new NewRosterWizardPage(base.driver);

            I.CheckCheckBox(newRosterWizardPage.getChkAcknowledgment());
            I.Click(newRosterWizardPage.getBtnNext());

            Thread.sleep(2000L);
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnAddPlayer());
            I.Click(newRosterWizardPage.getBtnAddPlayer());
            I.waitUntilElementIsVisible(newRosterWizardPage.getAddPlayerModal());
            I.waitUntilElementIsClickable(newRosterWizardPage.getAddPlayerModalElement("txtalta"));

            playerRosterList = table.asList(PlayerRoster.class);
            for (PlayerRoster player : playerRosterList) {

                I.JSWrite(newRosterWizardPage.getAddPlayerModalElement("txtalta"), player.getaLTA_Number());
                I.Write(newRosterWizardPage.getAddPlayerModalElement("txtfirst"), player.getFirst());
                I.Write(newRosterWizardPage.getAddPlayerModalElement("txtlast"), player.getLast());
                I.Click(newRosterWizardPage.getAddPlayerModalElement("btnSearch"));
                I.waitUntilInvisibilityOf(newRosterWizardPage.getAddPlayerModalElement("spinner"));
                List<WebElement> tblResults = newRosterWizardPage.getAddPlayerModalTblResults();
                int i = I.searchForPlayerInTheModal(player, tblResults);
                I.Click(tblResults.get(i).findElements(By.tagName("td")).get(0));
                I.waitUntilConfirmationLabel();
            }
            I.Click(newRosterWizardPage.getAddPlayerModalElement("bntclose"));
            I.fluentWaitUntilElementDisappears(newRosterWizardPage.getPlayerModalLocator());
            I.CheckCheckBox(newRosterWizardPage.getChkAcknowledgment());
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        } finally {
            if (newRosterWizardPage.getWarningModal().isDisplayed())
                I.Click(newRosterWizardPage.warningModalContinue());
        }
    }

    @And("^I select the captains$")
    public void iSelectTheCaptains() {
        try {
            Log.info("Selecting captains");
            List<String> captains = I.getCaptains.findCaptains(playerRosterList);
            I.SelectValue(newRosterWizardPage.getDdlCaptain(), captains.get(0));
            I.SelectValue(newRosterWizardPage.getDdlCoCaptain(), captains.get(1));

            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }


    @Then("^I select the following facility$")
    public void iSelectTheFollowingFacility(DataTable table) {
        try {
            Log.info("Selecting facility");
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsVisible(newRosterWizardPage.getChkAcknowledgment());
            I.CheckCheckBox(newRosterWizardPage.getChkAcknowledgment());
            I.Click(newRosterWizardPage.getBtnFacility());
            I.waitUntilElementIsVisible(newRosterWizardPage.getAddFacilityModal());
            I.waitUntilElementIsClickable(newRosterWizardPage.getAddFacilityModalElement("txtName"));
            List<Facility> facilities = table.asList(Facility.class);
            for (Facility facility : facilities) {
                I.Write(newRosterWizardPage.getAddFacilityModalElement("txtName"), facility.getName());
                I.Write(newRosterWizardPage.getAddFacilityModalElement("txtCity"), facility.getCity());
                I.SelectValue(newRosterWizardPage.getAddFacilityModalElement("txtCounty"), facility.getCounty());
                I.Click(newRosterWizardPage.getAddFacilityModalElement("btnSearch"));
                List<WebElement> tblResults = newRosterWizardPage.getAddFacilityModalTblResults();
                int i = I.searchForFacilityInTheModal(facility, tblResults);
                I.Click(tblResults.get(i).findElements(By.tagName("td")).get(0));
            }
            I.waitUntilInvisibilityOf(newRosterWizardPage.getAddFacilityModal());
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I select designees")
    public void iSelectDesignees() {
        try {
            Log.info("Selecting designee");
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsVisible(newRosterWizardPage.getBtnDesignee());
            I.Click(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I select \"([^\"]*)\" Level flight$")
    public void iSelectLevelFlight(String lF) {
        try {
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            Thread.sleep(2000L);
            I.SelectValue(newRosterWizardPage.getDdlLevelFlight(), lF);
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
            iRequestForReview();
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I request for review")
    public void iRequestForReview() {
        try {
            Log.info("Requesting review");
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
            iSelectRankings();
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I select rankings")
    public void iSelectRankings() {
        try {
            Log.info("Selecting rankings");
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I save my new roster without errors$")
    public void iSaveMyNewRosterWithoutErrors() {
        try {
            I.Click(newRosterWizardPage.getBtnNext());
            //wait for payments page to appear
            PendingPaymentsPage paymentsPage = new PendingPaymentsPage(base.driver);
            paymentsPage.setCreditCardInformationAndSubmit(new CreditCardInfo());

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }

    }

    @Then("^I create a new roster with default values and recently created accounts$")
    public void iCreateANewRosterWithDefaultValuesAndRecentlyCreatedAccounts(DataTable table) {
        try {
            Log.info("just chilling");
            iEnterTheFollowingPlayersOnPlayersTab(DataTable.create(Helpers.getAltaNumbers()));
            iSelectTheCaptains();
            iSelectTheFollowingFacility(createFacilityTable());
            iSelectDesignees();
            iSelectLevelFlight("C-3");
            iSaveMyNewRosterWithoutErrors();
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    private DataTable createFacilityTable() {
        String[][] header = {{"Id", "Name", "City"}, {"14", "ATHLETIC CLUB NE", ""}};
        List<List<String>> facilityTable = new ArrayList<>();

        for (int i = 0; i < header.length; i++) {
            List<String> row = new ArrayList<>(Arrays.asList(header[i]));
            facilityTable.add(row);
        }
        return DataTable.create(facilityTable);
    }

}

