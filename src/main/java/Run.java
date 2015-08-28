import enums.*;
import org.openqa.selenium.WebDriver;

public class Run {
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException {


        System.out.println(General.nowTime() + " : 1. Pages Creation(10)");
        General.pagesCreation(driver, 10, PageSharingMode.PRIVATE, false);
        General.pagesCreation(driver, 10, PageSharingMode.PUBLIC, false);

        System.out.println(General.nowTime() + " : 2. Pages Archiving(10)");
        General.pagesArchiving(driver, 10, false);

        System.out.println(General.nowTime() + " : 3. Pages Removing(5)");
        General.pagesRemoving(driver, 5, false);

        System.out.println(General.nowTime() + " : 4a. Widgets Creation(2). COLLAPSED. Fixed Color ");
        General.widgetsCreation(driver, 10, WidgetState.COLLAPSED, WidgetColor.YELLOW, false);

        System.out.println(General.nowTime() + " : 4b. Widgets Creation(2). EXPANDED. Random ");
        General.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);

        /*

        System.out.println(General.nowTime() + " : 5. Columns Creation(3)");
        General.columnsCreation(driver, 8, false);

        System.out.println(General.nowTime() + " : 6. Cards Creation(4*8)");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.firstBoardCardsGeneration(driver, 30, 3, false);
        General.firstIdeaCardsGeneration(driver, 30, false);


        System.out.println(General.nowTime() + " : 7a. TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7b. TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7c. TODO Create Finished Cards(10)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7c. TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEUNFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7a. TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEUNFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7e. TODO Cards Marking as Finished(10)");
        General.todoCardCreation(driver, 10, TodoCardStatus.MARKFINISHED, LogType.ACTIONLOG);

        System.out.println(General.nowTime() + " : 7f. TODO Create Unfinished Cards(20)");
        General.todoCardCreation(driver, 20, TodoCardStatus.CREATEFINISHED, LogType.ACTIONLOG);
        */
    }

    public static void Run(WebDriver driver) throws InterruptedException {


        runTestAllMethods(driver, false);

//        General.addUserToOrganization(driver);

//        General.todoCardRemoval(driver,TodoCardStatus.REMOVEALLUNFINISHED,LogType.XPATHLOG);
//        General.todoCardRemoval(driver,TodoCardStatus.REMOVEALLFINISHED,LogType.XPATHLOG);
//
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.XPATHLOG);
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.XPATHLOG);


//        General.todoCardCreation(driver,0,TodoCardStatus.REMOVEALLUNFINISHED,true);
//        General.todoCardCreation(driver,15,TodoCardStatus.CREATEUNFINISHED,true);
//        General.todoCardCreation(driver,0,TodoCardStatus.REMOVEALLUNFINISHED,true);
//        General.todoCardCreation(driver,30,TodoCardStatus.CREATEUNFINISHED,true);
//        General.todoCardCreation(driver,0,TodoCardStatus.REMOVEALLUNFINISHED,true);
//        General.todoCardCreation(driver,30,TodoCardStatus.CREATEUNFINISHED,true);

//        General.todoCardCreation(driver,0,TodoCardStatus.REMOVEALLUNFINISHED,true);

//        General.todoCardCreation(driver,0,TodoCardStatus.REMOVEALLFINISHED,true);

//        General.todoCardCreation(driver,10,TodoCardStatus.REMOVEALL,true);


//        General.todoCardCreation(driver, 10, TodoCardStatus.MARKFINISHED);

//        General.widgetsCreation(driver,14,WidgetState.EXPANDED);

//        General.widgetsCreation(driver,7,WidgetState.EXPANDED,WidgetColor.BLUE);
//        General.firstBoardCardsGeneration(driver,200,10);
//        General.firstIdeaCardsGeneration(driver,200);


//        General.pagesArchiving(driver,50);
//        General.pagesCreation(driver,10, PageSharingMode.PUBLIC);
//        General.pagesRemoving(driver,50);


//        General.widgetsCreation(driver, 2, WidgetState.EXPANDED, WidgetColor.YELLOW);
//        General.firstBoardCardsGeneration(driver, 10, 2);
//        General.firstIdeaCardsGeneration(driver, 10);

//        General.widgetsCreation(driver,2,WidgetState.EXPANDED,WidgetColor.ORANGE);
//        General.firstBoardCardsGeneration(driver,40,8);
//        General.firstIdeaCardsGeneration(driver,40);

    }

}
