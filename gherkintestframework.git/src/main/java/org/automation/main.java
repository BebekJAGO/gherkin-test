package org.automation;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe ");
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
