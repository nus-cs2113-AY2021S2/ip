package Duke;

import java.util.Scanner;

public class Duke {
    private static String LINE = "____________________________________________________________";
    public static String inputString;
    public static int taskCount = 0;
    public static String[][] lists = new String[100][5];

    public static void main(String[] args) {
        greetings();
        processInput();
        goodbye();
    }

    public static void processInput(){
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println(LINE);
            if (inputString.equalsIgnoreCase("bye")) {
                return;
            } else if (inputStringSplit[0].equalsIgnoreCase("done")) {
                if (inputStringSplit.length > 1) {
                    MarkAsDone.markAsDone(inputStringSplit[1]);
                } else {
                    DukeException.doneWithoutNo();
                }
            } else if (inputStringSplit[0].equalsIgnoreCase("delete")) {
                if (inputStringSplit.length > 1) {
                    Delete.deleteTask(inputStringSplit[1]);
                } else {
                    DukeException.deleteWithoutNo();
                }
            } else if (inputStringSplit[0].equalsIgnoreCase("list")) {
                printList(0, taskCount);
            } else if (AddToList.keywordCheck(inputStringSplit[0])) {
                if (inputStringSplit.length > 1) {
                    AddToList.AddToList(inputStringSplit[0], inputStringSplit[1]);
                } else {
                    DukeException.taskDescriptionEmpty();
                }
            } else {
                DukeException.illegalInput();
            }
        }
    }

    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n" + LINE + "\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": [" + lists[i][0] + "][" + lists[i][1] + "]:" + " " + lists[i][2] +lists[i][3]);
            }
        }
    }

    public static void greetings(){
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( LOGO + LINE + "\n" + " Hello! I'm Duke\n" + " What can I do for you?\n" + LINE + "\n" );
    }

    public static void goodbye(){
        System.out.println(" Bye! Hope to see you again soon!\n" + LINE );
    }
}
