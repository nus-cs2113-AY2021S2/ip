import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String line = "____________________________________________________________\n";
    public final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public final static String greet = line +
            "Hello! I'm Duke\n" +
            line + "What can I do for you?\n" + line;
    public final static String exit = line +
            "Bye. Hope to see you again!\n" +
            line;

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void printGreeting() {
        System.out.print(logo);
        System.out.print(greet);
    }
    public void printByeMessage(){
        System.out.println(exit);
    }

    public void printErrorMessage(){
        System.out.print(line);
        System.out.print("I don't quite understand." + "\n" + "Please enter the command again." + "\n");
        System.out.print(line);
    }
    public void printZeroTask(){
        System.out.print(line);
        System.out.print("You have zero task at the moment." + "\n");
        System.out.print(line);
    }
    public void printLoadErrorMessage(){
        System.out.println("no file found");
    }

    public void printTask(Task a, Integer size){
        System.out.print(line + "Got it. I've added this task:" + "\n" + a + "\n" + "Now you have " + size + " tasks in the list." + "\n" + line);
    }

    public static void printList(ArrayList<Task> tasks) {
//        System.out.println(line);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
        System.out.print(line);
    }
    public static void printFoundList(ArrayList<Task> foundList) {
        System.out.print(line);
        System.out.println("Here are the matching tasks in your list: ");
        printList(foundList);
    }




}
