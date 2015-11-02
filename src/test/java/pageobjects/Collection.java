package pageobjects;

import enums.CollectionSharingMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.Run;
import utils.Service;

public class Collection {
    //creation
    final static String COLLECTION_ADD_XPATH = "html/body/div[2]/div[2]/div[1]/div[1]";
    final static String COLLECTION_ENTER_NAME_XPATH = "html/body/div[3]/div[2]/div/div[1]/div[1]/div[1]/div[2]/input";
    final static String COLLECTION_SHARE_WITHORG_XPATH = "html/body/div[3]/div[2]/div/div[1]/div[1]/div[3]/div[6]/div/img";
    final static String COLLECTION_DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";
    final static String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[6]";
    //remove
    final static String COLLLECTION_OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
    final static String COLLECTION_DELETE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[1]";
    //archive
    final static String COLLLECTION_ARCHIVE_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[2]";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionCreation(WebDriver driver, int number, CollectionSharingMode pageSharingMode, boolean isLogged) throws InterruptedException {
        String name;
        String nameTemplate;
        if (pageSharingMode == CollectionSharingMode.PUBLIC) nameTemplate = Run.currentBrowser+"Collection.Public";
        else nameTemplate = Run.currentBrowser+"Collection.Private";
        for (int i = 1; i <= number; i++) {
            name = nameTemplate + " " + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + name + " was created:");
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(name);
            if (pageSharingMode == CollectionSharingMode.PUBLIC) driver.findElement(By.xpath(COLLECTION_SHARE_WITHORG_XPATH)).click();
            driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRemoving(WebDriver driver, int number, boolean isLogged) throws InterruptedException {
        String name;
        for (int i = 1; i <= number; i++) {
            name = "Collection" + Integer.toString(i);
            Thread.sleep(500);
            driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(COLLECTION_DELETE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(COLLECTION_DELETE_BUTTON_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + " Collection " + i + " was removed:");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionArchiving(WebDriver driver, int number, boolean isLogged) {
        String name;
        for (int i = 1; i <= number; i++) {
            name = Run.currentBrowser+"CollectionArch" + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + " CollectionArch " + i + " was created:");
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(name);
            driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
            driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(COLLLECTION_ARCHIVE_XPATH)).click();
            driver.findElement(By.xpath(COLLLECTION_ARCHIVE_XPATH)).click();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRenameCurrent(WebDriver driver, String name) {
        driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(name);
        driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------

}
