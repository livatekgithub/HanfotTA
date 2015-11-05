package pageobjects;

import org.openqa.selenium.Keys;
import tests.Run;
import utils.Service;
import enums.WidgetColor;
import enums.WidgetOperation;
import enums.WidgetState;
import enums.WidgetType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Widget {
    /**
     * .xaddbutton-icon
     * 16.10.2015 - 235 lines
     * 1. widgetsCreation
     * 2. widgetsArchive
     * 3. widgetsCurrentRename
     * 4. widgetsRemoval
     * 5. widgetsShowArchived
     * to be developed
     * >* 6. widgetFilterShowArchived
     * >* 7. widgetFilterShowAssignedToMeOnly
     * >* 8. widgetGetListOfWidgets
     * >* 9. widgetChoseWidget
     * 10.widgetShowRelations
     * >* 11.widgetApps
     * 12.EXTEND BOARD and IDEA FROM WIDGETS
     * >* 13.widgetShareManually
     * >* 14.widgetShareMakePrivate
     * >* 15.widgetsAddtoCollection
     * >* 16.widgetViewinNewTab
     * >* 17.widgetSubscribe
     * >* 18.widgetAppsShowLeadTime
     * >* 19.widgetAppsEnableLanes
     */
    //basics and creation
//    final static String IDEA_ADD_CSS = "img.xaddbutton-icon"; cannot be used
    final static String IDEA_ADD_XPATH= "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div/div[2]/div[1]/div/div[1]";
    final static String IDEA_NAME_FIELD_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div[1]/input";
    final static String IDEA_SAVE_BUTTON_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/button";
    final static String IDEA_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";
    final static String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div/div[3]/div/img";
    //    final static String BOARD_ADD_CSS = "div.xaddbutton-maindiv.js-workspace-tracking-add-board > img.xaddbutton-icon"; cannot be used
    final static String BOARD_NAME_FIELD_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div[1]/input";
    final static String BOARD_ADD_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[1]/div/div[2]/div/div/div[1]";
    final static String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div/div[3]/div/img";
    final static String BOARD_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";
    final static String BOARD_SAVE_BUTTON_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/button";
    final static String WIDGET_BOARD_OPTIONS_CSS = ".widget-menu-choice.js-widget-showboardoptionsmenu.js-tap-indication";
    //rename
    final static String IDEA_MENU_OPTIONS_XPATH = "html/body/div[3]/div/div[3]/div/div[7]/div[2]";
    final static String IDEA_RENAME_XPATH = "html/body/div[4]/div/div/div/div[2]/div[2]";
    final static String BOARD_MENU_OPTIONS_XPATH = "html/body/div[3]/div/div[3]/div/div[9]";
    final static String BOARD_RENAME_XPATH = "html/body/div[4]/div/div/div/div[2]/div[2]";
    //archive
    final static String IDEA_MENU_ARCHIVE_XPATH = "html/body/div[4]/div/div/div/div[3]/div[2]";
    final static String BOARD_MENU_ARCHIVE_XPATH = "html/body/div[4]/div/div/div/div[3]/div[2]";
    //removal
    final static String IDEA_MENU_DELETE_XPATH = "html/body/div[4]/div/div/div/div[4]/div[2]";
    final static String IDEA_MENU_CONFIRM_DELETE_XPATH = "html/body/div[4]/div/div/div/div[5]/div[1]";
    final static String BOARD_MENU_DELETE_XPATH = "html/body/div[4]/div/div/div/div[4]/div[2]";
    final static String BOARD_MENU_CONFIRM_DELETE_XPATH = "html/body/div[4]/div/div/div/div[5]/div[1]";
    //show archived
    final static String IDEA_CHECKBOX_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div/div[1]/img";
    final static String IDEA_CHECKBOX_SHOWARCHIVED_XPATH = "html/body/div[3]/div/div/div/div/div[1]/div";
    final static String BOARD_CHECKBOX_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[1]/div/div[1]/img";
    final static String BOARD_CHECKBOX_SHOWARCHIVED_XPATH = "html/body/div[3]/div/div/div/div/div[1]/div";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsCreation(WebDriver driver, int number, WidgetState widgetState, WidgetColor widgetColor, boolean isLogged) throws InterruptedException {
        String name;
        String colorId;
        String colorName = "";
        int colorCode = 0;
        driver.navigate().refresh(); //to get constant xpath links
        Thread.sleep(3000);
        for (int i = 11; i < 11 + number; i++) {
            if (widgetColor == WidgetColor.RANDOM) {
                if (i % 10 == 0) colorCode = 10;
                else colorCode = i % 10;
                colorId = Integer.toString(colorCode);
                colorName = WidgetColor.values()[colorCode].toString();
            } else if (widgetColor == WidgetColor.DEFAULT) {
                colorId = "1";
                colorName = WidgetColor.values()[colorCode].toString();
            } else {
                colorId = Integer.toString(widgetColor.ordinal());
                colorName = widgetColor.toString();
            }
            name = Integer.toString(i - 10) + " " + colorName;
            //Create Idea Widget with Sequential Name 1,2,..
            driver.findElement(By.xpath(IDEA_ADD_XPATH)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + "Idea " + name);
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Keys.ENTER);
//            driver.findElement(By.xpath(IDEA_SAVE_BUTTON_XPATH)).click(); Old version of code, doesn't work after some update
            if (isLogged) System.out.println(Service.nowTime() + " Idea " + name + " was created:");

            //Create Board Widget with Sequential Name 1,2,..
            driver.findElement(By.xpath(BOARD_ADD_XPATH)).click();
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + "Board " + name);
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Keys.ENTER);
//            driver.findElement(By.xpath(BOARD_SAVE_BUTTON_XPATH)).click(); Old version of code, doesn't work after some update
            if (isLogged) System.out.println(Service.nowTime() + " Board " + name + " was created:");
            if (widgetState == WidgetState.COLLAPSED) {
                driver.findElement(By.xpath(IDEA_HEADER_XPATH)).click();
                if (isLogged) System.out.println(Service.nowTime() + " Idea " + name + " was collapsed:");
                driver.findElement(By.xpath(BOARD_HEADER_XPATH)).click();
                if (isLogged) System.out.println(Service.nowTime() + " Board " + name + " was collapsed:");
            }
            if (isLogged)
                System.out.println(Service.nowTime() + " Color=" + WidgetColor.values()[colorCode] + " Set up for Board" + colorId);
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.cssSelector(WIDGET_BOARD_OPTIONS_CSS)).click();
            driver.findElement(By.id(colorId)).click();

            if (isLogged)
                System.out.println(Service.nowTime() + " Color=" + WidgetColor.values()[colorCode] + " Set up for Idea");
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.cssSelector(WIDGET_BOARD_OPTIONS_CSS)).click();
            driver.findElement(By.id(colorId)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsCurrentRename(WebDriver driver, String name, WidgetType widgetType, boolean isLogged) throws InterruptedException {
        String widgetName;
        if (widgetType == WidgetType.BOARD) {
            widgetName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_XPATH)).click();
            driver.findElement(By.xpath(BOARD_RENAME_XPATH)).click();
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).clear();
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + "Board " + name);
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Keys.ENTER);
//            driver.findElement(By.xpath(BOARD_SAVE_BUTTON_XPATH)).click(); Old version of code, doesn't work after some update
            if (isLogged)
                System.out.println("  " + Service.nowTime() + "  Board:'" + widgetName + "' was renamed to 'Board " + name);
        } else {
            widgetName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_XPATH)).click();
            driver.findElement(By.xpath(IDEA_RENAME_XPATH)).click();
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).clear();
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + "Idea " + name);
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Keys.ENTER);
//            driver.findElement(By.xpath(IDEA_SAVE_BUTTON_XPATH)).click(); Old version of code, doesn't work after some update
            if (isLogged)
                System.out.println("  " + Service.nowTime() + "  Idea:'" + widgetName + "' was renamed to 'Idea " + name);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsArchive(WebDriver driver, int number, WidgetOperation widgetOperation, boolean isLogged) throws InterruptedException {
        String boardName;
        String ideaName;
        if (widgetOperation == WidgetOperation.UNARCHIVE) {
            widgetsShowArchived(driver, false);
        }
        for (int i = 1; i <= number; i++) {
            //Idea Archiving- Unarchiving
            ideaName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            if (widgetOperation == WidgetOperation.ARCHIVE) {
                if (isLogged) System.out.println("  " + Service.nowTime() + " Idea:'" + ideaName + "' was archived");
                widgetsCurrentRename(driver, Run.currentBrowser + "ARCHIVED IDEA" + i, WidgetType.IDEA, false);
            }
            if (widgetOperation == WidgetOperation.UNARCHIVE) {
                if (isLogged) System.out.println("  " + Service.nowTime() + " Idea:'" + ideaName + "' was unarchived");
                widgetsCurrentRename(driver, Run.currentBrowser + "UNARCHIVED IDEA" + i, WidgetType.IDEA, false);
            }
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_XPATH)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(IDEA_MENU_ARCHIVE_XPATH)).click();
            //Board Archiving- Unarchiving
            boardName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            if (widgetOperation == WidgetOperation.ARCHIVE) {
                if (isLogged) System.out.println("  " + Service.nowTime() + " Board:'" + boardName + "' was archived");
                widgetsCurrentRename(driver, "ARCHIVED BOARD" + i, WidgetType.BOARD, false);
            }
            if (widgetOperation == WidgetOperation.UNARCHIVE) {
                if (isLogged)
                    System.out.println("  " + Service.nowTime() + " Board:'" + boardName + "' was unarchived");
                widgetsCurrentRename(driver, "UNARCHIVED BOARD" + i, WidgetType.BOARD, false);
            }
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_XPATH)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(BOARD_MENU_ARCHIVE_XPATH)).click();
        }
        if (widgetOperation == WidgetOperation.UNARCHIVE) {
            widgetsShowArchived(driver, false);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsRemoval(WebDriver driver, int number, boolean isLogged) throws InterruptedException {
        String boardName;
        String ideaName;
        for (int i = 1; i <= number; i++) {
            ideaName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_DELETE_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_CONFIRM_DELETE_XPATH)).click();
            if (isLogged) System.out.println("  " + Service.nowTime() + " Idea:'" + ideaName + "' was removed");

            boardName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_DELETE_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_CONFIRM_DELETE_XPATH)).click();
            if (isLogged) System.out.println("  " + Service.nowTime() + " Board:'" + boardName + "' was removed");
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsShowArchived(WebDriver driver, boolean isLogged) throws InterruptedException {
        driver.findElement(By.xpath(IDEA_CHECKBOX_MENU_XPATH)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(IDEA_CHECKBOX_SHOWARCHIVED_XPATH)).click();
        Thread.sleep(1000);
        if (isLogged) System.out.println("  " + Service.nowTime() + " Archived Ideas are / arent shown");
        driver.findElement(By.xpath("html/body/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_MENU_XPATH)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_SHOWARCHIVED_XPATH)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[3]")).click();
        if (isLogged) System.out.println("  " + Service.nowTime() + " Archived Boards are / arent shown");
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
//    Widget.widgetsCreateBigWidgets(driver, 80, 80, 4);
    public static void widgetsCreateBigWidgets(WebDriver driver, int yIdea, int xBoard, int yBoard) throws InterruptedException {
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        Widget.widgetsCurrentRename(driver, ".MANY CARDS IDEA", WidgetType.IDEA, false);
        Widget.widgetsCurrentRename(driver, ".MANY CARDS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, xBoard, yBoard, false);
        Card.cardsFirstIdeaGeneration(driver, yIdea, false);
        for (int j = 1; j <= yBoard; j++) {
            Card.cardAddManyNewTagsToCard(driver, 5, j, 1, j);
            String[] users = {"war", "Nine", "ei", "onc"};
            Card.cardAddFewPeople(driver, users, j, j, 1);
        }
    }

}
