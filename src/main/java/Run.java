import enums.*;
import org.openqa.selenium.WebDriver;

public class Run {
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException {

        System.out.println(General.nowTime() + " : 1. Add Users to Organization(20)");
        General.addUserToOrganization(driver, 5, true);

        System.out.println(General.nowTime() + " : 2. Remove Users from Organization(10)");
        General.removeAllUsersFromOrganization(driver, true);
        General.addUserToOrganization(driver, 10, true);

        System.out.println(General.nowTime() + " : 3. Pages Creation(10)");
        General.pagesCreation(driver, 8, PageSharingMode.PRIVATE, false);
        General.pagesCreation(driver, 8, PageSharingMode.PUBLIC, false);

        System.out.println(General.nowTime() + " : 4. Pages Archiving(10)");
        General.pagesArchiving(driver, 5, true);

        System.out.println(General.nowTime() + " : 5. Pages Removing(5)");
        General.pagesRemoving(driver, 5, false);

        System.out.println(General.nowTime() + " : 6a. Widgets Creation(2). COLLAPSED. Fixed Color ");
        General.renameCurrentPage(driver,"MANY WIDGETS COLLECTION");
        General.widgetsCreation(driver, 5, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);

        System.out.println(General.nowTime() + " : 6b. Widgets Creation(2). EXPANDED. Random ");
        General.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);

        System.out.println(General.nowTime() + " : 7. Columns Creation(3)");
        General.currentWidgetsRename(driver,"MANY СOLUMNS BOARD",WidgetType.BOARD);
        General.columnsCreation(driver, 8, false);

        System.out.println(General.nowTime() + " : 8. Cards Creation(4*8)");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.currentWidgetsRename(driver,"MANY CARDS IDEA",WidgetType.IDEA);
        General.currentWidgetsRename(driver,"MANY CARDS BOARD",WidgetType.BOARD);
        General.firstBoardCardsGeneration(driver, 30, 3, false);
        General.firstIdeaCardsGeneration(driver, 30, false);

        //Testing of Removal
        System.out.println(General.nowTime() + " : 9. TODO -Operations Block");
        System.out.println(General.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : b. Clearing - TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Finished card creation and removal
        System.out.println(General.nowTime() + " : c. TODO Create Finished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : c. TODO Remove all Finished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Unfinished card creation and removal
        System.out.println(General.nowTime() + " : d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : a. TODO Remove all Unfinished Cards");
        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        //Testing of marking unfinished cards
        System.out.println(General.nowTime() + " : d. TODO Create Unfinished Cards(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(General.nowTime() + " : e. TODO Cards Marking as Finished(10)");
        General.todoCardCreation(driver, 5, TodoCardStatus.MARKFINISHED, LogType.NOLOG);

        //Testing of creation of finished cards
        System.out.println(General.nowTime() + " : f. TODO Create Finished Cards(20)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        //Testing of creation of unfinished cards
        System.out.println(General.nowTime() + " : g. TODO Create Unfinished Cards(20)");
        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
    }

    public static void Run(WebDriver driver) throws InterruptedException {

        runTestAllMethods(driver, false);

    }

}
