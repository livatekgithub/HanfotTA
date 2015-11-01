package pageobjects;

import enums.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.AccessData;
import utils.Service;
import utils.WindowOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Users implements utils.AccessData {

    final static String MAIN_MENU_XPATH="html/body/div[2]/div[1]/div[1]/div[1]/a/div/img";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loginUser(WebDriver driver) throws InterruptedException {
        driver.get("https://test.hansoftx.com/logout");
        driver.manage().window().maximize();
//        WindowOperations.resizeWindowforIdea(driver);
        Thread.sleep(1000);
        driver.get(TESTURL);
        driver.findElement(By.id("form-email")).clear();
        driver.findElement(By.id("form-email")).sendKeys(TESTLOGIN);
        driver.findElement(By.id("form-psw")).clear();
        driver.findElement(By.id("form-psw")).sendKeys(TESTPASSWORD);
        Thread.sleep(3000);
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
    public static void usersAddToOrganization(WebDriver driver, int numberOfUsers, boolean isLogged) throws InterruptedException {

        final String ADD_USER_FIELD_XPATH_BEGIN = "html/body/div[4]/div/div/div[1]/div/div[2]/div[3]/div[2]/";
        final String ADD_USER_FIELD_XPATH_END = "/div[1]/input";

        String name;
        String dynamicPart;

        driver.findElement(By.xpath(MAIN_MENU_XPATH)).click();
        driver.findElement(By.cssSelector("div.app-menu-choice.js-org-invite")).click();
        driver.findElement(By.id("invitations")).click();

        for (int i = 0; i < numberOfUsers; i++) {
            name = Integer.toString(i + 1);
            if (i == 0) dynamicPart = "div";
            else dynamicPart = "div[" + name + "]";
            if (isLogged) System.out.println(TestData.testUserNames[i]);
            if (isLogged) System.out.println(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END);
            driver.findElement(By.xpath(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END)).sendKeys(TestData.testUserNames[i]);
            if (isLogged)
                System.out.println(Service.nowTime() + " User" + (i + 1) + ": " + TestData.testUserNames[i] + " was added:");
            driver.findElement(By.cssSelector(".fastcardpopup-taskinput-button.workspaceedit-add-button.js-workspacedit-add-invitation")).click();
        }
        driver.findElement(By.cssSelector(".ui-button-blue.js-workspacedit-sendinvitations")).click();
        driver.findElement(By.cssSelector(".popup-window-toolbar-button.mod-cancel.js-popup-cancel")).click();
        driver.findElement(By.cssSelector(".toolpopup-popup-bg")).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersRemoveAllFromOrganization(WebDriver driver, boolean isLogged) throws InterruptedException {

        String userName;
        final String LAST_USER_NAME = "html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]";
        final String SECOND_USER_CAN = "html/body/div[4]/div/div/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[2]/div[2]/div[2]/div";

        driver.findElement(By.xpath(MAIN_MENU_XPATH)).click();
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
    private static boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
