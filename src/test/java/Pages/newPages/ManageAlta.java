package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ManageAlta {

    By by = By.id("navbarDropdown");
    private WebDriver _driver;

    public ManageAlta(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDrpNavbar() {
        return _driver.findElement(by);
    }
    public By getDrpNavbarBy(){
        return by;
    }
}
