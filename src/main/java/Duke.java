import java.util.Scanner;

public class Duke {
    public static String inputString;
    public static int listindex = 0;
    public static String list[] = new String[100];
    public static String isDone[] = new String[100];

    public static void main(String[] args) {
        greetings();
        AddIntoList();
        goodbye();
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

    public static void AddIntoList(){
        do {
            Scanner userinput = new Scanner(System.in);
            inputString = userinput.nextLine(); //not sure if the content after whitespace will be included
            String inputStringSplit[] = inputString.split(" ");
            System.out.println("____________________________________________________________");
            if(inputStringSplit[0].equalsIgnoreCase("list")) {
                PrintList(0, listindex);
                continue;
            } else if (inputStringSplit[0].equalsIgnoreCase("done")){
                try{
                    int tempindex = Integer.parseInt(inputStringSplit[1]);
                    MarkAsDone(tempindex);
                    continue;
                } catch(Exception e) {
                    System.out.println("Which task is done?");
                    PrintList(0, listindex);
                    continue;
                }

            } else if(inputString.equalsIgnoreCase("bye")){
                break;
            }
            list[listindex] = inputString;
            isDone[listindex] = " ";
            listindex += 1;
            System.out.println(" Added:" + inputString);
            System.out.println("____________________________________________________________");
        }while(!inputString.equalsIgnoreCase("bye"));
    }

    public static void PrintList(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            System.out.println(i + 1 + "[" + isDone[i] + "]:" + list[i]);
        }
    }

    public static void MarkAsDone(int index){
        System.out.println("____________________________________________________________");
        System.out.println("Yay! This task is done!");
        isDone[index - 1] = "X";
        PrintList(index - 1, index);
    }
    public static void goodbye(){
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
