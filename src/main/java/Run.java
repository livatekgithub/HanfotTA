import enums.*;
import org.openqa.selenium.WebDriver;

public class Run {
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException {

//        System.out.println(General.nowTime() + " : 1. Add Users to Organization(20)");
//        General.usersAddToOrganization(driver, 5, false);
//
//        System.out.println(General.nowTime() + " : 2. Remove Users from Organization(10)");
//        General.usersRemoveAllFromOrganization(driver, false);
//        General.usersAddToOrganization(driver, 10, false);
//
//        System.out.println(General.nowTime() + " : 3. Pages Creation(10)");
//        General.pagesCreation(driver, 8, PageSharingMode.PRIVATE, false);
//        General.pagesCreation(driver, 8, PageSharingMode.PUBLIC, false);
//
//        System.out.println(General.nowTime() + " : 4. Pages Archiving(10)");
//        General.pagesArchiving(driver, 5, false);
//
//        System.out.println(General.nowTime() + " : 5. Pages Removing(5)");
//        General.pagesRemoving(driver, 5, false);
//
//        System.out.println(General.nowTime() + " : 5a. Pages Renaming(1)");
//        General.pageRenameCurrent(driver, "MANY WIDGETS COLLECTION");
//
//        System.out.println(General.nowTime() + " : 6a. Widgets Collapsed Creation(5)/Removal(1)/Archive(2)/Unarchive(1). Fixed Color ");
//        General.widgetsCreation(driver, 6, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);
//        General.widgetsRemoval(driver, 1, false);
//        General.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
//        General.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);
//
//        System.out.println(General.nowTime() + " : 6b. Widgets Expanded Creation(10)/Removal(4)/Archive(2)/Unarchive(1). Random ");
//        General.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
//        General.widgetsRemoval(driver, 4, false);
//        General.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
//        General.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);
//
//        System.out.println(General.nowTime() + " : 6c. Widgets Renaming");
//        System.out.println(General.nowTime() + " : 6d. Widgets Archiving(2)");
//        System.out.println(General.nowTime() + " : 7. Columns Creation(3)");
//        General.widgetsCurrentRename(driver, "MANY СOLUMNS BOARD", WidgetType.BOARD, false);
//        General.columnsCreation(driver, 8, false);

        System.out.println(General.nowTime() + " : 8. Cards Creation(4*8) for Board and Idea");
        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        General.widgetsCurrentRename(driver, "MANY CARDS IDEA", WidgetType.IDEA, false);
        General.widgetsCurrentRename(driver, "MANY CARDS BOARD", WidgetType.BOARD, false);
        General.cardsFirstBoardGeneration(driver, 30, 3, false);
        General.cardsFirstIdeaGeneration(driver, 30, false);

        System.out.println(General.nowTime() + " : 9. Card Pop-Up Operations Block");
        System.out.println(General.nowTime() + " : a. Add many new tags");
        General.cardAddManyNewTagsToCard(driver, 10);

        System.out.println(General.nowTime() + " : b. Add many tasks");
        General.cardAddManyTasks(driver, 10, false);

        System.out.println(General.nowTime() + " : c. Delete Cards");
        General.cardDeleteMany(driver, 3, false);

        System.out.println(General.nowTime() + " : d. Archive Cards");
        General.cardArchiveMany(driver, 3, false);

        System.out.println(General.nowTime() + " : e. Add dates");
        General.cardDatesAddFew(driver, "13-10-2015", "20-10-2015", false);

//        System.out.println(General.nowTime() + " : d. Add Calendar Board");
//        System.out.println(General.nowTime() + " : d. Add dates");
//        General.cardAddManyTasks(driver, 10, false);



//        System.out.println(General.nowTime() + " : 10. TODO -Operations Block");
//        System.out.println(General.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : b. Clearing - TODO Remove all Finished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);
//
//        //Testing of Finished card creation and removal
//        System.out.println(General.nowTime() + " : c. TODO Create Finished Cards(10)");
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : c. TODO Remove all Finished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);
//
//        //Testing of Unfinished card creation and removal
//        System.out.println(General.nowTime() + " : d. TODO Create Unfinished Cards(10)");
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : a. TODO Remove all Unfinished Cards");
//        General.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);
//
//        //Testing of marking unfinished cards
//        System.out.println(General.nowTime() + " : d. TODO Create Unfinished Cards(10)");
//        General.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
//
//        System.out.println(General.nowTime() + " : e. TODO Cards Marking as Finished(10)");
//        General.todoCardCreation(driver, 5, TodoCardStatus.MARKFINISHED, LogType.NOLOG);
//
//        //Testing of creation of finished cards
//        System.out.println(General.nowTime() + " : f. TODO Create Finished Cards(20)");
//        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);
//
//        //Testing of creation of unfinished cards
//        System.out.println(General.nowTime() + " : g. TODO Create Unfinished Cards(20)");
//        General.todoCardCreation(driver, 10, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
    }

    public static void Run(WebDriver driver) throws InterruptedException {

//        runTestAllMethods(driver, false);

//        7*5
//        String d
//        for (int i=1;i<=30;i++){
//            x=i%8
//            y=(i%8)+1
//            Date Integer.toString(i)+"-10-2015";
//        }
//        public static void cardDatesAddCalendar(Webdriver driver,String month,boolean isLogged){
//        }

        General.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.DARKGREEN, false);
        General.cardsFirstBoardGeneration(driver,7,5,false);

        String dateName="";
        int xCardIndex;
        int yCardIndex;

        for (int i=1;i<=32;i++){
            xCardIndex=((i-1)%7)+1;
            yCardIndex=((i-1)/7)+1;
            General.cardOpenBoardCard(driver,xCardIndex,yCardIndex,false);
            General.cardSetName(driver, "Day " + Integer.toString(i));
            dateName="2015-10-"+Integer.toString(i);
            General.cardDatesSet(driver,dateName,null,false);
            General.cardSave(driver);
        }

//        General.cardAddComment(driver,"My First Auto-Comment");
//        General.cardAddDescription(driver,"My First Auto Description My First Auto Description My First Auto Description");


    }
}