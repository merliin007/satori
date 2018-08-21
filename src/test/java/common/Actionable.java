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
import utility.calendar.Calendar;
import utility.event.Event;
import utility.league.LeagueComponents;

import java.time.LocalDateTime;
import java.util.List;

public interface Actionable {
    void Click(WebElement element);
    void Write(WebElement element, String val);
    void ClickOnRowButtonAtPosition(WebElement row, int pos);
    void JSClick(WebElement element);
    void JSScrollToView(WebElement element);
    void SelectValue(WebElement element, String val);
    boolean CompareDates(String date1, String date2);
    void selectOptionFromCell(int position, String action, Pages page) throws CustomExceptions;
    DataTable createLinkForNavigator(String section, String option);
    void sortByColumn(String columnName, Pages page);
    int searchForElementInTheCalendarList(Calendar calendar, List<WebElement> tblResults);
    int searchForElementInTheEventsList(Event event, List<WebElement> tblResults);
    int searchForElementInTheLeagueTemplateList(LeagueComponents.LeagueDescription league, List<WebElement> tblResults);
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

}
