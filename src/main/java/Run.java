import enums.*;
import org.openqa.selenium.WebDriver;

public class Run {
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException {

        System.out.println(General.nowTime() + " : 1. Add Users to Organization(20)");
        General.usersAddToOrganization(driver, 5, false);

        System.out.println(General.nowTime() + " : 2. Remove Users from Organization(10)");
        General.usersRemoveAllFromOrganization(driver, false);
        General.usersAddToOrganization(driver, 10, false);

        System.out.println(General.nowTime() + " : 3. Pages Creation(10)");
        General.pagesCreation(driver, 8, PageSharingMode.PRIVATE, false);
        General.pagesCreation(driver, 8, PageSharingMode.PUBLIC, false);

        System.out.println(General.nowTime() + " : 4. Pages Archiving(10)");
        General.pagesArchiving(driver, 5, false);

        System.out.println(General.nowTime() + " : 5. Pages Removing(5)");
        General.pagesRemoving(driver, 5, false);

        System.out.println(General.nowTime() + " : 5a. Pages Renaming(1)");
        General.pageRenameCurrent(driver, "MANY WIDGETS COLLECTION");

        System.out.println(General.nowTime() + " : 6a. Widgets Collapsed Creation(5)/Removal(1)/Archive(2)/Unarchive(1). Fixed Color ");
        General.widgetsCreation(driver, 6, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);
        General.widgetsRemoval(driver, 1, false);
        General.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        General.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(General.nowTime() + " : 6b. Widgets Expanded Creation(10)/Removal(4)/Archive(2)/Unarchive(1). Random ");
        General.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
        General.widgetsRemoval(driver, 4, false);
        General.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        General.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(General.nowTime() + " : 6c. Widgets Renaming");
        System.out.println(General.nowTime() + " : 6d. Widgets Archiving(2)");
        System.out.println(General.nowTime() + " : 7. Columns Creation(3)");
        General.widgetsCurrentRename(driver, "MANY СOLUMNS BOARD", WidgetType.BOARD, false);
        General.columnsCreation(driver, 8, false);

        System.out.println(General.nowTime() + " : 8. Cards Creation(4*8) for Board and Idea");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.widgetsCurrentRename(driver, "MANY CARDS IDEA", WidgetType.IDEA, false);
        General.widgetsCurrentRename(driver, "MANY CARDS BOARD", WidgetType.BOARD, false);
        General.cardsFirstBoardGeneration(driver, 30, 3, false);
        General.cardsFirstIdeaGeneration(driver, 30, false);

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

        System.out.println(General.nowTime() + " : 8. Cards Creation(4*8) for Board and Idea");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.widgetsCurrentRename(driver, "MANY CARDS IDEA", WidgetType.IDEA, false);
        General.widgetsCurrentRename(driver, "MANY CARDS BOARD", WidgetType.BOARD, false);
        General.cardsFirstBoardGeneration(driver, 30, 3, false);
        General.cardsFirstIdeaGeneration(driver, 30, false);

//        System.out.println(General.nowTime() + " : 9. TODO -Operations Block");
//        System.out.println(General.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : b. Clearing - TODO Remove all Finished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);
//
//        //Testing of Unfinished card creation and removal
//        System.out.println(General.nowTime() + " : d. TODO Create Unfinished Cards(10)");
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : f. TODO Create Finished Cards(20)");
//        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

//        runTestAllMethods(driver, false);

    }
}