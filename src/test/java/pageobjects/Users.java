package pageobjects;

import enums.TestData;
import enums.UserOrgRoles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utils.AccessData;
import utils.Service;
import utils.WindowOperations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Users implements utils.AccessData {
    //XPATH examples
    //div[text()="HX 5"]/parent::*/parent::*/following-sibling::*/child::div[2]
    //div[text()="hansoftxtest5@gmail.com"]/parent::*/parent::*/following-sibling::*/child::div[1]/child::*/child::option[1]

    final static String MAIN_MENU_XPATH = "html/body/div[2]/div[1]/div[1]/div[1]/a/div/img";
    final static String USER_MAIN_MENU_MEMBERS_LIST_CSS = "div.app-menu-choice.js-org-admin";
    final static String USER_ORG_MENU_CLOSE_BUTTON_CSS = ".popup-window-toolbar-button.mod-cancel.js-popup-cancel";
    final static String USER_ORG_MENU_DISMISS_CSS = ".toolpopup-popup-bg";

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loginUser(WebDriver driver) throws InterruptedException {
        driver.get("https://test.hansoftx.com/logout");
//        driver.manage().window().maximize();
        WindowOperations.resizeWindowforIdea(driver);
        driver.get(TESTURL);
        Thread.sleep(1000);
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
    public static void usersOpenMembersList(WebDriver driver) {
        driver.findElement(By.xpath(MAIN_MENU_XPATH)).click();
        driver.findElement(By.cssSelector(USER_MAIN_MENU_MEMBERS_LIST_CSS)).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void closeEditOrganizationMenu(WebDriver driver) {
        driver.findElement(By.cssSelector(USER_ORG_MENU_CLOSE_BUTTON_CSS)).click();
        driver.findElement(By.cssSelector(USER_ORG_MENU_DISMISS_CSS)).click();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersAddToOrganization(WebDriver driver, int numberOfUsers, String[] testNamesForTest, boolean isLogged) throws InterruptedException {

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
            if (isLogged) System.out.println(testNamesForTest[i]);
            if (isLogged) System.out.println(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END);
            driver.findElement(By.xpath(ADD_USER_FIELD_XPATH_BEGIN + dynamicPart + ADD_USER_FIELD_XPATH_END)).sendKeys(testNamesForTest[i]);
            if (isLogged)
                System.out.println(Service.nowTime() + " User" + (i + 1) + ": " + testNamesForTest[i] + " was added:");
            driver.findElement(By.cssSelector(".fastcardpopup-taskinput-button.workspaceedit-add-button.js-workspacedit-add-invitation")).click();
        }
        driver.findElement(By.cssSelector(".ui-button-blue.js-workspacedit-sendinvitations")).click();
        Thread.sleep(numberOfUsers * 1000);
        closeEditOrganizationMenu(driver);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersRemoveAllFromOrganization(WebDriver driver, boolean isLogged) throws InterruptedException {

        String userName;
        final String USER_SECOND_USER_EXIST_XPATH = "html/body/div[4]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div[2]/div[2]/div";
        final String USER_LAST_USER_NAME_XPATH = "html/body/div[4]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]";
        final String USER_LAST_USER_REMOVE_BUTTON_XPATH = "html/body/div[4]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[2]/div[2]/div";
        final String USER_LAST_USER_REMOVE_CONFIRM_BUTTON_XPATH = "html/body/div[4]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[2]/div";
        usersOpenMembersList(driver);
        while (isElementPresent(By.xpath(USER_SECOND_USER_EXIST_XPATH), driver)) {
            userName = driver.findElement(By.xpath(USER_LAST_USER_NAME_XPATH)).getText();
            if (isLogged) {
                System.out.println(userName + " was removed " + driver.findElement(By.xpath(USER_LAST_USER_REMOVE_BUTTON_XPATH)).isDisplayed());
            }
            Thread.sleep(500);
            driver.findElement(By.xpath(USER_LAST_USER_REMOVE_BUTTON_XPATH)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(USER_LAST_USER_REMOVE_CONFIRM_BUTTON_XPATH)).click();
        }
        closeEditOrganizationMenu(driver);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersSetupPermission(WebDriver driver, String userEmail, UserOrgRoles userOrgRoles) throws InterruptedException {
        String userRole="";
        switch(userOrgRoles){
            case ADMINISTRATOR:{
                userRole="Administrator";
            }break;
            case FULL_MEMBER:{
                userRole="Full member";
            }break;
            case RESTRICTED:{
                userRole="Restricted";
            }break;
            default:{
            }break;
        }
        String SELECT_XPATH="//div[text()=\""+userEmail+"\"]/parent::*/parent::*/following-sibling::*/child::div[1]/child::*";
        new Select(driver.findElement(By.xpath(SELECT_XPATH))).selectByVisibleText(userRole);
        Thread.sleep(2000);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void usersParticularUserRemoval(WebDriver driver, String userEmail) throws InterruptedException {
        Thread.sleep(2000);
        String DELETE_XPATH="//div[text()=\""+userEmail+"\"]/parent::*/parent::*/following-sibling::*/child::div[2]/div";
        String DELETE_CONFIRM_XPATH="//div[text()=\""+userEmail+"\"]/parent::*/parent::*/following-sibling::*/div";
        driver.findElement(By.xpath(DELETE_XPATH)).click();
        driver.findElement(By.xpath(DELETE_CONFIRM_XPATH)).click();
        Thread.sleep(2000);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static ArrayList usersGetListOfUsers(WebDriver driver) throws InterruptedException {
        ArrayList listOfOrgUsers = new ArrayList();
        Users.usersOpenMembersList(driver);
        List<WebElement> list = driver.findElements(By.cssSelector(".workspacedit-username"));
        for (WebElement element : list) {
            listOfOrgUsers.add(element.getText());
        }
        return listOfOrgUsers;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void createNewOrganization(WebDriver driver, String orgName) throws InterruptedException {
        final String ORGANIZATION_SELECT_XPATH = "html/body/div[3]/div[2]/div/div/div[11]/div";
        final String ORGANIZATION_CREATE_BUTTON_CSS = ".workspace-popup-button.workspace-popup-action.workspace-popup-action-create." +
                "js-createorganization.js-tap-indication.js-tap-indication-onwhite.js-tap-direct";
        final String ORGANIZATION_CREATE_ENTER_NAME_CSS = ".menu-popup-userfield.createorganization-input.js-createorganization-name";
        final String ORGANIZATION_CREATE_AGREE_CHECKBOX_CSS = ".createorganization-checkbox.createorganization-checkbox-disagree";
        final String ORGANIZATION_CREATE_NEXT_BUTTON_CSS = ".popup-window-toolbar-button.mod-done.js-createorganization-next";

        driver.findElement(By.xpath(MAIN_MENU_XPATH)).click();
        driver.findElement(By.xpath(ORGANIZATION_SELECT_XPATH)).click();
        driver.findElement(By.cssSelector(ORGANIZATION_CREATE_BUTTON_CSS)).click();
        driver.findElement(By.cssSelector(ORGANIZATION_CREATE_ENTER_NAME_CSS)).sendKeys(orgName);
        driver.findElement(By.cssSelector(ORGANIZATION_CREATE_AGREE_CHECKBOX_CSS)).click();
        driver.findElement(By.cssSelector(ORGANIZATION_CREATE_NEXT_BUTTON_CSS)).click();
        driver.findElement(By.cssSelector(ORGANIZATION_CREATE_NEXT_BUTTON_CSS)).click();
        Thread.sleep(5000);
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
