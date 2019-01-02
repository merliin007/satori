/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 12:16 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import pages.newPages.memberPortal.myActiveTeams.CalendarPage;
import utility.Helpers;
import utility.Log;
import utility.calendar.CalendarEvent;

import java.util.List;

import static org.testng.Assert.fail;

public class MemberCalendarSteps {

    private BaseUtil base;
    private Helpers I;
    private CalendarPage calendarPage;

    public MemberCalendarSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        calendarPage = new CalendarPage(base.driver);
    }

    @And("^I can create a new event with the following info$")
    public void iCanCreateANewEventWithTheFollowingInfo(DataTable table) {
        try{
            List<CalendarEvent> calendarEventList = table.asList(CalendarEvent.class);
            I.Click(calendarPage.getBtnNewEvent());
            I.waitUntilElementIsVisible(calendarPage.getModalEvent());
            I.Write(calendarPage.getModalObject("name"), calendarEventList.get(0).getName() + " " + System.currentTimeMillis());
            I.Write(calendarPage.getModalObject("description"), calendarEventList.get(0).getDescription()+ " " + System.currentTimeMillis());
            I.RemoveAttribute(calendarPage.getModalObject("date"));
            I.JSWrite(calendarPage.getModalObject("date"), calendarEventList.get(0).getDate());
            I.Click(calendarPage.getModalObject("submit"));

            I.WaitUntilPresenceOfElement(calendarPage.getToastrBy());

        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}
