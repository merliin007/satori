/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 12:52 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.newPages.Events.EventsPage;
import pages.newPages.Events.NewEventsPage;
import utility.Log;
import utility.event.Event;

import java.util.List;

import static utility.Helpers.createLinkForNavigator;
import static org.testng.Assert.fail;


public class EventSteps {
    private BaseUtil base;
    private EventsPage eventsPage;
    private NewEventsPage newEventsPage;

    List<Event> eventList;

    public EventSteps(BaseUtil baseUtil) {
        this.base = baseUtil;
    }

    @And("^I search for \"([^\"]*)\" calendar or create a new if there is not any$")
    public void iSearchForCalendarOrCreateANewIfThereIsNotAny(String calendar, DataTable table) throws Throwable {
        try {
            eventsPage = new EventsPage(base.driver);
            if (!eventsPage.searchFor(calendar)) {
                DataTable link = createLinkForNavigator("Website Support", "Calendars");
                new NavigationSteps(base).iNavigateToTheFollowingPagesOption(link);
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
        try{
            eventsPage.getBtnNew().click();
            eventList = event.asList(Event.class);
            newEventsPage = new NewEventsPage(base.driver);

            for(Event e: eventList){
                newEventsPage.EnterNewEventInfoAndSave(
                        e.getCalendarList(),
                        e.getEventName(),
                        e.getEventDescription(),
                        e.getDateOfEvent()
                );
            }

        }catch (Exception e){
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I delete such event$")
    public void iDeleteSuchEvent() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
