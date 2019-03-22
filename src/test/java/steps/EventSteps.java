/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 12:52 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.calendars.CalendarsPage;
import pages.events.EventsPage;
import pages.events.NewEventsPage;
import utility.Helpers;
import utility.Log;
import utility.event.Event;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


public class EventSteps {
    private BaseUtil base;
    private EventsPage eventsPage;
    private NewEventsPage newEventsPage;
    private Helpers I;
    private CalendarsPage calendarsPage;

    List<Event> eventList;

    public EventSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
        I = new Helpers(baseUtil);
    }

    @And("^I search for \"([^\"]*)\" calendar or create a new if there is not any$")
    public void iSearchForCalendarOrCreateANewIfThereIsNotAny(String calendar, DataTable table) throws Throwable {
        iGoToEventsPage();
        try {
            eventsPage = new EventsPage(base.driver);
            if (!eventsPage.searchFor(calendar)) {
                DataTable link = I.createLinkForNavigator("Website Support", "calendars");
                new NavigationSteps(base).iNavigateToTheFollowingPageOption(link);
                new CalendarSteps(base).iCreateANewCalendarUsing(table);
            }


        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I create a new Event using$")
    public void iCreateANewEventUsing(DataTable event) {
        try {
            I.Click(eventsPage.getBtnNew());
            eventList = event.asList(Event.class);
            newEventsPage = new NewEventsPage(base.driver);

            for (Event e : eventList) {
                newEventsPage.EnterNewEventInfoAndSave(
                        e.getCalendarList(),
                        e.getEventName() + "-" + System.currentTimeMillis(),
                        e.getEventDescription(),
                        e.getDateOfEvent()
                );
            }
            Log.info("Waiting for toastr to disappear");
            I.fluentWaitUntilElementDisappears(eventsPage.getToastContainerLocator());
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I delete such event$")
    public void iDeleteSuchEvent() {
        try {
            I.sortByColumn("Date", eventsPage);
            I.sortByColumn("Date", eventsPage);// as there's some issue sorting, we need to click on sort icon twice

            List<WebElement> tblResults = eventsPage.getTblResults(0);
            int index = I.searchForElementInTheEventsList(eventList.get(0), tblResults);
            if (index != -1) {
                I.selectOptionFromCell(index, "Delete Event", eventsPage);
                I.waitUntilElementIsVisible(eventsPage.getDeleteModal());
                eventsPage.getBtnConfirmDelete().click();
                tblResults = eventsPage.getTblResults(0);
                assertEquals(I.searchForElementInTheEventsList(eventList.get(0), tblResults), -1);
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    public void iGoToEventsPage() {
        try {
            calendarsPage = new CalendarsPage(base.driver);
            I.Click(calendarsPage.getLnkEvents());
        } catch (Exception e) {
            Log.info(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }


}
