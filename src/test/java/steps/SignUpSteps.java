/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 12:06 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.memberPortal.myAccount.MyMembershipPage;
import pages.singup.NewMemberSignUpPage;
import pages.singup.RegisterPage;
import utility.Helpers;
import utility.Log;
import utility.members.Member;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class SignUpSteps {

    private BaseUtil base;
    private Helpers I;
    private RegisterPage registerPage;
    private Member member;
    private MyMembershipPage myMembershipPage;

    public SignUpSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @Then("^User enters the following information for signing up$")
    public void userEntersTheFollowingInformationForSigningUp(DataTable table) {
        try {
            Log.info("New Account: Filling out main form");
            List<List<String>> tbl = table.raw();
            member = new Member(tbl.get(0));
            NewMemberSignUpPage signUpPage = new NewMemberSignUpPage(base.driver);

            I.Write(signUpPage.getTxtFirstName(), member.getFirst());
            I.Write(signUpPage.getTxtLastName(), member.getLast());
            I.Click(signUpPage.getBtnNext());

            registerPage = new RegisterPage(base.driver);
            I.waitUntilElementIsVisible(registerPage.getTxtFirstName());

            I.SelectValue(registerPage.getDropGender(), member.getGender());
            I.Write(registerPage.getTxtBirthDate(), member.getDob());
            I.CheckCheckBoxIf(registerPage.getChkWheelChair(), member.getWheelChair());
            I.Write(registerPage.getTxtHomePhone(), member.getHomePhone());
            I.Write(registerPage.getTxtMobilePhone(), member.getMobilePhone());
            I.Write(registerPage.getTxtWorkPhone(), member.getWorkPhone());
            I.Write(registerPage.getTxtEmail(), member.getEmail());
            I.Write(registerPage.getTxtStreet(), member.getStreet());
            I.Write(registerPage.getTxtApt(), member.getApt());
            I.Write(registerPage.getTxtCity(), member.getCity());
            I.SelectValue(registerPage.getDropState(), member.getState());
            I.Write(registerPage.getTxtZip(), member.getZip());
            I.SelectValue(registerPage.getDropCounty(), member.getCounty());
            I.Click(registerPage.getBtnNext());

            Log.info("New Account: Selecting Username");
            I.waitUntilElementIsVisible(registerPage.getTxtUserName());
            I.Write(registerPage.getTxtUserName(), member.getUserName());
            I.Write(registerPage.getTxtPassword(), member.getPassword());
            I.Write(registerPage.getTxtPasswordConfirmation(), member.getPassword());
            I.Click(registerPage.getBtnNext());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @And("^User selects the following rankings ([^\"]*)$")
    public void userSelectsTheFollowingRankings(String rankings) {
        if (member == null) fail();
        try {
            Log.info("New Account: Selecting Rankings");
            I.ClickIfButtonIsNotDisabled(registerPage.getBtnNext());
            String[] tbl = rankings.split(",");
            for (int i = 0; i < tbl.length; i++) {
                I.CheckCheckBox(registerPage.getRankingCheckbox(tbl[i]));
            }

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^After saving new account is created$")
    public void afterSavingNewAccountIsCreated() {
        if (member == null) fail();
        try {
            Log.info("New Account: Finishing registration");
            I.Click(registerPage.getBtnSave());
            I.AcceptAlert();

            myMembershipPage = new MyMembershipPage(base.driver);
            I.waitUntilElementIsVisible(myMembershipPage.getLblUserName());
            Log.info(myMembershipPage.getLblUserName().getText());
            String label = member.getFirst().toUpperCase() + " " + member.getLast().toUpperCase();
            assertTrue(myMembershipPage.getLblUserName().getText().contains(label));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
