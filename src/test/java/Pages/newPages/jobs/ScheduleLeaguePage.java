/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:15 AM
 */
package pages.newPages.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.Helpers;
import utility.Log;
import utility.job.Job;

import java.util.List;

public class ScheduleLeaguePage extends JobPage {
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlIgnoreConstraintsYesNoList")
    private WebElement ddlIgnore;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;

    @Override
    public WebElement QueueJobButton() {
        Log.info("Queuing " + this.getClass().getSimpleName() + " job");

        new Select(ddlIgnore).selectByVisibleText("Yes");
        btnSearch.click();
        int attempts = 1;
        int i;

        do {
            List<WebElement> tblResult = getTblResults(1);
            i = new Helpers(_driver).searchForElementInTheJobList(job, tblResult);
            if (i < 0)
                ClickNextPage(++attempts);
            else
                return getTblResults(0).get(i);
        } while (i < 0 && attempts < 4);
        return null;
    }

    private void ClickNextPage(int idx) {
        List<WebElement> paginator = getTblResults(1).get(0)
                .findElements(By.tagName("td"));
        paginator.get(idx).click();
    }

    public ScheduleLeaguePage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
    }
}
