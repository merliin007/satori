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
import utility.Helpers;
import utility.Log;
import utility.job.Job;

import java.util.List;

public class DropDuplicatesPage extends JobPage {
    Helpers I;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlIgnoreConstraintsYesNoList")
    private WebElement dropIgnore;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;


    public WebElement getDropIgnore() {
        return dropIgnore;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    @Override
    public WebElement QueueJobButton() {
        try {
            Log.info("Queuing " + this.getClass().getSimpleName() + " job");
            I.SelectValue(getDropIgnore(), "Yes");
            I.Click(getBtnSearch());

            List<WebElement> headers = getAllColumnHeaders();
            I.Click(headers.get(1));

            /*int i = I.searchForElementInTheJobList(job, getTblResults(1));
            return (i > 0) ? getTblResults(0).get(i) : null;*/

            int idx, j = 1, pagingSize = getTblPaging().size();
            do {
                idx = I.searchForElementInTheJobList(job, getTblResults(1));
                if (idx > 0)
                    break;
                else
                    goToNextPage(j, I);
            } while (++j < 5 && j < pagingSize);
            return (idx > 0) ? getTblResults(0).get(idx) : null;
        } catch (Exception e) {
            Log.error(e.getMessage());
            return null;
        }
    }

    public DropDuplicatesPage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
        I = new Helpers(driver);
    }
}
