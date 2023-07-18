package org.automation.extendReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Kevin Hertanto
 */
public class ExtentReportFactory {
    private static volatile ExtentReportFactory instance = null;

    private ExtentReportFactory() {

    }

    public static ExtentReportFactory getInit() {
        if (instance == null) {
            synchronized (ExtentReportFactory.class) {
                if (instance == null) {
                    instance = new ExtentReportFactory();
                }
            }
        }
        return instance;
    }

    ThreadLocal<ExtentTest> thrExtentTest = new ThreadLocal<>();

    public synchronized ExtentTest get() {
        return thrExtentTest.get();
    }

    public void set(ExtentTest extentTest) {
        thrExtentTest.set(extentTest);
    }

    public void remove() {
        thrExtentTest.remove();
    }

    /**
     *  Init ExtentReport for report automation
     */
    public ExtentReports setupExtentReports() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("ReportUIAutomation.html");

        ExtentReports extentReports = new ExtentReports();

        //Put into ExtentReports
        extentReports.attachReporter(extentSparkReporter);

        //Setting config the spark
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("UI Automation Testing Report");
        extentSparkReporter.config().setReportName("UI Automation Testing Report");

        //Setting config ExtentReport
        extentReports.setSystemInfo("Execute by ", "Kevin Hertanto");
        extentReports.setSystemInfo("Execute on OS ", "Web Framework Automation");

        return extentReports;
    }


}
