/*
 * Created by Miguel Angel Aguilar Cuevas
 * 19/07/2018 at 1:12 PM
 */
package utility;

import base.BaseUtil;
import base.CustomExceptions;
import common.Actionable;
import common.SuiteSetUp;
import cucumber.api.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.Pages;
import pages.jobs.*;
import pages.tss.TrackingSheetPage;
import utility.calendar.Calendar;
import utility.event.Event;
import utility.job.Job;
import utility.league.LeagueComponents;
import utility.members.Member;
import utility.newRoster.Facility;
import utility.newRoster.PlayerRoster;
import utility.tss.TrackingSheetScoreCard;
import utility.tss.TssWeekElements;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.NoSuchElementException;


public class Helpers implements Actionable {
    private BaseUtil base;
    private WebDriverWait wait;
    private WebDriver _driver;
    private static List<String> errorPages;

    public Helpers(WebDriver driver) {
        _driver = driver;
        wait = new WebDriverWait(_driver, 5);
        errorPages = new ArrayList<>();
    }

    public Helpers(BaseUtil baseUtil) {
        base = baseUtil;
        _driver = base.driver;
        wait = new WebDriverWait(_driver, 5);
    }

    @Override
    public void Write(WebElement element, String val) {
        if (val == null || val.isEmpty())
            return;
        element.clear();
        element.sendKeys(val);
    }

    @Override
    public void JSWrite(WebElement element, String val) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        String script = "arguments[0].value='" + val + "';";
        js.executeScript(script, element);
    }

    @Override
    public void AcceptAlert() {
        _driver.switchTo().alert().accept();
    }

    @Override
    public void RemoveAttribute(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        String script = "arguments[0].removeAttribute('onkeydown', 'onkeydown')";
        js.executeScript(script, element);

        script = "arguments[0].setAttribute('type', 'text')";
        js.executeScript(script, element);
    }

    @Override
    public void Click(WebElement element) {
        element.click();
    }

    @Override
    public void ClickIfButtonIsNotDisabled(WebElement element) {
        if (!element.getAttribute("class").contains("disabled"))
            Click(element);
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
        if (val == null || val.isEmpty())
            return;
        new Select(element).selectByVisibleText(val);
    }

    @Override
    public void SelectValueLike(WebElement element, String val) {
        if (val == null || val.isEmpty())
            return;
        List<WebElement> optList = element.findElements(By.tagName("option"));
        for (int i = 0; i < optList.size(); i++) {
            if (optList.get(i).getText().contains(val)) {
                new Select(element).selectByIndex(i);
                return;
            }
        }
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

    @Override
    public void CheckCheckBox(WebElement element) {
        if (!element.isSelected())
            Click(element);
    }

    @Override
    public void UncheckCheckBox(WebElement element) {
        if (element.isSelected())
            Click(element);
    }

    @Override
    public void CheckCheckBoxIf(WebElement element, boolean val) {
        if (val)
            CheckCheckBox(element);
        else
            UncheckCheckBox(element);
    }

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
            JSClick(list.get(position));
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
    public int searchForMemberInMembersList(Member member, List<WebElement> tblResults) {
        for (int i = (tblResults.size() > 3) ? 3 : 1; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (tblCel.get(1).getText().equals(member.getAltaNum()))
                return i;
        }
        return -1;
    }

    @Override
    public int searchForMemberByNameInMembersList(Member member, List<WebElement> tblResults) {
        for (int i = (tblResults.size() > 3) ? 3 : 1; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (tblCel.get(2).getText().toLowerCase().contains(member.getFirst().toLowerCase()) &&
                    tblCel.get(4).getText().toLowerCase().contains(member.getLast().toLowerCase()) && IsMembershipNotPayed(tblResults.get(i)))
                return i;
        }
        return -1;
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
            if (tblCel.get(1).getText().toLowerCase().contains(league.getLeagueType().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchForSuitableLeagueTemplate(LeagueComponents.LeagueDescription league, List<WebElement> tblResults) {
        for (int i = 1; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            if (league.getYear().equals(tblCel.get(1).getText()) &&
                    league.getSeason().toLowerCase().equals(tblCel.get(2).getText().toLowerCase()) &&
                    tblCel.get(3).getText().toLowerCase().contains(league.getLeagueType().toLowerCase())) {
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
    public int searchForElementInTheLeagueList(LeagueComponents.LeagueDescription leagueDescriptionList, LeagueComponents.LeagueDates leagueDates, List<WebElement> tblResults) {
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
    public int searchForElementInTheJobList(Job job, List<WebElement> tblResults) {

        for (int i = 1; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            try {
                if (tblCel.get(1).getText().equals(job.getYear()) &&
                        tblCel.get(2).getText().contains(job.getSeason()) &&
                        tblCel.get(3).getText().contains(job.getLeague())) {
                    Log.info("Element found at index: " + i);
                    return i;
                }
            } catch (IndexOutOfBoundsException e) {
                Log.info("Exception when searching for element");
            }
        }

        Log.info("Element not found");
        return -1;
    }

    @Override
    public int searchForElementInTheJobListIncludingAgeLevelFlight(Job job, List<WebElement> tblResults) {
        int i = 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            try {
                if (tblCel.get(1).getText().equals(job.getYear()) &&
                        tblCel.get(2).getText().contains(job.getSeason()) &&
                        tblCel.get(3).getText().contains(job.getLeague()) &&
                        tblCel.get(4).getText().equals(job.getAge()) &&
                        tblCel.get(5).getText().equals(job.getLevelFlight())
                ) {
                    Log.info("Element found at index: " + i);
                    return i;
                }
            } catch (IndexOutOfBoundsException e) {
                Log.info("Exception when searching for element");
            }
        }
        Log.info("Element not found");
        return -1;
    }

    @Override
    public int searchForPlayerInTheModal(PlayerRoster player, List<WebElement> tblResults) {
        int i = 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            try {
                if (tblCel.get(1).getText().equals(player.getaLTA_Number()) &&
                        tblCel.get(2).getText().contains(player.getLast() + ", " + player.getFirst())
                ) {
                    Log.info("Element found at index: " + i);
                    return i;
                }
            } catch (IndexOutOfBoundsException e) {
                Log.info("Exception when searching for element");
            }
        }
        Log.info("Element not found");
        return -1;
    }

    //searchForFacilityInTheModal
    @Override
    public int searchForFacilityInTheModal(Facility facility, List<WebElement> tblResults) {
        int i = 1;
        for (; i < tblResults.size(); i++) {
            List<WebElement> tblCel = tblResults.get(i).findElements(By.tagName("td"));
            try {
                if (tblCel.get(1).getText().equals(facility.getId()) &&
                        tblCel.get(2).getText().contains(facility.getName()) &&
                        (facility.getCounty() == null || tblCel.get(4).getText().contains(facility.getCounty()))
                ) {
                    Log.info("Element found at index: " + i);
                    return i;
                }
            } catch (IndexOutOfBoundsException e) {
                Log.info("Exception when searching for element");
            }
        }
        Log.info("Element not found");
        return -1;
    }

    @Override
    public void waitUntilElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void waitUntilElementIsClickable(WebElement element) {
        //wait.until(ExpectedConditions.elementToBeClickable(element));

        new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));

    }

    @Override
    public void changeZindex() {
        String script = "document.getElementById('WizardIsBusyIndicatorBehavior_backgroundElement').style.zIndex=-1000;";
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript(script);

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
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOf(element));

    }

    @Override
    public void waitUntilElementWithTextIsInvisible(By element, String text) throws Exception {
        new WebDriverWait(_driver, 5)
                .until(ExpectedConditions.invisibilityOfElementWithText(element, text));
    }

    @Override
    public void waitUntilConfirmationLabel() throws Exception {
        new WebDriverWait(_driver, 8)
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(StaleElementReferenceException.class)
                .until((ExpectedCondition<Boolean>) driver -> driver
                        .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_lblMessage"))
                        .getText().contains(SuiteSetUp.ADD_PLAYER_SUCCESS_LABEL)
                );
    }

    @Override
    public void fluentWaitUntilElementDisappears(By locator) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void waitUntilElementDisappears(WebElement element) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    @Override
    public void WaitUntilPresenceOfElement(By locator) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout((Duration.ofSeconds(6)))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public void WaitUntilVisualizationOfElementLocatedBy(By locator) throws Exception {
        new FluentWait<>(_driver)
                .withTimeout((Duration.ofSeconds(6)))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public void WaitUntilVisualizationOfElement(WebElement element) throws Exception {
/*
        new FluentWait<>(_driver)
                .withTimeout((Duration.ofSeconds(6)))
                .pollingEvery(Duration.ofMillis(100L))
                .ignoring(StaleElementReferenceException.class)
                //.until(ExpectedConditions.visibilityOf(element));
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                ));
*/
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table")));
    }

    @Override
    public ArrayList<JobPage> initJobVariables(List<Job> jobsList) {
        ArrayList<JobPage> jobs = new ArrayList<>();
//        jobs.add(new PlayoffDrawPage(_driver, jobsList.get(0)));


        jobs.add(new CalculateAwardsPage(_driver, jobsList.get(0)));
        jobs.add(new DropDuplicatesPage(_driver, jobsList.get(1)));
        jobs.add(new AssignToDivisionPage(_driver, jobsList.get(2)));
        jobs.add(new ScheduleLeaguePage(_driver, jobsList.get(3)));
        jobs.add(new PlayoffDrawPage(_driver, jobsList.get(4)));
        return jobs;
    }

    public CaptainsRetriever<PlayerRoster> getCaptains = (players) -> {
        List<String> captains = new ArrayList<>();
        for (PlayerRoster p : players) {
            if (p.isCaptain() || p.isCoCaptain())
                captains.add(p.getLast() + ", " + p.getFirst());
        }
        return captains;
    };

    @Override
    public void GoBackToPreviousPage() {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("window.history.go(-1)");
    }

    @Override
    public boolean CompareExpectedPage(String expected) {
        Log.info("Comparing actual page: " + _driver.getTitle() + " with expected: " + expected);
        return _driver.getTitle().contains(expected);
    }

    @Override
    public WebElement GetCheckBoxFromList(List<WebElement> checkList, String season) {

        for (WebElement element : checkList) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            for (WebElement cel : cells) {
                if (cel.findElement(By.tagName("label")).getText().toLowerCase().equals(season)) {
                    return cel.findElement(By.className("custom-control-input"));
                }
            }
        }
        return null;
    }

    @Override
    public void EnterTssSearchCriteria(List<String> criteria, TrackingSheetPage tss) throws Exception {
        if (tss == null || criteria == null) return;
        SelectValue(tss.getDdlStatus(), criteria.get(0));
        Write(tss.getTxtYear(), criteria.get(1));
        SelectValue(tss.getDdlSeason(), criteria.get(2));
        SelectValue(tss.getDdlLeague(), criteria.get(3));
        SelectValue(tss.getDdlAge(), criteria.get(4));
        SelectValue(tss.getDdlOriginalScorecard(), criteria.get(5));
        SelectValue(tss.getDdlLevelFlight(), criteria.get(6));
        SelectValue(tss.getDdlDivision(), criteria.get(5));
        SelectValue(tss.getDdlWeek(), criteria.get(8));
        Write(tss.getTxtRosterId(), criteria.get(9));
        Click(tss.getBtnSearch());
    }

    @Override
    public void EditTSSFields(TrackingSheetScoreCard tss, TssWeekElements weekElements) throws Exception {
        SelectValue(weekElements.getHomeResult(), tss.getHomeResult());
        SelectValue(weekElements.getHomePlayer1(), tss.getHomePlayer1());
        SelectValue(weekElements.getHomePlayer2(), tss.getHomePlayer2());
        SelectValue(weekElements.getHomeSet1(), String.valueOf(tss.getHomeSet1()));
        SelectValue(weekElements.getHomeSet2(), String.valueOf(tss.getHomeSet2()));
        SelectValue(weekElements.getHomeSet3(), String.valueOf(tss.getHomeSet3()));
        Write(weekElements.getPlayedDate(), tss.getPlayedDate());
        SelectValue(weekElements.getAwaySet1(), String.valueOf(tss.getAwaySet1()));
        SelectValue(weekElements.getAwaySet2(), String.valueOf(tss.getAwaySet2()));
        SelectValue(weekElements.getAwaySet3(), String.valueOf(tss.getAwaySet3()));
        SelectValue(weekElements.getAwayPlayer1(), tss.getAwayPlayer1());
        SelectValue(weekElements.getAwayPlayer2(), tss.getAwayPlayer2());
        SelectValue(weekElements.getAwayResult(), tss.getAwayResult());
    }

    public static void AddErrorPage(List<String> row) {
        errorPages.add(row.get(0) + " - " + row.get(1));
    }

    public static List<String> getErrorPages() {
        return errorPages;
    }

    @Override
    public boolean IsMembershipNotPayed(WebElement row) {
        return (row.findElements(By.tagName("td")).get(19).getText().contains("Unpaid"));

    }

}
