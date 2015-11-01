package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Service {
    public long timeStart;
    public long timeFinish;

    public void startCount() {
        timeStart = System.currentTimeMillis();
    }

    public void stopCount() {
        timeFinish = System.currentTimeMillis();
    }

    public double getTimeDuration() {
        return (1.00) * ((timeFinish - timeStart) / 60000);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static String nowTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static String nowTimeForFileName() {
        String dateCurrent = nowTime();
        dateCurrent = dateCurrent.replace("/", "");
        dateCurrent = dateCurrent.replace(':', '.');
        dateCurrent = dateCurrent.replace(" ", "_");
        return dateCurrent;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        System.out.println("*** Save Screenshot ***");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(AccessData.SCREENSHOTS_PATH + fileName));
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
