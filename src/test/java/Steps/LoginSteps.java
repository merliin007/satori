package steps;

import base.BaseUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.decision.DecisionPortal;
import org.openqa.selenium.TimeoutException;
import pages.home.LoginPage;
import pages.home.MainPage;
import pages.home.LandingPage;
import utility.Helpers;
import utility.Log;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginSteps {
    private BaseUtil base;
    private LoginPage loginPage;
    private LandingPage landingPage;
    private MainPage mainPage;
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
            loginPage = new LoginPage(base.driver);
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
                mainPage = new MainPage(base.driver);
                Log.info("Reviewing login succeed ");
                assertTrue(mainPage.getLblWelcome().isDisplayed());
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
            case "member": return 0;
            case "manage": return 2;
            default: return -1;
        }
    }

    @When("^User clicks on Join Now link$")
    public void userClicksOnJoinNowLink() {
        try{
            if(loginPage == null)
                loginPage = new LoginPage(base.driver);
            I.Click(loginPage.getLinkSingup());
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Given("^User navigates to ALTA website without signing in$")
    public void userNavigatesToALTAWebsiteWithoutSigningIn(){
        try {
            landingPage = new LandingPage(base.driver);
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
