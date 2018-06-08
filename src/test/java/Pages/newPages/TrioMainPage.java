package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class TrioMainPage {



    @FindBy(how = How.ID, using = "side-nav")
    private WebElement menu;

    private WebDriver _driver;

    public TrioMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }



    public WebElement MenuOption(){
        return _driver.findElement(By.xpath("//*[@id=\"side-nav\"]/li[8]/a"));
    }
}
