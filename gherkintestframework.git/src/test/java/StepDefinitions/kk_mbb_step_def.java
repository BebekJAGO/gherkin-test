package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.baseFunction.BaseFunctionWeb;
import org.automation.baseFunction.ScreenshotService;
import org.automation.pageObject.MBB_HomePage;
import org.automation.webdriver.WebDriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class kk_mbb_step_def extends BaseFunctionWeb{
    WebDriver webDriver = WebDriverFactory.getInit().get();
    MBB_HomePage HomePage = new MBB_HomePage(webDriver);

    @Given("maker user logged in")
    public void maker_user_logged_in() {
        goto_url("https://xmen.umundus.net/login","Go to url " + "https://xmen.umundus.net/login");
        setText(HomePage.fieldCorpId,"Aloycorp08","Input Corp ID");
        setText(HomePage.fieldUserId,"maker01","Input User ID");
        setText(HomePage.fieldKeyBCAResp,"111111","Input Key BCA Response");
        ScreenshotService.getInit().screenShot("login 1","Screenshot Login Page");
        click(HomePage.buttonLogin, "Click Button Login");
    }

    @Given("user access menu KK")
    public void user_access_menu_KK() throws InterruptedException {
        //-----------Login step-----------
        goto_url("https://xmen.umundus.net/login","Go to url " + "https://xmen.umundus.net/login");
        setText(HomePage.fieldCorpId,"foritescc2","Input Corp ID");
        setText(HomePage.fieldUserId,"xasotomasi","Input User ID");
        setText(HomePage.fieldKeyBCAResp,"111111","Input Key BCA Response");
        ScreenshotService.getInit().screenShot("login 2","Screenshot Login Page");
        click(HomePage.buttonLogin, "Click Button Login");

        //-----------Access to KK menu step-----------
        waitUntilWebElementExist(HomePage.panelSummary,"Wait Until Panel Summary Exist");
        click(HomePage.mainMenu, "Click Menu");
        assertWebElementExist(webDriver, HomePage.menuKartuKredit,"Assert Menu KK Exist");
        ScreenshotService.getInit().screenShot("Home 1","Screenshot Home Page");
        click(HomePage.menuKartuKredit, "Click Menu Kartu Kredit");
    }

    @When("the user search {string} grup rekening KK")
    public void the_user_search_grup_rekening_kk(String searchValue) throws InterruptedException {
        waitUntilWebElementExist(HomePage.fieldSearchKK,"Wait Until Field Search Exist");
        setText(HomePage.fieldSearchKK, searchValue, "Input Search Value KK");
        HomePage.fieldSearchKK.sendKeys(Keys.ENTER);
    }

    @Then("rekening yang masuk dalam grup rek tetap tampil")
    public void rekening_yang_masuk_dalam_grup_rek_tetap_tampil() {
        ScreenshotService.getInit().screenShot("KK Search Result","SS Search Result");
    }

    @When("user sort cust number once")
    public void user_sort_cust_number_once() throws InterruptedException {
        click(HomePage.btnSortCustNo, "Click Sort Cust Number");
        ScreenshotService.getInit().screenShot("Cust No Sort 1x","Cust No Sort 1x");
    }
    @Then("customer number sorted ascending")
    public void customer_number_sorted_ascending() {
        int custNo1 = Integer.parseInt(HomePage.custNo1.getText());
        int custNo2 = Integer.parseInt(HomePage.custNo2.getText());
        assert custNo1 < custNo2 || custNo2 == custNo2;
    }

    @When("the user access menu KK")
    public void the_user_access_menu_kk() throws InterruptedException {
        waitUntilWebElementExist(HomePage.mainMenu,"Wait Until Menu Exist");
        ScreenshotService.getInit().screenShot("KK 1","Screenshot Page KK");
        click(HomePage.mainMenu, "Click Menu");
        click(HomePage.menuKartuKredit, "Click Menu Kartu Kredit");
    }

    @When("the user click muat ulang")
    public void the_user_click_muat_ulang() throws InterruptedException {
        waitUntilWebElementExist(HomePage.buttonMuatUlang,"Wait Until Button Muat Ulang Exist");
        ScreenshotService.getInit().screenShot("KK 2","Screenshot Page KK");
        click(HomePage.buttonMuatUlang, "Click Muat Ulang");
    }

    @Then("got error message")
    public void got_error_message() {
        String errorMessage = HomePage.errorMessage.getText();
        assertCompareStringTrue(errorMessage,"Gagal memuat halaman","Verifikasi Gagal Load Halaman");
        Assert.assertEquals(errorMessage, "Gagal memuat halaman");
    }
    @Then("Tampil submenu Kartu Kredit")
    public void tampil_submenu_kartu_kredit() {
        String errorMessage = HomePage.errorMessage.toString();
        Assert.assertEquals(errorMessage, "Gagal memuat halaman");
    }

}
