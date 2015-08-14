/**
 * Created by kyak on 03.08.2015.
 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginTestIE {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.ie.driver", "D://Java/selenium/ie/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        baseUrl = AccessData.TESTURL;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() throws Exception {
        driver.get(AccessData.TESTURL);
        driver.findElement(By.id("form-email")).clear();
        driver.findElement(By.id("form-email")).sendKeys("livatek.user15@ukr.net");
        driver.findElement(By.id("form-psw")).clear();
        driver.findElement(By.id("form-psw")).sendKeys("User0215");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.btn-submit.js-tap-indication")).click();
        assertTrue(isElementPresent(By.cssSelector("span.avatar-initials")));
        driver.findElement(By.cssSelector("span.avatar-initials")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div.app-menu-choice.js-signout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}


