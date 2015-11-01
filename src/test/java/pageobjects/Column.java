package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import tests.Run;

public class Column {
    //Add column
    final static String COULMN_ADD_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div[2]/div";
    //Open column menu
    final static String COLUMN_MENU_FIRSTPART_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div[1]/div/";
    final static String COLUMN_MENU_SECONDPART_XPATH = "/div[1]/div";
    //Rename column
    final static String COLUMN_RENAME_FIRST_PART_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div[1]/div/";
    final static String COLUMN_RENAME_SECOND_PART_XPATH = "/div[1]/div/input";

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static String getOpenColumnMenuXpathByNumber(int number) {
        return COLUMN_MENU_FIRSTPART_XPATH + "div[" + Integer.toString(number) + "]" + COLUMN_MENU_SECONDPART_XPATH;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static String getRenameColumnXpathByNumber(int number) {
        return COLUMN_RENAME_FIRST_PART_XPATH + "div[" + Integer.toString(number) + "]" + COLUMN_RENAME_SECOND_PART_XPATH;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnsCreation(WebDriver driver, int number, boolean isLogged) {
        String currentXpathForRenaming;
        int shiftedValue;
        for (int i = 1; i <= number; i++) {
            shiftedValue = i + 2;
            currentXpathForRenaming = getRenameColumnXpathByNumber(shiftedValue);
            if (isLogged) System.out.println("Log: " + COULMN_ADD_XPATH);
            driver.findElement(By.xpath(COULMN_ADD_XPATH)).click();
            if (isLogged) System.out.println("Log: " + currentXpathForRenaming);
            driver.findElement(By.xpath(currentXpathForRenaming)).sendKeys(Run.currentBrowser + "Column " + shiftedValue);
            if (isLogged) System.out.println("Column" + (shiftedValue) + " created. Xpath=" + COULMN_ADD_XPATH);
            driver.findElement(By.xpath(currentXpathForRenaming)).sendKeys(Keys.ENTER);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnRemove(WebDriver driver, int columnNumber, boolean isLogged) {
        final String COLUMN_MENU_REMOVE_CSS = ".widget-menu-choice.editboardcolumn-delete-text.js-boardcolumn-delete.js-tap-indication";
        driver.findElement(By.xpath(getOpenColumnMenuXpathByNumber(columnNumber))).click();
        driver.findElement(By.cssSelector(COLUMN_MENU_REMOVE_CSS)).click();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnRename(WebDriver driver, int columnNumber, String newName, boolean isLogged) {
        final String COLUMN_MENU_RENAME_CSS = ".widget-menu-choice.js-boardcolumn-rename.js-tap-indication";
        driver.findElement(By.xpath(getOpenColumnMenuXpathByNumber(columnNumber))).click();
        driver.findElement(By.cssSelector(COLUMN_MENU_RENAME_CSS)).click();
        driver.findElement(By.xpath(getRenameColumnXpathByNumber(columnNumber))).clear();
        driver.findElement(By.xpath(getRenameColumnXpathByNumber(columnNumber))).sendKeys(newName);
        driver.findElement(By.xpath(getRenameColumnXpathByNumber(columnNumber))).sendKeys(Keys.ENTER);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnsArchiveAll(WebDriver driver, int columnNumber, boolean isLogged) {
        final String COLUMN_MENU_ARCHIVEALL_CSS = ".widget-menu-choice.js-boardcolumn-archiveall.js-tap-indication";
        driver.findElement(By.xpath(getOpenColumnMenuXpathByNumber(columnNumber))).click();
        driver.findElement(By.cssSelector(COLUMN_MENU_ARCHIVEALL_CSS)).click();
        columnRename(driver,columnNumber,"ArchivedAll",false);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnSubscribe(WebDriver driver, int columnNumber, boolean isLogged) {
        final String COLUMN_MENU_SUBSCRIBE_CSS = ".widget-menu-choice-icon.widget-menu-icon-unselected";
        driver.findElement(By.xpath(getOpenColumnMenuXpathByNumber(columnNumber))).click();
        driver.findElement(By.cssSelector(COLUMN_MENU_SUBSCRIBE_CSS)).click();
        columnRename(driver,columnNumber,"Subscribed",false);
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}