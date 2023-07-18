package org.automation.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MBB_HomePage {
    @FindBy(xpath = "//input[@id=\"corp-id-input\"]")
    public WebElement fieldCorpId;

    @FindBy(xpath = "//input[@id=\"user-id-input\"]")
    public WebElement fieldUserId;

    @FindBy(xpath = "//input[@id=\"key-response-input\"]")
    public WebElement fieldKeyBCAResp;

    @FindBy(xpath = "//button[@id=\"login-button\"]")
    public WebElement buttonLogin;

    @FindBy(xpath = "//div[@class=\"mbb-main-expansion-panel-content\"]")
    public WebElement panelSummary;

    @FindBy(xpath = "//a[text()=\"Menu\"]")
    public WebElement mainMenu;

    @FindBy(xpath = "//div[@id=\"CREDIT_CARD\"]")
    public WebElement menuKartuKredit;

    @FindBy(xpath = "//button[text()=\"Muat Ulang\"]")
    public WebElement buttonMuatUlang;

    @FindBy(xpath = "//h2[@class=\"error-message\"]")
    public WebElement errorMessage;

    @FindBy(xpath = "//a[text()=\"Keluar\"]")
    public WebElement buttonKeluar;

    //PINDAHIN KE CLASS KK_PAGE
    @FindBy(xpath = "//input[@class=\"search info-summary\"]")
    public WebElement fieldSearchKK;

    @FindBy(xpath = "//div[@id=\"num-sort\"]")
    public WebElement btnSortCustNo;

    @FindBy(xpath = "//div[@id=\"show-detail\"][1]//div[2]")
    public WebElement custNo1;

    @FindBy(xpath = "//div[@id=\"show-detail\"][2]//div[2]")
    public WebElement custNo2;

    public MBB_HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
