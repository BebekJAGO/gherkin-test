package org.automation.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * WebDriverFactory, all services web-driver in this class use Singleton design pattern.
 */
public class WebDriverFactory {
    private static volatile WebDriverFactory instance = null;
    private Properties properties;

    /**
     * For init this class, read config and setup web-driver.
     */
    private WebDriverFactory() {
        readConfig();
    }

    public static  WebDriverFactory getInit() {
        if (instance == null) {
            synchronized (WebDriverFactory.class) {
                if (instance == null) {
                    instance = new WebDriverFactory();
                }
            }
        }
        return instance;
    }

    ThreadLocal<WebDriver> thrWebDriver = new ThreadLocal<>();

    public synchronized WebDriver get() {
        if (thrWebDriver == null)
            setup();
        return thrWebDriver.get();
    }

    public void set(WebDriver webDriver) {
        thrWebDriver.set(webDriver);
    }

    public void remove() {
        thrWebDriver.remove();
    }

    /**
     * @param strURL url which is will execute by web driver
     */
    public void getAddress(String strURL) {
        thrWebDriver.get().get(strURL);
    }

    /**
     * method setup will read properties for config web driver
     */
    public void setup() {
        String strBrowser = properties.getProperty("Browser");
        String strSource = properties.getProperty("Source");
        WebDriver webDriver = null;
        useLocalorDefault(strSource,strBrowser);


        switch (strBrowser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");

                if (properties.getProperty("Maximize").equalsIgnoreCase("true"))
                    chromeOptions.addArguments("--start-maximized");

                if (properties.getProperty("Headless").equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
                    chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
                    chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
                    chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
                }

                if (properties.getProperty("Incognito").equalsIgnoreCase("true"))
                    chromeOptions.addArguments("--incognito");

                webDriver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;

            case "opera":
                //noinspection deprecation
                webDriver = new OperaDriver();
                break;
            case "ie":
                webDriver = new InternetExplorerDriver();
                break;
        }

        Long ObjectWaitTime = Long.parseLong(properties.getProperty("WaitTime"));
        webDriver.manage().timeouts().implicitlyWait(ObjectWaitTime, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(ObjectWaitTime * 3, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(ObjectWaitTime * 3, TimeUnit.SECONDS);

        set(webDriver);
    }

    /**
     * Shutdown web-drivers
     */
    public void tearDown() {
        thrWebDriver.get().quit();
        remove();

    }

    /**
     * Read driverConfig
     */
    private void readConfig() {
        try {
            FileReader reader = new FileReader("src\\main\\resources\\driverConfig.properties");
            properties = new Properties();
            properties.load(reader);

        } catch (IOException e) {
            System.out.println("File not Found");
        }
    }

    /**
     * @param strSource Source use local driver or webdrivermanager, because bca use proxy, sometimes we can't connect chromedriver
     * @param strBrowser What browser for the setup
     */
    private void useLocalorDefault(String strSource,String strBrowser){
        if(strSource.equalsIgnoreCase("driverLocal")){
            switch (strBrowser) {
                case "chrome":
                    String strdriverLocal = properties.getProperty("DriverLocal");
                    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driverLocal\\"+strdriverLocal);
                    break;
                case "firefox":
                    break;
                case "opera":
                    break;
                case "ie":
                    break;
            }
         }else{
            switch (strBrowser) {
                case "chrome":
                WebDriverManager.chromedriver().setup();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    break;
            }
        }
    }


}
