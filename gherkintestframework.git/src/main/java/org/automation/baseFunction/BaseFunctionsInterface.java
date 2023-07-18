package org.automation.baseFunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface BaseFunctionsInterface {
    void click(WebElement webElement, String strDescription);

    void waitUntilWebElementExist(WebElement webElement, String strDescription) throws InterruptedException;

    void waitUntilWebElementExistThenClick(WebElement webElement, String strDescription);

    void waitUntilWebElementExistThenSetText(WebElement webElement, String strDescription);

    void goto_url(String strURL, String strDescription);

    void setText(WebElement webElement, String strSendKeys, String strDescription);

    void changeIframe(WebDriver webDriver, WebElement webElement, String strDescription);

    void changeIframeDefault(WebDriver webDriver, String strDescription);

    void waitForSeconds(int waitTime);
}
