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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.newPages.Pages;
import utility.calendar.Calendar;
import utility.event.Event;
import utility.league.LeagueComponents;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Helpers implements Actionable {
    private BaseUtil base;
    private WebDriverWait wait;
    private WebDriver _driver;

    public Helpers(WebDriver driver) {
        _driver = driver;
        wait = new WebDriverWait(_driver, 5);
    }

    public Helpers(BaseUtil baseUtil) {
        base = baseUtil;
        _driver = base.driver;
        wait = new WebDriverWait(_driver, 5);
    }

    @Override
    public void Write(WebElement element, String val) {
        element.clear();
        element.sendKeys(val);
    }

    @Override
    public void Click(WebElement element) {
        element.click();
    }

    @Override
    public void ClickOnRowButtonAtPosition(WebElement row, int pos) {
        WebElement rowButton = row.findElements(By.tagName("td")).get(pos).
                findElement(By.className("command_buttons"))
                .findElement(By.tagName("div")).findElement(By.tagName("a"));
        Click(rowButton);
    }

    @Override
    public void JSClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        String script = "arguments[0].click();";
        js.executeScript(script, element);
    }

    @Override
    public void JSScrollToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, element);
    }

    @Override
    public void SelectValue(WebElement element, String val) {
        new Select(element).selectByVisibleText(val);
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

    private static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null;
    }

    @Override
    public boolean CompareDates(String date1, String date2) {

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
        if (action_index < 0)
            throw new CustomExceptions("Invalid selecting option");
        try {

            List<WebElement> list = page.getPopOverLocatorList();
            Click(list.get(position));
            List<WebElement> subElementCommands = page.getPopOverCommands(position);

            JSScrollToView(list.get(position));
            JSClick(subElementCommands.get(action_index));


        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public DataTable createLinkForNavigator(String section, String option) {
        List<List<String>> raw = new ArrayList<>();
        List<String> line = new ArrayList<>();
        line.add(section);
        line.add(option);
        raw.add(line);
        return DataTable.create(raw);
    }

    @Override
    public void sortByColumn(String columnName, Pages page) {
        try {
            List<WebElement> columns = page.getAllColumnHeaders();
            Click(columns.get(page.getIndexForHeader(columnName)));
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public int searchForElementInTheCalendarList(Calendar calendar, List<WebElement> tblResults) {
        int i = (tblResults.size() > 3) ? 3 : 1;
        for (; i < tblResults.size(); i++) {
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
        int i = (tblResults.size() > 3) ? 3 : 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (CompareDates(tblCel.get(0).getText(), event.getDateOfEvent()) &&
                    tblCel.get(2).getText().equals((event.getEventName())) &&
                    tblCel.get(3).getText().equals(event.getEventDescription())) {

                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchForElementInTheLeagueTemplateList(LeagueComponents.LeagueDescription league, List<WebElement> tblResults) {
        int i = 3;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (league.getLeagueType().equals(tblCel.get(1).getText())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchForElementInTheListAtPos(String element, List<WebElement> tblResults, int pos) {
        int i = (tblResults.size() > 3) ? 3 : 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (element.contains(tblCel.get(pos).getText())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchForElementInTheLeagueList(LeagueComponents.LeagueDescription leagueDescriptionList, LeagueComponents.LeagueDates leagueDates, List<WebElement> tblResults){
        int i = (tblResults.size() > 3) ? 3 : 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (tblCel.get(1).getText().equals(leagueDescriptionList.getYear()) &&
                    tblCel.get(2).getText().contains(leagueDescriptionList.getSeason()) &&
                    tblCel.get(3).getText().contains(leagueDescriptionList.getLeagueType()) &&
                    tblCel.get(5).getText().contains(leagueDescriptionList.getPlayday()) &&
                    CompareDates(tblCel.get(9).getText(), leagueDates.getEndDate()) &&
                    CompareDates(tblCel.get(10).getText(), leagueDates.getCaptMeeting()) &&
                    CompareDates(tblCel.get(11).getText(), leagueDates.getPlayWeek())) {

                return i;
            }
        }
        return -1;
    }

    @Override
    public void waitUntilElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public boolean IsLocalDateTimeAroundServerDate(LocalDateTime localDate, LocalDateTime serverDate, long tolerance) {
        LocalDateTime tempDate = localDate.plusHours(1L);
        return (serverDate.minusMinutes(tolerance).isBefore(tempDate) && serverDate.plusMinutes(tolerance).isAfter(tempDate));
    }

    @Override
    public void verifyAlertErrorAndAcceptIt() {
        if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
            _driver.switchTo().alert().accept();
            _driver.switchTo().defaultContent();
        }
    }

    @Override
    public void waitUntilExistenceOfElement(By elementBy) {
        new WebDriverWait(_driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    @Override
    public void waitUntilInvisibilityOf(WebElement element) throws Exception {
        new WebDriverWait(_driver, 10)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    @Override
    public void waitUntilElementWithTextIsInvisible(By element, String text) throws Exception {
        new WebDriverWait(_driver, 5)
                .until(ExpectedConditions.invisibilityOfElementWithText(element, text));
    }

    @Override
    public void fluentWaitUntilElementDisappears(By locator) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void FluentWaitUntilPresenceOfNewElement(By locator) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout((Duration.ofSeconds(5)))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


}
