package pageobjects;

import enums.CollectionSharingMode;
import enums.WidgetColor;
import enums.WidgetState;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Service;

public class Tutorial {

    final static String TUTORIAL_RUN_XPATH = "//div[@class='app-menu-choice-title'][contains(text(),\"Run Tutorial\")]";

    public static void runTutorial(WebDriver driver, boolean isLogged) throws InterruptedException {
        Users.openMainMenu(driver);
        driver.findElement(By.xpath(TUTORIAL_RUN_XPATH)).click();
        System.out.println(Service.nowTime() + " Starting tutorial");
        System.out.println(Service.nowTime() + " *** Tutorial.Step1. Create a Collection");
        Service.waitFor(driver, By.xpath("//div[@class='xaddbutton-icon tutorial-hilight-pulse']"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='xaddbutton-icon tutorial-hilight-pulse']")).click();
        driver.findElement(By.xpath("//input[@id='menu-popup-input-name']")).clear();

        System.out.println(Service.nowTime() + " *** Tutorial.Step2. Name your Collection");
        driver.findElement(By.xpath("//input[@id='menu-popup-input-name']")).sendKeys("Tutorial Collection");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='popup-window-toolbar-button mod-done js-popup-done']")).click();
//        Thread.sleep(2000);

        System.out.println(Service.nowTime() + " *** Tutorial.Step3. Create an Idea List");
        Service.waitFor(driver, By.cssSelector(".xaddbutton-icon.tutorial-hilight-pulse"));
        driver.findElement(By.cssSelector(".xaddbutton-icon.tutorial-hilight-pulse")).click();
        Thread.sleep(1000);

        System.out.println(Service.nowTime() + " *** Tutorial.Step4. Name the Idea List");
        driver.findElement(By.xpath("//input[@class='widget-title-edit js-textfield-textarea']")).sendKeys("Tutorial Idea");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='widget-title-save js-textfield-save']")).click();
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step5. Add at least three cards");
        String addCardxPath;
        for (int i = 1; i <= 3; i++) {
            if (i == 1) {
                addCardxPath = "//div[@class='board-column-addcard js-board-column-add js-nav-to" +
                        " js-create-item js-tap-indication js-tap-direct tutorial-hilight-pulse']";
            } else {
                addCardxPath = "(//div[@class='board-column-addcard js-board-column-add js-nav-to"+
                        " js-create-item js-tap-indication js-tap-direct'])[2]";
            }
            System.out.println(Service.nowTime()+" "+addCardxPath);
            Thread.sleep(2000);
            driver.findElement(By.xpath(addCardxPath)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//textarea[@class='boarditem-card-edit js-textfield-textarea']")).sendKeys("Tutorial Card " + i);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class='board-title-save js-textfield-save']")).click();
            Thread.sleep(1000);
        }
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step6. Edit you card details");
        System.out.println(Service.nowTime() + " " + "//div[@class='card-as-board-card js-nav-to card js-boarditem'][last()]");
        driver.findElement(By.xpath("//div[@class='card-as-board-card js-nav-to card js-boarditem'][last()]")).click();
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step7. Editing a Card");
        driver.findElement(By.xpath("//div[@class='ui-button-black cardpopup-sidebar-button js-tap-indication js-add-users tutorial-hilight-pulse']")).click();
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step8. Editing a Card");
        driver.findElement(By.xpath("//input[@id='js-add-user-autocomplete']")).click();
        driver.findElement(By.xpath("//input[@id='js-add-user-autocomplete']")).sendKeys("use");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='js-add-user-autocomplete']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step9. Finish Editing a Card");
        driver.findElement(By.xpath("//div[@class='cardpopup-close js-close-cardpopup js-tap-indication tutorial-hilight-pulse']")).click();
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step10. Create a Board");
        driver.findElement(By.xpath("//div[@class='xaddbutton-icon tutorial-hilight-pulse']")).click();
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " *** Tutorial.Step11. Name Your Board");
        System.out.println(Service.nowTime() + " *** Tutorial.Step12. Start Working on an Idea"); //Write a method for moving cards
        System.out.println(Service.nowTime() + " *** Tutorial.Step13. This card is now being tracked"); // Press Got it
        System.out.println(Service.nowTime() + " *** Tutorial.Step14. Update the card status"); //Move card to Done column
        System.out.println(Service.nowTime() + " *** Tutorial.Step15. Tracking Progress"); //Got it
        System.out.println(Service.nowTime() + " *** Tutorial.Step16. Searching"); //Press Search icon
        System.out.println(Service.nowTime() + " *** Tutorial.Step17. Search for a card"); // Enter search query
        System.out.println(Service.nowTime() + " *** Tutorial.Step18. Searching"); // Dismiss
        System.out.println(Service.nowTime() + " *** Tutorial.Step19. To do list"); // Press To do icon
        System.out.println(Service.nowTime() + " *** Tutorial.Step20. Working with To do list"); // Got it
        System.out.println(Service.nowTime() + " *** Tutorial.Step21. The end"); // The end






        Thread.sleep(5000);



    }
}
