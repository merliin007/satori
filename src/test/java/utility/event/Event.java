/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 5:04 PM
 */
package utility.event;

public class Event {

    private String calendarList;
    private String eventName;
    private String eventDescription;
    private String dateOfEvent;

    public Event(String calendarList, String eventName, String eventDescription, String dateOfEvent) {
        this.calendarList = calendarList;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.dateOfEvent = dateOfEvent;
    }

    public String getCalendarList() {
        return calendarList;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }
}
