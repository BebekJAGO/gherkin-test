package org.automation.baseFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface AssertInterface {
    void assertCompareStringTrue(String strObject,String strCompare, String strDescription);
    void assertCompareStringFalse(String strObject,String strCompare, String strDescription);
    void assertContainsString(String strObject,String strCompare, String strDescription);
    void assertNotContainsString(String strObject,String strCompare, String strDescription);
    void assertObjectNull(Object strObject, String strDescription);
    void assertObjectNotNull(Object strObject, String strDescription);
    void assertWebElementExist(WebDriver webDriver, String strBy,String strDescription);
    void assertWebElementNotExist(WebElement webElement,String strDescription);
    void assertSame(Object strObject,Object strObjectCompare,String strDescription);
    void assertNotSame(Object strObject,Object strObjectCompare,String strDescription);

}
