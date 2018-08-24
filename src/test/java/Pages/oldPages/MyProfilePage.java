package pages.oldPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage {
    private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_lnkMembership")
    private WebElement lnkMembershipPayments;

    public MyProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public void ClickLinkOnMyProfile(String lnkName){
        switch(lnkName.toLowerCase()){
            case "membership & payments":
                lnkMembershipPayments.click();
                break;
        }
    }
}
