package pageobjects;

import enums.LogType;
import enums.TodoCardStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import tests.Run;
import utils.Service;

public class Todo {

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
                        Thread.sleep(5000);
                        cardName = driver.findElement(By.xpath(TODO_UNFINISHED_FIRST_CARD)).getText();
                        driver.findElement(By.xpath(TODO_UNFINISHED_FIRST_CARD)).click();
                        driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-remove.js-tap-indication.js-popup-remove")).click();
                        Thread.sleep(300);
                        if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                            System.out.println("Log: " + Service.nowTime() + " UnFinished TODO Card [ " + cardName + " ]were removed");
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
                        System.out.println("Log: " + Service.nowTime() + " Finished TODO Card [ " + cardName + " ]were removed");
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
        final String TODO_ENTERCARD_XPATH_LAST = "/div/div[1]/textarea";
        final String TODO_SAVECARD_XPATH_LAST = "/div/div[1]/div/button";
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
                cardName = Run.currentBrowser + ".TODO Card " + stringNumber;
                dynamicPart = "div[" + i + "]";
                if (i == 1) currentXpathForAdding = TODO_ADDCARD_XPATH + "div" + TODO_ADDCARD_XPATH_LAST;
                else currentXpathForAdding = TODO_ADDCARD_XPATH + dynamicPart + TODO_ADDCARD_XPATH_LAST;
                driver.findElement(By.xpath(TODO_UNFINISHED_CARD_STATUS_XPATH)).click();
                driver.findElement(By.xpath(currentXpathForAdding)).click();
                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + Service.nowTime() + " ADD_CARD_XPATH:" + currentXpathForAdding);
                Thread.sleep(500);
                currentXpathForNaming = TODO_ADDCARD_XPATH + dynamicPart + TODO_ENTERCARD_XPATH_LAST;
                driver.findElement(By.xpath(currentXpathForNaming)).sendKeys(cardName);
                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + Service.nowTime() + " " + currentXpathForNaming);
                Thread.sleep(300);
                currentXpathForSaving = TODO_ADDCARD_XPATH + dynamicPart + TODO_SAVECARD_XPATH_LAST;
                driver.findElement(By.xpath(currentXpathForSaving)).click();
                Thread.sleep(300);
                if (logType == LogType.XPATHLOG)
                    System.out.println("Log: " + Service.nowTime() + " " + currentXpathForSaving);
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("Log: " + Service.nowTime() + " : UnFinished TODO Card " + stringNumber + " were created");
            }
        if ((todoCardStatus == TodoCardStatus.CREATEFINISHED) || (todoCardStatus == TodoCardStatus.MARKFINISHED))
            for (int i = 1; i <= number; i++) {
                Thread.sleep(1000);
                driver.findElement(By.xpath(TODO_MARK_AS_COMPLETED_XPATH)).click();
                if ((logType == LogType.ACTIONLOG) || (logType == LogType.XPATHLOG))
                    System.out.println("Log: " + Service.nowTime() + " : Unfinished TODO Card " + i + " were marked as Completed");
            }
    }
}
