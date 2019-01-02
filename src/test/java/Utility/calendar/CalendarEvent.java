/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 12:43 PM
 */
package utility.calendar;

public class CalendarEvent {

    private String name;
    private String description;
    private String date;

    public CalendarEvent(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
