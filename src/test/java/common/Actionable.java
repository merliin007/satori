/*
 * Created by Miguel Angel Aguilar Cuevas
 * 14/08/2018 at 11:13 AM
 */
package common;

import base.CustomExceptions;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.newPages.Pages;
import pages.newPages.jobs.JobPage;
import pages.newPages.tss.TrackingSheetPage;
import utility.calendar.Calendar;
import utility.event.Event;
import utility.job.Job;
import utility.league.LeagueComponents;
import utility.members.Member;
import utility.newRoster.Facility;
import utility.newRoster.PlayerRoster;
import utility.tss.TrackingSheetScoreCard;
import utility.tss.TssWeekElements;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface Actionable {
    void Click(WebElement element);

    void Write(WebElement element, String val);

    void ClickOnRowButtonAtPosition(WebElement row, int pos);

    void JSClick(WebElement element);

    void JSScrollToView(WebElement element);

    void RemoveAttribute(WebElement element);

    void SelectValue(WebElement element, String val);

    void CheckCheckBox(WebElement element);

    void UncheckCheckBox(WebElement element);

    boolean CompareDates(String date1, String date2);

    void CheckCheckBoxIf(WebElement element, boolean val);

    void selectOptionFromCell(int position, String action, Pages page) throws CustomExceptions;

    DataTable createLinkForNavigator(String section, String option);

    void sortByColumn(String columnName, Pages page);

    int searchForMemberInMembersList(Member member, List<WebElement> tblResults);

    int searchForElementInTheCalendarList(Calendar calendar, List<WebElement> tblResults);

    int searchForElementInTheEventsList(Event event, List<WebElement> tblResults);

    int searchForElementInTheLeagueTemplateList(LeagueComponents.LeagueDescription league, List<WebElement> tblResults);

    int searchForSuitableLeagueTemplate(LeagueComponents.LeagueDescription league, List<WebElement> tblResults);

    int searchForElementInTheListAtPos(String altaNumber, List<WebElement> tblResults, int pos);

    int searchForElementInTheLeagueList(LeagueComponents.LeagueDescription leagueDescriptionList, LeagueComponents.LeagueDates leagueDates, List<WebElement> tblResults);

    void waitUntilElementIsVisible(WebElement element);

    void waitUntilElementIsClickable(WebElement element);

    boolean IsLocalDateTimeAroundServerDate(LocalDateTime localDate, LocalDateTime serverDate, long tolerance);

    void verifyAlertErrorAndAcceptIt();

    void waitUntilExistenceOfElement(By elementBy);

    void waitUntilInvisibilityOf(WebElement element) throws Exception;

    void waitUntilElementWithTextIsInvisible(By element, String text) throws Exception;

    void fluentWaitUntilElementDisappears(By locator) throws Exception;

    void WaitUntilPresenceOfElement(By locator) throws Exception;

    void WaitUntilVisualizationOfElement(WebElement element) throws Exception;

    int searchForElementInTheJobList(Job job, List<WebElement> tblResults);

    int searchForElementInTheJobListIncludingAgeLevelFlight(Job job, List<WebElement> tblResults);


    int searchForPlayerInTheModal(PlayerRoster player, List<WebElement> tblResults);

    int searchForFacilityInTheModal(Facility facility, List<WebElement> tblResults);

    void GoBackToPreviousPage();

    boolean CompareExpectedPage(String expected);

    WebElement GetCheckBoxFromList(List<WebElement> checkList, String season);

    void JSWrite(WebElement element, String val);

    void AcceptAlert();

    void ClickIfButtonIsNotDisabled(WebElement element);

    void SelectValueLike(WebElement element, String val);

    void changeZindex();

    void waitUntilConfirmationLabel() throws Exception;

    void WaitUntilVisualizationOfElementLocatedBy(By locator) throws Exception;

    ArrayList<JobPage> initJobVariables(List<Job> jobsList);

    void EnterTssSearchCriteria(List<String> criteria, TrackingSheetPage tss) throws Exception;

    void EditTSSFields(TrackingSheetScoreCard tss, TssWeekElements tssWeekElements) throws Exception;

}
