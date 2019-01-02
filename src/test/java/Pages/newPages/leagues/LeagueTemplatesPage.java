/*
 * Created by Miguel Angel Aguilar Cuevas
 * 02/01/2019 at 10:13 AM
 */
package pages.newPages.leagues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LeagueTemplatesPage {

    public LeagueTemplatesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNew;

    private By toastrBy = By.id("toast-container");

    public By getToastrBy() {
        return toastrBy;
    }
    public WebElement getBtnNew() {
        return btnNew;
    }


}
