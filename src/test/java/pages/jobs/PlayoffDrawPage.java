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

public class PlayoffDrawPage extends JobPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlIgnoreConstraintsYesNoList")
    private WebElement ddlIgnore;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_txtYear")
    private WebElement txtYear;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlSeason")
    private WebElement ddlSeason;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlLeagueType")
    private WebElement ddlLeague;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;


    @Override
    public WebElement QueueJobButton() {
        Log.info("Queuing " + this.getClass().getSimpleName() + " job");
        new Select(ddlIgnore).selectByVisibleText("Yes");
        txtYear.clear();
        txtYear.sendKeys(job.getYear());
        new Select(ddlSeason).selectByVisibleText(job.getSeason());
        new Select(ddlLeague).selectByVisibleText(job.getLeague());
        btnSearch.click();

        int i = new Helpers(_driver).searchForElementInTheJobListIncludingAgeLevelFlight(job, getTblResults(1));
        return (i > 0) ? getTblResults(0).get(i) : null;
    }

    public PlayoffDrawPage(WebDriver driver, Job job) {
        super(driver, job);
        PageFactory.initElements(driver, this);
    }
}
