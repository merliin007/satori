package steps;

import base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.decision.DecisionPortal;
import org.openqa.selenium.TimeoutException;
import pages.newPages.ManageAlta;
import pages.newPages.nLoginPage;
import pages.newPages.nMainPage;
import pages.LandingPage;
import utility.Helpers;
import utility.Log;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginSteps/* extends BaseUtil*/ {
    private BaseUtil base;
    private nLoginPage loginPage;
    private ManageAlta manageAlta;
    private LandingPage landingPage;
    private nMainPage nMainPage;
    private DecisionPortal decisionPortal;
    private Helpers I;

    public LoginSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
        I = new Helpers(baseUtil);
    }

    @Given("^User navigates to ALTA website$")
    public void userNavigatesToALTAWebsite() {
        try {
            landingPage = new LandingPage(base.driver);
            I.Click(landingPage.getBtnSignIn());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @When("^I enter username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iEnterUsernameAsUsernameAndPasswordAsPassword(String username, String password) {
        try {
            loginPage = new nLoginPage(base.driver);
            I.waitUntilElementIsClickable(loginPage.getBtnSignIn());
            Log.info("Login attempt with: " + username);

            I.Write(loginPage.getTxtUsername(), username);
            I.Write(loginPage.getTxtPassword(),password);
            I.Click(loginPage.getBtnSignIn());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^The login should be \"([^\"]*)\"$")
    public void theLoginShouldBeValid(boolean valid) {
        try {
            if (valid) {
                nMainPage = new nMainPage(base.driver);
                Log.info("Reviewing login succeed ");
                assertTrue(nMainPage.getLblWelcome().isDisplayed());
            } else {
                Log.info("Reviewing login didn't succeed ");
                I.waitUntilElementIsVisible(loginPage.getLblError());
                try {
                    I.waitUntilExistenceOfElement(landingPage.getLblWelcomeBy());
                } catch (TimeoutException e) {
                    Log.info("Expected Login failed ");
                }
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^I select \"([^\"]*)\" portal$")
    public void iSelectPortal(String opt){
        try{
            decisionPortal = new DecisionPortal(base.driver);
            I.waitUntilElementIsClickable(decisionPortal.getOptions().get(1));
            I.Click(decisionPortal.getOptions().get(getOptIndex(opt)));
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    private int getOptIndex(String opt){
        switch (opt.toLowerCase()){
            case "player": return 0;
            case "manage": return 1;
            default: return -1;
        }
    }
}
