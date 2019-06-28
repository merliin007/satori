/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - jun 2019
 **/

package pages.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GenericPage {

    public GenericPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "main-frame-error")
    private WebElement divError;

    public WebElement getDivError() {
        return divError;
    }
}
