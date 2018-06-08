package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TransactionManagerPage {

    //select2-searchMerchant-results
    //select2-results__option loading-results

    By byTxtLocation = By.className("select2-search__field");
    By byLoadingResults = By.className("loading-results");
    By byHighlightedResults = By.cssSelector("li[class='select2-results__option select2-results__option--highlighted']");

    @FindBy(how = How.NAME, using ="select-zone")
    private WebElement drpCategory;

    @FindBy(how = How.ID, using = "reason")
    private WebElement txtReason;

    public WebElement getTxtReason() {
        return txtReason;
    }

    private WebDriver _driver;

    public TransactionManagerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getTxtLocation() {
        return _driver.findElement(byTxtLocation);
    }

    public By getByTxtLocation() {
        return byTxtLocation;
    }

    public By getByLoadingResults() {
        return byLoadingResults;
    }

    public By byHighlightedResults() {
        return byHighlightedResults;
    }

    public WebElement HighlightedResults(){
        return _driver.findElement(byHighlightedResults);
    }

    public WebElement getDrpCategory() {
        return drpCategory;
    }
}
