/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 11:01 AM
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public abstract class Pages{
    protected WebDriver _driver;

    public Pages(WebDriver _driver) {
        this._driver = _driver;
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    protected WebElement tblTableResults;

    @FindBy(how = How.CLASS_NAME, using = "nav-ribbon")
    private WebElement topRibbon;

    @FindBy(how = How.ID, using = "topNav")
    private WebElement topNav;

    public WebElement getTopNav() {
        return topNav;
    }

    public WebElement RibbonOption(String opt) throws Exception{
        List<WebElement> ribbonList = topRibbon.findElement(By.className("container"))
                .findElements(By.className("nav-ribbon__option"));
        for(WebElement rib: ribbonList){
            if(rib.findElement(By.tagName("a")).getText().toLowerCase().contains(opt.toLowerCase()))
                return rib;
        }
        return null;
    }

    public List<WebElement> getPopOverLocatorList(){
        return getTblResults(0);
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

    public WebElement getDdlSwtichTo(){
        return topNav.findElement(By.className("dropdown"));
    }

    public WebElement ddMenu(){
        return topNav.findElement(By.className("dropdown-menu"));
    }

    public List<WebElement> ddMenuOptions(){
        return ddMenu().findElements(By.tagName("a"));
    }

}
