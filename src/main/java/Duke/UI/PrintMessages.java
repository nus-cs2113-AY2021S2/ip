package Duke.UI;

import Duke.Commands.PrintListCommand;

public class PrintMessages extends TEXT {
    /**
     * Shows messages
     */
    public static void taskDescriptionEmpty(){
        System.out.println(TASK_EMPTY);
    }

    public static void taskWithoutTime() {
        System.out.println(TASK_WITHOUT_TIME);
    }

    public static void illegalInput() {
        System.out.println(ILLEGAL_INPUT);
    }

    public static void noIndexInput() {
        System.out.println(NO_INDEX);
    }

    public static void exceedListLength(int taskIndex) {
        System.out.println(YOU_DO_NOT_HAVE + taskIndex + IN_YOUR_LIST);
    }

    public static void taskAlreadyDone() {
        System.out.println(TASK_ALREADY_DONE);
    }

    public static void cannotFind(String input) {
        System.out.println( DUKE_CANNOT_FIND + input);
    }

    public static void greetings(){
        System.out.println( LOGO + LINE + GREETING + LINE );
    }

    public static void goodbye(){
        System.out.println( ENDING + LINE );
    }

    public static void helpMenu(){
        System.out.println(HELP_MENU);
    }

    public static void taskAddedText() {
        System.out.println(TASK_ADDED);
        PrintListCommand.printList(taskCount, taskCount + 1);
    }

    public static void taskDeleted() {
        System.out.println(TASK_DELETED);
    }

    public static void taskDone() {
        System.out.println(TASK_DONE);
    }

    public static void keywordEmpty() { System.out.println(KEYWORD_EMPTY);}

    public static void dateEmpty() {System.out.println(DATE_EMPTY);}
}
