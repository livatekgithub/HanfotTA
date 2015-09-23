import enums.*;
import org.openqa.selenium.WebDriver;

public class Run {
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException {

        System.out.println(General.nowTime() + " : 1. Pages Creation(10)");
        General.pagesCreation(driver, 8, PageSharingMode.PRIVATE, false);
        General.pagesCreation(driver, 8, PageSharingMode.PUBLIC, false);

        System.out.println(General.nowTime() + " : 2. Pages Archiving(10)");
        General.pagesArchiving(driver, 20, false);

        System.out.println(General.nowTime() + " : 3. Pages Removing(5)");
        General.pagesRemoving(driver, 5, false);

        System.out.println(General.nowTime() + " : 4a. Widgets Creation(2). COLLAPSED. Fixed Color ");
        General.widgetsCreation(driver, 10, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);

        System.out.println(General.nowTime() + " : 4b. Widgets Creation(2). EXPANDED. Random ");
        General.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);

        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.EMERALD, false);
        System.out.println(General.nowTime() + " : 5. Columns Creation(3)");
        General.columnsCreation(driver, 8, false);

        System.out.println(General.nowTime() + " : 6. Cards Creation(4*8)");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.firstBoardCardsGeneration(driver, 30, 3, false);
        General.firstIdeaCardsGeneration(driver, 30, false);

        //Testing of Removal
        System.out.println(General.nowTime() + " : 7a. Clearing - TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : 7b. Clearing - TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Finished card creation and removal
        System.out.println(General.nowTime() + " : 7c. TODO Create Finished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : 7c. TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Unfinished card creation and removal
        System.out.println(General.nowTime() + " : 7d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : 7a. TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        //Testing of marking unfinished cards
        System.out.println(General.nowTime() + " : 7d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : 7e. TODO Cards Marking as Finished(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.MARKFINISHED, LogType.NOLOG);

        //Testing of creation of finished cards
        System.out.println(General.nowTime() + " : 7f. TODO Create Finished Cards(20)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        //Testing of creation of unfinished cards
        System.out.println(General.nowTime() + " : 7g. TODO Create Unfinished Cards(20)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
    }

    public static void Run(WebDriver driver) throws InterruptedException {

//        runTestAllMethods(driver, false);

        General.pagesCreation(driver,10,PageSharingMode.PUBLIC,true);
        General.pagesArchiving(driver,5,true);


    }

}
