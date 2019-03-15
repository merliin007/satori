/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package steps;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import utility.Helpers;
import utility.Log;

import static org.testng.Assert.fail;


public class RolesSteps {
    private BaseUtil base;
    private Helpers I;

    public RolesSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @Then("^I visit each of the reports granted for <Role>$")
    public void iVisitEachOfTheReportsGrantedForRole() {
        try{

        }catch (Exception e){
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }
}
