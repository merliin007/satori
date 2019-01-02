/*
 * Created by Miguel Angel Aguilar Cuevas
 * 02/01/2019 at 10:19 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.newPages.leagues.LeagueTemplateDetailPage;
import pages.newPages.leagues.LeagueTemplatesPage;
import utility.Helpers;
import utility.Log;
import utility.league.LeagueComponents;

import java.util.List;

import static org.testng.Assert.fail;


public class LeagueTemplateSteps {
    private BaseUtil base;
    private Helpers I;
    private LeagueTemplatesPage leagueTemplatesPage;
    private LeagueTemplateDetailPage leagueTemplateDetailPage;
    private List<LeagueComponents.LeagueDescription> leagueDescriptionList;
    private List<LeagueComponents.LeagueDetails> leagueDetailsList;
    private List<LeagueComponents.LeagueSeasons> leagueSeasonsList;
    private List<LeagueComponents.LeagueExclusions> leagueExclusionsList;


    public LeagueTemplateSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        leagueTemplatesPage = new LeagueTemplatesPage(base.driver);
    }

    @And("^I click on New Template button$")
    public void IClickOnNewTemplateButton() {
        try {
            I.waitUntilElementIsClickable(leagueTemplatesPage.getBtnNew());
            I.Click(leagueTemplatesPage.getBtnNew());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I enter the following data as New Template description$")
    public void iEnterTheFollowingDataAsNewTemplateDescription(DataTable data) {
        leagueTemplateDetailPage = new LeagueTemplateDetailPage(base.driver);
        try {
            leagueDescriptionList = data.asList(LeagueComponents.LeagueDescription.class);
            for (LeagueComponents.LeagueDescription ld : leagueDescriptionList) {
                I.Write(leagueTemplateDetailPage.getTxtLeagueName(), ld.getLeagueName() + "-" + System.currentTimeMillis());
                I.SelectValue(leagueTemplateDetailPage.getDropGender(), ld.getGender());
                I.SelectValue(leagueTemplateDetailPage.getDropMinAgeType(), ld.getMinAgeType());
                I.Write(leagueTemplateDetailPage.getTxtMinAge(), ld.getMinAge());
                I.Write(leagueTemplateDetailPage.getTxtMaxAge(), ld.getMaxAge());
                I.SelectValue(leagueTemplateDetailPage.getDropScoreCardType(), ld.getScoreCardType());
                I.Write(leagueTemplateDetailPage.getTxtNumberOfSeasons(), ld.getNumberOfSeasonsPerYear());
                I.CheckCheckBoxIf(leagueTemplateDetailPage.getChkIsActive(), ld.isActive());
            }
            Log.info("Entered League Templates Description data successfully");
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I enter the following data as New Template details$")
    public void iEnterTheFollowingDataAsNewTemplateDetails(DataTable table) {
        try {
            I.Click(leagueTemplateDetailPage.getTabDetails());
            leagueDetailsList = table.asList(LeagueComponents.LeagueDetails.class);
            for (LeagueComponents.LeagueDetails ld : leagueDetailsList) {
                I.Write(leagueTemplateDetailPage.getTxtMinTeamMembers(), ld.getMinTeamMembers());
                I.Write(leagueTemplateDetailPage.getTxtMinRetTeamMembers(), ld.getMinRetTeamMembers());
                I.Write(leagueTemplateDetailPage.getTxtMinMatchPlayers(), ld.getMinMatchPlayers());
                I.Write(leagueTemplateDetailPage.getTxtMaxAddons(), ld.getMaxAddons());
                I.Write(leagueTemplateDetailPage.getTxtMinFemales(), ld.getMinFemales());
                I.Write(leagueTemplateDetailPage.getTxtMinRetFemales(), ld.getMinRetFemales());
                I.Write(leagueTemplateDetailPage.getTxtMinMales(), ld.getMinMales());
                I.Write(leagueTemplateDetailPage.getTxtMinRetMales(), ld.getMinRetMales());
                I.Write(leagueTemplateDetailPage.getTxtMinTeamMembersNeeded(), ld.getMinTeamMembersNeeded());
                I.Write(leagueTemplateDetailPage.getTxtMinFemalesNeeded(), ld.getMinFemalesNeeded());
                I.Write(leagueTemplateDetailPage.getTxtMinMalesNeeded(), ld.getMinMalesNeeded());
            }
            Log.info("Entered League Templates Detail data successfully");
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I choose the following options on New Template seasons$")
    public void iChooseTheFollowingOptionsOnNewTemplateSeasons(DataTable table) {
        leagueTemplateDetailPage = new LeagueTemplateDetailPage(base.driver);
        try {
            I.Click(leagueTemplateDetailPage.getTabSeasons());
            leagueSeasonsList = table.asList(LeagueComponents.LeagueSeasons.class);
            for (LeagueComponents.LeagueSeasons ls : leagueSeasonsList) {
                I.JSClick(I.GetCheckBoxFromList(leagueTemplateDetailPage.getSeasonChecks(), ls.getSeason().toLowerCase()));
            }
            Log.info("Entered League Templates Season data successfully");
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @And("^I choose the following options on new Template Exclusions$")
    public void iChooseTheFollowingOptionsOnNewTemplateExclusions(DataTable table) {
        try {
            I.Click(leagueTemplateDetailPage.getTabExclusions());
            leagueExclusionsList = table.asList(LeagueComponents.LeagueExclusions.class);
            for (LeagueComponents.LeagueExclusions le : leagueExclusionsList) {
                I.JSClick(I.GetCheckBoxFromList(leagueTemplateDetailPage.getExclusionChecks(), le.getExcludedLeague().toLowerCase()));
            }
            Log.info("Entered League Templates Exclusions data successfully");

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I save my new league templates without errors$")
    public void iSaveMyNewLeagueTemplatesWithoutErrors() {
        try {
            I.Click(leagueTemplateDetailPage.getBtnSave());
            I.WaitUntilPresenceOfElement(leagueTemplatesPage.getToastrBy());
            Log.info("Saved a new League Template successfully");
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }


}
