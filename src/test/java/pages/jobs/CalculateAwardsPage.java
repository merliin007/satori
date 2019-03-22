/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:14 AM
 */
package pages.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.Log;
import utility.job.Job;

public class CalculateAwardsPage extends JobPage {
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_cmdQueueJob")
    private WebElement btnQueueJob;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlYear")
    private WebElement ddlYear;

    @Override
    public WebElement QueueJobButton() {
        Log.info("Queuing " + this.getClass().getSimpleName() + " job");
        new Select(ddlYear).selectByVisibleText(job.getYear());
        return btnQueueJob;
    }


    public CalculateAwardsPage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
    }
}
