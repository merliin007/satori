/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 4:08 PM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class MemberProfile extends Pages {
    public MemberProfile(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public int getPageActionIndex(String action) {
        return 0;
    }

    @Override
    public int getIndexForHeader(String columnName) {
        return 0;
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return null;
    }


}
