package base;

import utility.Log;
import common.SuiteSetUp;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BaseUtil {

    public WebDriver driver;

    public BaseUtil() {
        DOMConfigurator.configure("log4j.xml");
    }

    public void GrabScreenShot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("target/screenshots/screenshot-" + System.currentTimeMillis() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void NavigateToPage(String environment, boolean isIe) {
        try {
            switch (environment.toLowerCase()) {
                case "qa":
                    driver.navigate().to((isIe) ? SuiteSetUp.QA_ENVIRONMENT_ie : SuiteSetUp.QA_ENVIRONMENT_all);
                    Log.info("Navigating to: " + ((isIe) ? SuiteSetUp.QA_ENVIRONMENT_ie : SuiteSetUp.QA_ENVIRONMENT_all));
                    break;
                case "test":
                    driver.navigate().to(SuiteSetUp.TEST_ENVIRONMENT);
                    Log.info("Navigating to: " + SuiteSetUp.TEST_ENVIRONMENT);
                    break;
                case "trio":
                    driver.navigate().to(SuiteSetUp.QA_TRIO);
                    Log.info("Navigating to: " + SuiteSetUp.QA_TRIO);
                    break;
                case "uat":
                    driver.navigate().to(SuiteSetUp.UAT_ENVIRONMENT_all);
                    Log.info("Navigating to: " + SuiteSetUp.UAT_ENVIRONMENT_all);
                    break;
            }
            if (isIe)
                driver.navigate().to("javascript:document.getElementById('overridelink').click();");

        } catch (NullPointerException e) {
            Log.error("Environment System property missing... Exiting");
            System.exit(-1);
        }
    }

}
