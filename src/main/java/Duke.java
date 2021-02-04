import java.util.Scanner;

public class Duke {
    public static String inputString;
    public static void main(String[] args) {
        greetings();
        echo();
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
        goodbye();
    }
    public static void goodbye(){
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
