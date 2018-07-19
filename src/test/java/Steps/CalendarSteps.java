package steps;

import base.BaseUtil;
import common.CommonActions;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.newPages.Calendars.CalendarsPage;
import pages.newPages.Calendars.NewCalendarPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;


import static utility.Helpers.CompareDates;

import utility.Log;
import utility.calendar.Calendar;

import java.util.List;


public class CalendarSteps {
    private CalendarsPage calendarsPage;
    private NewCalendarPage newCalendarPage;
    private BaseUtil base;
    private CommonActions commonActions;

    private List<Calendar> calendarList;

    public CalendarSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
        commonActions = new CommonActions(base.driver);
    }

    @And("^I create a new calendar using$")
    public void iCreateANewCalendarUsing(DataTable table) {
        try {
            calendarsPage = new CalendarsPage(base.driver);
            calendarsPage.getBtnNewCalendar().click();
            newCalendarPage = new NewCalendarPage(base.driver);
            commonActions.waitUntilElementIsClickable(newCalendarPage.getTxtStartDate());
            calendarList = table.asList(Calendar.class);
            assertTrue(CreateNewCalendar(newCalendarPage, calendarList, false));
            Log.info("Waiting for toastr to disappear");
            Log.info("toastr exists: " + calendarsPage.getToastContainer().isDisplayed());
            commonActions.fluentWaitUntilElementDisappears(calendarsPage.getToastContainerLocator());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }

    }

    @And("^I edit recently created calendar without issues$")
    public void iEditRecentlyCreatedCalendarWithoutIssues(DataTable table) {
        try {
            List<WebElement> tblResults = calendarsPage.getTblCalendarResults(0);
            int index = searchForElementInTheList(calendarList.get(0), tblResults);
            if (index != -1) {
                selectOptionFromCell(index, "view calendar");
                calendarList = table.asList(Calendar.class);
                assertTrue(EditCalendar(newCalendarPage, calendarList));
                Log.info("Waiting for toastr to disappear");
                Log.info("toastr exists: " + calendarsPage.getToastContainer().isDisplayed());
                commonActions.fluentWaitUntilElementDisappears(calendarsPage.getToastContainerLocator());
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
            List<WebElement> tblResults = calendarsPage.getTblCalendarResults(0);
            int index = searchForElementInTheList(calendarList.get(0), tblResults);
            if (index != -1) {
                selectOptionFromCell(index, "delete calendar");
                commonActions.waitUntilElementIsVisible(calendarsPage.getDeleteModal());
                calendarsPage.getBtnConfirmDelete().click();
                tblResults = calendarsPage.getTblCalendarResults(0);
                assertEquals(searchForElementInTheList(calendarList.get(0), tblResults), -1);
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

    private void selectOptionFromCell(int position, String action) {
        try {

            List<WebElement> list = calendarsPage.getPopOverLocatorList();
            list.get(position).click();

            List<WebElement> subElementCommands = calendarsPage.getPopOverCommands(position);

            JavascriptExecutor js = (JavascriptExecutor) base.driver;
            String script = "arguments[0].click();";
            int opt = 0;
            switch (action.toLowerCase()) {
                case "view calendar":
                    opt = 0;
                    break;
                case "view events":
                    opt = 1;
                    break;
                case "delete calendar":
                    opt = 2;
                    break;
            }
            js.executeScript(script, subElementCommands.get(opt));

        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    private int searchForElementInTheList(Calendar calendar, List<WebElement> tblResults) {
        int i;
        for (i = 3; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (tblCel.get(0).getText().equals(calendar.getYear()) &&
                    tblCel.get(1).getText().equals((calendar.getCalendarType())) &&
                    CompareDates(tblCel.get(4).getText(), calendar.getStartDate()) &&
                    CompareDates(tblCel.get(5).getText(), calendar.getEndDate())) {

                return i;
            }
        }
        return -1;
    }

}
