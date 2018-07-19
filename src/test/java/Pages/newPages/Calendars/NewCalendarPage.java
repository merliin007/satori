package pages.newPages.Calendars;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewCalendarPage {

    @FindBy(how = How.ID, using = "txtYear")
    private WebElement txtYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendar_ddlCalendarTypeID")
    private WebElement ddlCalendarType;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendar_txtAvailableStartDate")
    private WebElement txtStartDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendar_txtAvailableEndDate")
    private WebElement txtEndDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSaveNewCalendar;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendar_ddlUseEventsFrom")
    private WebElement ddlUseEvents;

    private WebDriver _driver;

    public NewCalendarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public void SetNewCalendarValuesAndSubmit(String year, String CalendarType, String StartDate, String EndDate, boolean isEdit) {
        clearFields();
        new Select(ddlCalendarType).selectByVisibleText(CalendarType);
        if(!isEdit) {
            CommonActions actions = new CommonActions(_driver);
            actions.waitUntilElementIsVisible(ddlUseEvents);
        }
        txtYear.sendKeys(year);
        txtStartDate.sendKeys(StartDate);
        txtEndDate.sendKeys(EndDate);

        btnSaveNewCalendar.click();
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public WebElement getTxtStartDate() {
        return txtStartDate;
    }

    private void clearFields() {
        txtYear.clear();
        txtStartDate.clear();
        txtEndDate.clear();
    }
}
