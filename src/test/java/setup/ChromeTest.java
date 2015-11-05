package setup; /**
 * Created by kyak on 03.08.2015.
 */

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Users;
import utils.AccessData;
import utils.Service;
import utils.WindowOperations;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ChromeTest {
    private WebDriver driver;
    private final String browser = "CHROME";
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", AccessData.CHROME_DRIVER__PATH + "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = AccessData.TESTURL;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() throws Exception {
        Users.loginUser(driver);
        tests.Run.Run(driver, browser);
//        General.userSignOut(driver);
//        General.loginUserWithCash(driver,10);
    }

    @After
    public void tearDown() throws Exception {
        Service.takeScreenshot(driver, Service.nowTimeForFileName() + " " + browser + ".screenshot.png");

        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}


