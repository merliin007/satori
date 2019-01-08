/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 11:09 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import pages.newPages.memberPortal.myAccount.MemberPreferencesPage;
import utility.Helpers;
import utility.Log;

import static org.testng.Assert.fail;

public class MemberPreferencesSteps {
    private BaseUtil base;
    private Helpers I;
    private MemberPreferencesPage preferencesPage;

    public MemberPreferencesSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        preferencesPage = new MemberPreferencesPage(base.driver);
    }

    @And("^I change his preferences toggling Sharing Contact Information$")
    public void iChangeHisPreferencesTogglingSharingContactInformation() {
        try {
            Log.info("Toggling share my email checkbox");
            I.Click(preferencesPage.RibbonOption("Preferences"));
            I.CheckCheckBox(preferencesPage.getChkShare());
            I.Click(preferencesPage.getBtnSave());
            I.WaitUntilPresenceOfElement(preferencesPage.getToastrBy());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
