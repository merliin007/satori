/*
 * Created by Miguel Angel Aguilar Cuevas
 * 19/07/2018 at 1:12 PM
 */
package utility;

import base.BaseUtil;
import base.CustomExceptions;
import common.Actionable;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.newPages.Pages;
import utility.calendar.Calendar;
import utility.event.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Helpers implements Actionable {
    private BaseUtil base;

    public Helpers(BaseUtil baseUtil) {
        base = baseUtil;
    }

    @Override
    public void Write(WebElement element, String val){
        element.sendKeys(val);
    }

    @Override
    public void Click(WebElement element){
        element.click();
    }

    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1}/\\d{1}/\\d{4}$", "M/d/yyyy");
        put("^\\d{1}/\\d{2}/\\d{4}$", "M/dd/yyyy");
        put("^\\d{2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    }};

    private static String determineDateFormat(String dateString){
        for(String regexp: DATE_FORMAT_REGEXPS.keySet()){
            if(dateString.toLowerCase().matches(regexp)){
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null;
    }

    @Override
    public boolean CompareDates(String date1, String date2){

        try {
            LocalDate dateObject1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern(Objects.requireNonNull(determineDateFormat(date1))));
            LocalDate dateObject2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern(Objects.requireNonNull(determineDateFormat(date2))));
            return (dateObject1.isEqual(dateObject2));
        } catch (Exception e) {
            Log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void selectOptionFromCell(int position, String action, Pages page) throws CustomExceptions {
        int action_index = page.getPageActionIndex(action);
        if ( action_index < 0)
            throw new CustomExceptions("Invalid selecting option");
        try {

            List<WebElement> list = page.getPopOverLocatorList();
            list.get(position).click();
            List<WebElement> subElementCommands = page.getPopOverCommands(position);

            JavascriptExecutor js = (JavascriptExecutor) base.driver;
            String script = "arguments[0].click();";
            js.executeScript("arguments[0].scrollIntoView();", list.get(position));
            js.executeScript(script, subElementCommands.get(action_index));

        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public DataTable createLinkForNavigator(String section, String option){
        List<List<String>> raw = new ArrayList<>();
        List<String> line = new ArrayList<>();
        line.add(section);
        line.add(option);
        raw.add(line);
        return DataTable.create(raw);
    }

    @Override
    public void sortByColumn(String columnName, Pages page){
        try {
            List<WebElement> columns = page.getAllColumnHeaders();
            columns.get(page.getIndexForHeader(columnName)).click();
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public int searchForElementInTheCalendarList(Calendar calendar, List<WebElement> tblResults) {
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

    @Override
    public int searchForElementInTheEventsList(Event event, List<WebElement> tblResults) {
        int i = 3;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (CompareDates(tblCel.get(0).getText(),event.getDateOfEvent()) &&
                    tblCel.get(2).getText().equals((event.getEventName())) &&
                    tblCel.get(3).getText().equals(event.getEventDescription())) {

                return i;
            }
        }
        return -1;
    }


}
