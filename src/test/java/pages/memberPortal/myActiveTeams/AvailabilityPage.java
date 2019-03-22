/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 10:44 AM
 */
package pages.memberPortal.myActiveTeams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityPage {

    private WebDriver _driver;

    public AvailabilityPage(WebDriver driver) {
        _driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "playoffs__table")
    private WebElement tblPlayoffs;

    @FindBy(how = How.CLASS_NAME, using = "btn-primary")
    private WebElement btnBack;

    private By toastrBy = By.id("toast-container");

    public WebElement getTblPlayoffs() {
        return tblPlayoffs;
    }

    public WebElement getBtnBack() {
        return btnBack;
    }

    public By getToastrBy() {
        return toastrBy;
    }
    public  WebElement getToastr(){
        return _driver.findElement(getToastrBy());
    }

    public List<WebElement> getColumnElementsOnWeek(int column) throws Exception{
        List<WebElement> tmp = getTblPlayoffs().findElements(By.tagName("tr"));
        List<WebElement> columnDrops = new ArrayList<>();
        for(int i = 1; i < tmp.size(); i++){
            List<WebElement> cells = tmp.get(i).findElements(By.className("text-center"));
            columnDrops.add(cells.get(column -1).findElement(By.className("dropdown")));
        }
        return columnDrops;
    }


}
