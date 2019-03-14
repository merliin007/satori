/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 3:03 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
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
        if (membersAdvancedSearchPage == null)
            membersAdvancedSearchPage = new MembersAdvancedSearchPage(base.driver);
        try {
            Log.info("Searching for: " + altaNumber);

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

    @And("^I Search and Select the following member$")
    public void iSearchAndSelectTheFollowingMember(DataTable table) {
        if (membersAdvancedSearchPage == null)
            membersAdvancedSearchPage = new MembersAdvancedSearchPage(base.driver);
        try {
            List<List<String>> rows = table.raw();
            Log.info("Searching for: " + rows.get(0).get(0) + " " + rows.get(0).get(1));

            for (List<String> row : rows) {
                member = new Member(row.get(0), row.get(1));
            }
            I.Write(membersAdvancedSearchPage.getTxtFirstName(), member.getFirst());
            I.Write(membersAdvancedSearchPage.getTxtLastName(), member.getLast());
            I.Click(membersAdvancedSearchPage.getBtnSearch());

            List<WebElement> tblResults = membersAdvancedSearchPage.getTblResults(1);
            int index = I.searchForMemberByNameInMembersList(member, tblResults);
            I.selectOptionFromCell(index, "Select", membersAdvancedSearchPage);

            memberProfile = new MemberProfile(base.driver);
            I.Click(memberProfile.RibbonOption("Memberships & Payments"));

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @And("^I Search and Impersonate the following member ([^\"]*)$")
    public void iSearchAndImpersonateTheFollowingMember(String altaNumber) {
        if (membersAdvancedSearchPage == null)
            membersAdvancedSearchPage = new MembersAdvancedSearchPage(base.driver);
        try {
            Log.info("Searching for: " + altaNumber);

            I.Write(membersAdvancedSearchPage.getTxtAltaNumber(), altaNumber);
            I.Click(membersAdvancedSearchPage.getBtnSearch());
            I.selectOptionFromCell(1, "Impersonate", membersAdvancedSearchPage);
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

}
