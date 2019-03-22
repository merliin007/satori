/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 11:15 AM
 */
package pages.singup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage extends SingUpCommon {

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Region 1
    @FindBy(how = How.ID, using = "txtFirstName")
    private WebElement txtFirstName;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtLastName")
    private WebElement txtLastName;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_ddlGender")
    private WebElement dropGender;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtBirthDate")
    private WebElement txtBirthDate;
    @FindBy(how = How.CSS, using = "#ctl00_CPHolder_dtv_tbc_tpProfile_editableContents > div:nth-child(4) > div > span > label")
    private WebElement chkWheelChair;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtHomePhone")
    private WebElement txtHomePhone;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtMobilePhone")
    private WebElement txtMobilePhone;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtWorkPhone")
    private WebElement txtWorkPhone;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtEmail")
    private WebElement txtEmail;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtStreet")
    private WebElement txtStreet;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtCity")
    private WebElement txtCity;
    @FindBy(how = How.ID, using ="ctl00_CPHolder_dtv_tbc_tpProfile_txtSuite")
    private WebElement txtApt;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_ddlState")
    private WebElement dropState;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtZip")
    private WebElement txtZip;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_txtZip2")
    private WebElement txtZipExt;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpProfile_ddlCounty")
    private WebElement dropCounty;
    // Region 2
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpLogin_txtUsername")
    private WebElement txtUserName;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpLogin_btnConfirmUsername")
    private WebElement btnConfirmUserName;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpLogin_lblUsernameValid")
    private WebElement lblUserNameValid;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpLogin_txtNewPassword")
    private WebElement txtPassword;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_dtv_tbc_tpLogin_txtConfirmPassword")
    private WebElement txtPasswordConfirmation;
    // Region 3
    @FindBy(how = How.ID, using ="ctl00_CPHolder_dtv_tbc_tpRankings_chkRankings")
    private WebElement grpRankings;

    public WebElement getTxtFirstName() {
        return txtFirstName;
    }

    public WebElement getTxtLastName() {
        return txtLastName;
    }

    public WebElement getDropGender() {
        return dropGender;
    }

    public WebElement getTxtBirthDate() {
        return txtBirthDate;
    }

    public WebElement getChkWheelChair() {
        return chkWheelChair;
    }

    public WebElement getTxtHomePhone() {
        return txtHomePhone;
    }

    public WebElement getTxtMobilePhone() {
        return txtMobilePhone;
    }

    public WebElement getTxtWorkPhone() {
        return txtWorkPhone;
    }

    public WebElement getTxtEmail() {
        return txtEmail;
    }

    public WebElement getTxtStreet() {
        return txtStreet;
    }

    public WebElement getTxtApt() {
        return txtApt;
    }

    public WebElement getTxtCity() {
        return txtCity;
    }

    public WebElement getDropState() {
        return dropState;
    }

    public WebElement getTxtZip() {
        return txtZip;
    }

    public WebElement getTxtZipExt() {
        return txtZipExt;
    }

    public WebElement getDropCounty() {
        return dropCounty;
    }

    public WebElement getTxtUserName() {
        return txtUserName;
    }

    public WebElement getLblUserNameValid() {
        return lblUserNameValid;
    }

    public WebElement getBtnConfirmUserName() {
        return btnConfirmUserName;
    }

    public WebElement getTxtPassword() {
        return txtPassword;
    }

    public WebElement getTxtPasswordConfirmation() {
        return txtPasswordConfirmation;
    }

    public WebElement getGrpRankings() {
        return grpRankings;
    }

    public WebElement getRankingCheckbox(String opt){
        List<WebElement> chkList = grpRankings.findElements(By.tagName("td"));
        for(WebElement chk: chkList){
            if(chk.findElement(By.tagName("label")).getText().toLowerCase().contains(opt.toLowerCase().trim()))
                return chk;
        }
        return null;
    }
}
