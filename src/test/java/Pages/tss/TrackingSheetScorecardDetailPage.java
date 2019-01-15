/*
 * Created by Miguel Angel Aguilar Cuevas
 * 10/01/2019 at 7:44 PM
 */
package pages.tss;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.tss.LineElement;
import utility.tss.TssWeekElements;

import java.util.ArrayList;
import java.util.List;

public class TrackingSheetScorecardDetailPage {
    public TrackingSheetScorecardDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        weekElements = getAllLinesElements();
    }

    List<TssWeekElements> weekElements;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_lstLines_tblLines")
    private WebElement tblLines;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSaveBottom")
    private WebElement btnSave;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancelBottom")
    private WebElement btnCancel;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_lstLines_txtHomeCoordinatorComments")
    private WebElement txtHomeComments;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_lstLines_txtAwayCoordinatorComments")
    private WebElement txtAwayComments;

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    private WebElement getTblLines() {
        return tblLines.findElement(By.tagName("tbody"));
    }

    public WebElement getTxtHomeComments() {
        return txtHomeComments;
    }

    public WebElement getTxtAwayComments() {
        return txtAwayComments;
    }

    private List<TssWeekElements> getAllLinesElements() {
        Log.info("Hold on a moment, mapping all elements, it'll take a while...");
        List<WebElement> hPlayers1 = getAllLinesPlayers(true, 1);
        List<WebElement> hPlayers2 = getAllLinesPlayers(true, 2);
        List<WebElement> aPlayers1 = getAllLinesPlayers(false, 1);
        List<WebElement> aPlayers2 = getAllLinesPlayers(false, 2);

        List<WebElement> rows = getTblLines().findElements(By.className("firstrowinsection"));
        List<TssWeekElements> weekElements = new ArrayList<>();
        int i = 0;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            try {
                weekElements.add(new TssWeekElements(
                        cells.get(LineElement.HOME_RESULT.getPosition()).findElement(By.tagName("select")),
                        hPlayers1.get(i),
                        hPlayers2.get(i),
                        cells.get(LineElement.HOME_SET1.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.HOME_SET2.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.HOME_SET3.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.PLAYED_DATE.getPosition()).findElement(By.tagName("input")),
                        cells.get(LineElement.AWAY_SET1.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.AWAY_SET2.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.AWAY_SET3.getPosition()).findElement(By.tagName("select")),
                        cells.get(LineElement.AWAY_RESULT.getPosition()).findElement(By.tagName("select")),
                        aPlayers1.get(i),
                        aPlayers2.get(i)
                ));
                i++;
            } catch (NoSuchElementException e) {
                Log.info("Adding results dropdown");
                break;
            } catch (IndexOutOfBoundsException e) {
                Log.info("No more results found");
                break;
            }
        }
        Log.info("Element mapping done!");
        return weekElements;
    }

    private List<WebElement> getAllLinesPlayers(boolean home, int pos) {
        if (pos > 2 || pos < 1)
            throw new IllegalArgumentException("Player position is not valid");

        List<WebElement> rows = getTblLines().findElements(By.tagName("tr"));
        List<WebElement> results = new ArrayList<>();
        String player = home ? "_ctl00_ddlHomePlayer" : "_ctl00_ddlAwayPlayer";

        for (int i = 0, j = (pos == 1) ? 7 : 8; j < rows.size(); j += 8, i++) {
            try {
                WebElement ddl = rows.get(j).findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_lstLines_ctrl" + i + player + pos));
                results.add(ddl);
            } catch (NoSuchElementException e) {
                Log.info("Still searching for players, currently found: " + results.size());
                return results;
            }
        }
        return results;
    }

    public List<TssWeekElements> getWeekElements() {
        return weekElements;
    }
}
