/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 10:15 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.java.en.And;
import pages.newPages.memberPortal.myAccount.ProfileDetailPage;
import utility.Helpers;
import utility.Log;

import static org.testng.Assert.fail;

public class MemberProfileSteps {
    private BaseUtil _base;
    private Helpers I;
    private ProfileDetailPage profileDetailPage;

    public MemberProfileSteps(BaseUtil base) {
        _base = base;
        I = new Helpers(base.driver);
    }

    @And("^I change his address with random information$")
    public void iChangeHisAddressWithRandomInformation() {
        try {
            profileDetailPage = new ProfileDetailPage(_base.driver);
            I.Click(profileDetailPage.getTab("Address"));
            I.Write(profileDetailPage.getTxtAddress(), String.valueOf(System.currentTimeMillis()));
            I.Click(profileDetailPage.getBtnSave());
            I.WaitUntilPresenceOfElement(profileDetailPage.getToastrBy());
            Log.info("Address successfully changed!");
        } catch (Exception e) {
            Log.error(e.getMessage());
            _base.GrabScreenShot();
            fail();
        }
    }

}
