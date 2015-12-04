package pageobjects;

import enums.*;
import org.openqa.selenium.Keys;
import tests.Run;
import utils.Service;
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
    final static String IDEA_ADD_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div/div[2]/div[1]/div/div[1]";
    final static String IDEA_NAME_FIELD_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div[1]/input";
    final static String IDEA_SAVE_BUTTON_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/button";
    final static String IDEA_HEADER_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";
    final static String IDEA_MENU_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div/div[3]/div/img";
    //    final static String BOARD_ADD_CSS = "div.xaddbutton-maindiv.js-workspace-tracking-add-board > img.xaddbutton-icon"; cannot be used
    final static String BOARD_NAME_FIELD_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div[1]/input";
    final static String BOARD_ADD_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div[1]";
    final static String BOARD_MENU_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div/div[3]/div/img";
    final static String BOARD_HEADER_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";
    final static String BOARD_SAVE_BUTTON_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/button";
    final static String WIDGET_BOARD_OPTIONS_CSS = ".widget-menu-choice.js-widget-showboardoptionsmenu.js-tap-indication";
    //rename
    final static String IDEA_MENU_OPTIONS_XPATH = "html/body/div[4]/div/div[3]/div/div[7]/div[2]";
    final static String IDEA_RENAME_XPATH = "html/body/div[5]/div/div/div/div[2]/div[2]";
    final static String BOARD_MENU_OPTIONS_XPATH = "html/body/div[4]/div/div[3]/div/div[9]";
    final static String BOARD_RENAME_XPATH = "html/body/div[5]/div/div/div/div[2]/div[2]";
    //archive
    final static String IDEA_MENU_ARCHIVE_XPATH = "html/body/div[5]/div/div/div/div[3]/div[2]";
    final static String BOARD_MENU_ARCHIVE_XPATH = "html/body/div[5]/div/div/div/div[3]/div[2]";
    //removal
    final static String IDEA_MENU_DELETE_XPATH = "html/body/div[5]/div/div/div/div[4]/div[2]";
    final static String IDEA_MENU_CONFIRM_DELETE_XPATH = "html/body/div[5]/div/div/div/div[5]/div[1]";
    final static String BOARD_MENU_DELETE_XPATH = "html/body/div[5]/div/div/div/div[4]/div[2]";
    final static String BOARD_MENU_CONFIRM_DELETE_XPATH = "html/body/div[5]/div/div/div/div[5]/div[1]";
    //show archived
    final static String IDEA_CHECKBOX_MENU_XPATH = "html/body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div/div[1]/img";
    final static String IDEA_CHECKBOX_SHOWARCHIVED_XPATH = "html/body/div[4]/div/div/div/div/div[1]/div";
    final static String BOARD_CHECKBOX_MENU_XPATH = "html/body/div[3]/div[1]/div[1]/div[3]/div/div[1]/div/div[1]/img";
    final static String BOARD_CHECKBOX_SHOWARCHIVED_XPATH = "html/body/div[4]/div/div/div/div/div[1]/div";
    //board and idea menu. sharing
    final static String WIDGET_MENU_SHARE = "//div[contains(text(),'Share')]";
    final static String WIDGET_MENU_SHARING_OPTIONS = "//div[contains(text(),'Configure public access')]";
    final static String WIDGET_MENU_SHARING_CANCEL = ".popup-window-toolbar-button.mod-cancel.js-popup-cancel";
    final static String WIDGET_MENU_SHARING_DONCE = ".popup-window-toolbar-button.mod-done.js-popup-done";
    final static String WIDGET_MENU_SHARING_SPECIFIC = "//div[contains(text(),'Off — Specific people only')]";
    final static String WIDGET_MENU_SHARING_ALL = "//div[contains(text(),'On — All members of this collection')]";
    final static String WIDGET_MENU_SHARING_INTERNET = "//div[contains(text(),'On — Everyone on the Internet')]";
    final static String WIDGET_MENU_SHARING_ENTER_USER = "//input[contains(@id,'menu-popup-avatar')]";
    final static String WIDGET_MENU_SHARING_SELECT = ".ui-dropdown-select.js-ui-dropdown.js-tap-indication.js-tap-direct.js-share-users-option";
    //board and idea menu. other
    final static String WIDGET_MENU_SUBSCRIBE = "//div[contains(text(),'Subscribe')]";
    final static String WIDGET_MENU_FULLSCREEN = "//div[contains(text(),'View in new tab')]";
    final static String WIDGET_MENU_REPORTS = "//div[contains(text(),'Reports')]";
    final static String WIDGET_MENU_ADDTOCOLLECTION = "//div[contains(text(),'Add to collection')]";
    //board and idea menu. apps & integrations
    final static String WIDGET_MENU_APPS_INTEGRATIONS = "//div[contains(text(),'Apps & Integrations')]";
    final static String WIDGET_MENU_APPS_POPUP = ".popup-bg.mod-transparent.js-workspace-popup-bg";
    final static String WIDGET_MENU_APPS_VALUES = "//div[@class='expandable-title-text'][contains(text(),'Default values')]";
    final static String WIDGET_MENU_APPS_WORKFLOW = "//div[@class='expandable-title-text'][contains(text(),'Defined workflow')]";
    final static String WIDGET_MENU_APPS_LANES = "//div[@class='expandable-title-text'][contains(text(),'Lanes')]";
    final static String WIDGET_MENU_APPS_LEADTIME = "//div[@class='expandable-title-text'][contains(text(),'Lead time')]";
    final static String WIDGET_MENU_APPS_WIP = "//div[@class='expandable-title-text'][contains(text(),'WIP limit')]";
    final static String WIDGET_MENU_APPS_ENABLE = ".ui-button-green.js-app-toggle";
    final static String WIDGET_MENU_APPS_DISABLE = ".ui-button-red.js-tap-indication.js-ui-button-delete";
    final static String WIDGET_MENU_APPS_DISABLE_CONFIRMATION = ".ui-button-red-confirmation.js-tap-indication.js-app-toggle";
    final static String WIDGET_MENU_APPS_CLOSE = ".popup-window-toolbar-button.mod-cancel.js-popup-cancel";
    final static String WIDGET_MENU_FILTER_CARDS = "//div[contains(text(),'Filter cards')]";
    final static String WIDGET_MENU_FILTER_SHOW_ARCHIVED = "//div[contains(text(),'Show archived cards')]";
    final static String WIDGET_MENU_FILTER_SHOW_ASSIGNED = "//div[contains(text(),'Show assigned to me only')]";
    final static String WIDGET_MENU_SHOW_ON_CARDS = "//div[contains(text(),'Show on cards')]";
    final static String WIDGET_MENU_SHOW_RELATIONS = "//div[contains(text(),'Relations')]";
    final static String WIDGET_MENU_SHOW_LEADTIME = "//div[contains(text(),'Lead time')]";
    //lanes
    final static String WIDGET_ADD_LANE = "(//div[contains(text(),'Add lane')])";

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
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + Service.nowTimeForObjectName()
                    + "Idea " + name);
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Keys.ENTER);
//            driver.findElement(By.xpath(IDEA_SAVE_BUTTON_XPATH)).click(); Old version of code, doesn't work after some update
            if (isLogged) System.out.println(Service.nowTime() + " Idea " + name + " was created:");

            //Create Board Widget with Sequential Name 1,2,..
            driver.findElement(By.xpath(BOARD_ADD_XPATH)).click();
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + Service.nowTimeForObjectName() +
                    "Board " + name);
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
            driver.findElement(By.xpath(BOARD_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser +
                    Service.nowTimeForObjectName() + " Board " + name);
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
            driver.findElement(By.xpath(IDEA_NAME_FIELD_XPATH)).sendKeys(Run.currentBrowser + Service.nowTimeForObjectName()
                    + "Idea " + name);
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
                widgetsCurrentRename(driver, Run.currentBrowser + Service.nowTimeForObjectName() + ".ARCHIVED IDEA" + i, WidgetType.IDEA, false);
            }
            if (widgetOperation == WidgetOperation.UNARCHIVE) {
                if (isLogged) System.out.println("  " + Service.nowTime() + " Idea:'" + ideaName + "' was unarchived");
                widgetsCurrentRename(driver, Run.currentBrowser + Service.nowTimeForObjectName() + ".UNARCHIVED IDEA" + i, WidgetType.IDEA, false);
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
        driver.findElement(By.xpath("html/body/div[4]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_MENU_XPATH)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_SHOWARCHIVED_XPATH)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[4]")).click();
        if (isLogged) System.out.println("  " + Service.nowTime() + " Archived Boards are / arent shown");
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
//    Widget.widgetsCreateBigWidgets(driver, 80, 80, 4);
    public static void widgetsCreateBigWidgets(WebDriver driver, int yIdea, int yBoard, int xBoard) throws InterruptedException {
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        Widget.widgetsCurrentRename(driver, Run.currentBrowser + Service.nowTimeForObjectName() + ".MANY CARDS IDEA", WidgetType.IDEA, false);
        Widget.widgetsCurrentRename(driver, Run.currentBrowser + Service.nowTimeForObjectName() + ".MANY CARDS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, yBoard, xBoard, false);
        Card.cardsFirstIdeaGeneration(driver, yIdea, false);
        for (int j = 1; j <= yBoard; j++) {
            for (int i = 1; i <= xBoard; i++) {
                Card.cardAddManyNewTagsToCard(driver, 3, j, j, i);
                String[] users = {"war", "Nine", "ei", "onc"};
                Card.cardAddFewPeople(driver, users, j, j, i);
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void enableDisableIntegrations(WebDriver driver, boolean enableYN) {
        if (enableYN) driver.findElement(By.cssSelector(WIDGET_MENU_APPS_ENABLE)).click();
        else {
            driver.findElement(By.cssSelector(WIDGET_MENU_APPS_DISABLE)).click();
            driver.findElement(By.cssSelector(WIDGET_MENU_APPS_DISABLE_CONFIRMATION)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetEnableSettings(WebDriver driver, WidgetType widgetType, WidgetSettings widgetSettings,
                                            boolean enableYesNo, boolean isLogged) throws InterruptedException {
        if (widgetType == WidgetType.BOARD) driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
        if (widgetType == WidgetType.IDEA) driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
        driver.findElement(By.xpath(WIDGET_MENU_APPS_INTEGRATIONS)).click();
        driver.findElement(By.cssSelector(WIDGET_MENU_APPS_POPUP)).click();
        switch (widgetSettings) {
            case VALUES: {
                driver.findElement(By.xpath(WIDGET_MENU_APPS_VALUES)).click();
                enableDisableIntegrations(driver, enableYesNo);
                driver.findElement(By.xpath(WIDGET_MENU_APPS_VALUES)).click();
            }
            break;
            case WORKFLOW: {
                driver.findElement(By.xpath(WIDGET_MENU_APPS_WORKFLOW)).click();
                enableDisableIntegrations(driver, enableYesNo);
                driver.findElement(By.xpath(WIDGET_MENU_APPS_WORKFLOW)).click();
            }
            break;
            case LANES: {
                driver.findElement(By.xpath(WIDGET_MENU_APPS_LANES)).click();
                enableDisableIntegrations(driver, enableYesNo);
                driver.findElement(By.xpath(WIDGET_MENU_APPS_LANES)).click();
            }
            break;
            case LEADTIME: {
                driver.findElement(By.xpath(WIDGET_MENU_APPS_LEADTIME)).click();
                enableDisableIntegrations(driver, enableYesNo);
                driver.findElement(By.xpath(WIDGET_MENU_APPS_LEADTIME)).click();
            }
            break;
            case WIP: {
                driver.findElement(By.xpath(WIDGET_MENU_APPS_WIP)).click();
                enableDisableIntegrations(driver, enableYesNo);
                driver.findElement(By.xpath(WIDGET_MENU_APPS_WIP)).click();
            }
            break;
            default: {
            }
            break;
        }
        driver.findElement(By.cssSelector(WIDGET_MENU_APPS_CLOSE)).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetEnableAllIntegrationsYN(WebDriver driver, boolean enableYesNo) throws InterruptedException {
        if (enableYesNo) widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.GREEN, false);
        widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.VALUES, enableYesNo, false);
        widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.WORKFLOW, enableYesNo, false);
        widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.LANES, enableYesNo, false);
        widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.LEADTIME, enableYesNo, false);
        widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.WIP, enableYesNo, false);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetEnableShowSetting(WebDriver driver, WidgetShowSettings widgetShowSettings, boolean isLogged) {

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetSetupWIPLimit(WebDriver driver, int columnNumber, int wiplimitNumber) {

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void lanesCreate(WebDriver driver, int widgetNumber, int lanesNumber, boolean isLogged) {
        String addLaneXpath;
        for (int i = 1; i < lanesNumber; i++) {
            addLaneXpath = WIDGET_ADD_LANE + "[" + Integer.toString(widgetNumber) + "]";
            driver.findElement(By.xpath(addLaneXpath)).click();
            driver.findElement(By.xpath("//textarea")).sendKeys("Lane " + Integer.toString(i+1));
            driver.findElement(By.xpath("//textarea")).sendKeys(Keys.ENTER);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void lanesCreate(WebDriver driver, int widgetNumber, String[] listOfLanes, boolean isLogged) {
        String addLaneXpath;
        for (String laneElement : listOfLanes) {
            addLaneXpath = WIDGET_ADD_LANE + "[" + Integer.toString(widgetNumber) + "]";
            driver.findElement(By.xpath(addLaneXpath)).click();
            driver.findElement(By.xpath("//textarea")).sendKeys(laneElement);
            driver.findElement(By.xpath("//textarea")).sendKeys(Keys.ENTER);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void lanesRemove(WebDriver driver, int lanesNumber, boolean isLogged) {
        final String WIDGET_CHOOSE_LANE = "(//div[@class=\"board-title-text-lanes\"])";
        driver.findElement(By.xpath(WIDGET_CHOOSE_LANE + "[" + lanesNumber + "]")).click();
        Card.cardDelete(driver, false);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void createCardsWithinLane(WebDriver driver, int boardNumberX, int lanesNumberY, int columnNumberZ, String cardName, boolean isLogged) throws InterruptedException {
        final String WIDGET_BOARD_AXIS = "(//div[@class=\"workspace-content workspace-content-tracking mod-scrollable-y\"]/div/div";
        final String WIDGET_LANE_AXIS = "//*[@class=\"widget-board-lane js-widgetlane-main\"]";
        final String WIDGET_COLUMN_AXIS = "//*[@class=\"js-paged-list-end mod-not-draggable\"])";
        String addCardXPath = "";
        addCardXPath = WIDGET_BOARD_AXIS + "[" + boardNumberX + "]" + WIDGET_LANE_AXIS + "[" + lanesNumberY + "]" + WIDGET_COLUMN_AXIS + "[" + columnNumberZ + "]";
        Thread.sleep(3000);
        System.out.println(addCardXPath);
        driver.findElement(By.xpath(addCardXPath)).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//textarea")).sendKeys(cardName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class=\"board-title-save js-textfield-save\"]")).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void createLanesWithManyCards(WebDriver driver, int numberOfColumnsX, int numberOfLanesY, int numberOfCardsZ, boolean isLogged) throws InterruptedException {
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.VIOLET, false);
        Widget.widgetsCurrentRename(driver, ".MANY LANES BOARD" + numberOfColumnsX + "x" + numberOfLanesY + "x" + numberOfCardsZ, WidgetType.BOARD, false);
        Widget.widgetEnableSettings(driver, WidgetType.BOARD, WidgetSettings.LANES, true, false);
        if (numberOfColumnsX > 2) Column.columnsCreation(driver, numberOfColumnsX - 2, false);
        Widget.lanesCreate(driver, 1, numberOfLanesY, false);
        String cardName;
        for (int i = 1; i <= numberOfLanesY; i++) {
            for (int j = 1; j <= numberOfColumnsX; j++) {
                for (int k = 1; k <= numberOfCardsZ; k++) {
                    cardName = Integer.toString(j) + Integer.toString(i) + Integer.toString(k);
                    createCardsWithinLane(driver, 1, i, j, "Card " + cardName, false);
                }
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------
}
