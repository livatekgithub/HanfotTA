package tests;

import enums.*;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import utils.AccessData;
import utils.Service;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;

public class Run {
    public static String currentBrowser;

    //----------------------------------------------------------------------------------------------------------------------
    public static void runShortTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException, IOException, AWTException {

        System.out.println("*** ShortTest - USS Endeavor3 ***");

        System.out.println(Service.nowTime() + " : 1. Users Block");
        System.out.println(Service.nowTime() + " : 1a. Remove All users and Add Four Users to Organization(R+4)");
        Users.usersRemoveAllFromOrganization(driver, false);

        Users.usersAddToOrganization(driver, 5, TestData.testUserNames, false);
        System.out.println(Service.nowTime() + " : 1b. Delete chosen User");
        Users.usersOpenMembersList(driver);
        Users.usersParticularUserRemoval(driver, "livatek.user6@ukr.net");

        System.out.println(Service.nowTime() + " : 1c. Change Users Permissions to Administrator/Full Member and Restricted");
        Users.usersSetupPermission(driver, "livatek.user9@gmail.com", UserOrgRoles.ADMINISTRATOR);
        Users.usersSetupPermission(driver, "livatek.user8@ukr.net", UserOrgRoles.FULL_MEMBER);
        Users.usersSetupPermission(driver, "livatek.user7@gmail.com", UserOrgRoles.RESTRICTED);
        Users.closeEditOrganizationMenu(driver);

        System.out.println(Service.nowTime() + " : 2. Collections Creation(2)");
        Collection.collectionCreation(driver, 1, CollectionSharingMode.PRIVATE, false);

        System.out.println(Service.nowTime() + " : 3. Collections Archiving(2)");
        Collection.collectionArchiving(driver, 2, false);

        System.out.println(Service.nowTime() + " : 4. Collections Removing(2)");
        Collection.collectionCreation(driver, 1, CollectionSharingMode.PUBLIC, false);
        Collection.collectionCreation(driver, 1, CollectionSharingMode.PRIVATE, false);
        Collection.collectionRemoving(driver, 2, false);

        System.out.println(Service.nowTime() + " : 5. Collections Renaming(1)");
        System.out.println(Service.nowTime() + " : 6a. Widgets Collapsed Creation(5)/Removal(1)/Archive(2)/Unarchive(1). Fixed Color ");
        Collection.collectionCreation(driver, 1, CollectionSharingMode.PUBLIC, false);
        Collection.collectionRenameCurrent(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY WIDGETS COLLECTION");
        Widget.widgetsCreation(driver, 4, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);
        Widget.widgetsRemoval(driver, 1, false);
        Widget.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        Widget.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(Service.nowTime() + " : 6b. Widgets Expanded Creation(10)/Removal(4)/Archive(2)/Unarchive(1). Random ");
        Widget.widgetsCreation(driver, 4, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
        Widget.widgetsRemoval(driver, 1, false);
        Widget.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        Widget.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(Service.nowTime() + " : 6c. Widgets Renaming");
        System.out.println(Service.nowTime() + " : 6d. Widgets Archiving(2)");
        System.out.println(Service.nowTime() + " : 7. Columns Creation(3)");
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY СOLUMNS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, 2, 6, false);
        Card.cardsFirstIdeaGeneration(driver, 2, false);
//        Column.columnsCreation(driver, 8, false);
        Column.columnRemove(driver, 2, false);
        Column.columnRename(driver, 2, currentBrowser + Service.nowTimeForObjectName()+".Renamed!", false);
        Column.columnsArchiveAll(driver, 3, false);
        Column.columnSubscribe(driver, 4, false);

        System.out.println(Service.nowTime() + " : 8b. Cards Creation(3*5) for Board and Idea");
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS IDEA", WidgetType.IDEA, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, 6, 3, false);
        Card.cardsFirstIdeaGeneration(driver, 10, false);

        System.out.println(Service.nowTime() + " : 9. Card Pop-Up Operations Block");

        System.out.println(Service.nowTime() + " : a. Delete Cards");
        Card.cardDeleteMany(driver, 1, 2, false);

        System.out.println(Service.nowTime() + " : b. Archive Cards");
        Card.cardArchiveMany(driver, 1, 2, false);
        Thread.sleep(3000);

        System.out.println(Service.nowTime() + " : c. Add many new tags");
        Card.cardAddManyNewTagsToCard(driver, 2, 1, 1, 1);

        System.out.println(Service.nowTime() + " : d. Add many tasks");
        Card.cardAddManyTasks(driver, 2, 2, 2, 1, false);

        System.out.println(Service.nowTime() + " : e. Add many users");
        String[] users = {"pent", "13","9","ei","sev"};
        Card.cardAddFewPeople(driver, users, 3, 3, 1);

        System.out.println(Service.nowTime() + " : f. Add dates");
        Card.cardDatesAddFew(driver, 4, 1, "13-10-2015", "20-10-2015", false);

        System.out.println(Service.nowTime() + " : g. Add description");
        Card.cardDescriptionAddtoManyCards(driver, TestData.USUAL_DESCRIPTION_TEXT, 4, 4, 1, false);

        System.out.println(Service.nowTime() + " : h. Add comments");
        Card.cardCommentAddMany(driver, "Hello There!!!!", 2, 5, 5, 1, false);

//        System.out.println(Service.nowTime() + " : i. Add files (PNG,TXT)");
//        String filePath = AccessData.GRAPHIC_FILE_PATH;
//        Card.cardFileAdd(driver, filePath, 1, 1, 1, false);
//        filePath = AccessData.TEXT_FILE_PATH;
//        Card.cardFileAdd(driver, filePath, 1, 1, 1, false);

        System.out.println(Service.nowTime() + " : 10. TODO -Operations Block");
        System.out.println(Service.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : b. Clearing - TODO Remove all Finished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Finished card creation and removal
        System.out.println(Service.nowTime() + " : c. TODO Create Finished Cards(3)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : c. TODO Remove all Finished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Unfinished card creation and removal
        System.out.println(Service.nowTime() + " : d. TODO Create Unfinished Cards(10)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : a. TODO Remove all Unfinished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        //Testing of marking unfinished cards
        System.out.println(Service.nowTime() + " : d. TODO Create Unfinished Cards(3)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : e. TODO Cards Marking as Finished(10)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.MARKFINISHED, LogType.NOLOG);

        //Testing of creation of finished cards
        System.out.println(Service.nowTime() + " : f. TODO Create Finished Cards(3)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        //Testing of creation of unfinished cards
        System.out.println(Service.nowTime() + " : g. TODO Create Unfinished Cards(3)");
        Todo.todoCardCreation(driver, 3, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
    }

    //----------------------------------------------------------------------------------------------------------------------
    public static void runTestAllMethods(WebDriver driver, boolean logMode) throws InterruptedException, IOException, AWTException {

        System.out.println("*** Test. UK Air Force***");
        System.out.println(Service.nowTime() + " : 1. Add Users to Organization(20)");
        Users.usersAddToOrganization(driver, 5, TestData.testUserNames, false);

        System.out.println(Service.nowTime() + " : 2. Remove Users from Organization(10)");
        Users.usersRemoveAllFromOrganization(driver, false);
        Users.usersAddToOrganization(driver, 10, TestData.testUserNames, false);

        System.out.println(Service.nowTime() + " : 3. Collections Creation(10)");
        Collection.collectionCreation(driver, 5, CollectionSharingMode.PRIVATE, false);
        Collection.collectionCreation(driver, 15, CollectionSharingMode.PUBLIC, false);

        System.out.println(Service.nowTime() + " : 4. Collections Archiving(10)");

        Collection.collectionArchiving(driver, 5, false);

        System.out.println(Service.nowTime() + " : 5. Collections Removing(5)");
        Collection.collectionRemoving(driver, 5, false);

        System.out.println(Service.nowTime() + " : 5a. Collections Renaming(1)");
        Collection.collectionRenameCurrent(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY WIDGETS COLLECTION");

        System.out.println(Service.nowTime() + " : 6a. Widgets Collapsed Creation(5)/Removal(1)/Archive(2)/Unarchive(1). Fixed Color ");
        Widget.widgetsCreation(driver, 6, WidgetState.COLLAPSED, WidgetColor.EMERALD, false);
        Widget.widgetsRemoval(driver, 1, false);
        Widget.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        Widget.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(Service.nowTime() + " : 6b. Widgets Expanded Creation(10)/Removal(4)/Archive(2)/Unarchive(1). Random ");
        Widget.widgetsCreation(driver, 10, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
        Widget.widgetsRemoval(driver, 4, false);
        Widget.widgetsArchive(driver, 2, WidgetOperation.ARCHIVE, false);
        Widget.widgetsArchive(driver, 1, WidgetOperation.UNARCHIVE, false);

        System.out.println(Service.nowTime() + " : 6c. Widgets Renaming");
        System.out.println(Service.nowTime() + " : 6d. Widgets Archiving(2)");
        System.out.println(Service.nowTime() + " : 7. Columns Creation(3)");
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RANDOM, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY СOLUMNS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, 3, 8, false);
        Card.cardsFirstIdeaGeneration(driver, 3, false);
//        Column.columnsCreation(driver, 8, false);
        Column.columnRemove(driver, 2, false);

        Column.columnRename(driver, 2, currentBrowser + Service.nowTimeForObjectName()+".Renamed!", false);
        Column.columnsArchiveAll(driver, 3, false);
        Column.columnSubscribe(driver, 4, false);

        System.out.println(Service.nowTime() + " : 8a. Add calendar board");
        Card.cardDatesAddCalendar(driver, "10", false);
//
        System.out.println(Service.nowTime() + " : 8b. Cards Creation(4*8) for Board and Idea");
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS IDEA", WidgetType.IDEA, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, 15, 3, false);
        Card.cardsFirstIdeaGeneration(driver, 15, false);

        System.out.println(Service.nowTime() + " : 9. Card Pop-Up Operations Block");

        System.out.println(Service.nowTime() + " : a. Delete Cards");
        Card.cardDeleteMany(driver, 2, 2, false);

        System.out.println(Service.nowTime() + " : b. Archive Cards");
        Card.cardArchiveMany(driver, 2, 2, false);
        Thread.sleep(3000);

        System.out.println(Service.nowTime() + " : c. Add many new tags");
        Card.cardAddManyNewTagsToCard(driver, 10, 1, 1, 1);

        System.out.println(Service.nowTime() + " : d. Add many tasks");
        Card.cardAddManyTasks(driver, 10, 2, 2, 1, false);

        System.out.println(Service.nowTime() + " : e. Add many users");
        String[] users = {"war", "Nine", "ei", "nex", "onc", "pent"};
        Card.cardAddFewPeople(driver, users, 3, 3, 1);
        String[] users1 = {"13", "war", "Nine"};
        Card.cardAddFewPeople(driver, users1, 7, 7, 1);

        System.out.println(Service.nowTime() + " : f. Add dates");
        Card.cardDatesAddFew(driver, 1, 3, "13-10-2015", "20-10-2015", false);

        System.out.println(Service.nowTime() + " : g. Add description");
        Card.cardDescriptionAddtoManyCards(driver, TestData.USUAL_DESCRIPTION_TEXT, 3, 1, 3, false);
//        Card.cardDescriptionAddtoManyCards(driver, TestData.DESCRIPTION_TEXT, 1, 1, 3, false);

        System.out.println(Service.nowTime() + " : h. Add comments");
        Card.cardCommentAddMany(driver, "Hello There!!!!", 10, 4, 4, 1, false);

        System.out.println(Service.nowTime() + " : i. Add files (PNG,TXT)");
        String filePath = AccessData.GRAPHIC_FILE_PATH;
        Card.cardFileAdd(driver, filePath, 5, 3, 2, false);
        filePath = AccessData.TEXT_FILE_PATH;
        Card.cardFileAdd(driver, filePath, 5, 3, 2, false);

        System.out.println(Service.nowTime() + " : 10. TODO -Operations Block");
        System.out.println(Service.nowTime() + " : a. Clearing - TODO Remove all Unfinished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : b. Clearing - TODO Remove all Finished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Finished card creation and removal
        System.out.println(Service.nowTime() + " : c. TODO Create Finished Cards(10)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : c. TODO Remove all Finished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLFINISHED, LogType.NOLOG);

        //Testing of Unfinished card creation and removal
        System.out.println(Service.nowTime() + " : d. TODO Create Unfinished Cards(10)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : a. TODO Remove all Unfinished Cards");
        Todo.todoCardRemoval(driver, TodoCardStatus.REMOVEALLUNFINISHED, LogType.NOLOG);

        //Testing of marking unfinished cards
        System.out.println(Service.nowTime() + " : d. TODO Create Unfinished Cards(10)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);

        System.out.println(Service.nowTime() + " : e. TODO Cards Marking as Finished(10)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.MARKFINISHED, LogType.NOLOG);

        //Testing of creation of finished cards
        System.out.println(Service.nowTime() + " : f. TODO Create Finished Cards(20)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.CREATEFINISHED, LogType.NOLOG);

        //Testing of creation of unfinished cards
        System.out.println(Service.nowTime() + " : g. TODO Create Unfinished Cards(20)");
        Todo.todoCardCreation(driver, 5, TodoCardStatus.CREATEUNFINISHED, LogType.NOLOG);
    }

    //----------------------------------------------------------------------------------------------------------------------
    public static void widgetsCreateBigWidgets(WebDriver driver, int yIdea, int yBoard, int xBoard) throws InterruptedException {
        Widget.widgetsCreation(driver, 1, WidgetState.EXPANDED, WidgetColor.RED, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS IDEA", WidgetType.IDEA, false);
        Widget.widgetsCurrentRename(driver, currentBrowser + Service.nowTimeForObjectName()+".MANY CARDS BOARD", WidgetType.BOARD, false);
        Card.cardsFirstBoardGeneration(driver, yBoard, xBoard, false);
        Card.cardsFirstIdeaGeneration(driver, yIdea, false);
        for (int j = 1; j <= yBoard; j++) {
            for (int i = 1; i <= xBoard; i++) {
                Card.cardAddManyNewTagsToCard(driver, 3, j, j, i);
                String[] users = {"war", "Nine", "ei", "onc"};
                Card.cardAddFewPeople(driver, users, j, j, i);
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    public static void createManyOrganizationsWithManyUsers(WebDriver driver, int numberOfOrgs, int numberofUsers,
                                                            String[] usersList, String[] adminUsersList, String maskName, boolean isLogged) throws InterruptedException {
        for (int i = 1; i <= numberOfOrgs; i++) {
            Users.createNewOrganization(driver, maskName + Integer.toString(i));
            System.out.println(usersList.length);
            System.out.println(Arrays.toString(usersList));
            driver.get(AccessData.TESTURLX);
            Thread.sleep(5000);
            Users.usersAddToOrganization(driver, numberofUsers, usersList, isLogged);
            for (String userChosen:adminUsersList)
            Users.usersSetupPermission(driver,userChosen,UserOrgRoles.ADMINISTRATOR);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    public static void Run(WebDriver driver, String browser) throws InterruptedException, IOException, AWTException {

//        Collection.collectionCreation(driver,1,CollectionSharingMode.PUBLIC,false);
//        Widget.widgetsCreation(driver,2,WidgetState.EXPANDED,WidgetColor.DARKGREEN,false);
//        Collection.collectionCreation(driver,1,CollectionSharingMode.PRIVATE,false);
//        Widget.widgetsCreation(driver,2,WidgetState.EXPANDED,WidgetColor.DARKGREEN,false);

//        old logs version
//        System.setOut(new PrintStream(new FileOutputStream(Service.nowTimeForFileName()+"_"+browser+".txt")));

        currentBrowser = browser;
        System.out.println("****** " + browser + " ************************************************************");
        Service service = new Service();
        service.startCount();

//        createManyOrganizationsWithManyUsers(driver, 1, 10, TestData.testUserNamesBunch, TestData.adminUsersList, "000 000 ManyUsers ORG 0", true);
//        createManyOrganizationsWithManyUsers(driver, 1, 40, TestData.testUserNamesForPayments, TestData.adminUsersList, "zzz PaymentTesting ORG 0", true);

        //**********************************************************************************

        runShortTestAllMethods(driver, false);
//        runTestAllMethods(driver, false);

//        Card.cardsFirstIdeaGeneration(driver, 2, false);
//        System.out.println(Service.nowTime() + " : i. Add files (PNG,TXT)");
//        String filePath = AccessData.GRAPHIC_FILE_PATH;
//        Card.cardFileAdd(driver, filePath, 1, 2, 1, false);
//        filePath = AccessData.TEXT_FILE_PATH;
//        Card.cardFileAdd(driver, filePath, 1, 2, 1, false);


        //**********************************************************************************
        service.stopCount();
        System.out.println("****** Time: " + service.getTimeDurationInMinutes() + " minutes || "+service.getTimeDurationInSeconds()+
                " seconds ************************************************************");

//        Service.takeScreenshot(driver, "screenshot1.png");

//        widgetsCreateBigWidgets(driver, 80, 80, 4);
    }
}