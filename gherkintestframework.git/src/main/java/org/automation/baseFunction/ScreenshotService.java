package org.automation.baseFunction;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import freemarker.template.SimpleDate;
import org.apache.commons.io.FileUtils;
import org.automation.MyConfig;
import org.automation.extendReports.ExtentReportFactory;
import org.automation.webdriver.WebDriverFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ScreenshotService {
    private static volatile ScreenshotService instance = null;
    ThreadLocal<Integer> trdIntCounterData = new ThreadLocal<>();

    private ScreenshotService() {
    }

    public static ScreenshotService getInit() {
        if (instance == null) {
            synchronized (ScreenshotService.class) {
                if (instance == null) {
                    instance = new ScreenshotService();
                }
            }
        }
        return instance;
    }

    public synchronized Integer getCounterData() {
        if(trdIntCounterData.get() == null)
            setCounterdata(1);

        return trdIntCounterData.get();
    }

    public synchronized void setCounterdata(Integer intCounterData) {
        trdIntCounterData.set(intCounterData);
    }

    public synchronized String screenShot(String strFileName,String strDescription) {
        String strDefaultPath = System.getProperty("user.dir") + "\\Screenshot\\";
        initFolderDefault(strDefaultPath);
//        strFileName = strFileName + "_" + getCounterData();
        strFileName = MyConfig.sdf.format(new Date());
        String strFullPath = strDefaultPath + strFileName + ".png";

        Shutterbug.shootPage(WebDriverFactory.getInit().get(), Capture.FULL, 500).withName(strFileName).save(strDefaultPath);

        putIntoReport(strFullPath,strDescription);

//        setCounterdata(getCounterData() + 1);
        return strDefaultPath;

    }

    private void putIntoReport(String strFullPath,String strDescription){
        String encodedString = changeImageToBase64(strFullPath);
        ExtentReportFactory.getInit().get().log(Status.INFO, strDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(encodedString).build());

        File fleImage = new File(strFullPath);
        fleImage.delete();
    }
    private  String changeImageToBase64(String strPathScreenShot) {
        String encodedString = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(strPathScreenShot));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    private void initFolderDefault(String strPathFolderResultTesting){
        File file = new File(strPathFolderResultTesting);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
