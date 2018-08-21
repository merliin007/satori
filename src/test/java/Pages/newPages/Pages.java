/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 11:01 AM
 */
package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public abstract class Pages{
    protected WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    protected WebElement tblTableResults;

    public Pages(WebDriver _driver) {
        this._driver = _driver;
    }

    public List<WebElement> getPopOverLocatorList(){
        return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid"))
                .findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")).get(0)
                .findElements(By.tagName("tr"));
    }

    public List<WebElement> getPopOverCommands(int position){
        return getPopOverLocatorList().get(position)
                .findElement(By.className("popover__commands"))
                .findElements(By.tagName("a"));
    }

    public abstract int getPageActionIndex(String action);

    public abstract int getIndexForHeader(String columnName);

    public abstract List<WebElement> getAllColumnHeaders();

    public List<WebElement> getTblResults(int i){
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

}
