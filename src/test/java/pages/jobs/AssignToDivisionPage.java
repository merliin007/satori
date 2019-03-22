/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:15 AM
 */
package pages.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.Helpers;
import utility.Log;
import utility.job.Job;

public class AssignToDivisionPage extends JobPage {
    private Helpers I;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlIgnoreConstraintsYesNoList")
    private WebElement ddlIgnore;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;

    @Override
    public WebElement QueueJobButton() {
        Log.info("Queuing " + this.getClass().getSimpleName() + " job");
        new Select(ddlIgnore).selectByVisibleText("Yes");
        btnSearch.click();

        int idx, j = 1, pagingSize = getTblPaging().size();
        do {
            idx = I.searchForElementInTheJobList(job, getTblResults(1));
            if (idx > 0)
                break;
            else
                goToNextPage(j, I);
        } while (++j < 5 && j < pagingSize);
        return (idx > 0) ? getTblResults(0).get(idx) : null;
    }


    public AssignToDivisionPage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
        I = new Helpers(_driver);
    }


}
