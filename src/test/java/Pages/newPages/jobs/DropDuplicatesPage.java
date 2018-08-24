/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:14 AM
 */
package pages.newPages.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.Helpers;
import utility.Log;
import utility.job.Job;

public class DropDuplicatesPage extends JobPage {

    @Override
    public WebElement QueueJobButton() {
        Log.info("Queuing " + this.getClass().getSimpleName() + " job");
        int i = new Helpers(super._driver).searchForElementInTheJobList(job, getTblResults(1));
        return (i > 0) ? getTblResults(0).get(i) : null;
    }

    public DropDuplicatesPage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
    }


}
