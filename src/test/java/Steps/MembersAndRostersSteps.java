/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 3:03 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.memberPortal.myAccount.MemberProfile;
import pages.members_and_rosters.MembersAdvancedSearchPage;
import utility.Helpers;
import utility.Log;
import utility.members.Member;

import java.util.List;

import static org.testng.Assert.fail;

public class MembersAndRostersSteps {
    private BaseUtil base;
    private Helpers I;
    private MembersAdvancedSearchPage membersAdvancedSearchPage;
    private MemberProfile memberProfile;
    private Member member;

    public MembersAndRostersSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @Then("^I Search and Select the following ALTA number \"([^\"]*)\"$")
    public void iSearchAndSelectTheFollowingALTANumber(String altaNumber) {
        try {
            Log.info("Searching for: " + altaNumber);
            membersAdvancedSearchPage = new MembersAdvancedSearchPage(base.driver);
            member = new Member(altaNumber);
            I.Write(membersAdvancedSearchPage.getTxtAltaNumber(), member.getAltaNum());
            I.Click(membersAdvancedSearchPage.getBtnSearch());

            List<WebElement> tblResults = membersAdvancedSearchPage.getTblResults(1);
            int index = I.searchForMemberInMembersList(member, tblResults);
            I.selectOptionFromCell(index, "Select", membersAdvancedSearchPage);

            memberProfile = new MemberProfile(base.driver);
            I.Click(memberProfile.RibbonOption("Profile"));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
