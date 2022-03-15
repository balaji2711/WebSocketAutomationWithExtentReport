package report;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentManager {
    private static ExtentReports extent;
    static String datetimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()).replaceAll(":", "-");

    public synchronized static ExtentReports getReporter()
    {
        if(extent == null)
        {
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"\\ExtentReports\\TestReport_"+datetimestamp+".html", true);
            extent.loadConfig(new File(workingDir+"\\extent-config.xml"));
            extent.addSystemInfo("Application","WebSocket API Automation");
            extent.addSystemInfo("Author","Balaji G");
        }
        return extent;
    }
}