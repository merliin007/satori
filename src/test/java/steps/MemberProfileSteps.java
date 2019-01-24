/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 10:15 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.memberPortal.myAccount.MembershipsPaymentsPage;
import pages.memberPortal.myAccount.ProfileDetailPage;
import utility.Helpers;
import utility.Log;

import java.util.List;

import static org.testng.Assert.fail;

public class MemberProfileSteps {
    private BaseUtil base;
    private Helpers I;
    private ProfileDetailPage profileDetailPage;

    public MemberProfileSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        profileDetailPage = new ProfileDetailPage(base.driver);
    }

    @And("^I change his address with random information$")
    public void iChangeHisAddressWithRandomInformation() {
        try {
            I.Click(profileDetailPage.getTab("Address"));
            I.Write(profileDetailPage.getTxtAddress(), String.valueOf(System.currentTimeMillis()));
            I.Click(profileDetailPage.getBtnSave());
            I.WaitUntilPresenceOfElement(profileDetailPage.getToastrBy());
            Log.info("Address successfully changed!");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }


}
