package pages.newPages.Calendars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarsPage {

    private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNewCalendar;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    private WebElement tblCalendarResults;

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement deleteModal;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_lnkConfirm")
    private WebElement btnConfirmDelete;

    private By  toastContainerLocator = By.id("toast-container");

    public By getToastContainerLocator() {
        return toastContainerLocator;
    }

    public List<WebElement> getPopOverLocatorList() {
        return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid"))
                .findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")).get(1)
                .findElements(By.tagName("tr"));
    }

    public List<WebElement> getPopOverCommands(int position) {
        return getPopOverLocatorList().get(position)
                .findElement(By.className("popover__commands"))
                .findElements(By.tagName("a"));
    }

    public CalendarsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getBtnNewCalendar() {
        return btnNewCalendar;
    }

    public WebElement getToastContainer() {
        return _driver.findElement(toastContainerLocator);
    }

    public List<WebElement> getTblCalendarResults(int i) {
        return tblCalendarResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public WebElement getDeleteModal() {
        return deleteModal;
    }

    public WebElement getBtnConfirmDelete() {
        return btnConfirmDelete;
    }
}
