/*
 * Created by Miguel Angel Aguilar Cuevas
 * 27/12/2018 at 4:56 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.kohsuke.rngom.parse.host.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.newPages.memberPortal.myActiveTeams.AvailabilityPage;
import utility.Helpers;
import utility.Log;

import java.util.List;

import static org.testng.Assert.fail;

public class AvailabilitySteps {
    private BaseUtil base;
    private Helpers I;
    private AvailabilityPage availabilityPage;

    public AvailabilitySteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @And("^I can change availability to \"([^\"]*)\" for some members$")
    public void iCanSetAvailabilityToForSomeMembers(String availabilityOpt){
        availabilityPage = new AvailabilityPage(base.driver);
        try{

            List<WebElement> columnDrops = availabilityPage.getColumnElementsOnWeek(1);
            for(int i = 0; i < 6; i+=2){
                I.JSScrollToView(columnDrops.get(i));
                I.Click(columnDrops.get(i));
                I.Click(getOptionsFromDropDown(columnDrops.get(i), availabilityOpt));

                I.WaitUntilPresenceOfElement(availabilityPage.getToastrBy());
                I.Click(availabilityPage.getToastr());
            }

        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }


    private WebElement getOptionsFromDropDown(WebElement drop, String opt){
        List<WebElement> opts =  drop.findElements(By.tagName("a"));
        for(int i = 1; i < opts.size(); i++){
            if(opts.get(i).getText().toLowerCase().equals(opt.toLowerCase()))
                return opts.get(i);
        }
        return null;
    }
}
