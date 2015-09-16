import enums.PageSharingMode;
import enums.TodoCardStatus;
import enums.WidgetColor;
import enums.WidgetState;
import enums.LogType;

import org.openqa.selenium.By;
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
    public static void pagesCreation(WebDriver driver, int number, PageSharingMode pageSharingMode, boolean isLogged) {

        final String ADD_PAGE_XPATH = "html/body/div[2]/div[2]/div[1]/img";
        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[5]";
        final String TYPE_PAGENAME_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[1]/input";
        final String SHARE_WITHORG_XPATH = "html/body/div[3]/div/div/div[1]/div[1]/div[3]/div[5]/div/img";
        String name;

        for (int i = 1; i <= number; i++) {
            name = "Collection" + Integer.toString(i);
            driver.findElement(By.xpath(ADD_PAGE_XPATH)).click();
            if (isLogged) System.out.println(nowTime() + " Collection " + i + " was created:");
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).clear();
            driver.findElement(By.xpath(TYPE_PAGENAME_XPATH)).sendKeys(name);
            if (pageSharingMode == PageSharingMode.PUBLIC) driver.findElement(By.xpath(SHARE_WITHORG_XPATH)).click();
            driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pagesRemoving(WebDriver driver, int number, boolean isLogged) {

        final String OPEN_PAGEMENU_XPATH = "html/body/div[2]/div[2]/div[2]/div[1]/div/div/div[1]";
        final String DELETE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[1]";
        String name;

        for (int i = 1; i <= number; i++) {
            name = "Collection" + Integer.toString(i);
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
            if (isLogged) System.out.println(nowTime() + " Collection " + i + " was removed:");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void pagesArchiving(WebDriver driver, int number, boolean isLogged) {

        final String ADD_PAGE_XPATH = "html/body/div[2]/div[2]/div[1]/img";
        final String DONE_BUTTON_XPATH = "html/body/div[3]/div/div/div[1]/div[2]/div/div[5]";
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
            driver.findElement(By.xpath(DONE_BUTTON_XPATH)).click();
            driver.findElement(By.xpath(OPEN_PAGEMENU_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
            driver.findElement(By.xpath(PAGE_ARCHIVE_XPATH)).click();
        }
    }

    public static void addUserToOrganization(WebDriver driver) throws InterruptedException {
        String[] usernames = {"livatek.user1@gmail.com", "livatek.user2@gmail.com", "livatek.user4@gmail.com"};
        try {
            driver.findElement(By.cssSelector(".avatar-initials")).click();

        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector("..avatar-image")).click();
        }
        driver.findElement(By.cssSelector("div.app-menu-choice.js-org-invite")).click();
        driver.findElement(By.id("invitations")).click();
        System.out.println("Waiting");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".fastcardpopup-userfield.fastcardpopup-taskinput-input.js-workspacedit-invitation")).sendKeys(usernames[0]);
        System.out.println("Waiting2");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='js-workspacedit-invitations']/div/div/button")).click();


    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void widgetsCreation(WebDriver driver, int number, WidgetState widgetState, WidgetColor widgetColor, boolean isLogged) {

        final String IDEA_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div/div[4]/img";
        final String BOARD_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div/div[4]/img";
        final String WIDGET_BOARD_OPTIONS_CSS=".widget-menu-choice.js-widget-showboardoptionsmenu.js-tap-indication";

        String name;
        String colorId;
        String colorName="";
        int colorCode = 0;
        for (int i = 11; i < 11 + number; i++) {

            if (widgetColor == WidgetColor.RANDOM) {
                if (i % 10 == 0) colorCode = 10;
                else colorCode = i % 10;
                colorId = Integer.toString(colorCode);
                colorName=     WidgetColor.values()[colorCode].toString();
            } else if (widgetColor == WidgetColor.DEFAULT) {
                colorId = "1";
                colorName=WidgetColor.values()[colorCode].toString();
            } else {
                colorId = Integer.toString(widgetColor.ordinal());
                colorName=widgetColor.toString();
            }

            name = Integer.toString(i - 10) + " " + colorName;
            //Create Idea Widget with Sequential Name 1,2,..
            driver.findElement(By.cssSelector("img.xaddbutton-icon")).click();
            driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/input")).sendKeys("Idea " + name);
            driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/button")).click();
            if (isLogged) System.out.println(nowTime() + " Idea " + name + " was created:");

            //Create Board Widget with Sequential Name 1,2,..
            driver.findElement(By.cssSelector("div.xaddbutton-maindiv.js-workspace-tracking-add-board > img.xaddbutton-icon")).click();
            driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/input")).sendKeys("Board " + name);
            driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/button")).click();
            if (isLogged) System.out.println(nowTime() + " Board " + name + " was created:");

            if (widgetState == WidgetState.COLLAPSED) {
                driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]")).click();
                if (isLogged) System.out.println(nowTime() + " Idea " + name + " was collapsed:");
                driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/div[2]/div")).click();
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
    public static void widgetsRemoval(WebDriver driver) {

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static void firstIdeaCardsGeneration(WebDriver driver, int number, boolean isLogged) {
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

    public static void firstBoardCardsGeneration(WebDriver driver, int numberX, int numberY, boolean isLogged) {
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


        if ((todoCardStatus == TodoCardStatus.REMOVEALLUNFINISHED)) {
            driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS_XPATH)).click();
            if (logType == LogType.XPATHLOG)
                System.out.println("TODO_UNFINISHED_CARD_STATUS : " + TODO_UNFINISHED_CARD_STATUS);
            try {
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
                System.out.println("Nothing to Remove!");
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
                System.out.println("Nothing to Remove!");
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

