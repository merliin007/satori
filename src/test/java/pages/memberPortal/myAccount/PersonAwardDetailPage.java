/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 11:58 AM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PersonAwardDetailPage {
    public PersonAwardDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_ddlAwardEarned")
    private WebElement ddlAward;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_ddlGiftEarned")
    private WebElement ddlGift;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_txtAwardYear")
    private WebElement txtAwardYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_txtDeniedDate")
    private WebElement txtDeniedDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_txtReceivedDate")
    private WebElement txtReceivedDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc")
    private WebElement tabContainer;

    public WebElement getDdlAward() {
        return ddlAward;
    }

    public WebElement getDdlGift() {
        return ddlGift;
    }

    public WebElement getTxtAwardYear() {
        return txtAwardYear;
    }

    public WebElement getTxtDeniedDate() {
        return txtDeniedDate;
    }

    public WebElement getTxtReceivedDate() {
        return txtReceivedDate;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getTabContainer() {
        return tabContainer.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_header"));
    }

    public WebElement getTab(String tab) {
        switch (tab.toLowerCase()) {
            case "errors":
                return tabContainer.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpErrors_tab"));
            case "information":
                return tabContainer.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPersonAward_tab"));
            default:
                return null;
        }
    }
}
