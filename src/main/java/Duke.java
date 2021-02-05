import java.util.Scanner;

public class Duke {
    public static String inputString;
    public static int listIndex = 0;
    public static String lists[][] = new String[100][5];

    public static void main(String[] args) {
        greetings();
        StartingMenu();
        goodbye();
    }
    public static void StartingMenu(){
        while(true) {
            Scanner userInput = new Scanner(System.in);
            inputString = userInput.nextLine();
            String inputStringSplit[] = inputString.split(" ", 2);
            System.out.println("____________________________________________________________");
            if (inputString.equalsIgnoreCase("bye")){
                break;
            } else if (inputStringSplit[0].equalsIgnoreCase("done")){
                MarkAsDone(inputStringSplit[1]);
            } else if (inputStringSplit[0].equalsIgnoreCase("list")){
                PrintList(0, listIndex);
            } else if (inputStringSplit.length > 1){
                AddToList(inputStringSplit[0],inputStringSplit[1]);
            } else {
                errorMsg();
            }
        }
    }

    public static void errorMsg(){
        System.out.println(" Oh this input is not recognised, try again^_^");
    }

    public static void AddToList(String tasktype, String task){
        if (tasktype.equalsIgnoreCase("todo")) {
            lists[listIndex][0] = "T";
            lists[listIndex][1] = " ";
            lists[listIndex][2] = task;
            lists[listIndex][3] = " ";
            System.out.println(" Task added! ^_^ [");
            PrintList(listIndex, listIndex + 1);
            listIndex += 1;
        } else if (tasktype.equalsIgnoreCase("deadline")) {
            if(task.contains("/")){
                String[] TaskAndTime = task.split("/", 2);
                lists[listIndex][0] = "T";
                lists[listIndex][1] = " ";
                lists[listIndex][2] = TaskAndTime[0];
                lists[listIndex][3] = "(at:" + TaskAndTime[1] + ")";
                System.out.println(" Task added! ^_^ [");
                PrintList(listIndex, listIndex + 1);
                listIndex += 1;
            } else{
                errorMsg();
            }
        } else if (tasktype.equalsIgnoreCase("event")) {
            if(task.contains("/")){
                String[] TaskAndTime = task.split("/", 2);
                lists[listIndex][0] = "E";
                lists[listIndex][1] = " ";
                lists[listIndex][2] = TaskAndTime[0];
                lists[listIndex][3] = "(at:" + TaskAndTime[1] + ")";
                System.out.println(" Task added! ^_^ [");
                PrintList(listIndex, listIndex + 1);
                listIndex += 1;
            } else{
                errorMsg();
            }
        } else {
            errorMsg();
        }
    }
    public static void PrintList(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            System.out.println(" " + (i+1) + ": [" + lists[i][0] + "][" + lists[i][1] + "]:" + " " + lists[i][2] +lists[i][3]);
        }
    }

    public static void MarkAsDone(String donetask){
        int donetaskIndex = Integer.parseInt(donetask) - 1;
        if (donetaskIndex<listIndex) {
            System.out.println("Yay! This task is done!");
            lists[donetaskIndex][1] = "X";
            PrintList(donetaskIndex, donetaskIndex + 1);
        } else {
            errorMsg();
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
    public static void echo(){
        System.out.println("Say something and i will repeat it ^_^");
        do {
            Scanner userInput = new Scanner(System.in);
            inputString = userInput.useDelimiter("\\A").nextLine();

            System.out.println("____________________________________________________________");
            if(inputString.equalsIgnoreCase("bye")){
                break;
            }
            System.out.println(inputString);
            System.out.println("____________________________________________________________");
        }while(!inputString.equalsIgnoreCase("bye"));
    }
    public static void goodbye(){
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
