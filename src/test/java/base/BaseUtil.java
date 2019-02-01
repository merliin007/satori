package base;

import org.openqa.selenium.*;
import utility.Log;
import common.SuiteSetUp;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;

import java.awt.*;
import java.awt.event.KeyEvent;
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
                case "uat":
                    driver.navigate().to(SuiteSetUp.UAT_ENVIRONMENT_all);
                    Log.info("Navigating to: " + SuiteSetUp.UAT_ENVIRONMENT_all);
                    break;
            }
            if (isIe)
                driver.navigate().to("javascript:document.getElementById('overridelink').click();");
            zoomInOut(-2);

        } catch (NullPointerException e) {
            Log.error("Environment System property missing... Exiting");
            System.exit(-1);
        }

    }

    private void zoomInOut(int z) {
        try {
            if (System.getProperty("autoZoom").equals("false"))
                return;
            Robot robot = new Robot();
            Log.info("AutoZooming: " + z);
            if (z < 0)
                while (z < 0) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    z++;
                }
            else
                while (z > 0) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    z--;
                }

        } catch (AWTException e) {
            Log.error(e.getMessage());
        } catch (NullPointerException npe) {
            Log.info("No Zoom requested");
        }
    }

}
