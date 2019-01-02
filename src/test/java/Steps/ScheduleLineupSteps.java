/*
 * Created by Miguel Angel Aguilar Cuevas
 * 27/12/2018 at 4:00 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.newPages.memberPortal.myActiveTeams.LineupPage;
import pages.newPages.memberPortal.myActiveTeams.ScheduleLineupPage;
import utility.Helpers;
import utility.Log;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class ScheduleLineupSteps {

    private ScheduleLineupPage scheduleLineupPage;
    private LineupPage lineupPage;
    private BaseUtil base;
    private Helpers I;

    public ScheduleLineupSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }

    @Then("^I can create a lineup on week (\\d+)$")
    public void iCanCreateALineupOnWeek(int wk) {
        try {
            scheduleLineupPage = new ScheduleLineupPage(base.driver);
            List<WebElement> weekLnks = scheduleLineupPage.weekLink(scheduleLineupPage.getSeasonList().get(wk - 1)).findElements(By.tagName("a"));
            assertEquals(weekLnks.get(0).getText().toLowerCase(), "create lineup");
            I.Click(weekLnks.get(0));

            lineupPage = new LineupPage(base.driver);
            I.waitUntilElementIsClickable(lineupPage.getBtnPublish());
            I.waitUntilElementIsClickable(lineupPage.getBtnSave());
            I.Click(lineupPage.getBtnCancel());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
