package base;

import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import utility.Log;

import java.io.File;

public class RunScript implements Runnable{

    private String browser;

    public RunScript(String browser) {
        this.browser = browser;
    }

    public void run(){
        if (!browser.equals("ie"))
            return;
        try {
            Log.info("Running autoit for: " + browser);
            File jcob = new File("lib/jacob-1.14.3-x64.dll");
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, jcob.getAbsolutePath());

            String windowName = "Seguridad de Windows";
            AutoItX autoIt = new AutoItX();
            autoIt.winActivate(windowName);
            autoIt.winWaitActive(windowName);
            if (autoIt.winExists(windowName)) {
                autoIt.winWaitActive(windowName);
                autoIt.send("{ENTER}", false);
                /*autoIt.send("Alta{TAB}", false);
                autoIt.send("TennisQa1{ENTER}", false);*/
            }
        } catch (Exception e) {
            Log.error("There was a problem with Authentication\n" + e.getMessage());
        }
    }
}
