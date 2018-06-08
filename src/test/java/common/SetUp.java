//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;


import autoitx4java.AutoItX;
import base.BaseUtil;
import base.RunScript;
import com.jacob.com.LibraryLoader;
import com.sun.istack.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utility.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class SetUp extends BaseUtil {

    private BaseUtil base;

    public SetUp(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) throws Exception {
        File driver_exe = null;
        boolean isIe = false;
        switch (SuiteSetUp.BROWSER.toLowerCase()) {
            case "firefox":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "geckodriver64.exe");
                System.setProperty("webdriver.gecko.driver", driver_exe.getAbsolutePath());
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.setCapability("browser.private.browsing.autostart", true);
                base.driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", driver_exe.getAbsolutePath());
                ChromeOptions options = new ChromeOptions();
                //options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                base.driver = new ChromeDriver(options);
                break;
            case "ie":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "IEDriverServer32.exe");
                System.setProperty("webdriver.ie.driver", driver_exe.getAbsolutePath());
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                //ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                //ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                base.driver = new InternetExplorerDriver(ieOptions);
                isIe = true;
                break;
            default:
                System.out.println("Browser not defined");
                System.exit(-1);
        }

        base.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        base.driver.manage().window().maximize();

        /*executeAutoItNeeded(SuiteSetUp.BROWSER.toLowerCase());*/
        Thread t = new Thread( new RunScript(SuiteSetUp.BROWSER.toLowerCase()));
        t.start();

        this.base.NavigateToPage(System.getProperty("environmentName"), isIe);
        Log.startTestCase(scenario.getName());
    }

    private void executeAutoItNeeded(@NotNull String browser) {
        if (!browser.equals("ie"))
            return;
        String executable = "IEAuthentication.exe";
        try {
            File file = new File("lib/", executable);
            Runtime.getRuntime().exec(file.getAbsolutePath());
            Log.info("Executing autoit file: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.info("There's a problem running autoit file\n" + e.getMessage());
        }
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed())
            Log.endTestCase(scenario.getName() + "\tFAILED");
        else
            Log.endTestCase(scenario.getName() + "\tPASSED");
        new TearDown(base);
    }

}
