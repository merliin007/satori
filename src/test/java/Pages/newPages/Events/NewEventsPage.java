/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 5:07 PM
 */
package pages.newPages.Events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewEventsPage{

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendarDetail_ddlCalendarList")
    private WebElement ddlCalendarList;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendarDetail_txtEventNameVal")
    private WebElement txtEventName;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendarDetail_txtEventDescription")
    private WebElement txtEventDescription;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtvCalendarDetail_txtDateOfEvent")
    private WebElement txtEventDate;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;

    public NewEventsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void EnterNewEventInfoAndSave(String calendarList, String eventName, String eventDesc,
                                         String eventDate) throws Exception{
        new Select(ddlCalendarList).selectByVisibleText(calendarList);
        txtEventName.sendKeys(eventName);
        txtEventDescription.sendKeys(eventDesc);
        txtEventDate.sendKeys(eventDate);

        btnSave.click();

    }

}
