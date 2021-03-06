//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;

import utility.Log;

public class SuiteSetUp {

    public static final String TEST_ENVIRONMENT = "https://54.68.108.124/webapp";

    public static final String QA_ENVIRONMENT_ie = "https://altarebuild.qa.satoriinteractive.com/webapp/";
    public static final String QA_ENVIRONMENT_all = "https://Alta:TennisQa1@altarebuild.qa.satoriinteractive.com/webapp/";

    public static final String UAT_ENVIRONMENT_ie = "https://altarebuild.uat.satoriinteractive.com/webapp/";
    public static final String UAT_ENVIRONMENT_all = "https://altatennis.org/";

    public static final String QA_UAT = "https://altatennis.org/";

    public static final String ADD_PLAYER_SUCCESS_LABEL = "successfully added to Roster. Please choose another member or Close";


    static String BROWSER = selectBrowser();
    static final String WEBDRIVERS_FOLDER = "lib/web_drivers";

    private static String selectBrowser() {
        String browser = System.getProperty("browser");
        if (browser != null)
            return browser;
        else {
            Log.warn("Browser option missing, using default instead");
            return "firefox";
        }
    }
}