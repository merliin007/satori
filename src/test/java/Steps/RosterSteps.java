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
import org.openqa.selenium.interactions.Actions;
import pages.newPages.myRosters.MyRostersPage;
import pages.newPages.myRosters.NewRosterPage;
import pages.newPages.myRosters.NewRosterWizardPage;
import utility.Helpers;
import utility.Log;
import utility.newRoster.Facility;
import utility.newRoster.PlayerRoster;

import javax.xml.crypto.Data;
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

    @And("^I create a new roster selecting \"([^\"]*)\" league$")
    public void iCreateANewRosterSelectingLeague(String league) {
        try {
            Log.info("Entering to New Roster Page");
            myRostersPage = new MyRostersPage(base.driver);
            I.Click(myRostersPage.getBtnCreateRoster());
            Log.info("Creating New Roster Wizard");
            newRosterPage = new NewRosterPage(base.driver);
            I.SelectValue(newRosterPage.getDdlSelectLeague(), league);
            I.Click(newRosterPage.getBtnAcknowledgeGetStarted());
            Log.info(league + " selected");
            newRosterWizardPage = new NewRosterWizardPage(base.driver);
            I.waitUntilElementIsVisible(newRosterWizardPage.getBtnNext());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }

    }

    @When("^I enter the following players on Players tab$")
    public void iEnterTheFollowingPlayersOnPlayersTab(DataTable table) {
        try {
            //Thread.sleep(2000L);
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.Click(newRosterWizardPage.getBtnNext());
            I.waitUntilElementIsVisible(newRosterWizardPage.getBtnAddPlayer());

            I.CheckCheckBox(newRosterWizardPage.getICertifyGrantedPermission());
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
            I.waitUntilElementIsVisible(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
            iSelectTheFollowingCaptains(playerRosterList);
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I select the following captains$")
    public void iSelectTheFollowingCaptains(List<PlayerRoster> players) {
        try {
            Log.info("Selecting captains");
            List<String> captains = I.getCaptains.findCaptains(players);
            I.SelectValue(newRosterWizardPage.getDdlCaptain(), captains.get(0));
            I.SelectValue(newRosterWizardPage.getDdlCoCaptain(), captains.get(1));

            I.waitUntilElementIsClickable(newRosterWizardPage.getBtnNext());
            I.Click(newRosterWizardPage.getBtnNext());
            iSelectTheFollowingDesignee(null);
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I select the following designee")
    public void iSelectTheFollowingDesignee(List<PlayerRoster> players) {
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


    @When("^I select the following facility$")
    public void iSelectTheFollowingFacility(DataTable table) {
        try {
            Log.info("Selecting facility");
            I.waitUntilInvisibilityOf(newRosterWizardPage.getWizardObscurer());
            I.waitUntilElementIsVisible(newRosterWizardPage.getICertifyGrantedPermission());
            I.CheckCheckBox(newRosterWizardPage.getICertifyGrantedPermission());
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


    @Then("^I save my new roster without errors$")
    public void iSaveMyNewRosterWithoutErrors() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
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

}

