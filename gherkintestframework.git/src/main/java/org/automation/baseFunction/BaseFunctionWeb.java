package org.automation.baseFunction;

import com.aventstack.extentreports.Status;
import org.automation.extendReports.ExtentReportFactory;
import org.automation.webdriver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseFunctionWeb extends AssertFunctionWeb implements BaseFunctionsInterface {

    @Override
    public void click(WebElement webElement, String strDescription) {
        try {
            webElement.click();
            ExtentReportFactory.getInit().get().log(Status.INFO, strDescription);

        } catch (Exception e) {
            ExtentReportFactory.getInit().get().log(Status.FAIL, convertException(e));
        }
    }

    @Override
    public void waitUntilWebElementExist(WebElement webElement, String strDescription) throws InterruptedException {
        String xpath = getXpathFromWebElement(webElement);
        int intObjectExist;
        Boolean bolObjectEnable;
        int intFlag = 0;

        do {
            intObjectExist = webElement.findElements(By.xpath(xpath)).size();
            if (intObjectExist > 0) {
                bolObjectEnable = webElement.findElement(By.xpath(xpath)).isEnabled();
            } else {
                bolObjectEnable = false;
            }
            if (intFlag == 10) {
                intObjectExist = 1;
                bolObjectEnable = true;
            }
            Thread.sleep(500);
            intFlag = intFlag + 1;
        } while (bolObjectEnable == false);
    }

    @Override
    public void waitUntilWebElementExistThenClick(WebElement webElement, String strDescription) {

    }

    @Override
    public void waitUntilWebElementExistThenSetText(WebElement webElement, String strDescription) {

    }

    @Override
    public void goto_url(String strURL, String strDescription) {
        try {
            WebDriverFactory.getInit().get().get(strURL);
            ExtentReportFactory.getInit().get().log(Status.INFO, strDescription);
        } catch (Exception e) {
            ExtentReportFactory.getInit().get().log(Status.FAIL, convertException(e));
        }
    }

    @Override
    public void setText(WebElement webElement, String strSendKeys, String strDescription) {
        try {
            webElement.clear();
            webElement.sendKeys(strSendKeys);
            ExtentReportFactory.getInit().get().log(Status.INFO, strDescription);
        } catch (Exception e) {
            ExtentReportFactory.getInit().get().log(Status.FAIL, convertException(e));
        }

    }

    @Override
    public void changeIframe(WebDriver webDriver, WebElement webElement, String strDescription) {
        try {
            webDriver.switchTo().frame(webElement);
            ExtentReportFactory.getInit().get().log(Status.INFO, strDescription);
        } catch (Exception ex) {
            ExtentReportFactory.getInit().get().log(Status.FAIL, convertException(ex));
        }

    }

    @Override
    public void changeIframeDefault(WebDriver webDriver, String strDescription) {
        try {
            webDriver.switchTo().defaultContent();
            ExtentReportFactory.getInit().get().log(Status.INFO, strDescription);
        } catch (Exception ex) {
            ExtentReportFactory.getInit().get().log(Status.FAIL, convertException(ex));
        }
    }

    @Override
    public void waitForSeconds(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static String getXpathFromWebElement(WebElement webElement){
        String[] temp = webElement.toString().split("xpath: ");
        String xpath = temp[1];
        xpath = xpath.substring(0,xpath.length()-1);

        return xpath;
    }
}
