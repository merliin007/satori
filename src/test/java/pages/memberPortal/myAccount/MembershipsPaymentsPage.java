/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 12:53 PM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class MembershipsPaymentsPage extends Pages {

    public MembershipsPaymentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_profileMembership_hlUpgradeCurrentMembership")
    private WebElement btnCurrentYearMembership;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_profileMembership_hlUpgradeNextMembership")
    private WebElement btnNextYearMembership;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_profileMembership_profilePurchaseList_tdvPurchase_pnlGrid")
    private WebElement tblTableResults;

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement deleteModal;

    private By toastrBy = By.id("toast-container");

    public WebElement getBtnCurrentYearMembership() {
        return btnCurrentYearMembership;
    }

    public WebElement getBtnNextYearMembership() {
        return btnNextYearMembership;
    }

    public WebElement getBtnByYear(String period) {
        switch (period.toLowerCase()) {
            case "current":
                return getBtnCurrentYearMembership();
            case "next":
                return getBtnNextYearMembership();
            default:
                return null;
        }
    }

    @Override
    public int getPageActionIndex(String action) {
        switch (action.toLowerCase()) {
            case "select":
                return 0;
            case "refund":
                return 1;
            case "delete":
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public int getIndexForHeader(String columnName) {
        return 0;
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return null;
    }

    @Override
    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_profileMembership_profilePurchaseList_tdvPurchase_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public By getToastrBy() {
        return toastrBy;
    }

    public String getToastrMesage() {
        return _driver.findElement(getToastrBy()).findElement(By.className("toast-message")).getText();
    }

    public WebElement getDeleteModal() {
        return deleteModal;
    }

    public WebElement deleteModalElements(String element) {
        switch (element.toLowerCase()) {
            case "delete":
                return deleteModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_profileMembership_profilePaymentList_tdvPayments_lnkConfirm"));
            case "cancel":
                return deleteModal.findElement(By.className("btn-outline-danger"));
            default:
                return null;
        }
    }
}
