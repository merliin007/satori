package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.calendars.CalendarsPage;
import pages.calendars.NewCalendarPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import utility.Helpers;
import pages.events.EventsPage;
import utility.Log;
import utility.calendar.Calendar;

import java.util.List;


public class CalendarSteps {
    private CalendarsPage calendarsPage;
    private NewCalendarPage newCalendarPage;
    private BaseUtil base;
    private EventsPage eventsPage;
    private Helpers I;
    private List<Calendar> calendarList;

    public CalendarSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
        I = new Helpers(base);

    }

    @And("^I create a new calendar using$")
    public void iCreateANewCalendarUsing(DataTable table) {
        try {
            calendarsPage = new CalendarsPage(base.driver);
            calendarsPage.getBtnNewCalendar().click();
            newCalendarPage = new NewCalendarPage(base.driver);
            I.waitUntilElementIsClickable(newCalendarPage.getTxtStartDate());
            calendarList = table.asList(Calendar.class);
            assertTrue(CreateNewCalendar(newCalendarPage, calendarList, false));
            Log.info("Waiting for toastr to disappear");
            Log.info("toastr exists: " + calendarsPage.getToastContainer().isDisplayed());
            I.fluentWaitUntilElementDisappears(calendarsPage.getToastContainerLocator());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }

    }

    @And("^I edit recently created calendar without issues$")
    public void iEditRecentlyCreatedCalendarWithoutIssues(DataTable table) {
        try {
            List<WebElement> tblResults = calendarsPage.getTblResults(0);
            int index = I.searchForElementInTheCalendarList(calendarList.get(0), tblResults);
            if (index != -1) {
                I.selectOptionFromCell(index, "view calendar", calendarsPage);
                calendarList = table.asList(Calendar.class);
                assertTrue(EditCalendar(newCalendarPage, calendarList));
                Log.info("Waiting for toastr to disappear");
                Log.info("toastr exists: " + calendarsPage.getToastContainer().isDisplayed());
                I.fluentWaitUntilElementDisappears(calendarsPage.getToastContainerLocator());
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^I delete such calendar$")
    public void iDeleteSuchCalendar() {
        try {
            List<WebElement> tblResults = calendarsPage.getTblResults(0);
            int index = I.searchForElementInTheCalendarList(calendarList.get(0), tblResults);
            if (index != -1) {
                I.selectOptionFromCell(index, "delete calendar", calendarsPage);
                I.waitUntilElementIsVisible(calendarsPage.getDeleteModal());
                calendarsPage.getBtnConfirmDelete().click();
                tblResults = calendarsPage.getTblResults(0);
                assertEquals(I.searchForElementInTheCalendarList(calendarList.get(0), tblResults), -1);
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    private boolean CreateNewCalendar(NewCalendarPage newCalendarPage, List<Calendar> calendarOptions, boolean isEdit) {
        try {
            for (Calendar cal : calendarOptions) {
                newCalendarPage.SetNewCalendarValuesAndSubmit(
                        cal.getYear(),
                        cal.getCalendarType(),
                        cal.getStartDate(),
                        cal.getEndDate(),
                        isEdit
                );
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean EditCalendar(NewCalendarPage newCalendarPage, List<Calendar> calendarOptions) {
        return CreateNewCalendar(newCalendarPage, calendarOptions, true);
    }


}
