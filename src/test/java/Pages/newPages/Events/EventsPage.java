/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 10:42 AM
 */
package pages.newPages.Events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.newPages.Pages;

import java.util.List;
import java.util.NoSuchElementException;

public class EventsPage extends Pages {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNew;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlCalendar")
    private WebElement ddlCalendar;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilterEvent")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")
    private WebElement tblResults;

    public EventsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Override
    public List<WebElement> getPopOverLocatorList(){ return null;}

    @Override
    public int getCalendarActionIndex(String action){return 0;}

    public boolean searchFor(String val){
        try {
            new Select(ddlCalendar).selectByVisibleText(val);
            btnSearch.click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<WebElement> getAllRecordsFromResultTable(){
        return tblResults.findElements(By.tagName("tr"));
    }

    public WebElement getBtnNew(){
        return btnNew;
    }
}
