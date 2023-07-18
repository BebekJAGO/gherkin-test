package org.automation.baseFunction;

import com.aventstack.extentreports.Status;
import org.automation.extendReports.ExtentReportFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AssertFunctionWeb implements AssertInterface {

    @Override
    public void assertCompareStringTrue(String strObject, String strCompare, String strDescription) {

        Assert.assertEquals(strObject, strCompare, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);

    }

    @Override
    public void assertCompareStringFalse(String strObject, String strCompare, String strDescription) {
        Assert.assertNotEquals(strObject, strCompare, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
    }

    @Override
    public void assertContainsString(String strObject, String strCompare, String strDescription) {
        if (strObject.contains(strCompare))
            ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
        else
            ExtentReportFactory.getInit().get().log(Status.FAIL, strDescription);
    }

    @Override
    public void assertNotContainsString(String strObject, String strCompare, String strDescription) {
        if (!strObject.contains(strCompare))
            ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
        else
            ExtentReportFactory.getInit().get().log(Status.FAIL, strDescription);
    }

    @Override
    public void assertObjectNull(Object strObject, String strDescription) {
        Assert.assertNull(strObject, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
    }

    @Override
    public void assertObjectNotNull(Object strObject, String strDescription) {
        Assert.assertNotNull(strObject, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
    }

    //TODO create class enum
    @Override
    public void assertWebElementExist(WebDriver webDriver, String strXpath, String strDescription) {
        if (webDriver.findElements(By.xpath(strXpath)).size() != 0) {
            ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
        } else {
            ExtentReportFactory.getInit().get().log(Status.FAIL, strDescription);
        }
    }

    public void assertWebElementExist(WebDriver webDriver, WebElement webElement, String strDescription) {
        String strXpath = BaseFunctionWeb.getXpathFromWebElement(webElement);

        if (webDriver.findElements(By.xpath(strXpath)).size() != 0) {
            ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
        } else {
            ExtentReportFactory.getInit().get().log(Status.FAIL, strDescription);
        }
    }

    @Override
    public void assertWebElementNotExist(WebElement webElement, String strDescription) {

    }

    @Override
    public void assertSame(Object objObject, Object objObjectCompare, String strDescription) {
        Assert.assertSame(objObject, objObjectCompare, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
    }

    @Override
    public void assertNotSame(Object objObject, Object objObjectCompare, String strDescription) {
        Assert.assertNotSame(objObject, objObjectCompare, strDescription);
        ExtentReportFactory.getInit().get().log(Status.PASS, strDescription);
    }

    private String convertException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
//TODO try to catch by AssertionError
//        try{
//            Assert.assertEquals(strObject,strCompare,strDescription);
//            ExtentReportFactory.getInit().get().log(Status.PASS,strDescription);
//        }catch (AssertionError  ex){
//            ExtentReportFactory.getInit().get().log(Status.FAIL,strDescription + "\n" + ex.getMessage());
//            throw  ex;
//        }