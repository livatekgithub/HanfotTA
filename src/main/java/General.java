import enums.*;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class General implements AccessData {

    /**
     * IDEAS FOR FURTHER TESTING
     * 1. Colourful Widgets
     * 2. Infinitive Removing
     * 3. Many Named Cards Creation
     */

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static String nowTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loginUser(WebDriver driver) throws InterruptedException {
        driver.get("https://test.hansoftx.com/logout");
        Thread.sleep(1000);
        driver.get(AccessData.TESTURL);
        driver.findElement(By.id("form-email")).clear();
        driver.findElement(By.id("form-email")).sendKeys(TESTLOGIN);
        driver.findElement(By.id("form-psw")).clear();
        driver.findElement(By.id("form-psw")).sendKeys(TESTPASSWORD);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.btn-submit.js-tap-indication")).click();

        try {
            assertTrue(isElementPresent(By.cssSelector(".avatar-initials"), driver));
        } catch (AssertionError e) {
            assertTrue(isElementPresent(By.cssSelector(".avatar-image"), driver));
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void userNotFound(WebDriver driver) {
        driver.get(AccessData.TESTURL);
        driver.findElement(By.id("form-email")).clear();
        driver.findElement(By.id("form-email")).sendKeys(WRONGTESTLOGIN);
        driver.findElement(By.id("form-psw")).clear();
        driver.findElement(By.id("form-psw")).sendKeys(WRONGTESTPASSWORD);
        driver.findElement(By.cssSelector("button.btn-submit.js-tap-indication")).click();
        assertEquals("User not found", driver.findElement(By.cssSelector("div.form-message.firepath-matching-node")).getText());
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loginUserWithCash(WebDriver driver, int numberofAttempts) throws InterruptedException {
        driver.navigate().to(AccessData.TESTURL);
        Thread.sleep(3000);
//        driver.findElement(By.id("form-email")).clear();
//        driver.findElement(By.id("form-email")).sendKeys(TESTLOGIN);
//        driver.findElement(By.id("form-psw")).clear();
//        driver.findElement(By.id("form-psw")).sendKeys(TESTLOGIN);
//        Thread.sleep(3000);
        for (int i = 1; i < numberofAttempts; i++) {
            driver.findElement(By.cssSelector("button.btn-submit.js-tap-indication")).click();
            Thread.sleep(3000);
            assertTrue(isElementPresent(By.cssSelector("span.avatar-initials"), driver));
            if (isElementPresent(By.cssSelector("span.avatar-initials"), driver))
                System.out.println("Attempt " + i + " was successful");
            else System.out.println("Attempt " + i + " was NOT successful");
            driver.findElement(By.cssSelector("span.avatar-initials")).click();
            driver.findElement(By.cssSelector("div.app-menu-choice.js-signout")).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void userSignOut(WebDriver driver) {
        try {
            driver.findElement(By.cssSelector(".avatar-initials")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector(".avatar-image")).click();
        }
        driver.findElement(By.cssSelector("div.app-menu-choice.js-signout")).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pagesCreation(WebDriver driver, int number, PageSharingMode pageSharingMode, boolean isLogged) throws InterruptedException {

        final String ADD_PAGE_XPATH = "html/body/div[2]/div[2]/div[1]/img";
//        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[5]"; without integrations button in menu
        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[6]";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String SHARE_WITHORG_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[3]/div[5]/div/img";
        String name;
        String nameTemplate;

        if (pageSharingMode == PageSharingMode.PUBLIC) nameTemplate = "Collection.Public";
        else nameTemplate = "Collection.Private";

        for (int i = 1; i <= number; i++) {
            name = nameTemplate + " " + Integer.toString(i);
            driver.findElement(By.xpath(ADD_PAGE_XPATH)).click();
            if (isLogged) System.out.println(nowTime() + name + " was created:");
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
            if (pageSharingMode == PageSharingMode.PUBLIC) driver.findElement(By.xpath(SHARE_WITHORG_XPATH)).click();
            driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
//            driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click(); xpath Done-button
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pagesRemoving(WebDriver driver, int number, boolean isLogged) throws InterruptedException {

        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String DELETE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[1]";
        String name;

        for (int i = 1; i <= number; i++) {
            name = "Collection" + Integer.toString(i);
            Thread.sleep(500);
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            if (isLogged) System.out.println(nowTime() + " Collection " + i + " was removed:");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pagesArchiving(WebDriver driver, int number, boolean isLogged) {
        final String ADD_PAGE_XPATH = "html/body/div[2]/div[2]/div[1]/img";
//        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[5]"; without integrations button in menu
        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[6]";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String PAGE_ARCHIVE_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[2]";

        String name;
        for (int i = 1; i <= number; i++) {
            name = "CollectionArch" + Integer.toString(i);
            driver.findElement(By.xpath(ADD_PAGE_XPATH)).click();
            if (isLogged) System.out.println(nowTime() + " CollectionArch " + i + " was created:");
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
//            driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click(); xpath Done-button
            driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pageRenameCurrent(WebDriver driver, String name) {
        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String DONE_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-popup-done";

        driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
        driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
        driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
        driver.findElement(By.cssSelector(DONE_BUTTON_CSS)).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersAddToOrganization(WebDriver driver, int numberOfUsers, boolean isLogged) throws InterruptedException {

        final String ADD_USER_FIELD_XPATH_BEGIN = "html/body/div[4]/div/div/div[1]/div/div[2]/div[3]/div[2]/";
        final String ADD_USER_FIELD_XPATH_END = "/div[1]/input";
        String name;
        String dynamicPart;

        try {
            Thread.sleep(5000);
            driver.findElement(By.cssSelector(".avatar-initials")).click();
        } catch (ElementNotVisibleException | NoSuchElementException e2) {
            driver.findElement(By.cssSelector(".avatar-image")).click();
        }
        driver.findElement(By.cssSelector("div.app-menu-choice.js-org-invite")).click();
        driver.findElement(By.id("invitations")).click();

        for (int i = 0; i < numberOfUsers; i++) {
            name = Integer.toString(i + 1);
            if (i == 0) dynamicPart = "div";
            else dynamicPart = "div[" + name + "]";
            if (isLogged) System.out.println(TestUsers.usernames[i]);
            if (isLogged) System.out.println(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END);
            driver.findElement(By.xpath(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END)).sendKeys(TestUsers.usernames[i]);
            if (isLogged)
                System.out.println(nowTime() + " User" + (i + 1) + ": " + TestUsers.usernames[i] + " was added:");
            driver.findElement(By.cssSelector(".fastcardpopup-taskinput-button.workspaceedit-add-button.js-workspacedit-add-invitation")).click();
        }
        driver.findElement(By.cssSelector(".xsolidbutton.mod-blue.workspaceedit-sendinvitations.js-workspacedit-sendinvitations")).click();
        driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-cancel.js-popup-cancel")).click();
        driver.findElement(By.cssSelector(".toolpopup-popup-bg")).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersRemoveAllFromOrganization(WebDriver driver, boolean isLogged) throws InterruptedException {

        String userName;
        final String LAST_USER_NAME = "html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]";
        final String SECOND_USER_CAN = "html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[2]/div[2]/div[2]/div";

        try {
            driver.findElement(By.cssSelector(".avatar-image")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector(".avatar-initials")).click();
        }
        driver.findElement(By.cssSelector("div.app-menu-choice.js-org-admin")).click();

        while (isElementPresent(By.xpath(SECOND_USER_CAN), driver)) {
            userName = driver.findElement(By.xpath(LAST_USER_NAME)).getText();
            if (isLogged)
                System.out.println(userName + " was removed " + driver.findElement(By.xpath(SECOND_USER_CAN)).isDisplayed());
            Thread.sleep(500);
            driver.findElement(By.xpath("html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/div")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div")).click();
        }
        driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-cancel.js-popup-cancel")).click();
        driver.findElement(By.cssSelector(".toolpopup-popup-bg")).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsCreation(WebDriver driver, int number, WidgetState widgetState, WidgetColor widgetColor, boolean isLogged) {

        final String IDEA_ADD_CSS = "img.xaddbutton-icon";
        final String IDEA_NAME_FIELD = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/input";
        final String IDEA_SAVE_BUTTON = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/button";
        final String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[3]/img";
        final String IDEA_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";
        final String BOARD_ADD_CSS = "div.xaddbutton-maindiv.js-workspace-tracking-add-board > img.xaddbutton-icon";
        final String BOARD_NAME_FIELD = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/input";
        final String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[3]/img";
        final String BOARD_SAVE_BUTTON = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/button";
        final String BOARD_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";
        final String WIDGET_BOARD_OPTIONS_CSS = ".widget-menu-choice.js-widget-showboardoptionsmenu.js-tap-indication";

        String name;
        String colorId;
        String colorName = "";
        int colorCode = 0;
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
            driver.findElement(By.cssSelector(IDEA_ADD_CSS)).click();
            driver.findElement(By.xpath(IDEA_NAME_FIELD)).sendKeys("Idea " + name);
            driver.findElement(By.xpath(IDEA_SAVE_BUTTON)).click();
            if (isLogged) System.out.println(nowTime() + " Idea " + name + " was created:");

            //Create Board Widget with Sequential Name 1,2,..

            driver.findElement(By.cssSelector(BOARD_ADD_CSS)).click();
            driver.findElement(By.xpath(BOARD_NAME_FIELD)).sendKeys("Board " + name);
            driver.findElement(By.xpath(BOARD_SAVE_BUTTON)).click();
            if (isLogged) System.out.println(nowTime() + " Board " + name + " was created:");

            if (widgetState == WidgetState.COLLAPSED) {
                driver.findElement(By.xpath(IDEA_HEADER_XPATH)).click();
                if (isLogged) System.out.println(nowTime() + " Idea " + name + " was collapsed:");
                driver.findElement(By.xpath(BOARD_HEADER_XPATH)).click();
                if (isLogged) System.out.println(nowTime() + " Board " + name + " was collapsed:");
            }

            if (isLogged)
                System.out.println(nowTime() + " Color=" + WidgetColor.values()[colorCode] + " Set up for Board" + colorId);
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.cssSelector(WIDGET_BOARD_OPTIONS_CSS)).click();
            driver.findElement(By.id(colorId)).click();

            if (isLogged)
                System.out.println(nowTime() + " Color=" + WidgetColor.values()[colorCode] + " Set up for Idea");
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.cssSelector(WIDGET_BOARD_OPTIONS_CSS)).click();
            driver.findElement(By.id(colorId)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsCurrentRename(WebDriver driver, String name, WidgetType widgetType, boolean isLogged) throws InterruptedException {
        final String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[3]/img";
        final String IDEA_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[7]/div[2]";
        final String IDEA_RENAME_MENU_ITEM = "html/body/div[4]/div/div/div/div[2]/div[2]";

        final String IDEA_NAME_FIELD = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/input";
        final String IDEA_SAVE_BUTTON = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/button";
        final String IDEA_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";

        final String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[3]/img";
        final String BOARD_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[9]";
        final String BOARD_RENAME_MENU_ITEM = "html/body/div[4]/div/div/div/div[2]/div[2]";
        final String BOARD_NAME_FIELD = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/input";
        final String BOARD_SAVE_BUTTON = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/button";
        final String BOARD_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";
        String widgetName;

        if (widgetType == WidgetType.BOARD) {
            widgetName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_ITEM)).click();
            driver.findElement(By.xpath(BOARD_RENAME_MENU_ITEM)).click();
            driver.findElement(By.xpath(BOARD_NAME_FIELD)).clear();
            driver.findElement(By.xpath(BOARD_NAME_FIELD)).sendKeys("Board " + name);
            driver.findElement(By.xpath(BOARD_SAVE_BUTTON)).click();
            if (isLogged)
                System.out.println("  " + nowTime() + "  Board:'" + widgetName + "' was renamed to 'Board " + name);
        } else {
            widgetName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_ITEM)).click();
            driver.findElement(By.xpath(IDEA_RENAME_MENU_ITEM)).click();
            driver.findElement(By.xpath(IDEA_NAME_FIELD)).clear();
            driver.findElement(By.xpath(IDEA_NAME_FIELD)).sendKeys("Idea " + name);
            driver.findElement(By.xpath(IDEA_SAVE_BUTTON)).click();
            if (isLogged)
                System.out.println("  " + nowTime() + "  Idea:'" + widgetName + "' was renamed to 'Idea " + name);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsRemoval(WebDriver driver, int number, boolean isLogged) throws InterruptedException {
        final String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[3]/img";
        final String IDEA_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[7]/div[2]";
        final String IDEA_MENU_DELETE_ITEM = "html/body/div[4]/div/div/div/div[4]/div[2]";
        final String IDEA_MENU_CONFIRM_DELETE_ITEM = "html/body/div[4]/div/div/div/div[5]/div[1]";
        final String IDEA_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";

        final String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[3]/img";
        final String BOARD_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[9]";
        final String BOARD_MENU_DELETE_ITEM = "html/body/div[4]/div/div/div/div[4]/div[2]";
        final String BOARD_MENU_CONFIRM_DELETE_ITEM = "html/body/div[4]/div/div/div/div[5]/div[1]";
        final String BOARD_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";

        String boardName;
        String ideaName;

        for (int i = 1; i <= number; i++) {
            ideaName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_ITEM)).click();
            driver.findElement(By.xpath(IDEA_MENU_DELETE_ITEM)).click();
            driver.findElement(By.xpath(IDEA_MENU_CONFIRM_DELETE_ITEM)).click();
            if (isLogged) System.out.println("  " + nowTime() + " Idea:'" + ideaName + "' was removed");

            boardName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_ITEM)).click();
            driver.findElement(By.xpath(BOARD_MENU_DELETE_ITEM)).click();
            driver.findElement(By.xpath(BOARD_MENU_CONFIRM_DELETE_ITEM)).click();
            if (isLogged) System.out.println("  " + nowTime() + " Board:'" + boardName + "' was removed");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsArchive(WebDriver driver, int number, WidgetOperation widgetOperation, boolean isLogged) throws InterruptedException {
        final String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[3]/img";
        final String IDEA_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[7]/div[2]";
        final String IDEA_MENU_ARCHIVE_ITEM = "html/body/div[4]/div/div/div/div[3]/div[2]";
        final String IDEA_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]";

        final String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[3]/img";
        final String BOARD_MENU_OPTIONS_ITEM = "html/body/div[3]/div/div[3]/div/div[9]/div[2]";
        final String BOARD_MENU_ARCHIVE_ITEM = "html/body/div[4]/div/div/div/div[3]/div[2]";
        final String BOARD_HEADER_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div";

        String boardName;
        String ideaName;

        if (widgetOperation == WidgetOperation.UNARCHIVE) {
            widgetsShowArchived(driver,false);
        }

        for (int i = 1; i <= number; i++) {
            //Idea Archiving- Unarchiving
            ideaName = driver.findElement(By.xpath(IDEA_HEADER_XPATH)).getText();
            if (widgetOperation == WidgetOperation.ARCHIVE) {
                if (isLogged) System.out.println("  " + nowTime() + " Idea:'" + ideaName + "' was archived");
                widgetsCurrentRename(driver, "ARCHIVED IDEA" + i, WidgetType.IDEA, false);
            }
            if (widgetOperation == WidgetOperation.UNARCHIVE) {
                if (isLogged) System.out.println("  " + nowTime() + " Idea:'" + ideaName + "' was unarchived");
                widgetsCurrentRename(driver, "UNARCHIVED IDEA" + i, WidgetType.IDEA, false);
            }
            driver.findElement(By.xpath(IDEA_MENU_XPATH)).click();
            driver.findElement(By.xpath(IDEA_MENU_OPTIONS_ITEM)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(IDEA_MENU_ARCHIVE_ITEM)).click();

            //Board Archiving- Unarchiving
            boardName = driver.findElement(By.xpath(BOARD_HEADER_XPATH)).getText();
            if (widgetOperation == WidgetOperation.ARCHIVE) {
                if (isLogged) System.out.println("  " + nowTime() + " Board:'" + boardName + "' was archived");
                widgetsCurrentRename(driver, "ARCHIVED BOARD" + i, WidgetType.BOARD, false);
            }
            if (widgetOperation == WidgetOperation.UNARCHIVE) {
                if (isLogged) System.out.println("  " + nowTime() + " Board:'" + boardName + "' was unarchived");
                widgetsCurrentRename(driver, "UNARCHIVED BOARD" + i, WidgetType.BOARD, false);
            }
            driver.findElement(By.xpath(BOARD_MENU_XPATH)).click();
            driver.findElement(By.xpath(BOARD_MENU_OPTIONS_ITEM)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(BOARD_MENU_ARCHIVE_ITEM)).click();
        }

        if (widgetOperation == WidgetOperation.UNARCHIVE) {
            widgetsShowArchived(driver,false);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsShowArchived(WebDriver driver,boolean isLogged) throws InterruptedException {
        final String IDEA_CHECKBOX_MENU = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div/div[1]/img";
        final String IDEA_CHECKBOX_SHOWARCHIVED = "html/body/div[3]/div/div/div/div/div[1]/div";
        final String BOARD_CHECKBOX_MENU = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[1]/div/div[1]/img";
        final String BOARD_CHECKBOX_SHOWARCHIVED = "html/body/div[3]/div/div/div/div/div[1]/div";

        driver.findElement(By.xpath(IDEA_CHECKBOX_MENU)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(IDEA_CHECKBOX_SHOWARCHIVED)).click();
        Thread.sleep(1000);
        if (isLogged) System.out.println("  " + nowTime() + " Archived Ideas are / arent shown");
        driver.findElement(By.xpath("html/body/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_MENU)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(BOARD_CHECKBOX_SHOWARCHIVED)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[3]")).click();
        if (isLogged) System.out.println("  " + nowTime() + " Archived Boards are / arent shown");
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void cardsFirstIdeaGeneration(WebDriver driver, int number, boolean isLogged) {
        String name;
        String dynamicPart;
        final String IDEA_CARD_X_PATH_FIRST_PART = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/";
        final String IDEA_CARD_X_PATH_BUTTON = "/div[1]/div[1]/button";
        final String IDEA_CARD_X_PATH_TEXT = "/div[1]/textarea";
        final String IDEA_CARD_X_PATH_ADD_CARD = "/div";


        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/textarea")).sendKeys("Idea Card 1");
        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[1]/button")).click();

        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div[2]/div")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div[2]/div[1]/textarea")).sendKeys("Idea Card 2");
        driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/div/div[2]/div[1]/div[1]/button")).click();

        for (int i = 3; i <= number; i++) {
            name = Integer.toString(i);
            dynamicPart = "div[" + name + "]";
            if (isLogged) System.out.println(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_ADD_CARD);
            driver.findElement(By.xpath(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_ADD_CARD)).click();

            if (isLogged) System.out.println(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_TEXT);
            driver.findElement(By.xpath(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_TEXT)).sendKeys("Idea Card " + name);

            if (isLogged) System.out.println(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_BUTTON);
            driver.findElement(By.xpath(IDEA_CARD_X_PATH_FIRST_PART + dynamicPart + IDEA_CARD_X_PATH_BUTTON)).click();
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void columnsCreation(WebDriver driver, int number, boolean isLogged) {

        //Deprecated - XPATH for Adding column became constant for all columns
        final String ADD_COLUMN_X_PATH_FIRST_PART_OBS = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div/div/";
        final String ADD_COULMN_XPATH_FIRSTPART_NEW = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div[2]/div";
        final String RENAME_COLUMN_X_PATH_SECOND_PART = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div/div/";

        String counterString;
        String dynamicPart;
        String currentXpathForAdding;
        String currentXpathForRenaming;
        int shiftedValue;

        for (int i = 1; i <= number; i++) {
            shiftedValue = i + 2;
            counterString = Integer.toString(shiftedValue);
            dynamicPart = "div[" + counterString + "]";

            //Deprecated - XPATH for Adding column became constant for all columns
//            currentXpathForAdding = ADD_COLUMN_X_PATH_FIRST_PART + dynamicPart + "/div";
            currentXpathForAdding = ADD_COULMN_XPATH_FIRSTPART_NEW;
            currentXpathForRenaming = RENAME_COLUMN_X_PATH_SECOND_PART + dynamicPart + "/div[1]/input";

            if (isLogged) System.out.println("Log: " + currentXpathForAdding);
            driver.findElement(By.xpath(currentXpathForAdding)).click();

            if (isLogged) System.out.println("Log: " + currentXpathForRenaming);
            driver.findElement(By.xpath(currentXpathForRenaming)).sendKeys("Column " + shiftedValue);
            if (isLogged) System.out.println("Column" + counterString + " created. Xpath=" + currentXpathForAdding);
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    public static void cardsFirstBoardGeneration(WebDriver driver, int numberX, int numberY, boolean isLogged) throws InterruptedException {
        String stringCounterX;
        String stringCounterY;
        String dynamicPartX;
        String dynamicPartY;
        String currentXpathForAdding;
        String currentXpathForNaming;
        String currentXpathForSaving;

        final String BOARD_CARD_X_PATH_FIRST_PART = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[2]/";
        final String BOARD_CARD_X_PATH_FIRST_PART_V2 = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[2]/div/div/";
        final String BOARD_CARD_X_PATH_ADD_CARD = "/div";
        final String BOARD_CARD_X_PATH_TEXT = "/div/textarea";
        final String BOARD_CARD_X_PATH_BUTTON = "/div/div[1]/button";
        final String BOARD_CARD_X_PATH_MEDIUM = "/div[2]/";

        if (numberY > 2) columnsCreation(driver, numberY - 2, isLogged);

        for (int i = 1; i <= numberX; i++) {
            stringCounterX = Integer.toString(i);
            if (i == 1) dynamicPartX = "div";
            else dynamicPartX = "div[" + stringCounterX + "]";

            for (int j = 1; j <= numberY; j++) {
                stringCounterY = Integer.toString(j);
                dynamicPartY = "div[" + stringCounterY + "]";

                currentXpathForAdding = BOARD_CARD_X_PATH_FIRST_PART_V2 + dynamicPartY + BOARD_CARD_X_PATH_MEDIUM + dynamicPartX + BOARD_CARD_X_PATH_ADD_CARD;
                currentXpathForNaming = BOARD_CARD_X_PATH_FIRST_PART_V2 + dynamicPartY + BOARD_CARD_X_PATH_MEDIUM + dynamicPartX + BOARD_CARD_X_PATH_TEXT;
                currentXpathForSaving = BOARD_CARD_X_PATH_FIRST_PART_V2 + dynamicPartY + BOARD_CARD_X_PATH_MEDIUM + dynamicPartX + BOARD_CARD_X_PATH_BUTTON;

                Thread.sleep(200);
                if (isLogged) System.out.println(currentXpathForAdding);

                driver.findElement(By.xpath(currentXpathForAdding)).click();

                if (isLogged) System.out.println(currentXpathForNaming);
                driver.findElement(By.xpath(currentXpathForNaming)).sendKeys("Board Card " + stringCounterX + stringCounterY);

                if (isLogged) System.out.println(currentXpathForSaving);
                driver.findElement(By.xpath(currentXpathForSaving)).click();

            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void todoCardRemoval(WebDriver driver, TodoCardStatus todoCardStatus, LogType logType) throws InterruptedException {

        String cardName;

        final String TODO_UNFINISHED_CARD_STATUS = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[1]/div";
        final String TODO_FINISHED_CARD_STATUS = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div[1]/div";

        final String TODO_UNFINISHED_FIRST_CARD = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[3]";
        final String TODO_FINISHED_FIRST_CARD = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div[3]";

        final String TODO_UNFINISHED_CARD_STATUS_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[1]";
        final String TODO_SHOWALL_LINK_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[2]";

        final String TODO_BUTTON_XPATH = "//div/div/div[3]";

        if (!driver.findElement(By.cssSelector(".workspace-sidepanel-title-content")).isDisplayed())
            driver.findElement(By.xpath(TODO_BUTTON_XPATH)).click();

        if ((todoCardStatus == TodoCardStatus.REMOVEALLUNFINISHED)) {
            driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS_XPATH)).click();
            try {
                if (logType == LogType.XPATHLOG)
                    System.out.println("TODO_UNFINISHED_CARD_STATUS : " + TODO_UNFINISHED_CARD_STATUS);
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("CONDITION, Are they Presented?=" + driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS)).isDisplayed());
                while (driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS)).isDisplayed()) {
                    if (logType == LogType.XPATHLOG) System.out.println(By.xpath(TODO_UNFINISHED_FIRST_CARD));
                    {
                        cardName = driver.findElement(By.xpath(TODO_UNFINISHED_FIRST_CARD)).getText();

                        driver.findElement(By.xpath(TODO_UNFINISHED_FIRST_CARD)).click();
                        driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-remove.js-tap-indication.js-popup-remove")).click();
                        Thread.sleep(300);
                        if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                            System.out.println("Log: " + General.nowTime() + " UnFinished TODO Card [ " + cardName + " ]were removed");
                    }

                }
            } catch (NoSuchElementException e) {
                if (logType != LogType.NOLOG) System.out.println("Nothing to Remove!");
            }
        }

        if (todoCardStatus == TodoCardStatus.REMOVEALLFINISHED) {
            driver.findElement(By.xpath(TODO_SHOWALL_LINK_XPATH)).click();
            Thread.sleep(300);
            if (logType == LogType.XPATHLOG) System.out.println("TODO_SHOWALL_LINK_XPATH : " + TODO_SHOWALL_LINK_XPATH);
            try {
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("CONDITION=" + driver.findElement(By.xpath(TODO_FINISHED_CARD_STATUS)).isDisplayed());
                while (driver.findElement(By.xpath(TODO_FINISHED_CARD_STATUS)).isDisplayed()) {

                    if (logType == LogType.XPATHLOG) System.out.println(By.xpath(TODO_FINISHED_FIRST_CARD));
                    cardName = driver.findElement(By.xpath(TODO_FINISHED_FIRST_CARD)).getText();
                    driver.findElement(By.xpath(TODO_FINISHED_FIRST_CARD)).click();

                    Thread.sleep(300);
                    driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-remove.js-tap-indication.js-popup-remove")).click();
                    Thread.sleep(300);
                    if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                        System.out.println("Log: " + General.nowTime() + " Finished TODO Card [ " + cardName + " ]were removed");
                }
            } catch (NoSuchElementException e) {
                if (logType != LogType.NOLOG) System.out.println("Nothing to Remove!");
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void todoCardCreation(WebDriver driver, int number, TodoCardStatus todoCardStatus, LogType logType) throws InterruptedException {

        String currentXpathForAdding;
        String currentXpathForNaming;
        String currentXpathForSaving;
        String dynamicPart;
        String cardName;
        String stringNumber;

        final String TODO_BUTTON_XPATH = "//div/div/div[3]";
        final String TODO_MARK_AS_COMPLETED_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[1]/div";
        final String TODO_ADDCARD_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/div/";
        final String TODO_ADDCARD_XPATH_LAST = "/div";
        final String TODO_ENTERCARD_XPATH_LAST = "/div/textarea";
        final String TODO_SAVECARD_XPATH_LAST = "/div/div[1]/button";

        final String TODO_FINISHED_CARD_STATUS_CSS = ".card-task-statusbutton.card-task-statusbutton-on";
        final String TODO_UNFINISHED_CARD_STATUS_CSS = ".card-task-statusbutton.card-task-statusbutton-off";

        final String TODO_UNFINISHED_CARD_STATUS_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[1]";
        final String TODO_SHOWALL_LINK_XPATH = "html/body/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[2]";

        final String TODO_SHOW_ALL_CSS = ".todo-filter-box.js-todo-all.js-tap-indication.js-tap-direct.todo-checked";
        final String TODO_UNFINISHED_CSS = ".todo-filter-box.js-todo-notdone.js-tap-indication.js-tap-direct.todo-checked";

        if (!driver.findElement(By.cssSelector(".workspace-sidepanel-title-content")).isDisplayed())
            driver.findElement(By.xpath(TODO_BUTTON_XPATH)).click();

        Thread.sleep(500);
        if ((todoCardStatus == TodoCardStatus.CREATEUNFINISHED) || (todoCardStatus == TodoCardStatus.CREATEFINISHED))
            for (int i = 1; i <= number; i++) {

                stringNumber = Integer.toString(i);
                cardName = "TODO Card " + stringNumber;
                dynamicPart = "div[" + i + "]";

                if (i == 1) currentXpathForAdding = TODO_ADDCARD_XPATH + "div" + TODO_ADDCARD_XPATH_LAST;
                else currentXpathForAdding = TODO_ADDCARD_XPATH + dynamicPart + TODO_ADDCARD_XPATH_LAST;

                driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS_XPATH)).click();

                driver.findElement(By.xpath(currentXpathForAdding)).click();
                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + General.nowTime() + " ADD_CARD_XPATH:" + currentXpathForAdding);
                Thread.sleep(300);

                currentXpathForNaming = TODO_ADDCARD_XPATH + dynamicPart + TODO_ENTERCARD_XPATH_LAST;
                driver.findElement(By.xpath(currentXpathForNaming)).sendKeys(cardName);
                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + General.nowTime() + " " + currentXpathForNaming);
                Thread.sleep(300);

                currentXpathForSaving = TODO_ADDCARD_XPATH + dynamicPart + TODO_SAVECARD_XPATH_LAST;
                driver.findElement(By.xpath(currentXpathForSaving)).click();
                Thread.sleep(300);

                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + General.nowTime() + " " + currentXpathForSaving);
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("Log: " + General.nowTime() + " : UnFinished TODO Card " + stringNumber + " were created");
            }

        if ((todoCardStatus == TodoCardStatus.CREATEFINISHED) || (todoCardStatus == TodoCardStatus.MARKFINISHED))
            for (int i = 1; i <= number; i++) {
                Thread.sleep(1000);
                driver.findElement(By.xpath(TODO_MARK_AS_COMPLETED_XPATH)).click();
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("Log: " + General.nowTime() + " : Unfinished TODO Card " + i + " were marked as Completed");
            }
    }

    private static boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}