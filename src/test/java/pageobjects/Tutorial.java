package pageobjects;

import enums.CollectionSharingMode;
import enums.WidgetColor;
import enums.WidgetState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Service;

public class Tutorial {

    final static String TUTORIAL_RUN_XPATH="//div[@class='app-menu-choice-title'][contains(text(),\"Run Tutorial\")]";

    public static void runTutorial(WebDriver driver,boolean isLogged) throws InterruptedException {
        Users.openMainMenu(driver);
        driver.findElement(By.xpath(TUTORIAL_RUN_XPATH)).click();
        System.out.println(Service.nowTime() + " Starting tutorial");
        Thread.sleep(2000);
        Collection.collectionCreation(driver, 1, CollectionSharingMode.PUBLIC, false);
        System.out.println(Service.nowTime() + " Tutorial.Step1. Adding Collection");
        System.out.println(Service.nowTime() + " Tutorial.Step2. Naming Collection");
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " Tutorial.Step3. Creating Idea");
        //SEPARATE METHODS for Ideas and Boards!!! to be able to create one board and one Idea
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false, false);
        Thread.sleep(2000);
        System.out.println(Service.nowTime() + " Tutorial.Step4. Creating Idea");
        driver.findElement(By.cssSelector(".xaddbutton-icon.tutorial-hilight-pulse")).click();

        Thread.sleep(5000);
        Card.cardOpenIdeaCard(driver, 1, false);
        System.out.println(Service.nowTime() + " Tutorial.Step5. Creating Idea");
        Thread.sleep(5000);


    }
}
