package pages.newPages.Calendars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.newPages.Pages;

import java.util.List;

public class CalendarsPage extends Pages {

    //private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNewCalendar;

    /*@FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    private WebElement tblTableResults;*/

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement deleteModal;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_lnkConfirm")
    private WebElement btnConfirmDelete;

    private By  toastContainerLocator = By.id("toast-container");
    public By getToastContainerLocator() {
        return toastContainerLocator;
    }

    public CalendarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
//        _driver = driver;
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
            case "view calendar":
                return 0;
            case "view events":
                return 1;
            case "delete calendar":
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public int getIndexForHeader(String columnName){
        //TODO
        return 0;

    }
    @Override
    public List<WebElement> getAllColumnHeaders(){
        return null;
    }

    public WebElement getBtnNewCalendar() {
        return btnNewCalendar;
    }

    public WebElement getToastContainer() {
        return _driver.findElement(toastContainerLocator);
    }

   /* @Override
    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }*/

    public WebElement getDeleteModal() {
        return deleteModal;
    }

    public WebElement getBtnConfirmDelete() {
        return btnConfirmDelete;
    }
}
