public class DukeUI {

    public static void printWelcomeMessage(){
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }

    public static void printExitMessage(){
        System.out.printf("Bye. Hope to see you again soon!\n");
    }

    public static void printMenu() {
        System.out.printf("\n\n--------User Menu--------\n" +
                "list: list current tasks and completion status\n" +
                "done x: Mark task x as completed\n" +
                "todo TASK_DESCRIPTION\n" +
                "deadline TASK_DESCRIPTION /KEYWORD DATE_TIME\n" +
                "event TASK_DESCRIPTION /KEYWORD DATE_TIME\n" +
                "bye: Exit\n" +
                "(KEYWORD: any single word such as before, by, after...)\n\n");
    }
}
