/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 10:42 AM
 */
package pages.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.Pages;

import java.util.List;
import java.util.NoSuchElementException;

public class EventsPage extends Pages {

    //private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNew;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlCalendar")
    private WebElement ddlCalendar;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilterEvent")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")
    private WebElement tblResults;

    /*@FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    private WebElement tblTableResults;*/

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement deleteModal;

    private By toastContainerLocator = By.id("toast-container");

    public By getToastContainerLocator() {
        return toastContainerLocator;
    }

    public WebElement getDeleteModal() {
        return deleteModal;
    }

    public WebElement getBtnConfirmDelete() {
        return deleteModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_lnkConfirm"));
    }

    public EventsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //_driver = driver;
    }

    /*@Override
    public List<WebElement> getPopOverLocatorList() {
        return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid"))
                .findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")).get(1)
                .findElements(By.tagName("tr"));
    }*/

    @Override
    public int getPageActionIndex(String action) {
        switch (action.toLowerCase()) {
            case "select event":
                return 0;
            case "copy event":
                return 1;
            case "delete event":
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public int getIndexForHeader(String columnName) {
        switch (columnName.toLowerCase()) {
            case "date":
                return 0;
            case "day":
                return 1;
            case "name":
                return 2;
            case "description":
                return 3;
            default:
                return -1;
        }
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return tblResults.findElements(By.tagName("th"));
    }

    /*@Override
    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }*/

    public boolean searchFor(String val) {
        try {
            new Select(ddlCalendar).selectByVisibleText(val);
            btnSearch.click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getBtnNew() {
        return btnNew;
    }


}
