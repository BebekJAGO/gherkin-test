package org.automation.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.automation.baseFunction.ScreenshotService;
import org.automation.extendReports.ExtentReportFactory;
import org.automation.webdriver.WebDriverFactory;
import org.testng.*;
import org.testng.annotations.BeforeTest;

public class ListenerTestNG implements ITestListener, IInvokedMethodListener {
    static ExtentReports extentReports;
    ExtentTest extentTest;

//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//        System.out.println("s");
//        IInvokedMethodListener.super.afterInvocation(method, testResult, context);
//    }

    @Override
    public void onTestStart(ITestResult result) {
        WebDriverFactory.getInit().setup();
        extentTest = extentReports.createTest(result.getParameters()[0].toString());
        ExtentReportFactory.getInit().set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        result.getParameters()[0] get Scenario Name
        ExtentReportFactory.getInit().get().log(Status.PASS, result.getName() + " Test Passed");
        WebDriverFactory.getInit().tearDown();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotService.getInit().screenShot("Failed Screen","Failed Screen");
        ExtentReportFactory.getInit().get().log(Status.FAIL,
                result.getName() + " Test Failed. </br>" +
                "Error : "+ result.getThrowable().getMessage());
        WebDriverFactory.getInit().tearDown();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportFactory.getInit().get().log(Status.SKIP, result.getName() + " Test Skipped");
        WebDriverFactory.getInit().tearDown();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReportFactory.getInit().setupExtentReports();

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }


}
