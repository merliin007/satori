package steps;

import base.BaseUtil;
import common.CommonActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.TimeoutException;
import pages.newPages.ManageAlta;
import pages.newPages.nLoginPage;
import pages.newPages.nMainPage;
import pages.LandingPage;
import utility.Log;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginSteps/* extends BaseUtil*/ {
    private BaseUtil base;
    private CommonActions commonActions;
    private nLoginPage loginPage;
    private ManageAlta manageAlta;
    private LandingPage landingPage;
    private nMainPage nMainPage;

    public LoginSteps(BaseUtil base) {
        this.base = base;
    }

    @Given("^User navigates to ALTA website$")
    public void userNavigatesToALTAWebsite() {
        try {
            commonActions = new CommonActions(base.driver);
            landingPage = new LandingPage(base.driver);
            landingPage.getBtnSignIn().click();
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
            commonActions.waitUntilElementIsClickable(loginPage.getBtnSignIn());
            Log.info("Login attempt with: " + username);
            loginPage.getTxtUsername().sendKeys(username);
            loginPage.getTxtPassword().sendKeys(password);
            loginPage.getBtnSignIn().click();
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
                commonActions.waitUntilElementIsVisible(loginPage.getLblError());
                try {
                    commonActions.waitUntilExistenceOfElement(landingPage.getLblWelcomeBy());
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
}
