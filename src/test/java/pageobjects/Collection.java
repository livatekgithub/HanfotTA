package pageobjects;

import enums.CollectionSharingMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.Run;
import utils.Service;

public class Collection {
    final static String COLLECTION_ADD_XPATH = "html/body/div[2]/div[2]/div[1]/div[1]";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionCreation(WebDriver driver, int number, CollectionSharingMode pageSharingMode, boolean isLogged) throws InterruptedException {


        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[6]";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
//        final String SHARE_WITHORG_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[3]/div[5]/div/img";
        final String SHARE_WITHORG_XPATH = "html/body/div[3]/div[2]/div/div[1]/div[1]/div[3]/div[6]/div/img";

        String name;
        String nameTemplate;

        if (pageSharingMode == CollectionSharingMode.PUBLIC) nameTemplate = Run.currentBrowser+"Collection.Public";
        else nameTemplate = Run.currentBrowser+"Collection.Private";

        for (int i = 1; i <= number; i++) {
            name = nameTemplate + " " + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + name + " was created:");
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
            if (pageSharingMode == CollectionSharingMode.PUBLIC) driver.findElement(By.xpath(SHARE_WITHORG_XPATH)).click();
            driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRemoving(WebDriver driver, int number, boolean isLogged) throws InterruptedException {

        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String DELETE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[1]";
        String name;

        for (int i = 1; i <= number; i++) {
            name = "Collection" + Integer.toString(i);
            Thread.sleep(500);
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + " Collection " + i + " was removed:");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionArchiving(WebDriver driver, int number, boolean isLogged) {
        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[6]";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        final String TYPE_COLLECTION_NAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String PAGE_ARCHIVE_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[2]";

        String name;
        for (int i = 1; i <= number; i++) {
            name = Run.currentBrowser+"CollectionArch" + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + " CollectionArch " + i + " was created:");
            driver.findElement(By.xpath(TYPE_COLLECTION_NAME_XPATH)).clear();
            driver.findElement(By.xpath(TYPE_COLLECTION_NAME_XPATH)).sendKeys(name);
//            driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click(); xpath Done-button
            driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRenameCurrent(WebDriver driver, String name) {
        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
        driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
        driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
        driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
    }
}
