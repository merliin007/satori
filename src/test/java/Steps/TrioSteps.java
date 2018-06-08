package steps;

import base.BaseUtil;
import common.CommonActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.newPages.TrioLoginPage;
import pages.newPages.TrioMainPage;
import utility.Log;

public class TrioSteps/* extends BaseUtil */{
    private BaseUtil base;
    private TrioLoginPage trioLoginPage;
    private TrioMainPage trioMainPage;
    private CommonActions commonActions;


    public TrioSteps(BaseUtil base) {
        this.base = base;
        commonActions = new CommonActions(base.driver);
    }

    @Given("^User navigates to Trio website$")
    public void userNamivatesToTrioWebsite() {
        try{
            trioLoginPage = new TrioLoginPage(base.driver);
            trioLoginPage.LoginWith("admin@triorewards.com", "tr10r3w4rd5p55");
            commonActions.waitUntilInvisibilityOf(trioLoginPage.getLoader());
        }catch (Exception e){
            Log.error(e.getMessage());
        }
    }

    @And("^I click on any element after spinner goes off$")
    public void iClickOnAnyElementAfterSpinnerGoesOff(){
        try{
            trioMainPage = new TrioMainPage(base.driver);
            commonActions.waitUntilElementIsClickable(trioMainPage.MenuOption());
            trioMainPage.MenuOption().click();
        }catch (Exception e){
            Log.error(e.getMessage());
        }
    }


}
