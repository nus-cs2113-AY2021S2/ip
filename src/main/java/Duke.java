import java.util.Scanner;

public class Duke {
    public static String inputString;
    public static int listIndex = 0;
    public static String[][] lists = new String[100][5];

    public static void main(String[] args) {
        greetings();
        StartingMenu();
        goodbye();
    }

    public static void StartingMenu(){
        while(true) {
            Scanner userInput = new Scanner(System.in);
            inputString = userInput.nextLine();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println("____________________________________________________________");
            if (inputString.equalsIgnoreCase("bye")){
                return;
            } else if (inputStringSplit[0].equalsIgnoreCase("done")){
                if (inputStringSplit.length>1){
                    MarkAsDone(inputStringSplit[1]);
                } else {
                    DukeException.doneWithoutNo();
                }
            } else if (inputStringSplit[0].equalsIgnoreCase("list")){
                PrintList(0, listIndex);
            } else if (AddToList.keywordCheck(inputStringSplit[0])){
                if (inputStringSplit.length > 1){
                    AddToList.AddToList(inputStringSplit[0],inputStringSplit[1]);
                } else {
                    DukeException.taskDescriptionEmpty();
                }
            } else {
                DukeException.illegalInput();
            }
        }
    }
    public static void PrintList(int startIndex, int endIndex) {
        for(int i = startIndex; i < endIndex; i++) {
            System.out.println(" " + (i + 1) + ": [" + lists[i][0] + "][" + lists[i][1] + "]:" + " " + lists[i][2] +lists[i][3]);
        }
    }

    public static void MarkAsDone(String doneTask){
        int donetaskIndex = Integer.parseInt(doneTask) - 1;
        if (donetaskIndex < listIndex) {
            System.out.println(" Yay! This task is done!");
            lists[donetaskIndex][1] = "X";
            PrintList(donetaskIndex, donetaskIndex + 1);
        } else {
            DukeException.exceedListLength(donetaskIndex);
        }
    }
    public static void greetings(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( logo +
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
    public static void goodbye(){
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
