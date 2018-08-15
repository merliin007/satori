/*
 * Created by Miguel Angel Aguilar Cuevas
 * 14/08/2018 at 11:13 AM
 */
package common;

import base.CustomExceptions;
import cucumber.api.DataTable;
import org.openqa.selenium.WebElement;
import pages.newPages.Pages;
import utility.calendar.Calendar;
import utility.event.Event;

import java.util.List;

public interface Actionable {
    void Click(WebElement element);
    void Write(WebElement element, String val);
    boolean CompareDates(String date1, String date2);
    void selectOptionFromCell(int position, String action, Pages page) throws CustomExceptions;
    DataTable createLinkForNavigator(String section, String option);
    void sortByColumn(String columnName, Pages page);
    int searchForElementInTheCalendarList(Calendar calendar, List<WebElement> tblResults);
    int searchForElementInTheEventsList(Event event, List<WebElement> tblResults);
}
