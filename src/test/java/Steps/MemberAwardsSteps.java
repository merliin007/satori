/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 11:49 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import pages.newPages.memberPortal.myAccount.MemberAwardsPage;
import pages.newPages.memberPortal.myAccount.PersonAwardDetailPage;
import utility.Helpers;
import utility.Log;

import org.openqa.selenium.NoSuchElementException;

import static org.testng.Assert.fail;

public class MemberAwardsSteps {

    private BaseUtil base;
    private Helpers I;
    private MemberAwardsPage awardsPage;

    public MemberAwardsSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        awardsPage = new MemberAwardsPage(base.driver);
    }

    @And("^I grant him a \"([^\"]*)\" award on \"([^\"]*)\"$")
    public void iGrantHimAAward(String award, String year) {
        try{
            Log.info("Accessing Awards page");
            I.Click(awardsPage.RibbonOption("Awards"));
            I.Click(awardsPage.getBtnNewAward());

            PersonAwardDetailPage awardDetailPage = new PersonAwardDetailPage(base.driver);
            I.SelectValue(awardDetailPage.getDdlAward(), award);
            I.Write(awardDetailPage.getTxtAwardYear(), year);
            I.Click(awardDetailPage.getBtnSave());
            try {
                if(awardDetailPage.getTab("errors").isDisplayed())
                    I.Click(awardDetailPage.getBtnSave());
            } catch (NoSuchElementException e) {
                Log.info("No Error tab is shown!");
            }
            Log.info("Award successfully granted");
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }

    }
}
