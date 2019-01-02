/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 4:07 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;
import pages.newPages.memberPortal.myActiveTeams.LeaderPreferencesPage;
import utility.Helpers;
import utility.Log;

import static org.testng.Assert.fail;

public class LeaderPreferencesSteps {

    private BaseUtil base;
    private LeaderPreferencesPage leaderPreferencesPage;
    private Helpers I;
    public LeaderPreferencesSteps(BaseUtil base) {
        this.base = base;
        leaderPreferencesPage = new LeaderPreferencesPage(base.driver);
        I = new Helpers(base.driver);
    }

    @And("^I enter random information as food assignment$")
    public void iEnterRandomInformationAsFoodAssignment(){
        try{
         //   I.Click(leaderPreferencesPage.getRdioLine());
            for(WebElement element: leaderPreferencesPage.getLineOptions()){
                I.Write(element, String.valueOf(System.nanoTime()));
            }
            I.Click(leaderPreferencesPage.getBtnSave());
            I.WaitUntilPresenceOfElement(leaderPreferencesPage.getToastrBy());
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
