import java.util.Scanner;

public class Duke {
    public static String inputString;
    public static int listindex = 0;
    public static String list[][] = new String[100][5];
    //public static String isDone[] = new String[100];
    //public static String tasktype[] = new String[100];

    public static void main(String[] args) {
        greetings();
        StartingMenu();
        goodbye();
    }
    public static void StartingMenu(){
        do {
            Scanner userinput = new Scanner(System.in);
            inputString = userinput.nextLine();
            String inputStringSplit[] = inputString.split(" ",2);
            System.out.println("____________________________________________________________");
            if (inputString.equalsIgnoreCase("bye")){
                break;
            } else if(inputString.equalsIgnoreCase("list")) {
                PrintList(0, listindex);
                continue;
            } else if (inputStringSplit.length >1){
                if (inputStringSplit[0].equalsIgnoreCase("done")) {
                    MarkAsDone(inputStringSplit[1]);
                } else if (inputStringSplit[0].equalsIgnoreCase("deadline")
                        ||inputStringSplit[0].equalsIgnoreCase("todo")
                        ||inputStringSplit[0].equalsIgnoreCase("event")) {
                    AddToList(inputStringSplit[0], inputStringSplit[1]);
                }
            } else {
                ErrorMsg();
                continue;
            }
        }while(!inputString.equalsIgnoreCase("bye"));
    }

    public static void ErrorMsg(){
        System.out.println("What is the task type? Do you wanna set it as done?");
    }

    public static void AddToList(String tasktype, String task){
        //try {
            if (tasktype.equalsIgnoreCase("todo")) {
                String[] TaskTime = task.split(" ", 2);
                list[listindex][0] = "T";
                list[listindex][1] = " ";
                list[listindex][2] = task;
                list[listindex][3] = " ";
            } else if (tasktype.equalsIgnoreCase("deadline")) {
                list[listindex][0] = "D";
                list[listindex][1] = " ";
                String[] TaskTime = task.split("/", 2);
                list[listindex][2] = TaskTime[0];
                list[listindex][3] = "(by:" + TaskTime[1] + ")";
            } else if (tasktype.equalsIgnoreCase("event")) {
                list[listindex][0] = "E";
                list[listindex][1] = " ";
                String[] TaskTime = task.split("/", 2);
                list[listindex][2] = TaskTime[0];
                list[listindex][3] = "(at:" + TaskTime[1] + ")";
            }
        //}catch(Exception e){
        //        ErrorMsg();
        //    }
            System.out.println(" Added:");
            PrintList(listindex, listindex + 1);
            listindex += 1;
    }
    //public static void AddToList(String tasktype, String task){
    //    list[listindex][0] = tasktype;
    //    list[listindex][1] = " ";
    //    list[listindex][2] = task;
    //    System.out.println(" Added:");
    //    PrintList(listindex, listindex + 1);
    //    listindex += 1;
    //}
//
    public static void PrintList(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            System.out.println(i + 1 + ": [" + list[i][0] + "][" + list[i][1] + "]:" + " " + list[i][2] +list[i][3]);
        }
    }

    public static void MarkAsDone(String donetask){
        try{
            int donetaskindex = Integer.parseInt(donetask) - 1;
            System.out.println("____________________________________________________________");
            System.out.println("Yay! This task is done!");
            list[donetaskindex][1] = "X";
            PrintList(donetaskindex, donetaskindex + 1);
            return;
        } catch(Exception e) {
            System.out.println("Which task is done?");
            PrintList(0, listindex);
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
            Scanner userinput = new Scanner(System.in);
            inputString = userinput.useDelimiter("\\A").nextLine();

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
