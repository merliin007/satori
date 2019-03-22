package utility.calendar;

public class Calendar {
    private String year;
    private String calendarType;
    private String startDate;
    private String endDate;

    public Calendar(String year, String calendarType, String startDate, String endDate) {
        this.year = year;
        this.calendarType = calendarType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getYear() {
        return year;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
