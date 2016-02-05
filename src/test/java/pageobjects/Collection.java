package pageobjects;

import enums.CollectionSharingMode;
import enums.WidgetColor;
import enums.WidgetState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.Run;
import utils.Service;

public class Collection {
    //creation
    final static String COLLECTION_ADD_XPATH = "html/body/div[3]/div[2]/div[1]/div[1]";
    final static String COLLECTION_ENTER_NAME_XPATH = "html/body/div[4]/div/div[1]/div[1]/div[1]/div[1]/div[2]/input";
    final static String COLLECTION_CONFIGURE_PUBLIC_XPATH = "html/body/div[4]/div/div[1]/div[1]/div[1]/div[3]/div[6]/div[2]/div[1]/div[2]";
    final static String COLLECTION_SHARE_WITH_ORG_XPATH = "html/body/div[4]/div/div[1]/div[1]/div[1]/div[3]/div[6]/div[2]/div[2]/div[2]/div/div[1]";
    final static String COLLECTION_SHARE_WITH_EVERYONE_XPATH = "html/body/div[4]/div/div[1]/div[1]/div[1]/div[3]/div[6]/div[2]/div[2]/div[3]/div/div[1]";
    final static String COLLECTION_DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";
    //remove
    final static String COLLLECTION_OPEN_PAGEMENU_XPATH = "html/body/div[3]/div[2]/div[2]/div[1]/div/div/div[1]";
    final static String COLLECTION_GETNAME_XPATH="html/body/div[3]/div[2]/div[2]/div/div/div/div[1]";
    final static String COLLECTION_DELETE_BUTTON_XPATH = "html/body/div[4]/div/div/div[1]/div[2]/div/div[1]";
    //archive
    final static String COLLLECTION_ARCHIVE_XPATH = "html/body/div[4]/div/div/div[1]/div[2]/div/div[2]";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    Collection(int number){
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionCreation(WebDriver driver, int number,CollectionSharingMode pageSharingMode, boolean isLogged) throws InterruptedException {
        String name;
        String nameTemplate;
        if (pageSharingMode == CollectionSharingMode.PUBLIC) nameTemplate = Run.currentBrowser+
                Service.nowTimeForObjectName()+".Collection.Public";
        else if (pageSharingMode == CollectionSharingMode.EVERYONE) nameTemplate = Run.currentBrowser+
                Service.nowTimeForObjectName()+".Collection.EveryOne";
        else nameTemplate = Run.currentBrowser+
                Service.nowTimeForObjectName()+"Collection.Private";
        for (int i = 1; i <= number; i++) {
            name = nameTemplate + " " + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + name + " was created:");
            Thread.sleep(2000);
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
            driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(name);
            if (pageSharingMode == CollectionSharingMode.PUBLIC) {
                driver.findElement(By.xpath(COLLECTION_CONFIGURE_PUBLIC_XPATH)).click();
                driver.findElement(By.xpath(COLLECTION_SHARE_WITH_ORG_XPATH)).click();
            }
            if (pageSharingMode == CollectionSharingMode.EVERYONE) {
                driver.findElement(By.xpath(COLLECTION_CONFIGURE_PUBLIC_XPATH)).click();
                driver.findElement(By.xpath(COLLECTION_SHARE_WITH_EVERYONE_XPATH)).click();
            }
            driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRemoving(WebDriver driver, int number, boolean isLogged) throws InterruptedException {
        String name;
        for (int i = 1; i <= number; i++) {
            name = driver.findElement(By.xpath(COLLECTION_GETNAME_XPATH)).getText();
            Thread.sleep(500);
            driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(COLLECTION_DELETE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(COLLECTION_DELETE_BUTTON_XPATH)).click();
            if (isLogged) System.out.println(Service.nowTime() + " Collection " + name + " was removed:");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionArchiving(WebDriver driver, int number, boolean isLogged) throws InterruptedException {
        String name;
        for (int i = 1; i <= number; i++) {
            name = Run.currentBrowser+Service.nowTimeForObjectName()+".CollectionArch" + Integer.toString(i);
            driver.findElement(By.xpath(COLLECTION_ADD_XPATH)).click();
            Thread.sleep(2000);
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
    public static void collectionRenameChosen(WebDriver driver, String nameOfCollection, String nameForRename) {
        driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(nameForRename);
        driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionRenameChosen(WebDriver driver, int number, String nameForRename) {
        driver.findElement(By.xpath(COLLLECTION_OPEN_PAGEMENU_XPATH)).click();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).clear();
        driver.findElement(By.xpath(COLLECTION_ENTER_NAME_XPATH)).sendKeys(nameForRename);
        driver.findElement(By.cssSelector(COLLECTION_DONE_BUTTON_CSS)).click();
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void collectionCreateManyWithWidgets(WebDriver driver, int numberOfCollections,int numberOfWidgets)throws InterruptedException{
        for (int i = 1; i <= numberOfCollections;i++){
            Collection.collectionCreation(driver,1,CollectionSharingMode.PUBLIC,false);
            Widget.widgetsCreation(driver, numberOfWidgets,Integer.toString(i), WidgetState.EXPANDED, WidgetColor.RANDOM, true, false);
        }
    }
}
